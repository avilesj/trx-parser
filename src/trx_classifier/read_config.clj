(ns trx-classifier.read-config
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.edn :as edn]))

(defn select-csv-format [csv-headers edn-configs]
  (let [csv-headers-keyworded (mapv (fn [header] (keyword (str/trim header))) csv-headers)]
    (first (filter #(= csv-headers-keyworded (get % :columns)) edn-configs))))

(defn read-edn [filename]
  (try
    (with-open [reader (io/reader filename)]
      (edn/read (java.io.PushbackReader. reader)))
    (catch java.io.IOException e
      (printf "Couldn't open '%s': %s\n" filename (.getMessage e)))))

(defn load-edn-from-path [path]
  (let [directory (io/file path)]
    (->> directory
         (file-seq)
         (filter #(re-matches #".*\.edn" (.getName %)))
         (mapv #(read-edn (.getPath %))))))
