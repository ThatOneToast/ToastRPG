package toast.pine;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerAttributes extends BukkitRunnable implements Listener {

    /**
     @param player - The player to set the damage attribute of
     @param amount - The amount to set the damage attribute to
    */

    public static void setDamage(Player player, double amount) {
        // Set the damage attribute of the player to the amount specified
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(amount);

    }

    /**
        @param player - The player to add the damage attribute to
        @param amount - The amount to add to the damage attribute
     */
    public static void addDamage(Player player, double amount) {
        // Add the amount specified to the damage attribute of the player
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + amount);
    }

    /**
     *
     * @param player - The player to remove the damage attribute from
     * @param amount - The amount to remove from the damage attribute
     */
    public static void removeDamage(Player player, double amount) {
        // Remove the amount specified from the damage attribute of the player
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() - amount);
    }

    /**
     *
     * @param player - The player to get the damage attribute from
     * @return - The damage value attribute of the player
     */

    public static double getDamage(Player player) {
        // Get the damage attribute of the player
        return player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
    }


    // ---------------- \\

    /**
     * @param player - The player to set the defense attribute of
     * @param amount - The amount to set the defense attribute to
     */
    public static void setDefense(Player player, double amount) {
        // Set the defense attribute of the player to the amount specified

        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(amount);

    }

    /**
     *
     * @param player - The player to add the defense attribute to
     * @param amount - The amount to add to the defense attribute
     */
    public static void addDefense(Player player, double amount) {

        Double base = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(base + amount);


    }

    /**
     *
     * @param player - The player to remove the defense attribute to
     * @param amount - The amount to remove to the defense attribute
     */
    public static void removeDefense(Player player, double amount) {

        Double base = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(base - amount);

    }

    /**
     *
     * @param player - The player to get the defense attribute from
     * @return - The defense value attribute of the player
     */
    public static Double getDefense(Player player) {
        return player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
    }

    // ------- \\

    /**
     *
     * @param player - The player to set the max health attribute of
     * @return - The max health value attribute of the player
     */
    public static Double getMaxHealth(Player player) {
        // Set the max health attribute of the player to the amount specified

        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

    }

    /**
     *
     * @param player - The player to set the max health attribute of
     * @param amount - The amount to set the max health attribute to
     */
    public static void setMaxHealth(Player player, double amount) {

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(amount);

    }

    /**
     *
     * @param player - The player to add the max health attribute to
     * @param amount - The amount to add to the max health attribute
     */
    public static void addMaxHealth(Player player, double amount) {

        Double base = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(base + amount);

    }

    /**
     *
     * @param player - The player to remove the max health attribute from
     * @param amount - The amount to remove from the max health attribute
     */
    public static void removeMaxHealth(Player player, double amount) {

        Double base = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(base - amount);
    }



    /**
     *
     * @param player - The player to add the health attribute to
     * @param amount - The amount to add to the health attribute
     */
    public static void addHealth(Player player, Double amount){

        Double base = player.getHealth();
        player.setHealth(base + amount);


    }


    /**
     *
     * @param player - The player to remove the health attribute from
     * @param amount - The amount to remove from the health attribute
     */
    public static void removeHealth(Player player, Double amount){

        Double base = player.getHealth();
        player.setHealth(base - amount);
    }



    // ------ \\
    private static Map<UUID, Double> mana = new HashMap<>();

    /**
     * Get the player's current mana
     * @param player The player to get the mana of
     * @return The player's current mana
     */
    public static double getMana(Player player) {
        return mana.getOrDefault(player.getUniqueId(), 0.0);
    }

    /**
     * Set the player's current mana
     * @param player The player to set the mana of
     * @param amount The amount to set the player's mana to
     */
    public static void setMana(Player player, double amount) {
        mana.put(player.getUniqueId(), amount);
    }


    /**
     * Get the player's maximum mana
     * @param player The player to get the maximum mana of
     * @return The player's maximum mana
     */
    public static double getMaxMana(Player player) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        if(!(playerData.has(Keys.MAX_MANA, PersistentDataType.DOUBLE))) {
            Bukkit.getLogger().warning("You have not registered values! defaulting to 1");
            setMaxMana(player, 1);
        }

        return playerData.get(Keys.MAX_MANA, PersistentDataType.DOUBLE);

    }

    /**
     * Get the player's mana per second
     * @param player The player to get the mana per second of
     * @return The player's mana per second
    */
    public static double getManaPerSec(Player player) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        if(!(playerData.has(Keys.MANA_PER, PersistentDataType.DOUBLE))) {
            Bukkit.getLogger().warning("You have not registered values! defaulting to 0.1");
            setManaPerSec(player, 0.1);
        }

        return playerData.get(Keys.MANA_PER, PersistentDataType.DOUBLE);
    }

    /**
     * Subtract mana from the player
     * @param player The player to subtract mana from
     * @param manaToSubtract The amount of mana to subtract
     */
    public static void subtractMana(Player player, double manaToSubtract) {
        // Get the player's current mana
        double currentMana = mana.getOrDefault(player.getUniqueId(), 0.0);

        currentMana -= manaToSubtract;

        if (currentMana < 0) {
            currentMana = 0;
        }

        mana.put(player.getUniqueId(), currentMana);
    }


    /**
     * Add mana to the player
     * @param player The player to add mana to
     * @param maxMana The amount of mana to add
     */
    public static void setMaxMana(Player player, double maxMana) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        playerData.set(Keys.MAX_MANA, PersistentDataType.DOUBLE, maxMana);
    }

    /**
     * Set the player's mana per second
     * @param player The player to set the mana per second of
     * @param manaPerSec The amount of mana per second to set
     */
    public static void setManaPerSec(Player player, double manaPerSec) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        playerData.set(Keys.MANA_PER, PersistentDataType.DOUBLE, manaPerSec);
    }

    /**
     * This task starts the mana regeneration for all players
     */
    @Override
    public void run() {
        // Loop through all online players and update their mana
        for (Player player : Bukkit.getOnlinePlayers()) {
            double currentMana = mana.getOrDefault(player.getUniqueId(), 0.0);
            double manaPerSec = getManaPerSec(player);

            currentMana += manaPerSec;

            // Ensure that the current mana doesn't exceed the maximum mana
            double maxMana = getMaxMana(player);
            if (currentMana > maxMana) {
                currentMana = maxMana;
            }

            // Update the player's mana in the mana map
            mana.put(player.getUniqueId(), currentMana);
        }
    }


}
