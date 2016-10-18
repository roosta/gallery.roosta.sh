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

(def cols {:lg 6 :md 4 :sm 2})

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

(def photoswipe-map (transform-map resources/items))

(defn open-photoswipe
  [index]
  (let [pswp (js/PhotoSwipe.
              (dom/getElementByClass "pswp")
              js/PhotoSwipeUI_Default
              photoswipe-map
              #js {:index index})]
    (.init pswp)
    ))


(defn get-filtered-items
  [cat]
  (if (= :all cat)
    resources/items
    (keep #(when (= (:category %) cat) %) resources/items))
  )

(defn generate-layout
  [cat]
  (zipmap
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
       (get-filtered-items cat)))
    cols)))

(defn Appbar
  [state]
  (let [menu-atom (r/atom false)]
    (fn []
      [:div.app-bar.z2
       [:span.menu-item.flex-middle.active
        [:div "WORK"]]
       [:div.dropdown
        [:span.menu-item.flex-middle {:on-click #(swap! menu-atom not)}
         [:div "FILTER" [:div.caret]]]
        [:ui.dropdown-menu {:class (if @menu-atom "menu-is-open" "")}
         [:li {:on-click #(swap! state assoc :category :all)}
          [:span "ALL"]]
         [:li {:on-click #(swap! state assoc :category :painting)}
          [:span "PAINTINGS"]]
         [:li {:on-click #(swap! state assoc :category :drawing)}
          [:span "DRAWINGS"]]
         [:li {:on-click #(swap! state assoc :category :photo)}
          [:span "PHOTOS"]]
         [:li {:on-click #(swap! state assoc :category :pixel)}
          [:span "PIXEL"]]
         [:li {:on-click #(swap! state assoc :category :design)}
          [:span "DESIGN"]]]]
       [:span.menu-item.flex-middle
        [:div "ABOUT"]]
       [:span.menu-item.flex-middle
        [:div "CONTACT"]]
       [:span.flex-middle.title
        [:div "DANIEL BERG"]]])))

(defn Grid
  [state]
  [ResponsiveGridLayout
   {:className "layout"
    :layouts (clj->js (generate-layout (:category @state)))
    :isDraggable false
    :isResizable false
    :container-padding [0 60]
    :breakpoints {:lg 1200 :md 996 :sm 768}
    :cols (clj->js cols)
    :items 57
    :margin [0 0]
    :rowHeight 100}
   (map-indexed
    (fn [index item]
      ^{:key (str (:id item) "n")}
      [:div.img-container.flex-middle {:on-click #(open-photoswipe index)}
       [:img {:src (:src item) :style {:width (:w item) :height (:h item)}}]
       [:div.info.flex-middle
        [:div (str (:title item))]]
       ])
    (get-filtered-items (:category @state)))])

(defn Main
  []
  (let [state (r/atom {:category :all})]
    (r/create-class
     {
      ;; :component-will-mount #(d/log (clj->js layouts))
      ;; :component-did-mount #(init! vsm state)
      ;; :component-will-unmount #(.dispose vsm)
      :reagent-render
      (fn []
        [:div
         [Appbar state]
         [Grid state]]
        )})))
