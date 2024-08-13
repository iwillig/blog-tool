(ns dev
  (:require
   [kaocha.repl :as k]
   [integrant.core :as ig]
   [integrant.repl :as ig.repl :refer [go]]
   [blog-tool.routes :as routes]
   [blog-tool.components :as bt.components]
   [clj-commons.pretty.repl :as pretty-repl]
   [clj-reload.core :as reload]))

(pretty-repl/install-pretty-exceptions)

(integrant.repl/set-prep!
 #(ig/expand bt.components/config))

(reload/init
 {:dirs ["src" "dev" "test"]})

(comment

  (routes/run-dev)

  (reload/reload)
  ;; Re compile clojure

  ;; Run the main test
  (k/run 'test.namespace))
