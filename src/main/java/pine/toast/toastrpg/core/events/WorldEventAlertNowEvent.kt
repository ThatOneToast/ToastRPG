package pine.toast.toastrpg.core.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.core.worldevents.WorldEvent

class WorldEventAlertNowEvent(worldEvent: WorldEvent) : Event() {

    private val event: WorldEvent = worldEvent

    fun getWorldEvent(): WorldEvent {
        return event
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
       private val handlerList = HandlerList()

       @JvmStatic
        fun getHandlerList(): HandlerList {
           return handlerList
        }
    }
}
