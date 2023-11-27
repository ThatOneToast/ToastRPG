package pine.toast.toastrpg.worldevents

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.events.WorldEventAlertEvent
import pine.toast.toastrpg.events.WorldEventAlertNowEvent
import java.io.File

class WorldEventManager {

   private var scheduledWorldEvents: HashMap<WorldEvent, WorldEventTime> = HashMap()
   private var worldEventsFile: File


   init {
//      val dataFolder = ToastRPG.getPassedPlugin()!!.dataFolder
//      val libraryFolder = File(dataFolder, "ToastRPGLibrary").apply { mkdir() }
//      val worldEventsJson = File(libraryFolder, "WorldEvents.json").apply { createNewFile() }
      val dataFolder = ToastRPG.getPassedPlugin()?.dataFolder
      val libraryFolder = File(dataFolder, "ToastRPGLibrary")

      // Ensure that the ToastRPGLibrary directory exists or create it
      if (!libraryFolder.exists() && !libraryFolder.mkdirs()) {
         // Handle the case where directory creation fails
         throw RuntimeException("Failed to create ToastRPGLibrary directory")
      }

      val worldEventsJson = File(libraryFolder, "WorldEvents.json")

      // Ensure that the WorldEvents.json file exists or create it
      if (!worldEventsJson.exists() && !worldEventsJson.createNewFile()) {
         // Handle the case where file creation fails
         throw RuntimeException("Failed to create WorldEvents.json file")
      }
      worldEventsFile = worldEventsJson

      val gson = Gson()
      val events: List<WorldEvent>? = gson.fromJson(worldEventsJson.reader(), object : TypeToken<List<WorldEvent>>() {}.type)

      if (events != null) {
         for (worldEvent in events) {
            val worldEventTime: WorldEventTime = worldEvent.getSpawnTime()
            scheduledWorldEvents[worldEvent] = worldEventTime
         }
         ToastRPG.getPassedPlugin()!!.logger.info("Loaded ${scheduledWorldEvents.size} world events.")
      } else {
         // Handle the case where deserialization failed or the file is empty.
         ToastRPG.getPassedPlugin()!!.logger.warning("Failed to load world events. The JSON file may be empty or invalid.")
      }

      ToastRPG.getPassedPlugin()!!.logger.info(" - WorldEventManager ~ Passed")


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

   fun getWorldEventsFile(): File {
      return worldEventsFile
   }

   /**
    * Unregisters all world events.
    * Clears the hash map.
    * Saves to WorldEvents.json.
    *
    * @return true if the operation is successful, false otherwise.
    */
   fun unRegisterAllWorldEvents(): Boolean {
      try {
         if (!worldEventsFile.delete()) {
            throw RuntimeException("Failed to delete existing WorldEvents.json file.")
         }

         if (!worldEventsFile.createNewFile()) {
            throw RuntimeException("Failed to create a new WorldEvents.json file.")
         }

         saveWorldEventsToFile()
         scheduledWorldEvents.clear()

         return true
      } catch (e: Exception) {
         e.printStackTrace()
         return false
      }

   }

   private fun saveWorldEventsToFile() {
      try {
         val eventsList: List<WorldEvent> = scheduledWorldEvents.keys.toList()

         val gson = Gson()
         val json = gson.toJson(eventsList)
         worldEventsFile.writeText(json)
      } catch (e: Exception) {
         e.printStackTrace()
      }
   }




}