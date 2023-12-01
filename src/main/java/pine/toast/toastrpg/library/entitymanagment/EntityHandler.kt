package pine.toast.toastrpg.library.entitymanagment

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent
import io.papermc.paper.event.entity.EntityPortalReadyEvent
import org.bukkit.event.entity.*


interface EntityHandler {
    
    fun onEntityExplode(event: EntityExplodeEvent)
    fun onEntityInteract(event: EntityInteractEvent)
    fun onEntityPortalEnter(event: EntityPortalEnterEvent)
    fun onEntityPortal(event: EntityPortalEvent)
    fun onEntityPortalExit(event: EntityPortalExitEvent)
    fun onEntityChangeBlock(event: EntityChangeBlockEvent)
    fun onEntityCombust(event: EntityCombustEvent)
    fun onEntityRegainHealth(event: EntityRegainHealthEvent)
    fun onEntityShootBow(event: EntityShootBowEvent)
    fun onEntityToggleGlide(event: EntityToggleGlideEvent)
    fun onEntityPotionEffect(event: EntityPotionEffectEvent)
    fun onEntityAddToWorld(event: EntityAddToWorldEvent)
    fun onEntityRemoveFromWorld(event: EntityRemoveFromWorldEvent)
    fun onEntityPortalCreate(event: EntityPortalReadyEvent)
    fun onEntityPickupItem(event: EntityPickupItemEvent)
    fun onEntityDropItem(event: EntityDropItemEvent)
    fun onEntityResurrect(event: EntityResurrectEvent)
    fun onEntityEnterLoveMode(event: EntityEnterLoveModeEvent)
    fun onEntityTame(event: EntityTameEvent)
    fun onEntityUnleash(event: EntityUnleashEvent)
    fun onEntityTeleport(event: EntityTeleportEvent)
    fun onEntityDamageByBlock(event: EntityDamageByBlockEvent)

    
}