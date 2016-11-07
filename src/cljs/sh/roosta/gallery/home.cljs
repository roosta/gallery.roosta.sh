(ns sh.roosta.gallery.home
  (:require
   [cljsjs.photoswipe]
   [cljsjs.photoswipe-ui-default]
   [cljsjs.react-grid-layout]
   [goog.dom :as dom]
   [sh.roosta.gallery.resources :as resources]
   [reagent.debug :as d]
   [goog.events :as events]
   [reagent.core :as r]))

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
       (conj acc {:src (str "http://res.cloudinary.com/dvkodtgl9/image/upload" (:src item))
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
  [items cat]
  (if (= :all cat)
    items
    (filter #(= (:category %) cat) items)))

(defn generate-layout
  [filtered-items]
  (zipmap
   [:lg :md :sm]
   (mapv
    (fn [[k v]]
      (into []
            (map-indexed
             (fn [idx item]
               {:i (str (:id item) "n")
                :x (mod idx v)
                :y js/Infinity
                :w 1
                :h (:th item)})
             filtered-items)))
    cols)))


(defn Appbar
  [category menu-open?]
  (fn []
     [:div.app-bar.z2
      [:span.menu-item.flex-middle.active
       [:div "WORK"]]
      [:div.dropdown
       [:span.menu-item.flex-middle.filter {:on-click #(swap! menu-open? not)}
        [:div.pointer-none "FILTER" [:div.caret]]]
       [:ui.dropdown-menu {:class (if @menu-open? "menu-is-open" "")}
        [:li {:on-click #(reset! category :all)}
         [:span "ALL"]]
        [:li {:on-click #(reset! category :painting)}
         [:span "PAINTINGS"]]
        [:li {:on-click #(reset! category :drawing)}
         [:span "DRAWINGS"]]
        [:li {:on-click #(reset! category :photo)}
         [:span "PHOTOS"]]
        [:li {:on-click #(reset! category :pixel)}
         [:span "PIXEL"]]
        [:li {:on-click #(reset! category :design)}
         [:span "DESIGN"]]]]
      #_[:span.menu-item.flex-middle
       [:div "ABOUT"]]
      [:a.menu-item.flex-middle
       {:href "mailto:mail@roosta.sh"}
       [:div "CONTACT"]]
      [:span.flex-middle.title
       [:div "DANIEL BERG"]]]))

(defn Grid
  [category menu-open?]
  [ResponsiveGridLayout
   {:className "layout"
    :layouts (clj->js (generate-layout (get-filtered-items resources/items @category)))
    :isDraggable false
    :isResizable false
    :container-padding [0 60]
    :breakpoints {:lg 1200 :md 996 :sm 768}
    :cols (clj->js cols)
    :margin [0 0]
    :rowHeight 1}
   (map-indexed
    (fn [index item]
      ^{:key (str (:id item) "n")}
      [:div.image
       {:on-click
        #(do (reset! menu-open? false)
             (open-photoswipe (:id item)))
        :style
        {:background-image
         (str
          "url("
          "http://res.cloudinary.com/dvkodtgl9/image/upload/"
          "c_crop,h_"
          (:th item)
          ",w_"
          (:w item)
          (if (:gif item)
            (clojure.string/replace (:src item) #"gif" "png")
            (:src item))
          ")"
          )}}

       [:div.info.flex-middle
        [:div (str (:title item))]]
       ])
    (get-filtered-items resources/items @category))])

(defn Main
  []
  (let [category (r/atom :all)
        menu-open? (r/atom false)]
    (r/create-class
     {;; :component-will-mount #(d/log (clj->js layouts))
      ;; :component-did-mount #(d/log (= (dom/getElementByClass "app-bar")
      ;;                                 (dom/getElementByClass "layout")))
      ;; :component-will-unmount #(.dispose vsm)
      :reagent-render
      (fn []
        [:div {:on-click #(let [target (.. % -target)
                                menu (dom/getElementByClass "dropdown-menu")
                                btn (dom/getElementByClass "filter")]
                            (if (not= target btn)
                              (reset! menu-open? false)
                              ;; (d/log target)
                              )
                            )}
         [Appbar category menu-open?]
         [Grid category menu-open?]]
        )})))
