package pine.toast.toastrpg.library.events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerLevelUpEvent(thePlayer: Player, theLevel: Int, theMaxLevel: Int, theExperience: Int) : Event() {
    private val handlers = HandlerList()

    private var player: Player = thePlayer
    private var level: Int = theLevel
    private var maxLevel: Int = theMaxLevel
    private var experience: Int = theExperience

    override fun getHandlers(): HandlerList {
        return handlers
    }

    fun getPlayer(): Player {
        return player
    }

    fun getLevel(): Int {
        return level
    }

    fun getMaxLevel(): Int {
        return maxLevel
    }

    fun getExperience(): Int {
        return experience
    }

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
}
