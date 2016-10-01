(ns sh.roosta.gallery.styles
  (:require [garden.core :as g]
            [sh.roosta.gallery.styles.app :as app]))

(defn get-all
  []
  (g/css app/style))
