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

(defn get-cols
  [width]
  (cond
     (>= width 1200) 6
     (and (< width 1200) (>= width 996)) 4
     (and (< width 996) (>= width 768)) 2
     (and (< width 768) (>= width 480)) 2
     :else 1
     ))

(defn Grid
  [state]
  (let [cols (:cols @state)]
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
        [:div.img-container {:data-grid {:x (mod index cols) :y 0 :w 1 :h (+ (rand-int 3) 2)}}
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
(defn init!
  [vsm state]
  (events/listen
   vsm
   (.-RESIZE events/EventType)
   (fn [e]
     (let [width (.-width (.getSize vsm))]
       (do
         (when (not= (:cols @state) (get-cols width))
           (swap! state assoc :cols (get-cols width))
           #_(d/log (:cols @state))
           ))))))

(defn Main
  []
  (let [
        items (transform-map resources/items)
        gallery (js/PhotoSwipe.
                 (dom/getElementByClass "pswp")
                 js/PhotoSwipeUI_Default
                 items
                 #js {:index 0})
        vsm (ViewportSizeMonitor.)
        state (r/atom {:cols (get-cols (.-width (.getSize vsm)))})]
    (r/create-class
     {:component-did-mount #(init! vsm state)
      :component-will-unmount #(.dispose vsm)
      :reagent-render
      (fn []
        [:div
         [Grid state]]
        )})))
