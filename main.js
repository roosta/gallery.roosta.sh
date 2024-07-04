import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";

window.Alpine = Alpine;

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
  calcSize(asset) {
    const ratio = asset.width / asset.height;
    if (asset.width > asset.height && ratio > 2) {
      return "sm:col-span-2 md:col-span-4";
    } else if (asset.height > asset.width) {
      return "sm:col-span-2 sm:row-span-2"
    } else {
      return "sm:col-span-2"
    }
  }
}));

Alpine.start();

