package pine.toast.toastrpg.classes.skills

import org.bukkit.NamespacedKey
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import pine.toast.toastrpg.classes.CharacterClass
import pine.toast.toastrpg.events.PlayerLeftClickEvent
import pine.toast.toastrpg.events.PlayerRightClickEvent

abstract class Skill(
    private val skillName: String,
    intendedClassUse: Class<out CharacterClass>,
    private val identifier: NamespacedKey,
    skillHandler: Class<out SkillHandler>,
    private val skillDescription: List<String>,
    private var skillLevel: Int,
    private val skillMaxLevel: Int,
    private var skillCost: Int,
    private var skillCooldown: Int
) {
    private val intendedClassUse: CharacterClass
    private val skillHandler: SkillHandler

    init {

        val classUse: CharacterClass = intendedClassUse.getDeclaredConstructor().newInstance()
        val handler: SkillHandler = skillHandler.getDeclaredConstructor().newInstance()
        this.intendedClassUse = classUse
        this.skillHandler = handler

    }


    fun handleRightClick(event: PlayerRightClickEvent) {
        skillHandler.handleRightClick(event)
    }

    fun handleLeftClick(event: PlayerLeftClickEvent) {
        skillHandler.handleLeftClick(event)
    }

    fun handleSelfMovement(event: PlayerMoveEvent) {
        skillHandler.handleSelfMovement(event)
    }

    fun handleSelfDeath(event: PlayerDeathEvent) {
        skillHandler.handleSelfDeath(event)
    }

    fun handleSelfDamage(event: EntityDamageEvent) {
        skillHandler.handleSelfDamage(event)
    }

}
