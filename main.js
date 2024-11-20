// import './style.css';
// import assets from "./assets.json";
import { wrapGrid } from 'animate-css-grid'
import intersection from "lodash/intersection";
// import anime from 'animejs/lib/anime.es.js';


// function loadImage(img){
//   return new Promise(resolve=>{img.onload = resolve})
// }

// State object, contains all state for site, and methods that operate on that
// state
const state = {
  categoriesOpen: false,
  menuOpen: false,
  menuIndex: -1,
  theme: "light",
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
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    if ((!localStorage.theme && prefersDark) || localStorage.theme === "dark") {
      this.theme = "dark"
    }
    const checkbox = document.querySelector(".theme-switch");
    checkbox.checked = this.theme === "light";
  },

  setTheme(theme) {
    const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
    const prefersLight = window.matchMedia("(prefers-color-scheme: light)").matches;
    this.theme = theme;
    if (document.documentElement.classList.contains("dark")) {
      document.documentElement.classList.remove("dark");
    } else {
      document.documentElement.classList.add("dark");
    }
    if ((prefersDark && theme === "dark") || (prefersLight && theme === "light")) {
      localStorage.removeItem('theme');
    } else if (prefersLight && theme === "dark") {
      localStorage.theme = "dark";
    } else if (prefersDark && theme === "light") {
      localStorage.theme = "light";
    }
  },

  // Toggle categories panel
  toggleCategories(filterButton, clearFilter = false) {
    const target = document.querySelector(".filter-container");
    target.classList.toggle("hidden");
    if (this.categoriesOpen) {
      clearFilter && this.setFilter();
      filterButton.className = filterButton.className.replace(
        filterButton.dataset.selectedClass,
        filterButton.dataset.unselectedClass,
      );
      this.categoriesOpen = false;
    } else {
      filterButton.className = filterButton.className.replace(
        filterButton.dataset.unselectedClass,
        filterButton.dataset.selectedClass,
      )
      this.categoriesOpen = true;
    }
  },
  // Swap selected with unselected classes, see markup for actual classnames
  toggleDetails(file, previousFile) {

    if (previousFile === file) {
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
      const previousEls = document.querySelectorAll(
        `div[data-handle="${previousFile}"]`
      );
      previousEls.forEach(el => {
        el.className = el.className.replace(
          el.dataset.selectedClass,
          el.dataset.unselectedClass
        )
      })

      const targetEls = document.querySelectorAll(
        `div[data-handle="${file}"]`
      );
      targetEls.forEach(el => {
        el.className = el.className.replace(
          el.dataset.unselectedClass,
          el.dataset.selectedClass
        )
      })
    }
  },

  openMenu() {
    const menu = document.getElementById("menu")
    const button = document.getElementById("menu-button");
    this.menuOpen = true;
    menu.className = menu.className.replace(
      menu.dataset.unselectedClass,
      menu.dataset.selectedClass
    )
    button.setAttribute("aria-expanded", "true");
  },

  closeMenu(focus = false) {
    const menu = document.getElementById("menu")
    const button = document.getElementById("menu-button");
    this.menuOpen = false;
    menu.className = menu.className.replace(
      menu.dataset.selectedClass,
      menu.dataset.unselectedClass,
    )
    if (focus) button.focus();
    button.setAttribute("aria-expanded", "false");
  },
  toggleMenu() {
    if (this.menuOpen) this.closeMenu();
    else this.openMenu();
  },

  // Handle opening and closing menu from event
  handleMenuButton(event) {
    if (event.type === "click") {
      this.toggleMenu();
    } else if (event?.key === "ArrowDown" ||
      event?.key === "Enter" || event?.key === " ") {
      event.preventDefault();
      this.openMenu();
      this.focusMenuItem(0);
    }
  },

  focusMenuItem(index) {
    const menuItems = document.querySelectorAll('[role="menuitem"]');
    // Wrap around if we go past the ends
    if (index >= menuItems.length) index = 0;
    if (index < 0) index = menuItems.length - 1;

    menuItems[index].focus();
    this.menuIndex = index;

  },

  // Handle keyboard navigation for menu
  handleMenuNav(event) {

    event.preventDefault();

    switch (event.key) {
      case "ArrowDown":
        this.focusMenuItem(this.menuIndex + 1);
        break;
      case "ArrowUp":
        this.focusMenuItem(this.menuIndex - 1);
        break;
      case 'Enter':
      case ' ':
        if (document.activeElement.tagName === 'LABEL') {
          // For the theme toggle
          const checkbox = document.activeElement.querySelector('input[type="checkbox"]');
          checkbox.checked = !checkbox.checked;
          checkbox.dispatchEvent(new Event('change'));
        } else {
          // For regular menu items
          document.activeElement.click();
        }

    }
  },

  // Set selected item, and deselect previous
  setSelected(el) {
    const file = el.dataset.file;
    const previousFile = this.selected.file;
    const headerEl = document.querySelector("header");

    // Unselect item
    if (this.selected.file === file) {
      headerEl.dataset.itemActive = false;
      el.className = el.className.replace(
        el.dataset.selectedClass,
        el.dataset.unselectedClass
      )
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

      headerEl.dataset.itemActive = true;

      // If there already exist a selected we want to deselect it
      if (this.selected.file) {
        this.selected.el.className = this.selected.el.className.replace(
          this.selected.el.dataset.selectedClass,
          this.selected.el.dataset.unselectedClass
        );

        this.selected.el.dataset.selected = false;
      }

      // Assign new selected
      this.selected = { el, file }
      el.className = el.className.replace(
        el.dataset.unselectedClass,
        el.dataset.selectedClass
      );
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

    this.toggleDetails(file, previousFile, el);

    // Ensure smooth scrolling to the selected item
    el.scrollIntoView({behavior: "smooth", block: "center"});

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
        tag.className = tag.className.replace(
          tag.dataset.unselectedClass,
          tag.dataset.selectedClass,
        )
      } else {
        tag.className = tag.className.replace(
          tag.dataset.selectedClass,
          tag.dataset.unselectedClass,
        );
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
  video.pause();
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

  /// Gallery
  // ------------------------
  const gridItems = document.querySelectorAll(".grid-item");
  gridItems.forEach(el => {
    el.addEventListener("click", () => state.setSelected(el))
    el.addEventListener("keydown", (event) => {
      if (event.key === "Enter") {
        state.setSelected(el)
      }
    })
  })

  /// Filters
  // ------------------------
  const filterButton = document.querySelector(".filter-button");
  filterButton.addEventListener("click", () => {
    return state.toggleCategories(filterButton, true);
  });

  const filterTags = document.querySelectorAll(".filter-tag");
  filterTags.forEach(tag => {
    tag.addEventListener("click", () => state.setFilter(tag, true))
  })

  /// Videos
  // ------------------------
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

  /// Menu
  // ------------------------
  const menuButton = document.getElementById("menu-button");
  menuButton.addEventListener("click", (e) => state.handleMenuButton(e))
  menuButton.addEventListener("keydown", (e) => state.handleMenuButton(e))

  const menu = document.getElementById("menu");
  menu.addEventListener("keydown", (e) => state.handleMenuNav(e))

  document.addEventListener("keydown", (e) => {
    if (state.menuOpen && e.key === "Escape") {
      state.closeMenu(true);
    }
  })
  document.addEventListener("click", (e) => {
    if (state.menuOpen) {
      if (!menu.contains(e.target) && !menuButton.contains(e.target)) {
        state.closeMenu();
      }
    }
  })
  const themeSwitch = document.querySelector(".theme-switch");
  themeSwitch.addEventListener("change", (event) => {
    state.setTheme(event.target.checked ? "light" : "dark");
  })
}

// Main entry
function main() {
  state.init();
  setupEvents();
}

document.addEventListener("DOMContentLoaded", state.loaded);
main();

