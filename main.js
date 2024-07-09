// import './style.css';
// import assets from "./assets.json";
import { wrapGrid } from 'animate-css-grid'
import intersection from "lodash/intersection";
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
  filter: {
    data: [],
    class: ""
  },
  loaded() {
    const grid = document.querySelector(".grid");
    wrapGrid(grid);
  },
  selected: {
    el: null,
    file: null,
    previous: "",
  },
  init() {
    // Initialize tag classname, swapping classname based on active state, so we need to store the original
    const tag = document.querySelector(".filter-tag");
    this.filter.class = tag.className;
  },
  toggleCategories() {
    const target = document.querySelector(".filter-container");
    target.classList.toggle("hidden");
    this.categoriesOpen != this.categoriesOpen
    if (!this.categoriesOpen) {
      this.setFilter();
    }
  },

  // Set selected item, and deselect previous
  setSelected(el) {
    const file = el.dataset?.file;
    const selectedClass = el.dataset?.selectedClass;
    if (!file || !selectedClass) return;
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

  // Set image filter, state array contains all active filters
  setFilter(el) {
    const category = el?.dataset?.category;
    if (el && category) {
      if (this.filter.data.includes(category)) {
        this.filter.data = this.filter.data.filter(x => x !== category);
      } else {
        this.filter.data.push(category)
      }
    }
    const gridItems = document.querySelectorAll(".grid-item");
    gridItems.forEach(el => {
      const categories = el.dataset.categories.split(",");
      if (this.filter.data.length === 0) {
        el.classList.remove("hidden");
      } else if (intersection(categories, this.filter.data).length === 0) {
        el.classList.add("hidden");
      } else {
        el.classList.remove("hidden");
      }
    })
    const filterTags = document.querySelectorAll(".filter-tag");
    filterTags.forEach(tag => {
      if (this.filter.data.includes(tag.dataset.category)) {
        tag.className = tag.dataset.selectedClass;
      } else {
        tag.className = this.filter.class;
      }
    })
  },

};

function attachListeners() {
  const gridItems = document.querySelectorAll(".grid-item");
  gridItems.forEach(el => {
    el.addEventListener("click", () => state.setSelected(el))
  })
  const filterButton = document.querySelector(".filter-button");
  filterButton.addEventListener("click", () => state.toggleCategories());

  const filterTags = document.querySelectorAll(".filter-tag");
  filterTags.forEach(tag => {
    tag.addEventListener("click", () => state.setFilter(tag))
  })
}


function main() {
  attachListeners();
  state.init();
}

document.addEventListener("DOMContentLoaded", state.loaded);
main();
