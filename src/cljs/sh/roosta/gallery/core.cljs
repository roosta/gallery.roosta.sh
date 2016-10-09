(ns sh.roosta.gallery.core
  (:import goog.history.Html5History)
  (:require
   [reagent.core :as r]
   [sh.roosta.gallery.home :as home]
   [goog.events :as events]
   [secretary.core :as secretary :refer-macros [defroute]]
   [goog.object :as gobj]
   [goog.history.EventType :as EventType]))

(enable-console-print!)

(defonce app-state (atom {:page :home}))

(defroute "/" []
  (swap! app-state assoc :page :home))

(defmulti current-page #(@app-state :page))

(defmethod current-page :home
  []
  [home/Main app-state])

(defmethod current-page :not-found
  []
  [:div "Not found"])

(defonce history
  (doto (Html5History.)
    (.setUseFragment true)
    (events/listen EventType/NAVIGATE
                   (fn [x]
                     (secretary/dispatch! (.-token x))
                     (.log js/console "Navigate! " (.-token x))))
    (.setEnabled true)))

(defn App
  []
  (r/create-class
   {:reagent-render
    (fn []
      [:div
       [current-page]]
      )}))

(r/render-component [App] (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
