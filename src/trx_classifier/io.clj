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

(defn read-csv [file config-path output-file]
  (with-open [reader (io/reader file)]
    (doall
     (let [[headers & data] (csv/read-csv reader)]
       (->> (cfg/load-edn-from-path config-path)
            (cfg/select-csv-format headers)
            (transform/transform-data data)
            (assemble-csv output-header)
            (write-csv output-file))))))
