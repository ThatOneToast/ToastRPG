package toast.pine.LevelSystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import toast.pine.Events.PlayerLevelUpEvent;
import toast.pine.Keys;
import toast.pine.ToastRPG;

public class LevelManager {

    private int Experience_Until_Level_Up = 105;

    /**
     * Sets the amount of experience needed to level up
     * @param amount The amount of experience needed to level up
     Default: 105
     */
    public void setExperience_Until_Level_Up(int amount) {
        Experience_Until_Level_Up = amount;
    }

    /**
     * Gets the amount of experience needed to level up
     * @return The amount of experience needed to level up
     */
    public int getExperience_Until_Level_Up() {
        return Experience_Until_Level_Up;
    }


    /**
     * Adds experience to the player
     * @param player The player to add experience to
     * @param amount The amount of experience to add
     Note: If the player's experience is greater than or equal to the amount of experience needed to level up,
        the player will proceed to the next level.
     */
    public void addExperience(Player player, int amount) {
        int currentLevel = player.getPersistentDataContainer().get(Keys.LEVEL, PersistentDataType.INTEGER);
        int EXP = player.getPersistentDataContainer().get(Keys.EXP, PersistentDataType.INTEGER);
        int MAX_LEVEL = player.getPersistentDataContainer().get(Keys.MAX_LEVEL, PersistentDataType.INTEGER);

        if (EXP + amount >= Experience_Until_Level_Up) {
            setLevel(player, currentLevel + 1);
            setExperience(player, 0);
            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerLevelUpEvent(player, currentLevel, MAX_LEVEL, EXP));
        } else {
            player.getPersistentDataContainer().set(Keys.EXP, PersistentDataType.INTEGER, EXP + amount);
        }
    }


    /**
     * Removes experience from the player
     * @param player The player to remove experience from
     * @param amount The amount of experience to remove
     */
    public void removeExperience(Player player, int amount) {
        int EXP = player.getPersistentDataContainer().get(Keys.EXP, PersistentDataType.INTEGER);

        player.getPersistentDataContainer().set(Keys.EXP, PersistentDataType.INTEGER, EXP - amount);

    }

    /**
     * Sets the player's experience
     * @param player The player to set the experience of
     * @param amount The amount of experience to set
     Note: This is not intended for use, please use addExperience or removeExperience instead.
     */
    public void setExperience(Player player, int amount) {
        player.getPersistentDataContainer().set(Keys.EXP, PersistentDataType.INTEGER, amount);
    }

    /**
     * Sets the player's level
     * @param player The player to set the level of
     * @param LEVEL The level to set the player to
     Note: This will grant the rewards upon leveling up, just as addExperience does.
        This method can be finicky at times.
     */
    public void setLevel(Player player, int LEVEL) {
        int MAX_LEVEL = player.getPersistentDataContainer().get(Keys.MAX_LEVEL, PersistentDataType.INTEGER);
        int currentLevel = player.getPersistentDataContainer().get(Keys.LEVEL, PersistentDataType.INTEGER);

        if (LEVEL >= MAX_LEVEL) {
            player.sendMessage(ChatColor.RED + "You have reached the max LEVEL!");
            player.getPersistentDataContainer().set(Keys.LEVEL, PersistentDataType.INTEGER, MAX_LEVEL);
        } else {
            for (int i = 0; i < LEVEL; i++); {
                addExperience(player, Experience_Until_Level_Up);
            };
        }
    }

    /**
     * Sets the player's max level
     * @param player The player to set the max level of
     * @param MAX_LEVEL The max level to set the player to
     */
    public void setMaxLevel(Player player, int MAX_LEVEL) {
        player.getPersistentDataContainer().set(Keys.MAX_LEVEL, PersistentDataType.INTEGER, MAX_LEVEL);
    }

    /**
     * Gets the player's experience
     * @param player The player to get the experience of
     * @return The player's experience
     */
    public Integer getExperience(Player player) {
        return player.getPersistentDataContainer().get(Keys.EXP, PersistentDataType.INTEGER);
    }

    /**
     * Gets the player's level
     * @param player The player to get the level of
     * @return The player's level
     */
    public Integer getLevel(Player player) {
        return player.getPersistentDataContainer().get(Keys.LEVEL, PersistentDataType.INTEGER);
    }

    /**
     * Gets the player's max level
     * @param player The player to get the max level of
     * @return The player's max level
     */
    public Integer getMaxLevel(Player player) {
        return player.getPersistentDataContainer().get(Keys.MAX_LEVEL, PersistentDataType.INTEGER);
    }


}
