import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";

import sortBy from "lodash/sortBy.js";
import { setupSaturate } from './saturate.js'

window.Alpine = Alpine

Alpine.data("state", () => ({
  assets() {
    return assets.filter(a => {
      return !a?.ignored
    })
  }
}))
Alpine.start()
setupSaturate();

