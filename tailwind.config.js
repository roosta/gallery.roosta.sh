const defaultTheme = require("tailwindcss/defaultTheme");

/** @type {import("tailwindcss").Config} */
export default {
  content: [
    "./index.html",
    "./main.js"
  ],
  theme: {
    extend: {
      fontFamily: {
        work: ["Work Sans", ...defaultTheme.fontFamily.sans],
        open: ["Open Sans", ...defaultTheme.fontFamily.sans],
      },
      colors: {
        white: "#FBFBFB",
        grayish: "#A4A497",
        backdrop: "#1A1A1A",
        whiteish: "#D1D1CA"
      }
    },
  },
  plugins: [],
}

