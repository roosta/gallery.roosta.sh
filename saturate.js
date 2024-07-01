export function setupSaturate() {
  const capture = document.querySelector(".saturate-capture");
  const overlay = capture.querySelector(".saturate-overlay");
  capture.addEventListener("mousemove", (event) => {
    const x = event.pageX - capture.offsetLeft;
    const y = event.pageY - capture.offsetTop + capture.scrollTop;
    overlay.style.setProperty("--saturate-x", `${x}px`);
    overlay.style.setProperty("--saturate-y", `${y}px`);
  })
}
