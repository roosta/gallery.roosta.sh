(ns sh.roosta.gallery.home
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt]]))

(def palette
  {:one "#FFE4D2"
   :two "#D6B596"
   :three "#DB9269"
   :four "#984E4F"
   :five "#2A2F33"
   :gray "#848484"
   :white "#FFE4D2"
   :black "#2b2b2b"})

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

  [:.pointer-none
   {:pointer-events "none"}]
  [:.app-bar
   {:position "fixed"
    :background-color (:black palette)
    :margin-right (px 20)
    :width "100%"
    :display "flex"
    :z-index 2
    :height (px 60)
    :left 0}]

  [:.menu-item
   {:padding-left (px 15)
    :padding-right (px 15)
    :font-family "Lato, sans-serif"
    :color (:white palette)}]

  [:.menu-item:hover
   {:background-color (:gray palette)
    :transition "all 200ms linear"}]

  [:.active
   {:color (:black palette)
    :height (px 60)
    :background-color (:white palette)}]

  [:.active:hover
   {:height (px 60)
    :background-color (:gray palette)}]

  [:.title
   {:font-size (px 24)
    :color "#FFE4D2"
    :font-family "Lato Light, sans-serif"
    :margin-right (px 20)
    :margin-left "auto"}]

  [:.flex-middle
   {:display "flex"
    :text-align "center"
    :align-items "center"
    :justify-content "center"}]

  [:.z1
   {:box-shadow "0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24)"}]

  [:.z2
   {:box-shadow "0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23)"}]

  [:.caret
   {:display "inline-block"
    :width 0
    :height 0
    :margin-left (px 2)
    :vertical-align "middle"
    :border-top "4px solid"
    :border-right "4px solid transparent"
    :border-left "4px solid transparent"}]

  [:.dropdown
   {:cursor "pointer"
    :display "flex"
    :position "relative"}]

  [:.dropdown-menu
   {:position "absolute"
    :top "100%"
    :left (px 0)
    :display "none"
    :min-width (px 160)
    :padding "5px 0"
    :margin "2px 0 0"
    :list-style "none"
    ;; :transition "all 200ms linear"
    :text-align "left"
    :background-color (:black palette)
    :border-radius (px 2)
    :z-index 1
    :background-clip "padding-box"}
   [:li
    [:span
     {:display "block"
      ;; :padding-left (px 15)
      :padding "3px 15px"
      :clear "both"
      :font-family "Lato, sans-serif"
      :text-decoration "none"
      :font-size (px 15)
      ;; :font-weight "normal"
      :color (:white palette)
      :white-space "nowrap"}
     [:&:hover
      :&:focus
      {:transition "all 100ms linear"
       :background-color (:gray palette)}]]]]
  [:.menu-is-open
   {:display "block"
    }])
