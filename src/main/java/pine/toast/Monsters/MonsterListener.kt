package toast.pine.Monsters

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import toast.pine.Entities.EntityManager
import toast.pine.Events.MonsterDeathEvent
import toast.pine.Events.MonsterSpawnEvent
import toast.pine.Events.MonsterTargetPlayerEvent

class MonsterListener(manager: EntityManager) : Listener {
    private val manager: EntityManager

    init {
        this.manager = manager
    }

    @EventHandler
    fun onMonsterSpawn(event: MonsterSpawnEvent) {
        manager.handleMonsterSpawn(event)
    }

    @EventHandler
    fun onMonsterDeath(event: MonsterDeathEvent) {
        manager.handleMonsterDeath(event)
    }

    @EventHandler
    fun onMonsterTarget(event: MonsterTargetPlayerEvent) {
        manager.handlerMonsterTarget(event)
    }
}
