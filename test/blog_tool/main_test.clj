(ns blog-tool.main-test
  (:require
   [clojure.test :refer [deftest is]]
   [blog-tool.routes :as routes]
   [matcher-combinators.test]
   [ring.mock.request :as mock]
   #_[matcher-combinators.matchers :as m]))

(defn test-request
  [& {:keys [method uri params] :or {method :get
                                     params {}}}]

  (routes/app
   (mock/request
    method uri params)))

(deftest test-index

  (is (match?
       {:status 200
        :body "pong"}
       (test-request :uri "/"))))


(deftest test-article-create

  (is (match?
       {:status 500
        :body ""}

       (test-request :uri "/api/articles"
                     :method :post
                     :params {}))))
