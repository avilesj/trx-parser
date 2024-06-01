(ns trx-classifier.core
  (:require
   [trx-classifier.io :as io])
  (:gen-class))

(defn parse-args [args]
  (if (seq args)
    (doseq [arg args]
      (println arg))
    (println "No args provided")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[file config-dir] (parse-args args)]
    (io/read-csv file config-dir ".")))



