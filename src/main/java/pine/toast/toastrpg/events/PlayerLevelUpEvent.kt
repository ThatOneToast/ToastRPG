package pine.toast.toastrpg.events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerLevelUpEvent(thePlayer: Player, theLevel: Int, theMaxLevel: Int, theExperience: Int) : Event() {
    private val handlers = HandlerList()

    private var player: Player
    private var level: Int
    private var maxLevel: Int
    private var experience: Int

    init {
        player = thePlayer
        level = theLevel
        maxLevel = theMaxLevel
        experience = theExperience
    }

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


}
