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
    [GridLayout
     {:className "layout"
      ;; :layout layout
      :width 1200
      :margin [0 0]
      :cols 1200
      :rowHeight 1}
     [:div {:key "a" :data-grid {:x 0 :y 0 :w 411 :h 664}}
      [:img {:src "img/baby.jpg"}]]
     [:div {:key "b" :data-grid {:x 411 :y 0 :w 466 :h 475}}
      [:img {:src "img/capucha.jpg"}]]
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
