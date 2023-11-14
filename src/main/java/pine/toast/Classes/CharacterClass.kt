package toast.pine.Classes

import org.bukkit.Material
import org.bukkit.entity.Player
import pine.toast.PlayerAttributes
import pine.toast.ToastRPG

abstract class CharacterClass(
    className: String,
    icon: Material,
    health: Double,
    damage: Double,
    maxMana: Double,
    manaPerSec: Double,
    defense: Double,
    maxLevel: Int
) {
    private val className: String
    private val maxMana: Double
    private val defense: Double
    private val maxLevel: Int
    private val manaPerSec: Double
    private val icon: Material
    private val health: Double
    private val damage: Double

    init {
        this.className = className
        this.icon = icon
        this.health = health
        this.damage = damage
        this.maxMana = maxMana
        this.manaPerSec = manaPerSec
        this.defense = defense
        this.maxLevel = maxLevel
    }

    fun getClassName(): String {
        return className
    }

    fun getIcon(): Material {
        return icon
    }

    /**
     * Call this method to set the players attributes to the class's specifications.
     * @param player - The player to set the attributes of
     * @param healthScale - The health scale to set the player to
     */
    fun enforceClass(player: Player, healthScale: Int) {
        val characterClass = this

        PlayerAttributes.setMaxHealth(player, characterClass.health)
        PlayerAttributes.setDamage(player, characterClass.damage)
        PlayerAttributes.setDefense(player, characterClass.defense)
        PlayerAttributes.setMaxMana(player, characterClass.maxMana)
        PlayerAttributes.setManaPerSec(player, characterClass.manaPerSec)
        ToastRPG.getLevelManager()!!.setMaxLevel(player, characterClass.maxLevel)

        player.healthScale = healthScale.toDouble()
        player.health = characterClass.health.toDouble()
        player.foodLevel = 200
        player.saturation = 200f
        player.health = characterClass.health.toDouble()


    }

    abstract fun getName(): String
    abstract fun getHealth(): Int
    abstract fun getDamage(): Double
    abstract fun getMaxMana(): Double
    abstract fun getManaPerSec(): Double
    abstract fun getDefense(): Double
    abstract fun getMaxLevel(): Int

}