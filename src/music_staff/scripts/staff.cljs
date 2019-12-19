(ns music-staff.scripts.staff)

; TODO we need to build in rests
(defn get-note-quality [noteType]
  "returns how many beats a particular note is"
  (cond
    (= noteType "quarter") 1
    (= noteType "half")    2
    (= noteType "whole")   4
    (= noteType "half-rest") 2
    (= noteType "whole-rest") 4))


(defn get-total-beats [notes]
  "counts and returns the total beats of a piece"
  (loop [i 0
         total 0]
    (if (>= i (count notes))
      total
      (recur (inc i) (+ (get-note-quality (:type (nth notes i))) total)))))


(defn draw-staff [ctx]
    ;f
    (.beginPath ctx)
    (.moveTo ctx 0 0)
    (.lineTo ctx 600 0)
    (.stroke ctx)

    ;d
    (.beginPath ctx)
    (.moveTo ctx 0 14)
    (.lineTo ctx 600 14)
    (.stroke ctx)

    ;b
    (.beginPath ctx)
    (.moveTo ctx 0 28)
    (.lineTo ctx 600 28)
    (.stroke ctx)

    ;g
    (.beginPath ctx)
    (.moveTo ctx 0 42)
    (.lineTo ctx 600 42)
    (.stroke ctx)

    ;e
    (.beginPath ctx)
    (.moveTo ctx 0 56)
    (.lineTo ctx 600 56)
    (.stroke ctx))


(defn draw-measure-lines [ctx width]
  "draws a measure - passing it width for now but should allow it to wrap/grow later on"
)