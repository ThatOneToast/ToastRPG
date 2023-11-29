package pine.toast.toastrpg.core.entities

import org.bukkit.entity.LivingEntity
import pine.toast.toastrpg.core.ToastRPG

class EntityManager {
    private val activeLivingEntities: MutableMap<LivingEntity, EntityHandler> = HashMap()

    init {
        ToastRPG.getPassedPlugin()!!.logger.info(" - EntityManager ~ Started")
    }

    fun registerHandledEntity(entity: LivingEntity, handler: EntityHandler) {
        activeLivingEntities[entity] = handler
    }

    fun unregisterHandledEntity(entity: LivingEntity) {
        activeLivingEntities.remove(entity)
    }

}