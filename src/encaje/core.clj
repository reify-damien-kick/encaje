(ns encaje.core)

(defmacro -- [& body]
  "A threading macro which does nothing but allows for some better
  indentation (IMNSHO). Minus minus just substracts itself!"
  `(~@body))
