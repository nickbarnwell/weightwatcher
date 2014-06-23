(ns weightwatcher.chart
  (:use (incanter core charts stats))
  (:require [weightwatcher.processing :as wwp])
  (:require [clj-time.coerce :as c]))

(def weight-x (map (comp c/to-long :date) wwp/weight))
(def weight-y (map :weight wwp/weight))

(defn individual-series-for-weight-col [col]
  {:x (map (comp c/to-long :date) col) :y (map :weight col)})

(defn weight-plot [col title]
   (let [series (individual-series-for-weight-col col)]
     (time-series-plot (:x series) (:y series) :points true :title title)))

(def all-weight-plot
   (weight-plot wwp/weight "All Weight Plot"))

(def median-weight-plot
   (weight-plot wwp/single-weight "Median Weight Plot"))

;(view median-weight-plot)
(view all-weight-plot)
