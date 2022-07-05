(ns encaje.core
  (:import [java.io Closeable]))

(defmacro --
  "A threading macro which does nothing but allows for some better
  indentation (IMNSHO). Minus minus just substracts itself! I also
  like that it looks like an SQL comment: this is code that is making
  a comment about the indentation that is desired. I also like that it
  looks like a dash that one might use in a list of things:

  Groceries:
  -- milk
  -- bread

  In that the only significance is to indent the items in the list."
  [x]
  x)

(defmacro fx
  "apply done as a macro. fn defines a function. fx is an application of
  a function to x: (fx + 1 2) is (+ 1 2) but without creating or
  needing a seq for the args, x instead of xs"
  [f & x-body]
  `(~f ~@x-body))

(defn fxs
  "apply f to xs. (fxs + 1 2) is (apply + [1 2]): it turns the arguments
  into a seq of xs as a bit of syntactic sugar, as opposed to the x in
  the macro fx."
  [f & xs]
  (apply f xs))

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
