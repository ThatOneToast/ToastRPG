package pine.toast.toastrpg.library.monsters

import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import pine.toast.toastrpg.library.entitymanagment.EntityHandler
import pine.toast.toastrpg.library.entitymanagment.EntityManager
import pine.toast.toastrpg.library.entitymanagment.LivingEntityHandler

abstract class Creature(
    private var name: String,
    private var entityClass: Class<out LivingEntity>,
    private var handler: LivingEntityHandler?,
    private var maxHealth: Double,
    private var armor: Double,

    private var attackDamage: Double,
    private var attackSpeed: Double,

    private var persistent: Boolean,
    private var displayName: Boolean,
    private var invulnerable: Boolean,

    private var speed: Double = 1.0


) {



    /**
     * Spawns the creature at the given location with the given attributes. This ass
     * @param location The location to spawn the creature at
     */
    fun spawn(location: Location) {

        val entity = location.world.spawn(location, entityClass)

        entity.customName(nameToComponent())
        entity.isCustomNameVisible = displayName
        entity.isPersistent = persistent
        entity.isInvulnerable = invulnerable

        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = maxHealth
        entity.getAttribute(Attribute.GENERIC_ARMOR)?.baseValue = armor
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.baseValue = attackDamage
        entity.getAttribute(Attribute.GENERIC_ATTACK_SPEED)?.baseValue = attackSpeed
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue = speed
        entity.health = maxHealth

        if (handler != null) EntityManager.injectLivingEntityHandler(entity, handler!!)

        entity.teleport(location)


    }

    /**
     * Get the name of the creature.
     */
    fun getName(): String {
        return name
    }

    /**
     * Get the LivingEntityHandler assigned to the creature.
     */
    fun getHandler(): EntityHandler? {
        return handler
    }

    /**
     * Get the maximum health of the creature.
     */
    fun getMaxHealth(): Double {
        return maxHealth
    }

    /**
     * Get the armor value of the creature.
     */
    fun getArmor(): Double {
        return armor
    }

    /**
     * Get the creatures attack damage
     */
    fun getAttackDamage(): Double {
        return attackDamage
    }

    /**
     * Get the attack speed of the creature.
     */
    fun getAttackSpeed(): Double {
        return attackSpeed
    }

    /**
     * Check if the creature is persistent.
     */
    fun isPersistent(): Boolean {
        return persistent
    }

    /**
     * Check if the creature's custom name is visible.
     */
    fun isDisplayNameVisible(): Boolean {
        return displayName
    }

    /**
     * Check if the creature is invulnerable.
     */
    fun isInvulnerable(): Boolean {
        return invulnerable
    }

    /**
     * Get the movement speed of the creature.
     */
    fun getSpeed(): Double {
        return speed
    }

    private fun nameToComponent(): Component {
        return Component.text(name)
    }

    fun getEntityClass(): Class<out Entity> {
        return entityClass
    }



}