(ns trx-classifier.core
  (:require
   [trx-classifier.io :as io]
   [clojure.java.io :as jio])
  (:gen-class))
(defn list-files [dir]
  (let [files (file-seq (jio/file dir))
        [_ & file-paths] (mapv #(.getAbsolutePath %) files)]
    file-paths))

(defn parse-args [args]
  (if (seq args)
    (let [[config-dir trx-dir] (into [] args)]
      [config-dir (list-files trx-dir)])
    (println "No args provided")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[config-dir files] (parse-args args)]
    (io/parse-transaction-files config-dir files)))

