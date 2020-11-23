(ns interactive-syntax.core
    (:require
     [clojure.string :as string]
     [cljs.tools.reader :refer [read read-string]]
     [cljs.tools.reader.reader-types :refer [indexing-push-back-reader
                                             get-line-number
                                             get-column-number]]
     [cljs.js :as cljs :refer [empty-state compile-str js-eval]]
     [cljs.pprint :refer [pprint]]
     ["@stopify/stopify" :as stopify]))

(defn eval-str [s output]
  (compile-str (empty-state)
               s
               "UNTITLED.cljs"
               {:eval js-eval
                :source-map true}
               (fn [program]
                 (binding [*print-fn* #(swap! output conj %)]
                   (cond
                     (contains? program :value)
                     (let [runner (stopify.stopifyLocally (:value program))]
                       (set! runner.g #js {:cljs js/cljs})
                       (.run runner #(swap! output conj nil))),
                     (contains? program :error) (pprint (-> program :error)))))))

(let [output (atom #queue [])]
  (set! js/window.stopify stopify)
  (eval-str "(println (+ 1 2))" output)
  (println output))


