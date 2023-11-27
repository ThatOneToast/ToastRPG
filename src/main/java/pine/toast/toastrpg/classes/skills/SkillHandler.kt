package pine.toast.toastrpg.classes.skills

import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import pine.toast.toastrpg.events.PlayerLeftClickEvent
import pine.toast.toastrpg.events.PlayerRightClickEvent

interface SkillHandler {

    fun handleLeftClick(event: PlayerLeftClickEvent)
    fun handleRightClick(event: PlayerRightClickEvent)
    fun handleSelfMovement(event: PlayerMoveEvent)
    fun handleSelfDeath(event: PlayerDeathEvent)
    fun handleSelfDamage(event: EntityDamageEvent)


}