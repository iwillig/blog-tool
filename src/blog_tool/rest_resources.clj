(ns blog-tool.rest-resources
  (:require
   [rum.core :as rum]
   [blog-tool.data-model :as data-model]
   [blog-tool.templates :as templates]
   [liberator.core :as liberator])
  (:import (java.net URL)))

(def available-media-type
  ["text/html"])

(def index-resource
  (liberator/resource
   :available-media-types available-media-type
   :handle-ok (fn [_request]
                (rum/render-static-markup
                 (templates/my-comp "hello")))))

(def admin-resource
  (liberator/resource
   :available-media-types available-media-type
   :handle-ok (fn [ctx]
                (rum/render-static-markup
                 (templates/layout
                  ctx
                  templates/article-form)))))


(def articles-resource
  (liberator/resource
   :allowed-methods [:post :get]
   :available-media-types available-media-type

   :malformed?
   data-model/add-article-mailformed?
   :post!
   data-model/add-article

   :handle-created (fn [ctx]
                     "created-post")
   :post-redirect? true

   :location (fn [ctx]
               (URL. "http://localhost:3000/articles"))

   :handle-ok (fn [ctx]
                "list-post")))

(def article-resource
  (liberator/resource

   :allowed-methods [:get :patch :delete]

   :respond-with-entity? true
   :can-put-to-missing? false

   :available-media-types available-media-type

   :patch-content-types available-media-type

   :malformed? (fn [_ctx]
                 false)

   :exists? (fn [ctx]
              [true ctx])

   :handle-not-found (fn [_] "not-found")

   :patch! (fn [ctx]
             ctx)

   :handle-ok "post"

   ))

(def comments-resource
  (liberator/resource))

(def comment-resource
  (liberator/resource))
