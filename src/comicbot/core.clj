(ns comicbot.core
  (:require [slackapi.api :as api])
  (:require [comicbot.comics :as comics]))

(def channel (System/getenv "SLACK_CHANNEL"))
(def strips {:cstrip "Commit Strip", :dilbert "Dilbert"})

(defn has-strip [name]
  (contains? strips (keyword (clojure.string/replace name #"#" "")))
)

(deftype MyActions [token]
  api/SlackActions
  (handle-hello [self client]
    (api/send-msg token channel "Worst. Customer. Ever. Whatever, this is what I've got, take your pick.\n")
    (doseq [key (keys strips)]
      (api/send-msg token channel (str "#" (name key) " " (get strips key) "\n"))))
  (handle-msg [self msg] 
    (if (and (has-strip (:text msg)) (not (= "bot_message" (:subtype msg))))
      (let [link (comics/strip (:text msg))]
        (println link)
        (api/send-msg token channel link)
      nil)))
)

(defn -main
  [& args]
  (let [token (System/getenv "SLACK_TOKEN")]
    (api/connect token (MyActions. token)))
)
