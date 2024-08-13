(ns blog-tool.components
  (:require
   [blog-tool.routes :as bt.routes]
   [next.jdbc :as jdbc]
   [ring.adapter.jetty :as jetty]
   [integrant.core :as ig]))

(def config
  {::database     {:dbtype "sqlite"
                   :dbname "database.db"}
   ::http-handler {:port 3000
                   :join? false
                   :database (ig/ref ::database)}})

(defmethod ig/init-key ::database
  [_ {:as config}]
  (jdbc/get-datasource config))

(defmethod ig/init-key ::http-handler
  [_ {:as config}]
  (jetty/run-jetty
   bt.routes/app
   config))

(defmethod ig/halt-key! ::http-handler
  [_ server]
  (when server
    (.stop server)))
