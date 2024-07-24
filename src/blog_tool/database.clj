(ns blog-tool.database
  (:require
   [honey.sql :as sql]
   [next.jdbc :as jdbc]))

(def db {:dbtype "sqlite" :dbname "database.db"})

(defonce data-source (jdbc/get-datasource db))


(defn execute!
  [data-source sql-map]
  (let [sql-string (sql/format sql-map)]
    (jdbc/execute! data-source
                   sql-string)))


(comment

  (execute!
   data-source
   {:select [:*]
    :from   [:comment]})

  )
