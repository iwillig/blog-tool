(ns blog-tool.routes
  (:require
   [blog-tool.templates :as templates]
   [ring.middleware.json :as json]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.params :as ring.params]
   [ring.middleware.reload :refer [wrap-reload]]
   [rum.core :as rum]
   [reitit.ring :as ring]
   [liberator.core :as liberator]))

(def available-media-type
  ["text/html"])

(def index
  (liberator/resource
   :available-media-types available-media-type
   :handle-ok (fn [_request]
                (rum/render-static-markup
                 (templates/my-comp "hello")))))

(def admin
  (liberator/resource
   :available-media-types available-media-type
   :handle-ok (fn [ctx]
                (rum/render-static-markup
                 [:html {:lang "en"}
                  [:head
                   [:script {:src "https://unpkg.com/htmx.org@2.0.1"}]]
                  [:body
                   [:div [:h1 "blog tools"]
                    (templates/article-form ctx)]]]))))

(def articles
  (liberator/resource
   :allowed-methods [:post :get]
   :available-media-types available-media-type

   ;; :malformed? (fn [_ctx]
   ;;               false)

   :post! (fn [ctx]

            {})

   :handle-created (fn [ctx]
                     "created-post")

   :handle-ok (fn [ctx]
                "list-post")))

(def article
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

(def comments nil)

(def comment nil)


(defn routes
  []
  [["/"                   {:name :index :handler index}]
   ["/admin"              {:name :admin :handler admin}]

   ["/api/articles"             {:name :posts :handler articles}]
   ["/api/articles/:article-id" {:name :post  :handler article}]

   ["/api/comments"             {:name :comments :handler comments}]
   ["/api/comments/:comment-id" {:name :comment :handler comment}]])

(defn router
  []
  (ring/router (routes)))

(def app
  (-> (router)
      (ring/ring-handler
       (ring/create-default-handler))
      (ring.params/wrap-params)
      (json/wrap-json-body {:keywords? true})))


(defn run-dev [& _args]
  (run-jetty

   (wrap-reload #'app)
   {:port 3000}))
