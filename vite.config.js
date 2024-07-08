import handlebars from "vite-plugin-handlebars";
import assets from "./assets.json";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";

function calcAspect(asset) {
  const ratio = asset.width / asset.height;
  if (ratio > 2) { return "landscape" }
  else if (ratio < 1) { return "portrait" }
  return "square";
};

const withAspect = assets.map(x => {
  x.aspect = calcAspect(x)
  return x;
})

const categories = uniq(flatten(assets.map(x => x.categories)));

export default {
  plugins: [handlebars({
    context: {
      assets: withAspect,
      categories
    },
    helpers: {
      landscape: (asset, options) => {
        if (asset.aspect === "landscape") {
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
