package pine.toast.toastrpg.core.classes

import org.bukkit.Material
import org.bukkit.entity.Player
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.playerutils.PlayerAttributes


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
        player.health = characterClass.health
        player.foodLevel = 200
        player.saturation = 200f
        player.health = characterClass.health


    }

    fun getName(): String {
        return className
    }

    fun getIcon(): Material {
        return icon
    }

    fun getDamage(): Double {
        return damage
    }

    fun getDefense(): Double {
        return defense
    }

    fun getHealth(): Double {
        return health
    }

    fun getManaPerSec(): Double {
        return manaPerSec
    }

    fun getMaxLevel(): Int {
        return maxLevel
    }

    fun getMaxMana(): Double {
        return maxMana
    }

}