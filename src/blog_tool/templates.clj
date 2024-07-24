(ns blog-tool.templates
  (:require
   [rum.core :as rum]))

(rum/defc my-comp [s]
  [:div s])
