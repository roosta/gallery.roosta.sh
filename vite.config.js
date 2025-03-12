import handlebars from "vite-plugin-handlebars";
import tailwindcss from '@tailwindcss/vite';
import { resolve } from 'path';
import assetsJson from "./assets.json";
import uniq from "lodash/uniq";
import shuffle from "lodash/shuffle";
import sortBy from "lodash/sortBy";
import flatten from "lodash/flatten";
import ColorThief from "colorthief";
import { imageSizeFromFile } from 'image-size/fromFile'
import tinycolor from "tinycolor2";

const AssetProcessor = {
  /**
   * Determines the aspect ratio category of an asset
   * @param {Object} asset - Asset with width and height properties
   * @returns {string} - Aspect ratio category: square, landscape, wide, or portrait
   */
  calcAspect(asset) {
    const ratio = asset.width / asset.height;

    if (ratio >= 0.8 && ratio <= 1.2) {
      return "square";
    } else if (ratio > 1.2 && ratio < 2) {
      return "landscape";
    } else if (ratio >= 2) {
      return "wide";
    } else {
      return "portrait";
    }
  },

  /**
   * Adds size information to an asset
   * @param {Object} item - Asset item
   * @returns {Promise<Object>} - Asset with width and height properties
   */
  async withSize(item) {
    try {
      const size = await imageSizeFromFile(item.file);
      return {
        ...item,
        ...size
      };
    } catch (error) {
      console.error(`Error getting size for ${item.file}:`, error);
      // Return original item with default dimensions if there's an error
      return {
        ...item,
        width: 0,
        height: 0
      };
    }
  },

  /**
   * Adds aspect ratio category as a boolean property
   * @param {Object} item - Asset with size information
   * @returns {Object} - Asset with aspect ratio boolean property
   */
  withAspect(item) {
    return {
      ...item,
      [this.calcAspect(item)]: true
    };
  },

  /**
   * Extracts color palette from an image
   * @param {Object} item - Asset item
   * @returns {Promise<Object>} - Asset with palette property
   */
  async withPalette(item) {
    // Number of color samples (default: 5)
    const n = item.colors || 5;

    try {
      const palette = await ColorThief.getPalette(item.file, n);

      if (!palette) {
        return { ...item, palette: null };
      }

      const colors = palette.map(([r, g, b]) => tinycolor({r, g, b}));
      const sorted = sortBy(colors, color => color.getBrightness());

      return {
        ...item,
        palette: sorted.map(color => color.toHexString())
      };
    } catch (error) {
      console.error(`Error extracting palette for ${item.file}:`, error);
      return { ...item, palette: null };
    }
  }
};

/**
 * Process all assets with enhanced error handling
 * @param {Array} assetsJson - Raw assets from JSON
 * @returns {Promise<Object>} - Processed assets and categories
 */
async function processAssets(assetsJson) {
  try {
    console.log('Processing assets...');
    const filtered = assetsJson.filter(x => !x.ignored);
    const categories = uniq(flatten(filtered.map(x => x.categories))).sort();

    // Process assets in sequence for better error handling
    let processedItems = [];
    for (const item of filtered) {
      try {
        // Process each item with palette and size
        const withPalette = await AssetProcessor.withPalette(item);
        const withSize = await AssetProcessor.withSize(withPalette);
        const withAspect = AssetProcessor.withAspect.call(AssetProcessor, withSize);
        processedItems.push(withAspect);
      } catch (error) {
        console.error(`Error processing asset ${item.file}:`, error);
        // Skip this item but continue processing others
      }
    }

    // Shuffle the processed items
    const assets = shuffle(processedItems);

    return { assets, categories };
  } catch (err) {
    console.error('Error processing assets:', err);
    // Return empty arrays as fallback
    return { assets: [], categories: [] };
  }
}

// Process assets
const context = await processAssets(assetsJson);

export default {
  build: {
    rollupOptions: {
      input: {
        main: resolve(__dirname, 'index.html'),
        about: resolve(__dirname, 'about.html')
      }
    }
  },
  plugins: [
    handlebars({
      partialDirectory: resolve(__dirname, 'partials'),
      context,
      helpers: {
        // very simple helper that lets you bind strings to named variables
        // that can be refered to within the scope of the let block.
        let: (options) => {
          return options.fn(options.hash);
        }
      }
    }),
    tailwindcss()
  ]
};
