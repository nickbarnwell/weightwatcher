(defproject weightwatcher "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :plugins [[lein-git-deps "0.0.1-SNAPSHOT"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [clj-time "0.4.4"]
                 [incanter/incanter-core   "1.3.0"]
                 [incanter/incanter-charts "1.3.0"]]
  :main weightwatcher.runner
)
