(ns trx-classifier.core
  (:require
   [trx-classifier.io :as io])
  (:gen-class))

(defn parse-args [args]
  (if (seq args)
    (let [[[dir & files]] (into [] args)]
      [dir files])
    (println "No args provided")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[config-dir files] (parse-args args)]
    (io/read-csv files config-dir ".")))



