package pine.toast.toastrpg.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.worldevents.WorldEvent

class WorldEventAlertNowEvent(worldEvent: WorldEvent) : Event() {

   private val handlers = HandlerList()
   private val event: WorldEvent = worldEvent

   fun getWorldEvent(): WorldEvent {
      return event
   }
   override fun getHandlers(): HandlerList {
      return handlers
   }
}