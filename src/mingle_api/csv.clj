(ns mingle-api.csv
  (:require
    [clojure.java.io :as io]
    [clojure.data.csv :refer [write-csv] :as csv]))

(def headers [:name :created-at :updated-at :modified-by :version :bytes])

(defn pages->csv [pages writer]
  (csv/write-csv writer
                 (cons (map name headers)
                       (for [page pages]
                         (for [key headers]
                           (get page key)))
                       )))

(defn pages->csvfile [pages filename]
  (with-open [out-file (io/writer filename)]
    (pages->csv pages out-file)))

