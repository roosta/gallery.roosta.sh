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
    // Initialize tag classname, swapping classname based on active state, so
    // we need to store the original
    const filterTag = document.querySelector(".filter-tag");
    const filterButton = document.querySelector(".filter-button");
    this.filter.tagClass = filterTag.className;
    this.filter.buttonClass = filterButton.className;
  },

  // Toggle categories panel
  toggleCategories(filterButton, clearFilter = false) {
    const target = document.querySelector(".filter-container");
    target.classList.toggle("hidden");
    if (this.categoriesOpen) {
      clearFilter && this.setFilter();
      filterButton.className = this.filter.buttonClass;
      this.categoriesOpen = false;
    } else {
      filterButton.className = filterButton.dataset.selectedClass;
      this.categoriesOpen = true;
    }
  },
  // Swap selected with unselected classes, see markup for actual classnames
  toggleDetails(file) {
    const headerEl = document.querySelector('header');
    if (this.selected.file === file) {
      const previousEl
        = document.querySelector(`div[data-handle="${this.selected.file}"]`)
      headerEl.classList.replace(
          headerEl.dataset.unselectedClass.split(" "),
          headerEl.dataset.selectedClass.split(" "),
      )
      previousEl.classList.replace(
        previousEl.dataset.selectedClass.split(" "),
        previousEl.dataset.unselectedClass.split(" ")
      );
    } else {
      const targetEl = document.querySelector(`div[data-handle="${file}"]`);
      if (!this.selected.file) {
        headerEl.classList.replace(
          headerEl.dataset.selectedClass.split(" "),
          headerEl.dataset.unselectedClass.split(" "),
        )
      } else {
        const previousEl
          = document.querySelector(`div[data-handle="${this.selected.file}"]`)
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
      const gridItems = document.querySelectorAll(".grid-item");
      gridItems.forEach(el => {
        if (el.classList.contains("opacity-50")) {
          el.classList.replace("opacity-50", "opacity-100");
        }
      })

    } else { // Select item
      if (this.selected.previous) {
        this.selected.el.className = this.selected.previous;
        this.selected.el.dataset.selected = null;
      }
      this.selected = { el, file, previous: el.className }
      el.className = selectedClass;
      el.dataset.selected = true;
      const notSelected
        = document.querySelectorAll(".grid-item[data-selected='false']");
      notSelected.forEach(el => {
        if (el.classList.contains("opacity-100")) {
          el.classList.replace("opacity-100", "opacity-50");
        }
      })
    }
  },

  // Set image filter, state array contains all active filters
  setFilter(el) {
    const category = el?.dataset?.category;

    // Open categories panel if it isn't already
    if (!this.categoriesOpen) {
      const filterButton = document.querySelector(".filter-button");
      this.toggleCategories(filterButton, false);
    }

    // Handle filter state
    if (el && category) {
      if (this.filter.data.includes(category)) {
        this.filter.data = this.filter.data.filter(x => x !== category);
      } else {
        this.filter.data.push(category)
      }

      // If we select a filter combination that doesn't include the currenly
      // selected's categories, we need to deselect it
      if (this.selected.el) {
        const categories = this.selected.el.dataset.categories.split(",");
        const includes = (
          this.filter.data.some(
            tag => categories.includes(tag)
          ) || this.filter.data.length === 0
        );
        if (!includes) {
          this.setSelected(this.selected.el)
        }
      }
    } else {
      this.filter.data = [];
    }

    // Toggle 'hidden' based on filter data
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

    // Toggle active status on filter tags
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
  filterButton.addEventListener("click", () => {
    return state.toggleCategories(filterButton, true);
  });

  const filterTags = document.querySelectorAll(".filter-tag");
  filterTags.forEach(tag => {
    tag.addEventListener("click", () => state.setFilter(tag, true))
  })
}


function main() {
  state.init();
  attachListeners();
}

document.addEventListener("DOMContentLoaded", state.loaded);
main();

