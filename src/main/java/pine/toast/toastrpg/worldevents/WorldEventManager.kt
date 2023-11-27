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

      when {
         eventStartTime - currentTime > 86400000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.ONE_DAY
         )
         )
         eventStartTime - currentTime > 43200000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.TWELVE_HOURS
         )
         )
         eventStartTime - currentTime > 21600000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.SIX_HOURS
         )
         )
         eventStartTime - currentTime > 3600000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.ONE_HOUR
         )
         )
         eventStartTime - currentTime > 1800000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.THIRTY_MINUTES
         )
         )
         eventStartTime - currentTime > 900000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.FIFTEEN_MINUTES
         )
         )
         eventStartTime - currentTime > 300000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.FIVE_MINUTES
         )
         )
         eventStartTime - currentTime > 60000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(
            WorldEventAlertEvent(worldEvent,
            WorldEventStage.ONE_MINUTE
         )
         )
         eventStartTime >= currentTime -> {
            ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertNowEvent(worldEvent))
            return

         }
         worldEvent.getSpawnTime().isExpired() -> {
            ToastRPG.getPassedPlugin()!!.logger.info("World event ${worldEvent.getName()} has expired.")
            unRegisterWorldEvent(worldEvent)
         }

      }

   }

   /**
    * Stops scheduling the world event.
    * @param worldEvent the world event to stop scheduling.
    */
   fun stopWorldEvent(worldEvent: WorldEvent) {
      worldEvent.getSpawnTime().setExpired()
   }

   fun getScheduledWorldEvents(): HashMap<WorldEvent, WorldEventTime> {
      return scheduledWorldEvents
   }



}