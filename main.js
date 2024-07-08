// import './style.css';
// import assets from "./assets.json";
import { wrapGrid } from 'animate-css-grid'
// import anime from 'animejs/lib/anime.es.js';


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

const state = {
  categoriesOpen: false,
  selected: {
    el: null,
    file: null,
    previous: "",
  },
  toggleCategories() {
    const target = document.querySelector(".filter-container");
    target.classList.toggle("hidden");
    this.categoriesOpen != this.categoriesOpen
  },

  // Set selected item, and deselect previous
  setSelected(el) {
    const file = el.dataset?.file;
    const selectedClass = el.dataset?.selectedClass;
    if (this.selected.file === file) { // Unselect item
      el.className = this.selected.previous;
      this.selected = { el: null, file: null};
    } else { // Select item
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
  const gridItems = document.querySelectorAll(".grid-item");
  gridItems.forEach(el => {
    el.addEventListener("click", () => state.setSelected(el))
  })
  const filterButton = document.querySelector(".filter-button");
  filterButton.addEventListener("click", () => state.toggleCategories());
}


function main() {
  const grid = document.querySelector(".grid");
  attachListeners();
  wrapGrid(grid);
}

document.addEventListener("DOMContentLoaded", main);
// main();
