package pine.toast.toastrpg.library.entitymanagment

import org.bukkit.event.entity.*


interface LivingEntityHandler : EntityHandler {

    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent)
    fun onEntityDeath(event: EntityDeathEvent)
    fun onFoodLevelChange(event: FoodLevelChangeEvent)
    fun onEntityBreed(event: EntityBreedEvent)
    fun onEntityDamage(event: EntityDamageEvent)
    fun onEntityTargetLiving(event: EntityTargetLivingEntityEvent)
    fun onEntityTarget(event: EntityTargetEvent)


}