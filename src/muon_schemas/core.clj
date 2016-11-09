(ns muon-schemas.core
  (:require [schema.core :as s]))

;; Schemas
(s/defschema StreamInfo
  {:stream-name s/Str
   :total-events s/Num})

(s/defschema StreamInfoMap
  {:streams [StreamInfo]})

(s/defschema ProjectionKeyMap
  {:projection-keys [s/Str]})

(s/defschema ReductionValue
  ;; TODO: Improve
  s/Any)

(s/def Encoding
  ;; TODO: Extend
  s/Str #_(s/enum "application/json"))

(s/def Provenance
  ;; TODO: Improve
  {s/Keyword s/Any})

(s/defschema FreeSchema
  {s/Keyword s/Any})

(s/defschema EventTemplate
  {:stream-name s/Str
   :event-type s/Str
   (s/optional-key :caused-by) (s/maybe s/Num)
   (s/optional-key :caused-by-relation) (s/maybe s/Str)
   :payload FreeSchema
   :service-id s/Str
   (s/optional-key :event-time) (s/maybe s/Num)
   (s/optional-key :order-id) (s/maybe s/Num)
   (s/optional-key :schema) (s/maybe s/Str)})

(s/defschema Event
  {:stream-name s/Str
   :event-type s/Str
   (s/optional-key :caused-by) (s/maybe s/Num)
   (s/optional-key :caused-by-relation) (s/maybe s/Str)
   :payload FreeSchema
   :service-id s/Str
   :event-time s/Num
   :order-id s/Num
   (s/optional-key :schema) (s/maybe s/Str)})

(s/defschema ReductionFunction
  ;; TODO: Improve
  s/Any)

(s/defschema StreamContentsResponse
  {:results [Event]})

(s/defschema ProjectionTemplate
  {:language (s/maybe (s/enum :clojure :javascript :js-experimental))
   :reduction s/Str
   :initial-value s/Str
   :stream-name s/Str
   :projection-name s/Str})

(s/defschema ProjectionValue
  {:fn s/Str
   :last-error (s/maybe s/Str)
   :current-value (s/maybe ReductionValue)
   :init-time s/Num
   :status (s/enum :running :failed)
   :language (s/maybe (s/enum :clojure :javascript :js-experimental))
   :initial-value ReductionValue
   :processed s/Num
   :last-event (s/maybe Event)
   :reduction ReductionFunction
   :mem-used s/Num
   :last-measured s/Num
   :stream-name s/Str
   :avg-time Double
   :avg-global-time Double
   :projection-name s/Str})

(s/defschema Projection
  {:fn s/Str
   :last-error (s/maybe s/Str)
   :init-time s/Num
   :status (s/enum :running :failed)
   :language (s/maybe (s/enum :clojure :javascript :js-experimental))
   :initial-value ReductionValue
   :processed s/Num
   :last-event (s/maybe Event)
   :reduction ReductionFunction
   :mem-used s/Num
   :last-measured s/Num
   :stream-name s/Str
   :avg-time Double
   :avg-global-time Double
   :projection-name s/Str})

(s/defschema ProjectionList
  {:projections [Projection]})

(s/defschema EventResponse (s/maybe Event))

(s/defschema PostResponse (s/maybe {:correct (s/enum true)}))

(s/defschema ProjectionResponse
  (s/maybe ProjectionValue))
