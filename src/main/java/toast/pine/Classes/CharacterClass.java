package toast.pine.Classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import toast.pine.PlayerAttributes;
import toast.pine.ToastRPG;

public abstract class CharacterClass {
    private final double maxMana;
    private final double defense;
    private final int maxLevel;
    private final double manaPerSec;
    private final String name;
    private final Material icon;
    private final int health;
    private final int damage;



    public CharacterClass(String className, Material icon, int health, int damage, double maxMana, double manaPerSec, double defense, int maxLevel) {
        this.name = className;
        this.icon = icon;
        this.health = health;
        this.damage = damage;
        this.maxMana = maxMana;
        this.manaPerSec = manaPerSec;
        this.defense = defense;
        this.maxLevel = maxLevel;
    }



    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }


    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }


    public double getMaxMana() {
        return maxMana;
    }
    public double getManaPerSec() {
        return manaPerSec;
    }

    public double getDefense() {
        return defense;
    }
    public int getMaxLevel() {
        return maxLevel;
    }


    /**
    * Call this method to set the players attributes to the class's specifications.
    * @param player - The player to set the attributes of
    * @param characterClass - The class to set the attributes to
    * @param healthScale - The health scale to set the player to
     */
    public void enforceClass(Player player, CharacterClass characterClass, int healthScale) {
        player.setHealthScale(healthScale);

        PlayerAttributes.setMaxHealth(player, characterClass.getHealth());
        player.setHealth(characterClass.getHealth());
        PlayerAttributes.setDamage(player, characterClass.getDamage());
        PlayerAttributes.setDefense(player, characterClass.getDefense());
        PlayerAttributes.setMaxMana(player, characterClass.getMaxMana());
        PlayerAttributes.setManaPerSec(player, characterClass.getManaPerSec());
        ToastRPG.getLevelManager().setMaxLevel(player, characterClass.getMaxLevel());


        player.setHealth(characterClass.getHealth());
        player.setFoodLevel(200);
        player.setSaturation(200f);

    }


}