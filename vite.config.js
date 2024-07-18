import handlebars from "vite-plugin-handlebars";
import assetsJson from "./assets.json";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";
import ColorThief from "colorthief";
import sizeOf from "image-size";

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
  const size = sizeOf(item.file)
  return {
    ...item,
    ...size
  }
}

function withAspect(item) {
  return {
    ...item,
    aspect: calcAspect(item)
  }
}

// https://stackoverflow.com/a/5624139/4306379
function componentToHex(c) {
  var hex = c.toString(16);
  return hex.length == 1 ? "0" + hex : hex;
}

function rgbToHex(r, g, b) {
  return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}

// Handle promise returned from ColorThief
function withPalette(item) {
  return ColorThief.getPalette(item.file, 5)
    .then(palette => {
      try {
        const hexes = palette.map(color => rgbToHex(...color))
        return {
          ...item,
          palette: hexes
        }
      } catch (err) {
        console.error(err);
        return {
          ...item,
          palette: null
        }
      }
  }).catch(err => {
    console.error(err);
  })
}


const categories = uniq(flatten(assetsJson.map(x => x.categories)));
const assets =
  await Promise.all(assetsJson.map(withPalette))
  .then(p => p.map(withSize).map(withAspect))
  .catch(err => console.error(err))

export default {
  plugins: [handlebars({
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
