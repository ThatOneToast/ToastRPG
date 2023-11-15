package pine.toast.EventTimeManagement

import pine.toast.Events.WorldEventAlertEvent
import pine.toast.Events.WorldEventAlertNowEvent
import pine.toast.ToastRPG

class EventTimeManager {
   private val scheduledEvents: MutableMap<pine.toast.WorldEvents.WorldEvent, EventTime> = HashMap()

   fun scheduleWorldEvent(worldEvent: pine.toast.WorldEvents.WorldEvent) {
      val eventTime = worldEvent.getSpawnTime()
      scheduledEvents[worldEvent] = eventTime
      startScheduling(worldEvent)
   }

   fun unScheduleWorldEvent(worldEvent: pine.toast.WorldEvents.WorldEvent) {
      scheduledEvents.remove(worldEvent)
   }

   private fun startScheduling(worldEvent: pine.toast.WorldEvents.WorldEvent) {
      val eventStartTime: Long = worldEvent.getSpawnTime().getStartDate()
      val currentTime: Long = System.currentTimeMillis()

      when {
         eventStartTime - currentTime > 86400000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.ONE_DAY))
         eventStartTime - currentTime > 43200000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.TWELVE_HOURS))
         eventStartTime - currentTime > 21600000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.SIX_HOURS))
         eventStartTime - currentTime > 3600000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.ONE_HOUR))
         eventStartTime - currentTime > 1800000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.THIRTY_MINUTES))
         eventStartTime - currentTime > 900000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.FIFTEEN_MINUTES))
         eventStartTime - currentTime > 300000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.FIVE_MINUTES))
         eventStartTime - currentTime > 60000 -> ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(WorldEventAlertEvent(worldEvent, EventStage.ONE_MINUTE))
         else -> {
            worldEvent.getSpawnTime().setExpired()
            WorldEventAlertNowEvent(worldEvent)
         }
      }

   }
}