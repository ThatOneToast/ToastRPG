package pine.toast.toastrpg.events

import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.inventory.ItemStack
import pine.toast.toastrpg.playerutils.PlayerDirection

class PlayerRightCLickEvent(
    player: Player,
    mainHand: ItemStack,
    offHand: ItemStack,
    block: Block,
    direction: PlayerDirection,
    isSneaking: Boolean
) : Event() {
    private val player: Player
    private val mainHand: ItemStack
    private val offHand: ItemStack
    private val block: Block
    private val direction: PlayerDirection
    private val isSneaking: Boolean

    init {
        this.player = player
        this.mainHand = mainHand
        this.offHand = offHand
        this.block = block
        this.direction = direction
        this.isSneaking = isSneaking
    }

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
        return HANDLERS
    }

    companion object {
        private val HANDLERS: HandlerList = HandlerList()
    }
}
