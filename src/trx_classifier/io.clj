(ns trx-classifier.io
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [trx-classifier.read-config :as cfg]
            [trx-classifier.transform :as transform]))

(def output-header ["Date", "Description", "Amount"])

(defn write-csv [filename data]
  (with-open [writer (io/writer (str filename ".csv"))]
    (csv/write-csv writer data)))

(defn assemble-csv [header data]
  (into [header] data))

(defn read-csv [file configs]
  (with-open [reader (io/reader file)]
    (doall
     (let [[headers & data] (csv/read-csv reader)]
       (->> (cfg/select-csv-format headers configs)
            (transform/transform-data data))))))

(defn parse-transaction-files [config-dir files]
  (let [configs (cfg/load-edn-from-path config-dir)]
    (->>
     (mapcat #(read-csv % configs) files)
     (assemble-csv output-header)
     (write-csv "output"))))
