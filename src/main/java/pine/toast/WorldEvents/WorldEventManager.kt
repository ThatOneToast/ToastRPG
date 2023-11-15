package pine.toast.WorldEvents

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import pine.toast.EventTimeManagement.EventTime
import pine.toast.ToastRPG
import java.io.File

class WorldEventManager() {

   private var worldEvents: HashMap<WorldEvent, EventTime> = HashMap()
   private var worldEventsFile: File


   init {
      val dataFolder = ToastRPG.getPassedPlugin()!!.dataFolder
      val libraryFolder = File(dataFolder, "ToastRPGLibrary").apply { mkdir() }
      val worldEventsJson = File(libraryFolder, "WorldEvents.json").apply { createNewFile() }
      worldEventsFile = worldEventsJson

      val gson = Gson()
      val events: List<WorldEvent> = gson.fromJson(worldEventsJson.reader(), object : TypeToken<List<WorldEvent>>() {}.type)

      for (worldEvent in events) {
         val eventTime: EventTime = worldEvent.getSpawnTime()
         worldEvents[worldEvent] = eventTime
      }
   }

   fun registerWorldEvent(worldEvent: WorldEvent) {
      val eventTime: EventTime = worldEvent.getSpawnTime()
      worldEvents[worldEvent] = eventTime
   }

   fun unRegisterWorldEvent(worldEvent: WorldEvent) {
      worldEvents.remove(worldEvent)
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
         worldEvents.clear()

         return true
      } catch (e: Exception) {
         e.printStackTrace()
         return false
      }
   }

   private fun saveWorldEventsToFile() {
      try {
         val eventsList: List<WorldEvent> = worldEvents.keys.toList()

         val gson = Gson()
         val json = gson.toJson(eventsList)
         worldEventsFile.writeText(json)
      } catch (e: Exception) {
         e.printStackTrace()
      }
   }




}