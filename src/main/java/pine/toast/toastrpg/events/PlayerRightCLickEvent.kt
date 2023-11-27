package pine.toast.toastrpg.events

import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.inventory.ItemStack
import pine.toast.toastrpg.playerutils.PlayerDirection

class PlayerRightCLickEvent(
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

    override fun getHandlers(): HandlerList {
        return handlers
    }


}
