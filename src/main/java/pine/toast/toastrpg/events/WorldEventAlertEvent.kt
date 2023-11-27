package pine.toast.toastrpg.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.worldevents.WorldEvent
import pine.toast.toastrpg.worldevents.WorldEventStage

class WorldEventAlertEvent(worldEvent: WorldEvent, worldEventStage: WorldEventStage) : Event() {

    private val handlers = HandlerList()

    private val event: WorldEvent = worldEvent
    private val stage: WorldEventStage = worldEventStage

    fun getWorldEvent(): WorldEvent {
        return event
    }

    fun getEventStage(): WorldEventStage {
        return stage
    }

    override fun getHandlers(): HandlerList {
        return handlers
    }

    companion object {
        private val handlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlerList
        }
    }
}
