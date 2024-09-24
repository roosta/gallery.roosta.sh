// import './style.css';
// import assets from "./assets.json";
import { wrapGrid } from 'animate-css-grid'
import intersection from "lodash/intersection";
import { gridPos, breakpoints } from "./utils.js";
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
  toggleDetails(file, previousFile, row, selectedEl) {

    const winWidth = window.innerWidth;
    const headerEl = winWidth >= breakpoints.lg ?
      document.querySelector("header") : null;

    if (previousFile === file) {
      if (headerEl) {
        headerEl.className = headerEl.className.replace(
          headerEl.dataset.unselectedClass,
          headerEl.dataset.selectedClass,
        )
      }
      const previousEls = document.querySelectorAll(
        `div[data-handle="${previousFile}"]`
      );
      previousEls.forEach(el => {
        el.className = el.className.replace(
          el.dataset.selectedClass,
          el.dataset.unselectedClass
        );
      })
    } else {
      if (!previousFile) {
        if (headerEl) {
          headerEl.className = headerEl.className.replace(
            headerEl.dataset.selectedClass,
            headerEl.dataset.unselectedClass,
          )
        }
      } else {
        const previousEls = document.querySelectorAll(
          `div[data-handle="${previousFile}"]`
        );
        previousEls.forEach(el => {
          el.className = el.className.replace(
            el.dataset.selectedClass,
            el.dataset.unselectedClass
          )
        })
      }

      const targetEls = document.querySelectorAll(
        `div[data-handle="${file}"]`
      );
      targetEls.forEach(el => {
        if (el.classList.contains("tail-detail")) {
          const style = window.getComputedStyle(selectedEl);
          const gridRow = style.getPropertyValue("grid-row");
          const rowSpan = gridRow.includes("span") ? parseInt(gridRow.split("span")[1]) : 1;
          el.style.setProperty("grid-row-start", row + rowSpan)
        }
        el.className = el.className.replace(
          el.dataset.unselectedClass,
          el.dataset.selectedClass
        )
      })
    }

    // }
  },

  // Set selected item, and deselect previous
  setSelected(el) {
    const file = el.dataset.file;
    const {row, } = gridPos(el);
    const previousFile = this.selected.file;

    // Unselect item if clicking on same
    if (this.selected.file === file) {
      el.className = el.className.replace(
        el.dataset.selectedClass,
        el.dataset.unselectedClass
      )
      el.style.setProperty("grid-row-start", "auto")
      this.selected = { el: null, file: null};
      el.dataset.selected = false;

      document.querySelectorAll(".grid-item").forEach(el => {
        el.className = el.className.replace(
          el.dataset.unfocusClass,
          el.dataset.focusClass
        )
      });
    // Select item
    } else {

      // If there already exist a selected we want to deselect it
      if (this.selected.file) {
        this.selected.el.className = this.selected.el.className.replace(
          this.selected.el.dataset.selectedClass,
          this.selected.el.dataset.unselectedClass
        );

        this.selected.el.style.setProperty("grid-row-start", "auto")
        this.selected.el.dataset.selected = false;
      }

      // Assign new selected
      this.selected = { el, file }
      el.className = el.className.replace(
        el.dataset.unselectedClass,
        el.dataset.selectedClass
      );
      el.style.setProperty("grid-row-start", row)
      el.dataset.selected = true;
      el.className = el.className.replace(
        el.dataset.unfocusClass,
        el.dataset.focusClass,
      )

      document
        .querySelectorAll(".grid-item[data-selected='false']").forEach(el => {
          el.className = el.className.replace(
            el.dataset.focusClass,
            el.dataset.unfocusClass
          )
      });
    }

    this.toggleDetails(file, previousFile, row, el);

    // Stop any playing videos when changing selection
    const videos = document.querySelectorAll("video[data-playing='true']")
    videos.forEach(video => {
      stopVideo(video.dataset.file)
    })
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
function stopVideo(handle) {
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
    button.addEventListener("click", () => stopVideo(button.dataset.handle))
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

