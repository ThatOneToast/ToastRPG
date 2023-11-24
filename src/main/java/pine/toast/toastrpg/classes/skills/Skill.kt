package pine.toast.toastrpg.classes.skills

import org.bukkit.NamespacedKey
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import pine.toast.toastrpg.classes.CharacterClass
import pine.toast.toastrpg.events.PlayerLeftClickEvent
import pine.toast.toastrpg.events.PlayerRightCLickEvent

abstract class Skill(
    skillName: String,
    intendedClassUse: Class<out CharacterClass>,
    identifier: NamespacedKey,
    skillHandler: Class<out SkillHandler>,
    skillDescription: List<String>,
    skillLevel: Int,
    skillMaxLevel: Int,
    skillCost: Int,
    skillCooldown: Int
) {
    private val skillName: String
    private val intendedClassUse: CharacterClass
    private val identifier: NamespacedKey
    private val skillHandler: SkillHandler
    private val skillDescription: List<String>
    private var skillLevel: Int
    private val skillMaxLevel: Int
    private var skillCost: Int
    private var skillCooldown: Int

    init {
        this.skillName = skillName
        this.skillDescription = skillDescription
        this.identifier = identifier
        this.skillLevel = skillLevel
        this.skillMaxLevel = skillMaxLevel
        this.skillCost = skillCost
        this.skillCooldown = skillCooldown

        val classUse: CharacterClass = intendedClassUse.getDeclaredConstructor().newInstance()
        val handler: SkillHandler = skillHandler.getDeclaredConstructor().newInstance()
        this.intendedClassUse = classUse
        this.skillHandler = handler

    }


    fun handleRightClick(event: PlayerRightCLickEvent) {
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
