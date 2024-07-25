(ns blog-tool.templates
  (:require
   [rum.core :as rum]))

(rum/defc my-comp [s]
  [:div s])

(rum/defc post [post])

(rum/defc post-link [post]
  [:a {:href ""}])

(rum/defc post-form []
  [:form {:action "/posts"}])


(rum/defc posts-table [posts]
  [:table
   [:thread
    [:tr
     [:th "ID"]]]
   (for [post posts]
     [:tr
      [:td ]])
   ])
