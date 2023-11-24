package pine.toast.toastrpg.monsters

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.entities.EntityManager
import pine.toast.toastrpg.events.MonsterDeathEvent
import pine.toast.toastrpg.events.MonsterSpawnEvent
import pine.toast.toastrpg.events.MonsterTargetPlayerEvent

class MonsterListener(manager: EntityManager) : Listener {
    private val manager: EntityManager

    init {
        this.manager = manager
    }

    @EventHandler
    fun onMonsterSpawn(event: MonsterSpawnEvent) {
        ToastRPG.getMonsterFactory()!!.handleMonsterSpawn(event)
    }

    @EventHandler
    fun onMonsterDeath(event: MonsterDeathEvent) {
        ToastRPG.getMonsterFactory()!!.handleMonsterDeath(event)
    }

    @EventHandler
    fun onMonsterTarget(event: MonsterTargetPlayerEvent) {
        ToastRPG.getMonsterFactory()!!.handlerMonsterTarget(event)
    }
}
