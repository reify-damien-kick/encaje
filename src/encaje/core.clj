(ns encaje.core)

(defmacro -- [& body]
  "Just a utility to give indentation: a threading macro which does
  nothing but allows for some better indentation (IMNSHO)"
  `(~@body))
