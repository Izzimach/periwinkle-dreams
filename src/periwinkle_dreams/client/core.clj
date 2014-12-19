(ns periwinkle-dreams.client.core
  (:require [environ.core :refer [env]]))

;;
;; Basically we want to start up the dev tools like figwheel and weasel
;; only in dev mode. So the variable :is-dev is set by leiningen (specifically lein-environ)
;; when doing a dev build, and we check that variable. So if you needed
;; to run dev stuff in your uberjar you could set it to true in that profile as well
;;

(defmacro start-dev-tools []
  (when (env :is-dev)
    `(fw/watch-and-reload {
                :on-jsload (fn [] "reloaded!")
                })))