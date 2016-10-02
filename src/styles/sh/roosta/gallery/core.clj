(ns sh.roosta.gallery.core
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]))

;; Change defstylesheet to defstyles.
(defstyles screen
  [:body
   {:background-color "white"}])
