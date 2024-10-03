import handlebars from "vite-plugin-handlebars";
// import { resolve } from 'path';
import assetsJson from "./assets.json";
import uniq from "lodash/uniq";
import shuffle from "lodash/shuffle";
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


const filtered = assetsJson.filter(x => !x.ignored);
const categories = uniq(flatten(filtered.map(x => x.categories)));
const assets = await Promise.all(filtered.map(withPalette))
  .then(p => shuffle(p.map(withSize).map(withAspect)))
  .catch(err => console.error(err))

export default {
  plugins: [handlebars({
    context: {
      assets,
      categories
    },
    helpers: {
      let: (options) => {
        return options.fn(options.hash);
      }
    }
  })]
};
