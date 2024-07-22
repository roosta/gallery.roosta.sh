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
    tagClass: "",
    buttonClass: "",
  },
  // Run after dom content is loaded
  loaded() {
    const grid = document.querySelector(".grid");
    wrapGrid(grid);
  },
  selected: {
    el: null,
    file: null,
    previous: "",
  },
  // Run possibly before dom is done rendering, before everything else
  init() {
    // Initialize tag classname, swapping classname based on active state, so we need to store the original
    const filterTag = document.querySelector(".filter-tag");
    const filterButton = document.querySelector(".filter-button");
    this.filter.tagClass = filterTag.className;
    this.filter.buttonClass = filterButton.className;
  },

  // Toggle categories panel
  toggleCategories(filterButton) {
    const target = document.querySelector(".filter-container");
    target.classList.toggle("hidden");
    if (this.categoriesOpen) {
      this.setFilter();
      filterButton.className = this.filter.buttonClass;
      this.categoriesOpen = false;
    } else {
      filterButton.className = filterButton.dataset.selectedClass;
      this.categoriesOpen = true;
    }
  },
  // Swap selected with unselected classes, see markup for actual classnames
  toggleDetails(file) {
    const defaultEl = document.querySelector('div[data-handle="default"]');
    if (this.selected.file === file) {
      const previousEl = document.querySelector(`div[data-handle="${this.selected.file}"]`)
      defaultEl.classList.replace(
          defaultEl.dataset.unselectedClass.split(" "),
          defaultEl.dataset.selectedClass.split(" "),
      )
      previousEl.classList.replace(
        previousEl.dataset.selectedClass.split(" "),
        previousEl.dataset.unselectedClass.split(" ")
      );
    } else {
      const targetEl = document.querySelector(`div[data-handle="${file}"]`);
      if (!this.selected.file) {
        defaultEl.classList.replace(
          defaultEl.dataset.selectedClass.split(" "),
          defaultEl.dataset.unselectedClass.split(" "),
        )
      } else {
        const previousEl = document.querySelector(`div[data-handle="${this.selected.file}"]`)
        previousEl.classList.replace(
          previousEl.dataset.selectedClass.split(" "),
          previousEl.dataset.unselectedClass.split(" ")
        )
      }
      targetEl.classList.replace(
        targetEl.dataset.unselectedClass.split(" "),
        targetEl.dataset.selectedClass.split(" ")
      )
    }
  },

  // Set selected item, and deselect previous
  setSelected(el) {
    const file = el.dataset?.file;
    const selectedClass = el.dataset?.selectedClass;
    if (!file || !selectedClass) return;
    this.toggleDetails(file);
    if (this.selected.file === file) { // Unselect item
      el.className = this.selected.previous;
      this.selected = { el: null, file: null};
      el.dataset.selected = false;
    } else { // Select item
      if (this.selected.previous) {
        this.selected.el.className = this.selected.previous;
        this.selected.el.dataset.selected = null;
      }
      this.selected = { el, file, previous: el.className }
      el.className = selectedClass;
      el.dataset.selected = true;
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
    } else {
      this.filter.data = [];
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
        tag.className = this.filter.tagClass;
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
  filterButton.addEventListener("click", () => state.toggleCategories(filterButton));

  const filterTags = document.querySelectorAll(".filter-tag");
  filterTags.forEach(tag => {
    tag.addEventListener("click", () => state.setFilter(tag))
  })
}


function main() {
  state.init();
  attachListeners();
}

document.addEventListener("DOMContentLoaded", state.loaded);
main();

