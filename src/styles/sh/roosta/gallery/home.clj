(ns sh.roosta.gallery.home
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt]]))

(defstyles screen
  [:.img-container
   {:text-align "center"
    :display "flex"
    :align-items "center"
    :justify-content "center"
    :overflow "hidden"
    }
   [:img
    {
     ;; :position "relative"
     ;; :top "100%"
     ;; :left "100%"
     ;; :margin-top "-200%"
     ;; :margin-left "-200%"
     }]
   ]
  )
