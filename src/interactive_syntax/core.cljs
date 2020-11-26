(ns interactive-syntax.core
    (:require
     [cljs.js :as cljs :refer [empty-state eval-str js-eval]]))

(eval-str (empty-state) "(+ 1 2)" "UNTITLED" {:eval js-eval} println)

