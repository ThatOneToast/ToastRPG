package pine.toast.toastrpg.worldevents

import pine.toast.toastrpg.ToastRPG
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
    fun startWorldEvent(worldEvent: WorldEvent, tickPerSc: Int = 50) {
        val eventStartTime: Long = worldEvent.getSpawnTime().getStartDate()
        val currentTime: Long = System.currentTimeMillis()

        if (eventStartTime <= currentTime) {
            ToastRPG.getPassedPlugin()!!.logger.warning("World event ${worldEvent.getName()} start time has already passed.")
            return
        }

        registerWorldEvent(worldEvent)
        ToastRPG.getPassedPlugin()!!.logger.info("Started scheduling world event ${worldEvent.getName()}.")

        val delayMillis = eventStartTime - currentTime

        ToastRPG.getPassedPlugin()!!.server.scheduler.scheduleSyncDelayedTask(
            ToastRPG.getPassedPlugin()!!,
            {
                if (!worldEvent.getSpawnTime().isExpired()) {
                    ToastRPG.getPassedPlugin()!!.logger.info("World event ${worldEvent.getName()} is starting now!")
                    ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertNowEvent(worldEvent))
                } else {
                    ToastRPG.getPassedPlugin()!!.logger.info("World event ${worldEvent.getName()} has expired during the delay.")
                    stopWorldEvent(worldEvent)
                }
            },
            delayMillis / tickPerSc // Assuming your server runs at 20 ticks per second, adjust this value if needed
        )
    }


    /**
     * Stops scheduling the world event.
     * @param worldEvent the world event to stop scheduling.
     */
    private fun stopWorldEvent(worldEvent: WorldEvent) {
        worldEvent.getSpawnTime().setExpired()
        unRegisterWorldEvent(worldEvent)

    }




}
