(ns blog-tool.database
  (:require
   [honey.sql :as sql]
   [next.jdbc :as jdbc]))

(def db {:dbtype "sqlite" :dbname "database.db"})

(def data-source (jdbc/get-datasource db))


(defn execute!
  [data-source sql-map]
  (let [sql-string (sql/format sql-map)]
    (jdbc/execute! data-source
                   sql-string)))

(defn find-tags
  [])

(defn find-posts
  [])

(defn find-comments
  [])


(comment

  (execute!
   data-source
   {:select [:*]
    :from   [:comment]})


  (execute!
   data-source
   {:select [:*]
    :from   [:post]})

  )
