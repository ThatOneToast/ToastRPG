package toast.pine.Monsters;

import toast.pine.ToastRPG;

import java.util.Set;

public class CalcMonsterAttributes {




    /**
     * Calculates the multiplier of monster attributes based on the player level
     * @param playerLevel The level of the player
     * @return The multiplier of monster attributes
     */
    private static double Multiplier(int playerLevel) {
        // Define the base multiplier for low-level players
        double baseMultiplier = 1.0;

        // Define a factor to increase difficulty
        double difficultyFactor = 0.1;

        // Calculate the multiplier
        double multiplier = baseMultiplier + (playerLevel - 1) * difficultyFactor;

        return multiplier;
    }


    /**
     * Determines the multiplier done to attributes based off of its type
     * @param type The type of monster
     * @return Double value ( Multiplier )
     */
    private static double typeMultiplier(MonsterType type) {
        Set<MonsterType> monsters = ToastRPG.getMonsterManager().getMonsterTypes();
        int index = 0;
        double multiplier = 1.0;

        for (MonsterType monster : monsters) {
            if (monster.equals(type)) {
                multiplier += 0.2 * index;
                break;
            }
            index++;
        }
        return multiplier;
    }

    /**
     * Calculates the health of a player based on their level
     * @param Type The type of monster
     * @param PlayerLevel The level of the player
     * @return entity health based off player Level.
     */
    private static double health(MonsterType Type, Integer PlayerLevel) {
        double levelMultiplier = Multiplier(PlayerLevel);
        double typeMultiplier = typeMultiplier(Type);
        double baseHealth = 50.0;

        return (levelMultiplier * typeMultiplier) * baseHealth;


    }

    /**
     * Calculates the damage of a player based on their level and the monster type
     * @param Type The type of monster
     * @param PlayerLevel The level of the player
     * @return entity damage based on player level and monster type.
     */
    private static double damage(MonsterType Type, Integer PlayerLevel) {
        double levelMultiplier = Multiplier(PlayerLevel);
        double typeMultiplier = typeMultiplier(Type);
        double baseDamage = 5.5; // Adjust the base damage as needed

        return (levelMultiplier * typeMultiplier) * baseDamage;
    }



}
