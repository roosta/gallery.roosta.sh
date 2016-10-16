(ns sh.roosta.gallery.home
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt]]))

(defstyles screen
  [:.img-container
   {:position "relative"
    :display "flex"
    :overflow "hidden"
    }]
  [:.info
   {:color "white"
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
  [:.app-bar
   {:position "fixed"
    :background-color "white"
    :width "100%"
    :display "flex"
    :align-items "center"
    :z-index 2
    :height (px 100)
    :left 0}]
  [:.flex-middle
   {:display "flex"
    :text-align "center"
    :align-items "center"
    :justify-content "center"}]
  )
