(ns dev
  (:require
   [kaocha.repl :as k]
   [clj-commons.pretty.repl :as pretty-repl]
   [clj-reload.core :as reload]))

(pretty-repl/install-pretty-exceptions)

(reload/init
  {:dirs ["src" "dev" "test"]})


(comment

  (reload/reload)
  ;; Re compile clojure

  ;; Run the main test
  (k/run 'test.namespace)



  )
