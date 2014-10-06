(ns mingle-api.config
  (:require [environ.core :refer [env]]))

(defn get-env-or-fail [key]
  (if-let [val (env key)]
    val
    (throw (Exception. (str "missing config key:" key)))))

(defn mingle-user []
  (get-env-or-fail :mingle-user))

(defn mingle-host []
  (get-env-or-fail :mingle-host))

(defn mingle-key []
  (get-env-or-fail :mingle-key))