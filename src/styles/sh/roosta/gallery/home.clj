(ns sh.roosta.gallery.home
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt]]))

(defstyles screen
  [:.img-container
   {:position "relative"
    :display "flex"
    :overflow "hidden"}]
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
    :background-color "#BF4D28"
    ;; :padding-left (px 20)
    :padding-right (px 20)
    :width "100%"
    :display "flex"
    :align-items "center"
    :z-index 2
    :height (px 80)
    :left 0}
   [:span
    {:margin-left (px 20)
     :font-family "Lato, sans-serif"
     :color "white"}]
   [:.active
    {:border-bottom "1px solid white"
     :padding-bottom "3px"}]
   [:.daniel
    {:font-size (px 24)
     :font-family "Lato Heavy, sans-serif"
     :margin-right (px 20)
     :margin-left "auto"
     }]]
  [:.flex-middle
   {:display "flex"
    :text-align "center"
    :align-items "center"
    :justify-content "center"}]
  [:.z1
   {:box-shadow "0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23)"}
   #_{:box-shadow "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)"}]
  )
