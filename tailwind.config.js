const defaultTheme = require("tailwindcss/defaultTheme");

/** @type {import("tailwindcss").Config} */
export default {
  content: [
    "*.js",
    "*.html",
    "./partials/*",
  ],
  darkMode: 'selector',
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
        "white": "#E5E1D8",
        "black": "#141414",
        "dim-dark": "#A4A497",
        "dim-light": "#484848",
        "backdrop-dark": "#0D0D0D",
        "backdrop-light": "#B4B1AB",
      }
    },
  },
}

