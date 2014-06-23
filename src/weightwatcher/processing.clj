(ns weightwatcher.processing
  (:use weightwatcher.core)
  (:use incanter.core)
  (:use [incanter.stats :only (median)])
  (:require [clj-time.core :as t])
  (:require [clj-time.coerce :as c]))
  

(defn seq-deltas [col] (map #(apply (fn [x y] (- y x)) %1) (partition 2 1 col)))

(defn seq-deltas-by [k col] 
  (seq-deltas (map k col)))

(defn average [lst] (/ (reduce + lst) (count lst)))
(defn moving-average [lst window] (map average (partition window 3 lst)))

;; Test Functions Here ;;
(def weight (read-weights "/Users/Nick/Dropbox/weightwatcher/weight.weights"))

(def weight-deltas
  (seq-deltas-by :weight weight))

(defn midnight-of-date-time [date]
  (apply t/date-midnight (map #(% date) [t/year t/month t/day])))

(defn midnight-to-midnight-interval [date]
  (let [date (midnight-of-date-time date)]
    (t/interval date (t/plus date (t/days 1)))))

(defn create-weight-col [col item]
  (let [weight (:weight item)
        date (-> item :date midnight-of-date-time)] 
    (if (contains? col date) 
      (assoc-in col [date] (cons weight (get col date)))
      (assoc-in col [date] [weight]))))

(def single-weight
  (map #(apply ->Weight [(-> % val average) (key %)])
    (reduce create-weight-col {} weight)))

(comment (map (fn [n] (weighting-function (-> n t/days t/ago))) (range 1 14)) ;Weighting Test)
