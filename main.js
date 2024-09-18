// import './style.css';
// import assets from "./assets.json";
import { wrapGrid } from 'animate-css-grid'
import intersection from "lodash/intersection";
import { gridPos } from "./utils.js";
// import anime from 'animejs/lib/anime.es.js';


// function loadImage(img){
//   return new Promise(resolve=>{img.onload = resolve})
// }

// State object, contains all state for site, and methods that operate on that
// state
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

    // Unselect item if clicking on same
    if (this.selected.file === file) {
      el.className = this.selected.previous;
      el.style.setProperty("grid-row-start", "auto")
      this.selected = { el: null, file: null};
      el.dataset.selected = false;

      // Set all items to opacity-100
      document.querySelectorAll(".grid-item").forEach(el => {
        if (el.classList.contains("opacity-30")) {
          el.classList.replace("opacity-30", "opacity-100");
        }
      });
    // Select item
    } else {

      // If there already exist a selected we want to deselect it
      if (this.selected.previous) {
        this.selected.el.className = this.selected.previous;

        this.selected.el.style.setProperty("grid-row-start", "auto")
        this.selected.el.dataset.selected = false;
      }

      // Assign new selected
      this.selected = { el, file, previous: el.className }
      const {row, } = gridPos(el);
      el.className = selectedClass;
      el.style.setProperty("grid-row-start", row)
      el.dataset.selected = true;

      // Set opacity to 50 on all items that isn't selected
      document
        .querySelectorAll(".grid-item[data-selected='false']").forEach(el => {
          if (el.classList.contains("opacity-100")) {
            el.classList.replace("opacity-100", "opacity-30");
          }
      });
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

function toggleVideo(button) {
  const handle = button.dataset.handle;
  const video = document.querySelector(`video[data-file="${handle}"]`);
  const flag = video.dataset.playing;
  const icon = button.querySelector("i");
  if (flag == "true") {
    video.pause()
    icon.classList.replace("fa-pause", "fa-play")
    video.dataset.playing = false;
  } else {
    video.play();
    icon.classList.replace("fa-play", "fa-pause")
    video.dataset.playing = true;
  }
}
function stopVideo(button) {
  const handle = button.dataset.handle;
  const input = document.querySelector(`input[data-handle="${handle}"]`);
  const video = document.querySelector(`video[data-file="${handle}"]`);
  const icon = document.querySelector(
    `button.play-pause[data-handle="${handle}"] i.fa-pause`
  )
  if (icon) {
    icon.classList.replace("fa-pause", "fa-play");
  }
  video.dataset.playing = false;
  video.pause();;
  video.currentTime = 0;
  input.value = 0;
}

function seekChange(input) {
  const handle = input.dataset.handle;
  const video = document.querySelector(`video[data-file="${handle}"]`);
  let percent = input.value / 100;
  let position = video.duration * percent;
  video.currentTime = position;
}

function seekUpdate(video) {
  const handle = video.dataset.file;
  const input = document.querySelector(`input[data-handle="${handle}"]`);
  const pos = Math.floor(100 * video.currentTime / video.duration);
  input.value = pos;
}

function setupEvents() {
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
  const playPauseButtons = document.querySelectorAll(".play-pause");
  playPauseButtons.forEach(button => {
    button.addEventListener("click", () => toggleVideo(button))
  })
  const stopButtons = document.querySelectorAll(".stop");
  stopButtons.forEach(button => {
    button.addEventListener("click", () => stopVideo(button))
  })
  const seekInputs = document.querySelectorAll("input.seek");
  seekInputs.forEach(input => {
    input.addEventListener("change", () => seekChange(input))
  })
  const videoElements = document.querySelectorAll("video");
  videoElements.forEach(video => {
    video.addEventListener('timeupdate', () => seekUpdate(video));
  })
}

// Main entry
function main() {
  state.init();
  setupEvents();
}

document.addEventListener("DOMContentLoaded", state.loaded);
main();

