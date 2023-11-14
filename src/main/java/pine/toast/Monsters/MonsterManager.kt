package toast.pine.Monsters

import org.bukkit.entity.LivingEntity

class MonsterManager {
    private val monsters: MutableMap<LivingEntity, Monster> = HashMap()
    private val monsterTypes: MutableSet<MonsterType> = HashSet()

    /**
     * Links an entity to a monster
     * @param monster The monster
     */
    fun addMonster(monster: Monster) {
        val entity = monster.getLivingEntity()
        monsters[entity] = monster
    }

    /**
     * Removes a monster from the manager
     * @param entity The entity
     */
    fun removeMonster(entity: LivingEntity) {
        monsters.remove(entity)
    }

    /**
     * Adds a monster type to the manager
     * @param monsterType The monster type
     */
    fun addMonsterType(monsterType: MonsterType) {
        monsterTypes.add(monsterType)
    }

    /**
     * Removes a monster type from the manager
     * @param monsterType The monster type
     */
    fun removeMonsterType(monsterType: MonsterType) {
        monsterTypes.remove(monsterType)
    }

    /**
     * Gets the monster type of an entity
     * @param entity The entity
     * @return The monster type of the entity
     */
    fun getMonster(entity: LivingEntity): Monster {
        return monsters[entity]!!
    }

    fun getMonsterTypes(): Set<MonsterType> {
        return java.util.Set.copyOf(monsterTypes)
    }

    fun getMonsters(): Map<LivingEntity, Monster> {
        return java.util.Map.copyOf(monsters)
    }

    /**
     * Checks if an entity is a monster
     * @param entity The entity
     * @return Whether or not the entity is a monster
     */
    fun isMonster(entity: LivingEntity): Boolean {
        return monsters.containsKey(entity)
    }
}
