const defaultTheme = require("tailwindcss/defaultTheme");
const plugin = require('tailwindcss/plugin')

/** @type {import("tailwindcss").Config} */
export default {
  content: [
    "./index.html",
    "./main.js"
  ],
  plugins: [
    plugin(function({ addVariant }) {
      addVariant("selected", "&[data-selected='true']")
    })
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
}

