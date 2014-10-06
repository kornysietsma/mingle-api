(ns mingle-api.parse
  (:require [mingle-api.http :refer [mingle-query]]
    [clojure.data.xml :refer [parse-str] :as cdx]
            [clojure.zip :refer [xml-zip] :as cz]
            [clojure.data.zip :as cdz]
            [clojure.data.zip.xml
             :refer [xml-> xml1-> attr attr= text] :as cdzx]
            [clj-time.format :as time-f]))

(defn wiki-pages-path [project]
  (str "/api/v2/projects/" project "/wiki.xml"))

(defn page-parse-stats "parse a wiki page element returning page stats map"
  [element]
  (let [z (xml-zip element)]
    { :id (xml1-> z :id text)
      :identifier (xml1-> z :identifier text)
      :name (xml1-> z :name text)
      :created-at (time-f/parse (xml1-> z :created_at text))
      :updated-at (time-f/parse (xml1-> z :updated_at text))
      :modified-by (xml1-> z :modified_by :name text)
      :version (Integer/parseInt (xml1-> z :version text))
      :bytes (count (xml1-> z :content text))}))

(defn filter-page-elements [xml]
  (filter #(= :page (:tag %)) (:content xml)))

(defn wiki-pages [project parserfn]
  (->> project
       wiki-pages-path
       mingle-query
       parse-str
       filter-page-elements
       (map parserfn)))

