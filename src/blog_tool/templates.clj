(ns blog-tool.templates
  (:require
   [rum.core :as rum]))

(rum/defc my-comp [s]
  [:div s])

(rum/defc article [post])

(rum/defc article-link [post]
  [:a {:href ""}])

(rum/defc article-form []
  [:form {:action "/posts" :method "post"}
   ])


(rum/defc article-table [articles]
  [:table
   [:thread
    [:tr
     [:th "ID"]]]
   (for [article articles]
     [:tr
      [:td ]])
   ])
