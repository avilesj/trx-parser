(ns trx-classifier.core
  (:require
   [trx-classifier.io :as io])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (io/read-csv "resources/transactions.csv" "resources/config" "resources/output"))



