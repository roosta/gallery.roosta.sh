const state = {
  menuOpen: false,
  menuIndex: -1,
  theme: "light",
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
  init() {
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    if ((!localStorage.theme && prefersDark) || localStorage.theme === "dark") {
      this.theme = "dark"
    }
    const checkbox = document.querySelector(".theme-switch");
    checkbox.checked = this.theme === "light";
  },
}

function setup() {
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

export default function() {
  state.init();
  setup();
}
