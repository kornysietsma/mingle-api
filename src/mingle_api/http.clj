(ns mingle-api.http
  (:require
    [mingle-api.config :as c]
    [pandect.core :as pandect]
    [clj-http.client :as client]
    [clj-time.core :as time]
    [clj-time.format :as time-f]
    [clojure.data.codec.base64 :as b64]))

(defn format-date [d]
  (time-f/unparse (time-f/formatters :rfc822) d))

(defn base64 [bytes]
  (String. (b64/encode bytes) "UTF-8"))

(defn auth-string [content-type content-md5 path request-date user key]
  (let [
        payload (str content-type "," content-md5 "," path "," request-date)
        auth-hash (base64 (pandect/sha1-hmac-bytes payload key))]
    (str "APIAuth " user ":" auth-hash)))

(defn mingle-query [path]
  (let [request-date (format-date (time/now))
        content-type "application/xml"
        content-md5 (base64 (pandect/md5-bytes ""))
        auth-header (auth-string content-type content-md5 path request-date (c/mingle-user) (c/mingle-key))
        uri (str "https://" (c/mingle-host) path)]
    (:body (client/get uri
                       {:headers      {"Date"          request-date
                                       "Content-MD5"   content-md5
                                       "Authorization" auth-header}
                        :content-type content-type}))))