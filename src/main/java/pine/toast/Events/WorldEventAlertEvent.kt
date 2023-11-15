package pine.toast.Events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.EventTimeManagement.EventStage
import pine.toast.WorldEvents.WorldEvent

class WorldEventAlertEvent(worldEvent: WorldEvent, eventStage: EventStage) : Event() {

   private val handlers = HandlerList()

   private val event: WorldEvent = worldEvent
   private val stage: EventStage = eventStage


   fun getWorldEvent(): WorldEvent {
      return event
   }

   fun getEventStage(): EventStage {
      return stage
   }

   override fun getHandlers(): HandlerList {
      return handlers
   }

}
