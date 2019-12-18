(ns music-staff.scripts.notes)


(def notePositionMap {:e 80 :f 70 :g 60 :a 50 :b 40 :c 30 :d 20})


(defn draw-quarter-note [x y ctx]
  (.beginPath ctx)
  (.arc ctx x y 5 0 (* 2 3.14) false)
  (.fill ctx)
  (.stroke ctx)

  ; line
  (.beginPath ctx)
  (.moveTo ctx (+ 5 x) y)
  (.lineTo ctx (+ 5 x) (- y 30))
  (.stroke ctx))


(defn draw-half-note [x y ctx]
  (.beginPath ctx)
  (.arc ctx x y 5 0 (* 2 3.14) false)
  (.stroke ctx)

  ; line
  (.beginPath ctx)
  (.moveTo ctx (+ 5 x) y)
  (.lineTo ctx (+ 5 x) (- y 30))
  (.stroke ctx))

(defn draw-whole-note [x y ctx]
  (.beginPath ctx)
  (.arc ctx x y 5 0 (* 2 3.14) false)
  (.stroke ctx))

(defn draw-sharp [])

(defn draw-flat [])

(defn draw-rest [])

(defn draw-quater-rest [])

(defn draw-half-rest [])

(defn draw-full-rest [])


(defn draw-note [x y type ctx]
  (cond
    (= type "quarter") (draw-quarter-note x y ctx)
    (= type "half")    (draw-half-note x y ctx)
    (= type "whole")   (draw-whole-note x y ctx)))
