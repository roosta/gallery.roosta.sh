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

// This requires size fields to be set, make sure its called after `withSize`
function calcAspect(asset) {
  const ratio = asset.width / asset.height;
  if (ratio >= 0.8 && ratio <= 1.2) {
    return "square"
  } else if (ratio > 1.2 && ratio < 2) {
    return "landscape"
  } else if (ratio >= 2) {
    return "wide"
  } else {
    return "portrait"
  }
};

function withSize(item) {
  return imageSizeFromFile(item.file).then((size) => {
    return {
      ...item,
      ...size
    };

  })
}

function withAspect(item) {
  return {
    ...item,
    [calcAspect(item)]: true
  }
}


// Handle promise returned from ColorThief
function withPalette(item) {

  // Number of color samples
  let n = 5;

  // if colors is defined in json, use that as the sample count
  if (item.colors) n = item.colors

  return ColorThief.getPalette(item.file, n)
    .then(palette => {
      const res = {
        ...item,
        palette: null
      };
      if (palette) {
        const colors = palette.map(([r, g, b]) => {
          return tinycolor({r, g, b})
        });
        const sorted = sortBy(colors, color => color.getBrightness());
        res.palette = sorted.map(color => color.toHexString());
      };
      return res
  })
}


const filtered = assetsJson.filter(x => !x.ignored);
const categories = uniq(flatten(filtered.map(x => x.categories))).sort();
const assets = await Promise.all(filtered.map(withPalette))
  .then(items => Promise.all(items.map(withSize)) )
  .then(items => shuffle(items.map(withAspect)))
  .catch(err => console.error(err))

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
      context: {
        assets,
        categories
      },
      helpers: {
        let: (options) => {
          return options.fn(options.hash);
        }
      }
    }),
    tailwindcss()
  ]
};
