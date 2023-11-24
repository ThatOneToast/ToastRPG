package pine.toast.toastrpg.worldevents

import org.bukkit.NamespacedKey
import pine.toast.toastrpg.ToastRPG

abstract class WorldEvent(
     private var eventName: String,
     private var eventType: NamespacedKey,
     private var worldEventTime: WorldEventTime,
) {

    fun start() {
        ToastRPG.getWorldEventManager()!!.startWorldEvent(this)
    }


   fun getName(): String {
      return eventName
   }

   fun getType(): NamespacedKey {
      return eventType
   }

   fun getSpawnTime(): WorldEventTime {
      return worldEventTime
   }



}

