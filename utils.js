// tailwind breakpoints, unused cyr
const breakpoints = { // eslint-disable-line
  sm: 640,
  md: 768,
  lg: 1024,
  xl: 1280,
  "2xl": 1536
}

// Get grid position of clicked element, taking into account col and row span
// where items can span either multiple columns or multiple rows. To get the
// correct position, row/col span needs to be accounted for, hence the
// complexity
function gridPos(itemEl) {
  const gridEl = document.getElementById("gallery");
  const gridStyle = window.getComputedStyle(gridEl);
  const colCount = gridStyle.gridTemplateColumns.split(" ").length;

  let occupiedCells = new Array(100).fill(0).map(() => new Array(colCount).fill(false));
  let maxRow = 1;

  for (let el of gridEl.children) {
    const elStyle = window.getComputedStyle(el);
    if (elStyle.display === 'none') continue; // Skip elements with display: none

    const gridColumn = elStyle.getPropertyValue("grid-column");
    const gridRow = elStyle.getPropertyValue("grid-row");
    const colSpan = gridColumn.includes("span") ? parseInt(gridColumn.split("span")[1]) : 1;
    const rowSpan = gridRow.includes("span") ? parseInt(gridRow.split("span")[1]) : 1;

    let row, column;
    for (row = 1; row <= maxRow + rowSpan; row++) {
      for (column = 1; column <= colCount; column++) {
        if (!occupiedCells[row - 1][column - 1]) {
          let canPlace = true;
          for (let r = 0; r < rowSpan; r++) {
            for (let c = 0; c < colSpan; c++) {
              if (column + c > colCount || occupiedCells[row + r - 1][column + c - 1]) {
                canPlace = false;
                break;
              }
            }
            if (!canPlace) break;
          }
          if (canPlace) {
            for (let r = 0; r < rowSpan; r++) {
              for (let c = 0; c < colSpan; c++) {
                occupiedCells[row + r - 1][column + c - 1] = true;
              }
            }
            maxRow = Math.max(maxRow, row + rowSpan - 1);
            if (el === itemEl) {
              return { row, column };
            }
            break;
          }
        }
      }
      if (column <= colCount) break;
    }
  }

  return null; // Item not found
}


export { gridPos, breakpoints };
