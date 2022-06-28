(defproject org.clojars.dkick/encaje "0.1.0"
  :description "A collection of Clojure utilities: Clojure Enca(s|j)e(d)"
  :url "https://github.com/dkick"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.0"]]
  :plugins [[lein-ancient "1.0.0-RC3"]]
  :repl-options {:init-ns encaje.core}
  :signing {:gpg-key "06C9F682577E497967DF60D4CF53CBC793B99019"})
