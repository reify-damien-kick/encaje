(ns encaje.core
  (:require [clojure.pprint])
  (:import [java.io Closeable]))

(defmacro --
  "A threading macro which does nothing but allows for some better
  indentation (IMNSHO). Minus minus just substracts itself! Basically,
  this is a macro version of identity. I also like that it looks like
  an SQL comment: this is code that is making a comment about the
  indentation that I want. I also like that it looks like a dash that
  one might use in a list of things:

  Groceries:
  -- milk
  -- bread

  In that the only significance is to indent the items in the list."
  [x]
  x)

(defmacro fx
  "apply done as a macro. fn defines a function. fx is an application of
  a function to x: (fx + 1 2) is (+ 1 2) but without creating or
  needing a seq for the args, x instead of xs. Another opinionated
  take on how one can make the code ident like I want it to indent,
  similar to -- but different:

  (def a-really-long-and-annoying-name-for-addtion +)

  (a-really-long-and-annoying-name-for-addtion 1
                                               2)

  (a-really-long-and-annoying-name-for-addtion
   1 2)

  (fx a-really-long-and-annoying-name-for-addtion
      1 2)
  "
  [f & x-body]
  `(~f ~@x-body))

(defn fxs
  "apply f to xs. (fxs + 1 2) is (apply + [1 2]): it turns the arguments
  into a seq of xs as a bit of syntactic sugar, as opposed to the x in
  the macro fx."
  [f & xs]
  (apply f xs))

(prefer-method clojure.pprint/simple-dispatch
               clojure.lang.IPersistentMap
               clojure.lang.IDeref)

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
  ;; -|| is supposed to look like an account book
  `(->Escrow ~body ~(if (seq? on-close)
                      `~on-close
                      `(fn [asset#]
                         (~on-close asset#)))))
