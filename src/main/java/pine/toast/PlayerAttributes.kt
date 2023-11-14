package pine.toast

import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType
import java.util.*

object PlayerAttributes : Listener {
    /**
     * @param player - The player to set the damage attribute of
     * @param amount - The amount to set the damage attribute to
     */
    fun setDamage(player: Player, amount: Double) {
        // Set the damage attribute of the player to the amount specified
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue = amount
    }

    /**
     * @param player - The player to add the damage attribute to
     * @param amount - The amount to add to the damage attribute
     */
    fun addDamage(player: Player, amount: Double) {
        // Add the amount specified to the damage attribute of the player
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue =
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue + amount
    }

    /**
     *
     * @param player - The player to remove the damage attribute from
     * @param amount - The amount to remove from the damage attribute
     */
    fun removeDamage(player: Player, amount: Double) {
        // Remove the amount specified from the damage attribute of the player
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue =
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue - amount
    }

    /**
     *
     * @param player - The player to get the damage attribute from
     * @return - The damage value attribute of the player
     */
    fun getDamage(player: Player): Double {
        // Get the damage attribute of the player
        return player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue
    }
    // ---------------- \\
    /**
     * @param player - The player to set the defense attribute of
     * @param amount - The amount to set the defense attribute to
     */
    fun setDefense(player: Player, amount: Double) {
        // Set the defense attribute of the player to the amount specified
        player.getAttribute(Attribute.GENERIC_ARMOR)!!.baseValue = amount
    }

    /**
     *
     * @param player - The player to add the defense attribute to
     * @param amount - The amount to add to the defense attribute
     */
    fun addDefense(player: Player, amount: Double) {
        val base = player.getAttribute(Attribute.GENERIC_ARMOR)!!.value
        player.getAttribute(Attribute.GENERIC_ARMOR)!!.baseValue = base + amount
    }

    /**
     *
     * @param player - The player to remove the defense attribute to
     * @param amount - The amount to remove to the defense attribute
     */
    fun removeDefense(player: Player, amount: Double) {
        val base = player.getAttribute(Attribute.GENERIC_ARMOR)!!.value
        player.getAttribute(Attribute.GENERIC_ARMOR)!!.baseValue = base - amount
    }

    /**
     *
     * @param player - The player to get the defense attribute from
     * @return - The defense value attribute of the player
     */
    fun getDefense(player: Player): Double {
        return player.getAttribute(Attribute.GENERIC_ARMOR)!!.value
    }
    // ------- \\
    /**
     *
     * @param player - The player to set the max health attribute of
     * @return - The max health value attribute of the player
     */
    fun getMaxHealth(player: Player): Double {
        // Set the max health attribute of the player to the amount specified
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }

    /**
     *
     * @param player - The player to set the max health attribute of
     * @param amount - The amount to set the max health attribute to
     */
    fun setMaxHealth(player: Player, amount: Double) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = amount
    }

    /**
     *
     * @param player - The player to add the max health attribute to
     * @param amount - The amount to add to the max health attribute
     */
    fun addMaxHealth(player: Player, amount: Int) {
        val base = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = base + amount
    }

    /**
     *
     * @param player - The player to remove the max health attribute from
     * @param amount - The amount to remove from the max health attribute
     */
    fun removeMaxHealth(player: Player, amount: Int) {
        val base = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = base - amount
    }

    /**
     *
     * @param player - The player to add the health attribute to
     * @param amount - The amount to add to the health attribute
     */
    fun addHealth(player: Player, amount: Double) {
        val base = player.health
        player.health = base + amount
    }

    /**
     *
     * @param player - The player to remove the health attribute from
     * @param amount - The amount to remove from the health attribute
     */
    fun removeHealth(player: Player, amount: Double) {
        val base = player.health
        player.health = base - amount
    }

    // ------ \\
    private val mana: MutableMap<UUID, Double> = HashMap()

    /**
     * Get the player's current mana
     * @param player The player to get the mana of
     * @return The player's current mana
     */
    fun getMana(player: Player): Double {
        return mana.getOrDefault(player.uniqueId, 0.0)
    }

    /**
     * Set the player's current mana
     * @param player The player to set the mana of
     * @param amount The amount to set the player's mana to
     */
    fun setMana(player: Player, amount: Double) {
        mana[player.uniqueId] = amount
    }

    /**
     * Get the player's maximum mana
     * @param player The player to get the maximum mana of
     * @return The player's maximum mana
     */
    fun getMaxMana(player: Player): Double {
        val playerData = player.persistentDataContainer
        if (!playerData.has(Keys.MAX_MANA, PersistentDataType.DOUBLE)) {
            Bukkit.getLogger().warning("You have not registered values! defaulting to 1")
            setMaxMana(player, 1.0)
        }
        return playerData.get(Keys.MAX_MANA, PersistentDataType.DOUBLE)!!
    }

    /**
     * Get the player's mana per second
     * @param player The player to get the mana per second of
     * @return The player's mana per second
     */
    fun getManaPerSec(player: Player): Double {
        val playerData = player.persistentDataContainer
        if (!playerData.has(Keys.MANA_PER, PersistentDataType.DOUBLE)) {
            Bukkit.getLogger().warning("You have not registered values! defaulting to 0.1")
            setManaPerSec(player, 0.1)
        }
        return playerData.get(Keys.MANA_PER, PersistentDataType.DOUBLE)!!
    }

    /**
     * Subtract mana from the player
     * @param player The player to subtract mana from
     * @param manaToSubtract The amount of mana to subtract
     */
    fun subtractMana(player: Player, manaToSubtract: Double) {
        // Get the player's current mana
        var currentMana = mana.getOrDefault(player.uniqueId, 0.0)
        currentMana -= manaToSubtract
        if (currentMana < 0) {
            currentMana = 0.0
        }
        mana[player.uniqueId] = currentMana
    }

    /**
     * Add mana to the player
     * @param player The player to add mana to
     * @param maxMana The amount of mana to add
     */
    fun setMaxMana(player: Player, maxMana: Double) {
        val playerData = player.persistentDataContainer
        playerData.set(Keys.MAX_MANA, PersistentDataType.DOUBLE, maxMana)
    }

    /**
     * Set the player's mana per second
     * @param player The player to set the mana per second of
     * @param manaPerSec The amount of mana per second to set
     */
    fun setManaPerSec(player: Player, manaPerSec: Double) {
        val playerData = player.persistentDataContainer
        playerData.set(Keys.MANA_PER, PersistentDataType.DOUBLE, manaPerSec)
    }
}
