package toast.pine.LevelSystem

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import toast.pine.ColorAPI.Colors
import toast.pine.Events.PlayerGainExperienceEvent
import pine.toast.Keys
import pine.toast.ToastRPG

class LevelManager {

    private var experienceUntilLevelUp = 105

    /**
     * Adds experience to the player
     * @param player The player to add experience to
     * @param amount The amount of experience to add
     * Note: If the player's experience is greater than or equal to the amount of experience needed to level up,
     * the player will proceed to the next level.
     */
    fun addExperience(player: Player, amount: Int) {
        val currentLevel: Int = player.persistentDataContainer.get(Keys.LEVEL, PersistentDataType.INTEGER)!!
        val exp: Int = player.persistentDataContainer.get(Keys.EXP, PersistentDataType.INTEGER)!!
        val maxLevel: Int = player.persistentDataContainer.get(Keys.MAX_LEVEL, PersistentDataType.INTEGER)!!

        if (exp >= experienceUntilLevelUp) {
            if ((currentLevel + 1) > maxLevel) {
                player.sendMessage(Colors.GREEN + "You are max level.")
            } else {
                player.sendMessage(Colors.GREEN + "You have leveled up! You are now level -> " + (currentLevel + 1))
                player.persistentDataContainer.set(Keys.LEVEL,PersistentDataType.INTEGER, currentLevel + 1)
            }


        }

        ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerGainExperienceEvent(player, amount, currentLevel ))


    }

    /**
     * Removes experience from the player
     * @param player The player to remove experience from
     * @param amount The amount of experience to remove
     */
    fun removeExperience(player: Player, amount: Int) {
        val exp: Int = player.persistentDataContainer.get(Keys.EXP, PersistentDataType.INTEGER)!!
        player.persistentDataContainer.set(Keys.EXP, PersistentDataType.INTEGER, exp - amount)
    }

    /**
     * Sets the player's experience
     * @param player The player to set the experience of
     * @param amount The amount of experience to set
     * Note: This is not intended for use, please use addExperience or removeExperience instead.
     */
    fun setExperience(player: Player, amount: Int) {
        player.persistentDataContainer.set(Keys.EXP, PersistentDataType.INTEGER, amount)
    }

    /**
     * Sets the player's level
     * @param player The player to set the level of
     * @param LEVEL The level to set the player to
     * Note: This will grant the rewards upon leveling up, just as addExperience does.
     * This method can be finicky at times.
     */
    fun setLevel(player: Player, LEVEL: Int) {
        val maxLevel: Int = player.persistentDataContainer.get(Keys.MAX_LEVEL, PersistentDataType.INTEGER)!!

        if (LEVEL >= maxLevel) {
            player.sendMessage(Colors.RED + "You have reached the max LEVEL!")
            player.persistentDataContainer.set(Keys.LEVEL, PersistentDataType.INTEGER, maxLevel)
        } else {
            for (i in 0 until LEVEL) run { addExperience(player, this.experienceUntilLevelUp) }


        }
    }

    /**
     * Sets the player's max level
     * @param player The player to set the max level of
     * @param MAX_LEVEL The max level to set the player to
     */
    fun setMaxLevel(player: Player, MAX_LEVEL: Int) {
        player.persistentDataContainer.set(Keys.MAX_LEVEL, PersistentDataType.INTEGER, MAX_LEVEL)
    }

    /**
     * Gets the player's experience
     * @param player The player to get the experience of
     * @return The player's experience
     */
    fun getExperience(player: Player): Int {
        return player.persistentDataContainer.get(Keys.EXP, PersistentDataType.INTEGER)!!
    }

    /**
     * Gets the player's level
     * @param player The player to get the level of
     * @return The player's level
     */
    fun getLevel(player: Player): Int {
        return player.getPersistentDataContainer().get(Keys.LEVEL, PersistentDataType.INTEGER)!!
    }

    /**
     * Gets the player's max level
     * @param player The player to get the max level of
     * @return The player's max level
     */
    fun getMaxLevel(player: Player): Int {
        return player.getPersistentDataContainer().get(Keys.MAX_LEVEL, PersistentDataType.INTEGER)!!
    }
}
