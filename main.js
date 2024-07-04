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
    return assets.map(asset => {
      asset.aspect = this.getAspect(asset)
      return asset
    })
  },
  filtered() {
    return this.data().filter(asset => {
      return !asset?.ignored
    });
  },
  categories() {
    const tags = assets.map(x => x.categories);
    return uniq(flatten(tags))
  },
  selected: null,
  getClass(asset) {
    if (this.selected?.file === asset.file) {
      return `${asset.aspect}-selected`;
    } else {
      return asset.aspect;
    }
  },
  setSelected(asset) {
    if (this.selected?.file === asset.file) {
      this.selected = null;
    } else {
      this.selected = asset;
    }
  },
  getAspect(asset) {
    const ratio = asset.width / asset.height;
    if (asset.width > asset.height && ratio > 2) {
      return "landscape";
    } else if (asset.height > asset.width) {
      return "portrait"
    } else {
      return "square"
    }
  }
}));

Alpine.start();

