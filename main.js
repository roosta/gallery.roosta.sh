// import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";
import { wrapGrid } from 'animate-css-grid'

// tailwind breakpoints
const breakpoints = { // eslint-disable-line
  sm: 640,
  md: 768,
  lg: 1024,
  xl: 1280,
  "2xl": 1536
}


// function loadImage(img){
//   return new Promise(resolve=>{img.onload = resolve})
// }

// document.addEventListener('alpine:initialized', () => {
//
//   // const images = document.querySelectorAll("img");
//   // const promises = [];
//   // images.forEach(img => {
//   //   promises.push(loadImage(img))
//   // })
//   // Promise.all(promises).then(() => {
//   //   const grid = document.querySelector(".grid");
//   //   wrapGrid(grid);
//   // })
//     // const grid = document.querySelector(".grid");
//     // wrapGrid(grid);
// })


Alpine.data("filter", () => ({
  open: false,
  categories() {
    const tags = assets.map(x => x.categories);
    return uniq(flatten(tags))
  },
}));

Alpine.start();


const state = {
  selected: {
    el: null,
    file: null,
    previous: "",
  },
  // Set selected item, and deselect previous
  setSelected(el) {
    // const aspect = el.dataset?.aspect;
    const file = el.dataset?.file;
    const selectedClass = el.dataset?.selectedClass;
    if (this.selected.file === file) {
      el.className = this.selected.previous;
      this.selected = { el: null, file: null};
    } else {
      if (this.selected.previous) {
        this.selected.el.className = this.selected.previous;
      }
      this.selected = { el, file, previous: el.className }
      el.className = selectedClass;
    }
  },
  isSelected(file) {
    return this.selected === file
  },
};

function attachListeners() {
  const elements = document.querySelectorAll(".grid-item");
  elements.forEach(el => {
    el.addEventListener("click", () => state.setSelected(el))
  })
}


function main() {
  const grid = document.querySelector(".grid");
  attachListeners();
  wrapGrid(grid);
}

document.addEventListener("DOMContentLoaded", main);
// main();
