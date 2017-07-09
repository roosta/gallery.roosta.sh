(ns sh.roosta.gallery.home
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt]]))

(def srcery {:black          {:hex  "#1C1B19"
                              :rgb  [28,  27,  25]
                              :code 0}
             :red            {:hex  "#FF3128"
                              :rgb  [255, 49,  40]
                              :code 1}
             :green          {:hex  "#519F50"
                              :rgb  [81,  159, 80]
                              :code 2}
             :yellow         {:hex  "#FBB829"
                              :rgb  [251, 184, 41]
                              :code 3}
             :blue           {:hex  "#5573A3"
                              :rgb  [85,  115, 163]
                              :code 4}
             :magenta        {:hex  "#E02C6D"
                              :rgb  [224, 44,  109]
                              :code 5}
             :cyan           {:hex  "#0AAEB3"
                              :rgb  [10, 174, 179]
                              :code 6}
             :white          {:hex      "#918175"
                              :rgb      [145, 129, 117]
                              :code     7 }
             :bright-black   {:hex  "#2D2B28"
                              :rgb  [45,  43,  40]
                              :code 8}
             :bright-red     {:hex  "#F75341"
                              :rgb  [247, 83, 65]
                              :code 9}
             :bright-green   {:hex  "#F75341"
                              :rgb  [247, 83, 65]
                              :code 10}
             :bright-yellow  {:hex  "#FED06E"
                              :rgb  [254, 208, 110]
                              :code 11}
             :bright-blue    {:hex  "#8EB2F7"
                              :rgb  [142, 178, 247]
                              :code 12}
             :bright-magenta {:hex  "#E35682"
                              :rgb  [227, 86,  130]
                              :code 13}
             :bright-cyan    {:hex  "#53FDE9"
                              :rgb  [83, 253, 233]
                              :code 14}
             :bright-white   {:hex      "#FCE8C3"
                              :rgb      [252, 232, 195]
                              :code     15}})

(defstyles screen

  [:.image
   {:background-position "center"
    :background-repeat "no-repeat"}]

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
    :background-color (-> srcery :bright-black :hex)
    :margin-right (px 20)
    :width "100%"
    :display "flex"
    :z-index 2
    :height (px 44)
    :left 0}]

  [:.menu-item
   {:padding-left (px 15)
    :padding-right (px 15)
    :text-decoration "none"
    :font-family "Lato, sans-serif"
    :color (-> srcery :bright-white :hex)}]

  [:.menu-item:hover
   {:background-color (-> srcery :white :hex)
    :transition "all 200ms linear"}]

  [:.active
   {:color (-> srcery :black :hex)
    :height (px 44)
    :background-color (-> srcery :bright-white :hex)}]

  [:.active:hover
   {:height (px 44)
    :background-color (-> srcery :white :hex)}]

  [:.title
   {:font-size (px 16)
    :color "#FFE4D2"
    :font-family "Lato, sans-serif"
    :margin-right (px 16)
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
    :background-color (-> srcery :black :hex)
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
      :font-size (px 16)
      ;; :font-weight "normal"
      :color (-> srcery :bright-white :hex)
      :white-space "nowrap"}
     [:&:hover
      :&:focus
      {:transition "all 100ms linear"
       :background-color (-> srcery :white :hex)}]]]]
  [:.menu-is-open
   {:display "block"}]
  [:.pswp__bg {:background-color (-> srcery :black :hex)}]
  [:.pswp__img {:background-color (-> srcery :black :hex)}]
  (at-media
   {:max-width (px 450)}
   [:.title
    {:font-size (px 16)}]
   [:.menu-item
    {:font-size (px 11)}]
   )
  )
