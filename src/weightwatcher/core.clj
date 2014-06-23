(ns weightwatcher.core
  (:use [clojure.java.io :only (reader)])
  (:require [clj-time.core :as time])
  (:require [clj-time.local :as local])
  (:require [clojure.string :as string]))

(defrecord Weight [weight date])

(defn parse-unix [stamp]
  (let [stamp (Integer. stamp)]
    (local/to-local-date-time (time/plus (time/date-time 1970) (time/secs stamp)))))

(defn parse-weight [ln]
  (let [ln (map string/trim (string/split ln #":"))]
  (apply ->Weight [(parse-unix (first ln)) (Float. (second ln))])))

(defn read-weights [path]
  (with-open [rdr (reader path)]
    (doall (map parse-weight (line-seq rdr)))))
