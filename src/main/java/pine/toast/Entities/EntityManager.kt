package toast.pine.Entities

import org.bukkit.entity.LivingEntity
import toast.pine.Events.MonsterDeathEvent
import toast.pine.Events.MonsterSpawnEvent
import toast.pine.Events.MonsterTargetPlayerEvent

class EntityManager {
    private val activeLivingEntities: MutableMap<LivingEntity, EntityHandler> = HashMap()


    fun registerHandledEntity(entity: LivingEntity, handler: EntityHandler) {
        activeLivingEntities[entity] = handler
    }

    fun unregisterHandledEntity(entity: LivingEntity) {
        activeLivingEntities.remove(entity)
    }

    fun handleMonsterSpawn(event: MonsterSpawnEvent) {
        val handler = activeLivingEntities[event.entity]
        handler?.onMonsterSpawn(event)
    }

    fun handleMonsterDeath(event: MonsterDeathEvent) {
        val handler = activeLivingEntities[event.getEntity()]
        handler?.onMonsterDeath(event)
    }

    fun handlerMonsterTarget(event: MonsterTargetPlayerEvent) {
        val handler = activeLivingEntities[event.getMonster().getLivingEntity()]
        handler?.onMonsterTarget(event)
    }
}
