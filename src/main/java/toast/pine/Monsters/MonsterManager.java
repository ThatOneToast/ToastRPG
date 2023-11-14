package toast.pine.Monsters;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonsterManager {

    private final Map<LivingEntity, Monster> monsters = new HashMap<>();
    private final Set<MonsterType> monsterTypes = new HashSet<>();


    /**
     * Links an entity to a monster
     * @param entity The entity
     * @param monster The monster

     */
    public void addMonster(LivingEntity entity, Monster monster) {
        monsters.put(entity, monster);
    }

    /**
     * Removes a monster from the manager
     * @param entity The entity
     */
    public void removeMonster(LivingEntity entity) {
        monsters.remove(entity);
    }

    /**
     * Adds a monster type to the manager
     * @param monsterType The monster type
     */
    public void addMonsterType(MonsterType monsterType) {
        monsterTypes.add(monsterType);
    }

    /**
     * Removes a monster type from the manager
     * @param monsterType The monster type
     */
    public void removeMonsterType(MonsterType monsterType) {
        monsterTypes.remove(monsterType);
    }

    /**
     * Gets the monster type of an entity
     * @param entity The entity
     * @return The monster type of the entity
     */
    public Monster getMonster(LivingEntity entity) {
       return monsters.get(entity);
    }


    public Set<MonsterType> getMonsterTypes() {
        return Set.copyOf(monsterTypes);
    }

    public Map<LivingEntity, Monster> getMonsters() {
        return Map.copyOf(monsters);
    }


    /**
     * Checks if an entity is a monster
     * @param entity The entity
     * @return Whether or not the entity is a monster
     */
    public Boolean isMonster(LivingEntity entity) {
        return monsters.containsKey(entity);
    }



}
