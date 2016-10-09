(ns sh.roosta.gallery.home
  (:require
   [cljsjs.photoswipe]
   [cljsjs.photoswipe-ui-default]
   [cljsjs.react-grid-layout]
   [goog.dom :as dom]
   [sh.roosta.gallery.resources :as resources]
   [reagent.debug :as d]
   [reagent.core :as r]))

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

(defn generate-grid-layout
  []
  (reduce
   (fn [acc item]
     (conj acc {:i (str (:id item) "n")}))
   []
   resources/items))

(defn Grid
  []
  (let [layout [{:i "a" :x 0 :y 0 :w 1 :h 2}
                {:i "b" :x 2 :y 0 :w 3 :h 2}]]
    [ResponsiveGridLayout
     {:className "layout"
      ;; :layout layout
      ;; :width (.-innerWidth js/window)
      :isDraggable false
      :isResizable false
      :breakpoints {:lg 1200 :md 996 :sm 768 :xs 480 :xxs 0}
      :cols {:lg 6 :md 4 :sm 2 :xs 2 :xxs 1}
      :items 57
      :margin [0 0]
      ;; :cols 4
      :rowHeight 100
      }
     (map-indexed
      (fn [index item]
        ^{:key (str (:id item) "n")}
        [:div.img-container {:data-grid {:x (mod index 6) :y 0 :w 1 :h (+ (rand-int 3) 2)}}
         [:img {:src (:src item)}]
         ])
          resources/items)
     ;; [:div.img-container {:key "a" :data-grid {:x 0 :y 0 :w 1 :h 3}}
     ;;  [:img {:src "img/baby.jpg"}]]
     ;; [:div.img-container {:key "b" :data-grid {:x 1 :y 0 :w 1 :h 2}}
     ;;  [:img {:src "img/capucha.jpg"}]]
     ;; [:div.img-container {:key "c" :data-grid {:x 2 :y 0 :w 1 :h 3}}
     ;;  [:img {:src "img/cake.jpg"}]]
     ;; [:div.img-container {:key "d" :data-grid {:x 3 :y 0 :w 1 :h 2}}
     ;;  [:img {:src "img/cloak.jpg"}]]
     ])
  )

(defn Main
  []
  (let [items (transform-map resources/items)
        gallery (js/PhotoSwipe.
                 (dom/getElementByClass "pswp")
                 js/PhotoSwipeUI_Default
                 items
                 #js {:index 0})]
    (r/create-class
     {
      :component-did-mount #(d/log (generate-grid-layout))
      ;; :component-did-mount #(.init gallery)
      ;; :component-will-unmount #(.close gallery)
      :reagent-render
      (fn []
        [:div
         [Grid]]
        )})))
