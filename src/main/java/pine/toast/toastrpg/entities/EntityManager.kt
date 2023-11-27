package pine.toast.toastrpg.entities

import org.bukkit.entity.LivingEntity

class EntityManager {
    private val activeLivingEntities: MutableMap<LivingEntity, EntityHandler> = HashMap()

    init {
        print(" - EntityManager ~ Started")
    }

    fun registerHandledEntity(entity: LivingEntity, handler: EntityHandler) {
        activeLivingEntities[entity] = handler
    }

    fun unregisterHandledEntity(entity: LivingEntity) {
        activeLivingEntities.remove(entity)
    }

}
