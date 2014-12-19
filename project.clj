(defproject org.clojars.haussman/periwinkle-dreams "0.1.0-SNAPSHOT"
  :description "Sample app using om-react-pixi"
  :url "https://github.com/Izzimach/periwinkle-dreams"
  :license {:name "Apache 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2277"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.7.1"]
                 [org.clojars.haussman/om-react-pixi "0.1.0-SNAPSHOT"]
                 [prismatic/schema "0.2.4"]
                 [prismatic/om-tools "0.3.6"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [compojure "1.3.1"]
                 [environ "1.0.0"]]

  :source-paths ["src"]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.10"]
            [lein-environ "1.0.0"]]


  :profiles {:dev {:dependencies [[figwheel "0.1.5-SNAPSHOT"]]
                   :source-paths ["src" "dev-src"]
                   :figwheel {
                              :http-server-root "public"
                              :server-port 8081
                              :ring-handler periwinkle-dreams.server.core/handler
                              }

                   :env {:is-dev true}

                   :plugins [[lein-figwheel "0.1.5-SNAPSHOT"]]}}

  :ring {:handler periwinkle-dreams.server.core/handler :port 8081 }

  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src/periwinkle_dreams/client"]
                        :compiler {
                                   :output-to "resources/public/scripts/out/periwinkle-dreams.js"
                                   :output-dir "resources/public/scripts/out"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "packed"
                        :source-paths ["src/periwinkle_dreams/client"]
                        :compiler {
                                   :output-to "resources/public/scripts/periwinkle-dreams.min.js"
                                   :output-dir "resources/public/scripts/out-min"
                                   :optimizations :advanced
                                   :preamble ["react_pixi/react-pixi.min.js"]
                                   :externs ["react_pixi/react-pixi.js"]
                                   :closure-warnings {:externs-validation :off
                                                      :non-standard-jsdoc :off}}}]})

