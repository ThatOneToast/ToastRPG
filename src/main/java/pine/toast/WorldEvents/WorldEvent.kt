package pine.toast.WorldEvents

import org.bukkit.NamespacedKey
import pine.toast.EventTimeManagement.EventTime

 abstract class WorldEvent(
   private var eventName: String,
   private var eventType: NamespacedKey,
   private var eventTime: EventTime,
) {


   fun getName(): String {
      return eventName
   }

   fun getType(): NamespacedKey {
      return eventType
   }

   fun getSpawnTime(): EventTime {
      return eventTime
   }



}

