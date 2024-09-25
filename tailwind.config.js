const defaultTheme = require("tailwindcss/defaultTheme");

/** @type {import("tailwindcss").Config} */
export default {
  content: [
    "./index.html",
    "./main.js",
    "./partials/*"
  ],
  theme: {
    data: {
      selected: 'selected~="true"',
      'item-active': 'item-active~="true"'
    },
    extend: {
      fontFamily: {
        work: ["Work Sans", ...defaultTheme.fontFamily.sans],
        open: ["Open Sans", ...defaultTheme.fontFamily.sans],
      },
      gridAutoRows: {
        'gallery': 'minmax(12rem, 2fr)',
      },
      spacing: {
        '128': '32rem',
      },
      colors: {
        white: "#FBFBFB",
        grayish: "#A4A497",
        backdrop: "#0D0D0D",
        whiteish: "#D1D1CA"
      }
    },
  },
}

