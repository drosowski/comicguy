(defproject comicbot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.8.0"], 
    [org.clojure/data.zip "0.1.2"],
    [org.clojars.scsibug/feedparser-clj "0.5.0"],
    [hickory "0.6.0"], 
    [clj-http "2.2.0"]
    [slackapi "0.1.0"]]
  :main ^:skip-aot comicbot.core)
