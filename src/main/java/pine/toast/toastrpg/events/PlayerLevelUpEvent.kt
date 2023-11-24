package pine.toast.toastrpg.events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerLevelUpEvent(val player: Player, val level: Int, val maxLevel: Int, val experience: Int) : Event() {
    private val handlers = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlers
    }
}
