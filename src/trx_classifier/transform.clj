(ns trx-classifier.transform
  (:require
   [clojure.string :as str]))

(defn usa-to-iso-date [date]
  (let [[month day year] (str/split date #"\/")]
    (str/join "-" [year month day])))

(defn amount-to-string [amount]
  (str amount))

(defn transform-date [date format]
  (cond
    (= "YYYY-MM-DD" format) date
    (= "MM/DD/YYYY" format) (usa-to-iso-date date)
    :else
    date))

(defn map-row
  "Maps CSV row to EDN map"
  [row headers]
  (zipmap headers row))

(defn transform-record
  [record map-to]
  (reduce (fn [acc [k v]]
            (assoc acc k (get record (keyword v)))) {} map-to))

(defn transform-values [record date-format]
  [(transform-date (get record :Date) date-format)
   (get record :Description)
   (amount-to-string (get record :Amount))])

(defn format-columns [columns]
  (mapv (fn [column] (keyword (str/replace column #" " "-"))) columns))

(defn transform-row [row config]
  (let [{:keys [columns map-to date-format]} config]
    (-> row
        (map-row (format-columns columns))
        (transform-record map-to)
        (transform-values date-format))))

(defn transform-data [data config]
  (map (fn [row] (transform-row row config)) data))
