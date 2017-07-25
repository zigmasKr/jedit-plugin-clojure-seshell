
;;; startup utils and useful functions for Clojure SE Shell plugin

(require '[clojure.reflect :as r])
(use '[clojure.pprint :only [print-table]])

(defn alert
	[txt]
	(org.gjt.sp.jedit.Macros/message view txt))

(defn prompt
	"Shows a prompt box to the user and returns the answer"
	[question defaultValue]
	(org.gjt.sp.jedit.Macros/input view question defaultValue))

;; https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
(defn confirm
	"Shows a confirmation message box to the user.
	YES returns: 0; NO returns: 1; CANCEL retruns: 2."
	[question]
	(org.gjt.sp.jedit.Macros/confirm
		view
		question
		(. javax.swing.JOptionPane YES_NO_CANCEL_OPTION)))

;; http://stackoverflow.com/questions/5821286/how-can-i-get-the-methods-of-a-java-class-from-clojure
(defn inspect
	"Inspects Java object, prints to console."
	[object]
	(let
		[reflected-obj (r/reflect object)
		sorted-members (sort-by :name
			(filter :exception-types (:members reflected-obj)))]
		(print-table sorted-members)
	))

(defn print-buf
	"Prints anything to new buffer."
	[anything]
	(let
		[new-buffer (. org.gjt.sp.jedit.jEdit newFile view)]
		(. new-buffer insert 0 (print-str anything))
	))

;; https://clojuredocs.org/clojure.core/with-out-str
(defn inspect-buf
	"Inspects Java object, prints output to new buffer."
	[object]
	(let
		[reflected-obj (r/reflect object)
		sorted-members (sort-by :name
			(filter :exception-types (:members reflected-obj)))
		table-str (with-out-str (print-table sorted-members))
		new-buffer (. org.gjt.sp.jedit.jEdit newFile view)]
		(. new-buffer insert 0 table-str)
	))