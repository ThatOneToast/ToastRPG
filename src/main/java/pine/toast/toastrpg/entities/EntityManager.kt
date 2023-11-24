package pine.toast.toastrpg.entities

import org.bukkit.entity.LivingEntity

class EntityManager {
    private val activeLivingEntities: MutableMap<LivingEntity, EntityHandler> = HashMap()


    fun registerHandledEntity(entity: LivingEntity, handler: EntityHandler) {
        activeLivingEntities[entity] = handler
    }

    fun unregisterHandledEntity(entity: LivingEntity) {
        activeLivingEntities.remove(entity)
    }

}
