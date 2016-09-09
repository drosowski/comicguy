(ns comicbot.comics
  (:require [clojure.xml :as xml])
  (:require [clojure.zip :as zip])
  (:require [clojure.data.zip.xml :as zip-xml])
  (:require [hickory.core :as html])
  (:require [hickory.select :as s])
  (:require [clj-http.client :as http]))

(defn extract-link [feed]
  (System/setProperty "http.agent" "curl/7.43.0")
  (let [item (-> feed
                 xml/parse 
                 zip/xml-zip
                 (zip-xml/xml1-> :channel :item :description) 
                 zip-xml/text 
                 html/parse
                 html/as-hickory
                 )]
    (:src (:attrs (peek (s/select (s/tag :img) item)))))
)

(defn strip [comic]
  (case comic
    "#cstrip" (extract-link "http://www.comicsyndicate.org/Feed/CommitStrip")
    "#dilbert" (extract-link "http://www.comicsyndicate.org/Feed/Dilbert"))
)
