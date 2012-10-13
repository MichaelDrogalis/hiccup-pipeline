(ns hiccup-pipeline.core-test
  (:require [clojure.test :refer :all]
            [fs.core :as fs]
            [hiccup-pipeline.core :refer :all])
  (:import java.io.File
           java.net.URI))

(defn set-up-directory-structure [root extension]
  (fs/mkdir root)
  (fs/touch (str root File/separator (str "foo" extension)))
  (fs/touch (str root File/separator (str "bar" extension)))
  (fs/mkdir (str root File/separator "baz"))
  (fs/touch (str root File/separator "baz" File/separator (str "bat" extension))))

(defn tear-down-directory-structure [root]
  (fs/delete-dir root))

(deftest include-all-css-test
  (set-up-directory-structure "css" ".css")
  (is (= (into #{} (include-all-css "css"))
         #{(list [:link {:type "text/css", :href (URI. "css/foo.css"), :rel "stylesheet"}])
           (list [:link {:type "text/css", :href (URI. "css/bar.css"), :rel "stylesheet"}])
           (list [:link {:type "text/css", :href (URI. "css/baz/bat.css"), :rel "stylesheet"}])}))
  (tear-down-directory-structure "css"))

(deftest include-all-js-test
  (set-up-directory-structure "js" ".js")
  (is (= (into #{} (include-all-js "js"))
         #{(list [:script {:type "text/javascript", :src (URI. "js/foo.js")}])
           (list [:script {:type "text/javascript", :src (URI. "js/bar.js")}])
           (list [:script {:type "text/javascript", :src (URI. "js/baz/bat.js")}])}))
  (tear-down-directory-structure "js"))