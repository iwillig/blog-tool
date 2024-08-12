(ns dev
  (:require
   [kaocha.repl :as k]
   [integrant.core :as ig]
   [integrant.repl :as ig.repl]
   [blog-tool.routes :as routes]
   [clj-commons.pretty.repl :as pretty-repl]
   [clj-reload.core :as reload]))

(pretty-repl/install-pretty-exceptions)

(reload/init
 {:dirs ["src" "dev" "test"]})

(comment

  (routes/run-dev)

  (reload/reload)
  ;; Re compile clojure

  ;; Run the main test
  (k/run 'test.namespace))
