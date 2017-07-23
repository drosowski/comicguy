(ns comicbot.comics-test
  (:require [clojure.test :refer :all]
            [comicbot.comics :refer :all]))

(deftest a-test
  (testing "should extract img link from feed"
    (is (thrown? org.xml.sax.SAXParseException (extract-link "test/comicbot/invalid_xml.xml")))
  )
)
