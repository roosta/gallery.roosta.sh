import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";

window.Alpine = Alpine

Alpine.data("state", () => ({
  assets() {
    return assets.filter(a => {
      return !a?.ignored
    })
  }
}))
Alpine.start()
