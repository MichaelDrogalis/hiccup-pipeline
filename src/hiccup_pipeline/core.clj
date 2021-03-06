(ns hiccup-pipeline.core
  (:require [hiccup.page :refer :all]))

(defn only-file-type [files file-matcher]
  (filter (fn [file] (not (nil? (re-seq file-matcher (.getName file))))) files))

(defn only-css-files [files]
  (only-file-type files #".+.css"))

(defn only-js-files [files]
  (only-file-type files #".+.js"))

(defn resolve-path [path]
  (let [resource-path "resources/public"]
    (if (= (seq resource-path) (take (count resource-path) path))
      (apply str (drop (count resource-path) path))
      path)))

(defn include-dir [dir filter-fn include-fn]
  (let [files (file-seq (clojure.java.io/file dir))
        filtered-files (filter-fn files)]
    (map include-fn (map resolve-path (map #(.getPath %) filtered-files)))))
  
(defn include-all-css
  ([] (include-all-css "resources/public/css"))
  ([dir]
     (include-dir dir only-css-files include-css)))

(defn include-all-js
  ([] (include-all-js "resources/public/js"))
  ([dir]
     (include-dir dir only-js-files include-js)))

