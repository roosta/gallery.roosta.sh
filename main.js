import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";

window.Alpine = Alpine;

// const breakpoints = {
//   sm: 640,
//   md: 768,
//   lg: 1024,
//   xl: 1280,
//   "2xl": 1536
// }

Alpine.data("assets", () => ({
  filtersOpen: false,
  data() {
    return assets.filter(a => {
      return !a?.ignored
    })
  },
  categories() {
    const tags = assets.map(x => x.categories);
    return uniq(flatten(tags))
  },
  selected: {
    asset: null,
    element: null,
  },
  setSelected(asset, element) {
    this.selected = {
      asset,
      element
    }
    console.log(element)
  },
  calcSize(asset) {
    const ratio = asset.width / asset.height;
    if (asset.width > asset.height && ratio > 2) { // Landscape
      return "sm:col-span-2 md:col-span-4";
    } else if (asset.height > asset.width) { // portrait
      return "sm:col-span-2 sm:row-span-2"
    } else {
      return "sm:col-span-2" // square
    }
  }
}));

Alpine.start();

