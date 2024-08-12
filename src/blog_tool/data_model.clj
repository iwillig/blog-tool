(ns blog-tool.data-model)

(defn params
  [ctx]
  (get-in ctx [:requiest :params]))

(defn add-article-mailformed?
  [ctx]
  [true ctx])

(defn add-article
  [ctx]
  (do
    (println)
    (clojure.pprint/pprint
     (params ctx))
    (println)))

(defn update-article
  [ctx])

(defn delete-article
  [ctx])
