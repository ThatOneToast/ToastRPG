package toast.pine.Events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList


class PlayerGainExperienceEvent(
   player: Player,
   amount: Int,
   level: Int
) : Event() {

   private val player: Player
   private val amount: Int
   private val level: Int

   init {
      this.player = player
      this.amount = amount
      this.level = level
   }

   fun getPlayer(): Player {
      return player
   }

   fun getAmount(): Int {
      return amount
   }

   fun getLevel(): Int {
      return level
   }

   override fun getHandlers(): HandlerList {
      return HANDLERS
   }

   companion object {
      private val HANDLERS: HandlerList = HandlerList()
   }
}
