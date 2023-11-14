package toast.pine.Classes.Quests.Objectives

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import toast.pine.Classes.Quests.QuestObjective
import toast.pine.socialSystem.PlayerSocial

class MonsterSlaying(private val targetQuantity: Int, playerSocial: PlayerSocial) : QuestObjective, Listener {
    private var currentProgress = 0
    private val owner: PlayerSocial

    init {
        owner = playerSocial
    }

    override val isComplete: Boolean
        get() {
            if (currentProgress >= targetQuantity) {
                EntityDeathEvent.getHandlerList().unregister(this)
                return true
            }
            return false
        }


    override val description: String get() = "Kill $targetQuantity monsters."

    override fun updateProgress() {
        currentProgress++
    }

    @EventHandler
    private fun onPlayerKill(event: EntityDeathEvent) {
        if (isComplete) return
        if (event.entity.killer === owner.getPlayer()) {
            updateProgress()
        }
    }
}
