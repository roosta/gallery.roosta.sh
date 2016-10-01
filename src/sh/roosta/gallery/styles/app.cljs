(ns sh.roosta.gallery.styles.app
  (:require [garden.core :as g]
            [garden.units :as u]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :as stylesheet]))

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
