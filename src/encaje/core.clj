(ns encaje.core
  (:import [java.io Closeable]))

(defmacro --
  "A threading macro which does nothing but allows for some better
  indentation (IMNSHO). Minus minus just substracts itself!"
  [& body]
  `(~@body))

(defrecord Escrow [asset on-close]
  ;; escrow: a bond, deed, or other document kept in the custody of a
  ;; third party and taking effect only when a specified condition has
  ;; been fulfilled.
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
