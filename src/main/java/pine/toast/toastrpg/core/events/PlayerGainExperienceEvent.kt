package pine.toast.toastrpg.core.events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerGainExperienceEvent(
    private val player: Player,
    private val amount: Int,
    private val level: Int
) : Event() {

    private val handlers = HandlerList()

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
        return handlers
    }

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
}
