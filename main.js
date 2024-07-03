import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";

window.Alpine = Alpine;

Alpine.data("assets", () => ({
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
    if (asset.width > asset.height) {
      return  ratio > 2 && window.screen.width < 640
        ? 'col-span-4 row-span-1'
        : 'col-span-2 row-span-1'
    } else if (asset.height > asset.width) {
      return 'col-span-2 row-span-2'
    } else {
      return 'col-span-2 row-span-1'
    }
  }
}));

Alpine.start();

