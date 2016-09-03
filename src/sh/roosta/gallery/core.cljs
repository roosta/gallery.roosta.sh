(ns sh.roosta.gallery.core
  (:require
   [garden.core :as g]
   [reagent.core :as r :refer [atom]]
   [garden.units :as u]
   [goog.style :as gs]
   [garden.color :as color :refer [hsl rgb]]
   [garden.stylesheet :as stylesheet]
   ))

(def files
  [{:id 1 :name "Sea of sand" :file ""}])

(enable-console-print!)

(def style
  (g/css
   [:body
    {:color (rgb 255 255 255)
     :background-color (rgb 0 0 0)}]

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
      "col 1"
      ]
     [:div.col-xs.4
      "col 2"
      ]
     [:div.col-xs.4
      "col 3"

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
