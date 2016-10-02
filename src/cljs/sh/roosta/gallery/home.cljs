(ns sh.roosta.gallery.home
  (:require
   [cljsjs.photoswipe]
   [cljsjs.photoswipe-ui-default]
   [goog.dom :as dom]
   [sh.roosta.gallery.resources :as resources]

   [reagent.debug :as d]
   [reagent.core :as r]))

(defn init-home!
  []
  (let [pswp-el (dom/getElementByClass "pswp")
        items (clj->js (reduce (fn [acc item]
                        (conj acc {:src (:src item)
                                   :w (:w item)
                                   :h (:h item)})
                        ) [] resources/items))
        gallery (js/PhotoSwipe.
                 pswp-el
                 js/PhotoSwipeUI_Default
                 items
                 #js {:index 0})]
    (.init gallery)
    ))

(defn Main
  []
  (r/create-class
   {:component-did-mount #'init-home!
    :reagent-render
    (fn []
      [:div]
      )}))

#_(defn Main
  [app-state]

  ;; Root element of PhotoSwipe. Must have class pswp.
  [:div.pswp {:tabindex "-1" :role "dialog" :aria-hidden true}

   ;; Background of PhotoSwipe.
   ;; It's a separate element as animating opacity is faster than rgba().
   [:div.pswp__bg]

   ;; Slides wrapper with overflow:hidden.
   [:div.pswp__scroll-wrap

    ;; Container that holds slides.
    ;; PhotoSwipe keeps only 3 of them in the DOM to save memory.
    ;; Don't modify these 3 pswp__item elements, data is added later on.
    [:div.pswp__container
     [:div.pswp__item]
     [:div.pswp__item]
     [:div.pswp__item]]

    ;;Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed.
    [:div {:class "pswp__ui pswp__ui--hidden"}


     [:div {:class "pswp__top-bar"}]]

    ]])
