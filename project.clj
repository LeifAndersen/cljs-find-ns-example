(defproject interactive-syntax "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 ;;[org.clojure/clojurescript "1.10.741"]
                 [org.clojure/tools.reader "1.3.3"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.20"]]

  :clean-targets ^{:protect false}

  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public"]

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
             :css-dirs ["public/css"]}

  :cljsbuild {:builds {:app
                       {:source-paths ["src"]
                        :compiler
                        {:main "interactive-syntax.core"
                         :output-to "public/js/development/app.js"
                         :output-dir "public/js/development"
                         :asset-path   "js/development"
                         :target :bundle
                         :bundle-cmd
                         {:none ["npx" "webpack" "--mode=development"
                                 "src/js/bundle.js" "public/js/development/app.js"
                                 "--output-path" "public/js"
                                 "--output-filename" "main.js"]
                          :default ["npx" "webpack" "--mode=production"
                                    "src/js/bundle.js"
                                    "public/js/development/app.js"
                                    "--output-path" "public/js"
                                    "--output-filename" "main.js"]}
                         :source-map true
                         :optimizations :none
                         :pretty-print  true}
                        :figwheel
                        {:on-jsload "interactive-syntax.core/mount-root"
                         :open-urls ["http://localhost:3449/index.html"]}}}}

  :profiles {:dev {:source-paths ["src" "env/dev/clj"]
                   :dependencies [[binaryage/devtools "1.0.2"]
                                  [figwheel-sidecar "0.5.20"]
                                  [nrepl "0.7.0"]
                                  [cider/piggieback "0.5.0"]]}})
