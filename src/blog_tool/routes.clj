(ns blog-tool.routes
  (:require
   [blog-tool.templates :as templates]
   [ring.middleware.json :as json]
   [ring.middleware.params :as ring.params]
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

(defn routes
  []
  [["/" index]])


(defn router
  []
  (ring/router (routes)))


(def app
  (-> (router)
      (ring/ring-handler)
      (ring.params/wrap-params)
      (json/wrap-json-body {:keywords? true})))
