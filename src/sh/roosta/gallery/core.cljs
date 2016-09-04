(ns sh.roosta.gallery.core
  (:require
   [garden.core :as g]
   [reagent.core :as r :refer [atom]]
   [garden.units :as u]
   [goog.style :as gs]
   [sh.roosta.gallery.resources :as resources]
   [garden.color :as color :refer [hsl rgb]]
   [garden.stylesheet :as stylesheet]
   ))

(enable-console-print!)

(def style
  (g/css
   [:body
    {:color (rgb 255 255 255)
     :background-color (rgb 0 0 0)}
    ]
   [:.img-container {:width (u/px 50)
                     :height (u/px 50)}
    [:img {:max-height "100%"
           :max-width "100%"}]]

   ))


;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"}))

(defn App []
  [:div.container
   [:div.row.center-xs
    [:h4 "Gallery"]
    ]
   [:div.row.middle-xs.center-xs
    [:div.col-xs.4
     (map
      (fn [item]
        ^{:key (:id item)}
        [:span.row.end-xs (:title item)])
      resources/items)
      ]
     [:div.col-xs.4
      #_(map
       (fn [item]
         ^{:key (:id item)}
         [:div.img-container [:img {:src (str "/img/" (:file item))}]])
       resources/items)
      ]
     [:div.col-xs.4
      #_(map
       (fn [item]
         ^{:key (:id item)}
         [:span.row (:desc item)]
         )
       resources/items)

      ]]

   ]
  )

(r/render-component [App] (. js/document (getElementById "app")))
(gs/installStyles style)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
