(ns blog-tool.templates
  (:require
   [rum.core :as rum]))

(rum/defc my-comp [s]
  [:div s])

(rum/defc article [post])

(rum/defc article-link [post]
  [:a {:href ""}])


(rum/defc layout [ctx content]
  [:html {:lang "en"}
   [:head
    [:link
     {:rel "stylesheet"
      :href "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"}]
    [:script {:src "https://unpkg.com/htmx.org@2.0.1"}]]

   [:body
    [:div..container
     [:div [:h1 "Blog Tool"]
      (content ctx)]]]])




(rum/defc article-form [ctx]
  [:form {:action "/api/articles" :method "post"}
   [:fieldset
    [:p
     [:label {:for "name"} "Title"]
     [:input {:type "text" :name "title"}]]

    [:p
     [:label {:for "content"} "Content"]
     [:textarea {:name "content"}]]

    [:button "Save"]]])


(rum/defc article-table [articles]
  [:table
   [:thread
    [:tr
     [:th "ID"]]]
   (for [article articles]
     [:tr
      [:td ]])
   ])
