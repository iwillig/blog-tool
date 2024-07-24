(ns blog-tool.routes
  (:require
   [ring.middleware.json :as json]
   [ring.middleware.params :as ring.params]
   [reitit.ring :as ring]
   [liberator.core :as liberator]))

(def available-media-type
  ["text/html"])

(def ping
  (liberator/resource
   :available-media-types available-media-type
   :handle-ok "pong"))

(defn routes
  []
  [["/" ping]])


(defn router
  []
  (ring/router (routes)))


(def app
  (-> (router)
      (ring/ring-handler)
      (ring.params/wrap-params)
      (json/wrap-json-body {:keywords? true})))
