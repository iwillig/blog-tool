(ns blog-tool.routes
  (:require
   [blog-tool.templates :as templates]
   [blog-tool.rest-resources :as resources]
   [ring.middleware.json :as json]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.params :as ring.params]
   [ring.middleware.reload :refer [wrap-reload]]
   [rum.core :as rum]
   [reitit.ring :as ring]
   [liberator.core :as liberator])
  (:import (java.net URL)))

(defn routes
  []
  [["/"                   {:name :index
                           :handler resources/index-resource}]
   ["/admin"              {:name :admin
                           :handler resources/admin-resource}]

   ["/api/articles"             {:name :posts
                                 :handler resources/articles-resource}]
   ["/api/articles/:article-id" {:name :post
                                 :handler resources/article-resource}]

   ["/api/comments"             {:name :comments
                                 :handler resources/comments-resource}]
   ["/api/comments/:comment-id" {:name :comment
                                 :handler resources/comment-resource}]])

(defn router
  []
  (ring/router (routes)))

(def app
  (-> (router)
      (ring/ring-handler
       (ring/create-default-handler))
      (ring.params/wrap-params)
      (json/wrap-json-body {:keywords? true})))
