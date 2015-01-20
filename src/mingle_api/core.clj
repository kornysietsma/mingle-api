(ns mingle-api.core
  (:require [mingle-api.parse :as parse]
            [mingle-api.http :as http]
            [mingle-api.csv :as csv])
  (:gen-class))

(defn -main
  [& args]
  (if-not (= 1 (count args))
    (println "Please specify a project to dump it's wiki data. "
             "Also note you need environment vars for MINGLE_KEY MINGLE_USER and MINGLE_HOST.")
    (csv/pages->csv (parse/wiki-pages (first args) parse/page-parse-stats) *out*)))