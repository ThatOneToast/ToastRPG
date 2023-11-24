package pine.toast.toastrpg.monsters

import org.bukkit.entity.LivingEntity
import pine.toast.toastrpg.entities.EntityHandler
import pine.toast.toastrpg.events.MonsterDeathEvent
import pine.toast.toastrpg.events.MonsterSpawnEvent
import pine.toast.toastrpg.events.MonsterTargetPlayerEvent

class MonsterFactory {
    private val monsters: MutableMap<Monster, EntityHandler> = HashMap()


    /**
     * Marks a monster as a monster.
     * And registers their handlers.
     * @param monster The monster to mark.
     */
    fun markMonster(monster: Monster) {
        monsters[monster] = monster.getMonsterHandler()
    }

    /**
     * Checks if a living entity is a monster.
     * @param livingEntity The living entity to check.
     * @return True if the living entity is a monster.
     */
    fun isMonster(livingEntity: LivingEntity): Boolean {
        return monsters.keys.any { it.getLivingEntity() == livingEntity }
    }

    /**
     * Gets the monster from a living entity.
     * @param livingEntity The living entity to get the monster from.
     */
    fun getMonster(livingEntity: LivingEntity): Monster {
        return monsters.keys.first { it.getLivingEntity() == livingEntity }
    }


    fun handleMonsterSpawn(event: MonsterSpawnEvent) {
        val handler = monsters[event.monster]
        handler?.onMonsterSpawn(event)
    }

    fun handleMonsterDeath(event: MonsterDeathEvent) {
        val handler = monsters[event.getMonster()]
        handler?.onMonsterDeath(event)
    }

    fun handlerMonsterTarget(event: MonsterTargetPlayerEvent) {
        val handler = monsters[event.getMonster()]
        handler?.onMonsterTarget(event)
    }



}