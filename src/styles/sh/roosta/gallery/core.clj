(ns sh.roosta.gallery.core
  (:require [garden.def :refer [defstylesheet defstyles]]
            [sh.roosta.gallery.home :as home]
            [garden.units :refer [px]]))

;; Change defstylesheet to defstyles.
(defstyles screen
  [:body
   {:background-color "#655643"}]
  home/screen)
