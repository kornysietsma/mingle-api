(defproject mingle-api "0.1.0-SNAPSHOT"
            :description "Querying ThoughtWorks Mingle APIs"
            :url "http://example.com/FIXME"
            :license {:name "Do What The Fuck You Want To Public License (WTFPL)"
                      :url "http://www.wtfpl.net/"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [clj-http "1.0.0"]
                           [pandect "0.3.4"]
                           [clj-time "0.8.0"]
                           [environ "1.0.0"]
                           [org.clojure/data.codec "0.1.0"]
                           [org.clojure/data.csv "0.1.2"]
                           [org.clojure/data.xml "0.0.8"]
                           [org.clojure/data.zip "0.1.1"]]
            :main mingle-api.core
)
