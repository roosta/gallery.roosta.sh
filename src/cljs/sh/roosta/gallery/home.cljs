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
  [layouts cols]
  [ResponsiveGridLayout
   {:className "layout"
    :layouts (clj->js layouts)
    :isDraggable false
    :isResizable false
    :breakpoints {:lg 1200 :md 996 :sm 768}
    :cols (clj->js cols)
    :items 57
    :margin [0 0]
    :rowHeight 100}
   (map-indexed
    (fn [index item]
      (let [pswp (js/PhotoSwipe.
                (dom/getElementByClass "pswp")
                js/PhotoSwipeUI_Default
                (transform-map resources/items)
                #js {:index index})]
        ^{:key (str (:id item) "n")}
        [:div.img-container {:on-click #(.init pswp)}
         [:img {:src (:src item)}]
         ]))
    resources/items)])

(defn Main
  []
  (let [cols {:lg 6 :md 4 :sm 2}
        layouts (zipmap
                 [:lg :md :sm]
                 (mapv
                  (fn [[k v]]
                    (reduce
                     (fn [acc item]
                       (conj acc
                             {:i (str (:id item) "n")
                              :x (mod (:id item) v)
                              :y 0
                              :w 1
                              :h (+ (rand-int 3) 2)}))
                     []
                     resources/items))
                  cols)
                 )]
    (r/create-class
     {
      ;; :component-will-mount #(d/log (clj->js layouts))
      ;; :component-did-mount #(init! vsm state)
      ;; :component-will-unmount #(.dispose vsm)
      :reagent-render
      (fn []
        [:div
         [Grid layouts cols]]
        )})))
