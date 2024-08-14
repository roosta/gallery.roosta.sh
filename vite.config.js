import handlebars from "vite-plugin-handlebars";
import { resolve } from 'path';
import assetsJson from "./assets.json";
import uniq from "lodash/uniq";
import sortBy from "lodash/sortBy";
import flatten from "lodash/flatten";
import ColorThief from "colorthief";
import sizeOf from "image-size";
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
  return {
    ...item,
    ...sizeOf(item.file)
  }
}

function withAspect(item) {
  return {
    ...item,
    aspect: calcAspect(item)
  }
}


// Handle promise returned from ColorThief
function withPalette(item) {
  return ColorThief.getPalette(item.file, 5)
    .then(palette => {
      const ret = {
        ...item,
        palette: null
      };
      if (palette) {
        const colors = palette.map(([r, g, b]) => {
          return tinycolor({r, g, b})
        });
        const sorted = sortBy(colors, color => color.getBrightness());
        ret.palette = sorted.map(color => color.toHexString());
      };
      return ret
  })
}


const filtered = assetsJson.filter(x => !x?.ignored);
const categories = uniq(flatten(filtered.map(x => x.categories)));
const assets = await Promise.all(filtered.map(withPalette))
  .then(p => p.map(withSize).map(withAspect))
  .catch(err => console.error(err))

export default {
  plugins: [handlebars({
    partialDirectory: resolve(__dirname, 'partials'),
    context: {
      assets,
      categories
    },
    helpers: {
      landscape: (asset, options) => {
        if (asset.aspect === "landscape") {
          return options.fn(this);
        }
      },
      wide: (asset, options) => {
        if (asset.aspect === "wide") {
          return options.fn(this);
        }
      },
      portrait: (asset, options) => {
        if (asset.aspect === "portrait") {
          return options.fn(this);
        }
      },
      square: (asset, options) => {
        if (asset.aspect === "square") {
          return options.fn(this);
        }
      }
    },
  })],
};
