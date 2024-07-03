import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";

window.Alpine = Alpine;

Alpine.data("state", () => ({
  assets() {
    return assets.filter(a => {
      return !a?.ignored
    })
  },
  calcSize(asset) {
    if (asset.width > asset.height) {
      return 'col-span-2 row-span-1'
    } else {
      return 'col-span-2 row-span-2'
    }
  }
}));

Alpine.start();

