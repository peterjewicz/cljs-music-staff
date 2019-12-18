(ns music-staff.scripts.staff)

; TODO we need to build in rests
(defn get-note-quality [noteType]
  "returns how many beats a particular note is"
  (cond
    (= noteType "quarter") 1
    (= noteType "half") 2
    (= noteType "whole") 4))


(defn get-total-beats [notes]
  "counts and returns the total beats of a piece"
  (loop [i 0
         total 0]
    (if (>= i (count notes))
      total
      (recur (inc i) (+ (get-note-quality (:type (nth notes i))) total)))))