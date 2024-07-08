// import './style.css';
import assets from "./assets.json";
import Alpine from "alpinejs";
import uniq from "lodash/uniq";
import flatten from "lodash/flatten";
import { wrapGrid } from 'animate-css-grid'

window.Alpine = Alpine;

// tailwind breakpoints
const breakpoints = { // eslint-disable-line
  sm: 640,
  md: 768,
  lg: 1024,
  xl: 1280,
  "2xl": 1536
}

function loadImage(img){
  return new Promise(resolve=>{img.onload = resolve})
}

document.addEventListener('alpine:initialized', () => {

  const images = document.querySelectorAll("img");
  const promises = [];
  images.forEach(img => {
    promises.push(loadImage(img))
  })
  Promise.all(promises).then(() => {
    const grid = document.querySelector(".grid");
    wrapGrid(grid);
  })
})


Alpine.data("assets", () => ({
  selected: null,
  data() {
    return assets.map(asset => {
      asset.aspect = this.getAspect(asset)
      return asset
    })
  },
  filtered() {
    return this.data().filter(asset => {
      return !asset?.ignored
    });
  },
  getAspect(asset) {
    const ratio = asset.width / asset.height;
    if (ratio > 2) {
      return "landscape";
    } else if (ratio < 1) {
      return "portrait"
    } else {
      return "square"
    }
  },
  isSelected(asset) {
    return this.selected?.file === asset.file
  },
  setSelected(asset) {
    if (this.selected?.file === asset.file) {
      this.selected = null;
    } else {
      this.selected = asset;
    }
  },
}))

Alpine.data("filter", () => ({
  open: false,
  categories() {
    const tags = assets.map(x => x.categories);
    return uniq(flatten(tags))
  },
}));

Alpine.start();

