package pine.toast.toastrpg.core.events

import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.inventory.ItemStack
import pine.toast.toastrpg.core.playerutils.PlayerDirection

class PlayerRightClickEvent(
    private val player: Player,
    private val mainHand: ItemStack,
    private val offHand: ItemStack,
    private val block: Block,
    private val direction: PlayerDirection,
    private val isSneaking: Boolean
) : Event() {
    private val handlers = HandlerList()

    fun getPlayer(): Player {
        return player
    }

    fun getMainHand(): ItemStack {
        return mainHand
    }

    fun getOffHand(): ItemStack {
        return offHand
    }

    fun getDirection(): PlayerDirection {
        return direction
    }

    fun isSneaking(): Boolean {
        return isSneaking
    }

    override fun getHandlers(): HandlerList {
        return handlers
    }

    companion object {
        private val HANDLERS: HandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }
}
