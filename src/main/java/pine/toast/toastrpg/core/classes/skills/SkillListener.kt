package pine.toast.toastrpg.core.classes.skills

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.events.PlayerLeftClickEvent
import pine.toast.toastrpg.core.events.PlayerRightClickEvent

class SkillListener : Listener {


    @EventHandler
    fun onRightClick(eventHandler: PlayerRightClickEvent) {
        val item: ItemStack = eventHandler.getMainHand()
        val container = item.itemMeta.persistentDataContainer

        if (!container.has(TKeys.SKILL)) return

        val skill: Skill? = container.get(TKeys.SKILL, ToastRPG.getAdapterManager()!!.skillAdapter)
        skill?.handleRightClick(eventHandler)

    }

    @EventHandler
    fun onLeftClick(eventHandler: PlayerLeftClickEvent) {
        val item: ItemStack = eventHandler.getMainHand()
        val container = item.itemMeta.persistentDataContainer

        if (!container.has(TKeys.SKILL)) return

        val skill: Skill? = container.get(TKeys.SKILL, ToastRPG.getAdapterManager()!!.skillAdapter)
        skill?.handleLeftClick(eventHandler)

    }

    @EventHandler
    fun onSelfMovement(eventHandler: PlayerMoveEvent) {
        val player: Player = eventHandler.player
        val container = player.persistentDataContainer


        if (!container.has(TKeys.SKILL)) return

        val skill: Skill? = container.get(TKeys.SKILL, ToastRPG.getAdapterManager()!!.skillAdapter)
        skill?.handleSelfMovement(eventHandler)

    }

    @EventHandler
    fun onSelfDeath(eventHandler: PlayerDeathEvent) {
        val player: Player = eventHandler.player
        val container = player.persistentDataContainer


        if (!container.has(TKeys.SKILL)) return

        val skill: Skill? = container.get(TKeys.SKILL, ToastRPG.getAdapterManager()!!.skillAdapter)
        skill?.handleSelfDeath(eventHandler)

    }

    @EventHandler
    fun onSelfDamage(eventHandler: EntityDamageEvent) {
        val entity: Entity = eventHandler.entity; if (entity !is Player) return
        val player: Player = eventHandler.entity as Player
        val container = player.persistentDataContainer


        if (!container.has(TKeys.SKILL)) return

        val skill: Skill? = container.get(TKeys.SKILL, ToastRPG.getAdapterManager()!!.skillAdapter)
        skill?.handleSelfDamage(eventHandler)

    }


}