(ns periwinkle-dreams.client.core
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [periwinkle-dreams.client.core :refer [start-dev-tools]])
  (:require [goog.events :as events]
            [om.core :as om :include-macros true]
            [schema.core :as schema]
            [om-tools.core :as omtools :include-macros true]
            [omreactpixi.abbrev :as pixi]
            [clojure.string :as string]
            [cljs.core.async :as async :refer [>! <! put!]]
            [figwheel.client :as fw]))


(defn assetpath [name] (str "../assets/" name))

(enable-console-print!)

(omtools/defcomponentk simplestage [[:data width height xposition] owner]
  (render [_]
          (pixi/stage
            {:width width :height height}
            (pixi/tilingsprite {:image (assetpath "bg_castle.png") :width width :height height :key 1})
            (pixi/text {:text "Vector text" :x xposition :y 10 :style #js {:font "40px Times"} :anchor (PIXI.Point. 0.5 0) :key 3})))
  (display-name [_] "SimpleStage"))


(defonce appstate (atom {:width 0 :height 0 :xposition 100}))

(defn startperiwinkle []
  (let [inset #(- % 16)
        w (-> js/window .-innerWidth inset)
        h (-> js/window .-innerHeight inset)]
    (swap! appstate #(-> % (assoc :width w) (assoc :height h)))
    (om/root simplestage appstate
             {:target (.getElementById js/document "my-app")})))


(startperiwinkle)

;; load up figwheel if in a dev environment

(start-dev-tools)