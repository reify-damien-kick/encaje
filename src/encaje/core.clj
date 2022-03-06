(ns encaje.core
  (:import [java.io Closeable]))

(defmacro -- [& body]
  "A threading macro which does nothing but allows for some better
  indentation (IMNSHO). Minus minus just substracts itself!"
  `(~@body))

(defrecord Escrow [asset on-close]
  clojure.lang.IDeref
  (deref [escrow]
    (.asset escrow))

  Closeable
  (close [escrow]
    ((.on-close escrow) (.asset escrow))))

(defmacro -|| [on-close & body]
  `(->Escrow ~body ~(if (seq? on-close)
                      `~on-close
                      `(fn [asset#]
                         (~on-close asset#)))))
