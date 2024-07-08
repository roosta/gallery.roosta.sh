import handlebars from "vite-plugin-handlebars";
import assets from "./assets.json";

export default {
  plugins: [handlebars({
    context: { assets }
  })],
};
