package pine.toast.toastrpg.worldevents

import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.events.WorldEventAlertEvent
import pine.toast.toastrpg.events.WorldEventAlertNowEvent
import java.time.Instant
import java.util.*

class WorldEventManager {

    private var scheduledWorldEvents: HashMap<WorldEvent, WorldEventTime> = HashMap()


    fun areWeThereYet(destination: WorldEvent) {
        val worldEvent: WorldEvent = scheduledWorldEvents.keys.find { it == destination }!!
        val worldEventTime: WorldEventTime = scheduledWorldEvents[worldEvent]!!

        if(worldEventTime.check()){
            ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, WorldEventStage.getStage(worldEventTime.getLastCheck())))
        }
    }


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
        val eventStartTime: Instant = worldEvent.getSpawnTime().getStartDate()
        val currentTime: Instant = Instant.now()


        if(Date.from(worldEvent.getSpawnTime().getStartDate()).before(Date.from(Date().toInstant()))){
            ToastRPG.getPassedPlugin()!!.logger.warning("World event ${worldEvent.getName()} has expired before scheduling.")
            return
        }

        registerWorldEvent(worldEvent)
        ToastRPG.getPassedPlugin()!!.logger.info("Started scheduling world event ${worldEvent.getName()}.")

        val delayMillis = eventStartTime.toEpochMilli() - currentTime.toEpochMilli()

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
            delayMillis / tickPerSc 
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
