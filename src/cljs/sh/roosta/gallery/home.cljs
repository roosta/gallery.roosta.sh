(ns sh.roosta.gallery.home
  (:require
   [cljsjs.photoswipe]
   [cljsjs.photoswipe-ui-default]
   [cljsjs.react-grid-layout]
   [goog.dom :as dom]
   [sh.roosta.gallery.resources :as resources]
   [reagent.debug :as d]
   [goog.events :as events]
   [reagent.core :as r])
  (:import  [goog.dom ViewportSizeMonitor])
  )

(def GridLayout (r/adapt-react-class js/ReactGridLayout))
(def Responsive js/ReactGridLayout.Responsive)
(def ResponsiveGridLayout (r/adapt-react-class (js/ReactGridLayout.WidthProvider Responsive)))

(defn transform-map
  "Transform resource map to conform to photoswipe"
  [items]
   (clj->js
    (reduce
     (fn [acc item]
       (conj acc {:src (:src item)
                  :w (:w item)
                  :h (:h item)}))
     []
     items)))

(defn Grid
  [state]
  (let [cols (:cols @state)]
    [ResponsiveGridLayout
     {:className "layout"
      ;; :layout layout
      ;; :width (.-innerWidth js/window)
      :isDraggable false
      :on-breakpoint-change #(swap! state assoc :cols %2)
      :isResizable false
      :breakpoints {:lg 1200 :md 996 :sm 768 :xs 480 :xxs 0}
      :cols {:lg 6 :md 4 :sm 2 :xs 2 :xxs 1}
      :items 57
      :margin [0 0]
      ;; :cols 4
      :rowHeight 100}
     (map-indexed
      (fn [index item]
        ^{:key (str (:id item) "n")}
        [:div.img-container {:data-grid {:x (mod index cols) :y 0 :w 1 :h (+ (rand-int 3) 2)}}
         [:img {:src (:src item)}]
         ])
      resources/items)]))

(defn Main
  []
  (let [
        items (transform-map resources/items)
        gallery (js/PhotoSwipe.
                 (dom/getElementByClass "pswp")
                 js/PhotoSwipeUI_Default
                 items
                 #js {:index 0})
        state (r/atom {:cols 6})]
    (r/create-class
     {
      ;; :component-did-mount #(init! vsm state)
      ;; :component-will-unmount #(.dispose vsm)
      :reagent-render
      (fn []
        [:div
         [Grid state]]
        )})))
