(ns sh.roosta.gallery.home
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt]]))

(defstyles screen
  [:.img-container
   {:text-align "center"
    :position "relative"
    :display "flex"
    :align-items "center"
    :justify-content "center"
    :overflow "hidden"
    }
   [:img
    ]
   ]
  ;; [:.img-container:hover]
  [:.info
   {:display "flex"
    :text-align "center"
    :align-items "center"
    :justify-content "center"
    :color "white"
    :background-color "black"
    :top 0
    :left 0
    :width "100%"
    :height "100%"
    :position "absolute"
    :opacity 0}]
  [:.info:hover
   {:transition "opacity 200ms linear"
    :opacity 0.6}]
  )
