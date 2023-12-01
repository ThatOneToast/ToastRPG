package pine.toast.toastrpg.library.entitymanagment

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent
import io.papermc.paper.event.entity.EntityPortalReadyEvent
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.*
import pine.toast.toastrpg.library.Keys
import pine.toast.toastrpg.library.ToastRPG

object EntityManager : Listener {

    /**
     * Attaches the handler to the entity
     * @param entity The entity to attach the handler to
     * @param handler The handler to attach to the entity
     */
    fun injectEntityHandler(entity: Entity, handler: EntityHandler) {
        entity.persistentDataContainer.set(Keys.entityHandler, ToastRPG.getAdapters()!!.entityHandler, handler )
    }

    /**
     * Attaches the handler to the entity
     * @param entity The entity to attach the handler to
     * @param handler The handler to attach to the entity
     */
    fun injectLivingEntityHandler(entity: LivingEntity, handler: LivingEntityHandler) {
        entity.persistentDataContainer.set(Keys.livingEntityHandler, ToastRPG.getAdapters()!!.livingEntityHandler, handler )
    }

    /**
     * Retrieves the entity handler associated with the entity
     * @param entity The entity
     * @return The entity handler or null if none is associated
     */
    private fun getEntityHandler(entity: Entity): EntityHandler? {
        return entity.persistentDataContainer.get(Keys.entityHandler, ToastRPG.getAdapters()!!.entityHandler)
    }

    /**
     * Retrieves the living entity handler associated with the living entity
     * @param entity The living entity
     * @return The living entity handler or null if none is associated
     */
    private fun getLivingEntityHandler(entity: LivingEntity): LivingEntityHandler? {
        return entity.persistentDataContainer.get(Keys.livingEntityHandler, ToastRPG.getAdapters()!!.livingEntityHandler)
    }

    /**
     * Handles the specified event for the entity
     * @param entity The entity for which the event occurred
     * @param event The event to handle
     */
    private fun handleEntityEvent(entity: Entity, event: Event) {
        val handler = getEntityHandler(entity)
        handler?.let {
            when (event) {
                is EntityExplodeEvent -> it.onEntityExplode(event)
                is EntityInteractEvent -> it.onEntityInteract(event)
                is EntityPortalEnterEvent -> it.onEntityPortalEnter(event)
                is EntityPortalEvent -> it.onEntityPortal(event)
                is EntityPortalExitEvent -> it.onEntityPortalExit(event)
                is EntityChangeBlockEvent -> it.onEntityChangeBlock(event)
                is EntityCombustEvent -> it.onEntityCombust(event)
                is EntityRegainHealthEvent -> it.onEntityRegainHealth(event)
                is EntityShootBowEvent -> it.onEntityShootBow(event)
                is EntityToggleGlideEvent -> it.onEntityToggleGlide(event)
                is EntityPotionEffectEvent -> it.onEntityPotionEffect(event)
                is EntityAddToWorldEvent -> it.onEntityAddToWorld(event)
                is EntityRemoveFromWorldEvent -> it.onEntityRemoveFromWorld(event)
                is EntityPortalReadyEvent -> it.onEntityPortalCreate(event)
                is EntityPickupItemEvent -> it.onEntityPickupItem(event)
                is EntityDropItemEvent -> it.onEntityDropItem(event)
                is EntityResurrectEvent -> it.onEntityResurrect(event)
                is EntityEnterLoveModeEvent -> it.onEntityEnterLoveMode(event)
                is EntityTameEvent -> it.onEntityTame(event)
                is EntityUnleashEvent -> it.onEntityUnleash(event)
                is EntityTeleportEvent -> it.onEntityTeleport(event)
                is EntityDamageByBlockEvent -> it.onEntityDamageByBlock(event)
            }
        }
    }

    /**
     * Handles the specified event for the living entity
     * @param entity The living entity for which the event occurred
     * @param event The event to handle
     */
    private fun handleLivingEntityEvent(entity: LivingEntity, event: Event) {
        val handler = getLivingEntityHandler(entity)
        handler?.let {
            when (event) {
                is EntityDamageByEntityEvent -> it.onEntityDamageByEntity(event)
                is EntityDeathEvent -> it.onEntityDeath(event)
                is FoodLevelChangeEvent -> it.onFoodLevelChange(event)
                is EntityBreedEvent -> it.onEntityBreed(event)
                is EntityDamageEvent -> it.onEntityDamage(event)
                is EntityTargetLivingEntityEvent -> it.onEntityTargetLiving(event)
                is EntityTargetEvent -> it.onEntityTarget(event)
            }
        }
    }

    @EventHandler
    private fun onEntityEvent(event: EntityEvent) {
        handleEntityEvent(event.entity, event)
    }

    @EventHandler
    private fun onLivingEntityEvent(event: EntityEvent) {
        if (event.entity is LivingEntity) {
            handleLivingEntityEvent(event.entity as LivingEntity, event)
        }
    }





}