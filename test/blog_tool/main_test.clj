(ns blog-tool.main-test
  (:require
   [clojure.test :refer [deftest is]]
   [matcher-combinators.test]
   [matcher-combinators.matchers :as m]))

(deftest test-okay
  (is (match? false true)))