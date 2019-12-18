(ns music-staff.core
    (:require [reagent.core :as reagent :refer [atom]]
              [music-staff.scripts.notes :as notes]
              [music-staff.scripts.staff :as staff]))

(declare canvas)
(declare ctx)
(enable-console-print!)

(println "This text is printed from src/music-staff/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

; draw the staff  - go basic for now fixed with


;; in order to itterate notes we need to know the time signiture to create a a bar
;itterate though notes
  ;at height for nottype
  ;at width by count
  ;by type of note



; GENERATE the staff bars

; this is going to be based on the total number of notes that we pass in as well as the
; time signiture of the piece - beats per measure ddetermines how many measures we need to generate
; count the notes assigning a value for each ond depending on which type of note it is
    ; quater = 1 half = 2 whole = 4  - we're only concerned with 4/4 time at the moment

; once we have a a count we can create the size of the staff based on the amount of measures we need
;    total / 4 = measures - adjust later once we have 4/4 working

; then we can paint the notes by adding 4 amounts to each measure until we hit the end
; X will jump every time we hit a measure.



; (def notePositionMap {:e "38px"  :f "33px" :g "28px" :a "23px" :b "18px" :c "13px"  :d "8px"})
(def notePositionMap {:e 80 :f 70 :g 60 :a 50 :b 40 :c 30 :d 20 :rest 0})


(defrecord Note [value type])

(def notes [(Note. :e "quarter") (Note. :f "half") (Note. :g "whole")])


(defn draw-staff []
  ;TODO clean these up
  (def beat-count (staff/get-total-beats notes))
  (print beat-count)
  (def canvas (.getElementById js/document "Canvas"))
  (def ctx (.getContext canvas "2d"))

  ;f
  (.beginPath ctx)
  (.moveTo ctx 0 0)
  (.lineTo ctx 600 0)
  (.stroke ctx)

  ;d
  (.beginPath ctx)
  (.moveTo ctx 0 20)
  (.lineTo ctx 600 20)
  (.stroke ctx)

  ;b
  (.beginPath ctx)
  (.moveTo ctx 0 40)
  (.lineTo ctx 600 40)
  (.stroke ctx)

  ;g
  (.beginPath ctx)
  (.moveTo ctx 0 60)
  (.lineTo ctx 600 60)
  (.stroke ctx)

  ;e
  (.beginPath ctx)
  (.moveTo ctx 0 80)
  (.lineTo ctx 600 80)
  (.stroke ctx)

  (loop [noteCount 0]
    (if (>= noteCount (count notes))
    "done"
    (do
      (notes/draw-note (+ 10 (* 20 noteCount)) ((:value (nth notes noteCount)) notePositionMap) (:type (nth notes noteCount)) ctx)
      (recur (inc noteCount))))))

(defn Canvas []
  (reagent/create-class                 ;; <-- expects a map of functions
    {:display-name  "canvas"      ;; for more helpful warnings & errors

      :component-did-mount               ;; the name of a lifecycle function
        (fn [this]
          (draw-staff)
          (println "component-did-mount")) ;; your implementation

       :component-did-update              ;; the name of a lifecycle function
        (fn [this old-argv]                ;; reagent provides you the entire "argv", not just the "props"
          )

        ;; other lifecycle funcs can go in here
        :reagent-render        ;; Note:  is not :render
         (fn []           ;; remember to repeat parameters
          [:div.Stage
            [:div.canvasParent
              [:canvas#Canvas {:width "800" :height "200"}]]])}))

(reagent/render-component [Canvas]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
