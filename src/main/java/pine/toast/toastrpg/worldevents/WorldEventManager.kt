package pine.toast.toastrpg.worldevents

import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.events.WorldEventAlertEvent
import pine.toast.toastrpg.events.WorldEventAlertNowEvent

class WorldEventManager {

    private var scheduledWorldEvents: HashMap<WorldEvent, WorldEventTime> = HashMap()

    private fun registerWorldEvent(worldEvent: WorldEvent) {
        val worldEventTime: WorldEventTime = worldEvent.getSpawnTime()
        scheduledWorldEvents[worldEvent] = worldEventTime
    }

    private fun unRegisterWorldEvent(worldEvent: WorldEvent) {
        scheduledWorldEvents.remove(worldEvent)
    }

    /**
     * Starts scheduling the world event.
     * @param worldEvent the world event to schedule.
     *
     */
    fun startWorldEvent(worldEvent: WorldEvent) {
        val eventStartTime: Long = worldEvent.getSpawnTime().getStartDate()
        val currentTime: Long = System.currentTimeMillis()
        registerWorldEvent(worldEvent)
        ToastRPG.getPassedPlugin()!!.logger.info("Started scheduling world event ${worldEvent.getName()}.")

        val delayMillis = eventStartTime - currentTime

        if (delayMillis > 0) {
            ToastRPG.getPassedPlugin()!!.server.scheduler.scheduleSyncDelayedTask(
                ToastRPG.getPassedPlugin()!!,
                {
                    if (!worldEvent.getSpawnTime().isExpired()) {
                        ToastRPG.getPassedPlugin()!!.logger.info("World event ${worldEvent.getName()} is starting now!")
                        ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertNowEvent(worldEvent))
                    } else {
                        ToastRPG.getPassedPlugin()!!.logger.info("World event ${worldEvent.getName()} has expired during the delay.")
                        unRegisterWorldEvent(worldEvent)
                    }
                },
                delayMillis / 50
            )
        } else {
            ToastRPG.getPassedPlugin()!!.logger.warning("World event ${worldEvent.getName()} start time has already passed.")
        }
    }


    /**
     * Stops scheduling the world event.
     * @param worldEvent the world event to stop scheduling.
     */
    fun stopWorldEvent(worldEvent: WorldEvent) {
        worldEvent.getSpawnTime().setExpired()
    }




}
