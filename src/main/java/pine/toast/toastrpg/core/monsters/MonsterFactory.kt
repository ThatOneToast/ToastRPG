package pine.toast.toastrpg.core.monsters

import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.events.MonsterDeathEvent
import pine.toast.toastrpg.core.events.MonsterSpawnEvent
import pine.toast.toastrpg.core.events.MonsterTargetPlayerEvent

class MonsterFactory : Listener {
    private val monsters: MutableMap<Monster, LivingEntity> = HashMap()

    init {
        ToastRPG.getPassedPlugin()!!.logger.info(" - MonsterFactory ~ Started")
    }

    /**
     * Marks a monster as a monster.
     * And registers their handlers.
     * @param monster The monster to mark.
     */
    fun markMonster(monster: Monster, entity: LivingEntity) {
        monsters[monster] = entity
    }

    /**
     * Checks if a living entity is a monster.
     * @param livingEntity The living entity to check.
     * @return True if the living entity is a monster.
     */
    fun isMonster(livingEntity: LivingEntity): Boolean {
        return livingEntity.persistentDataContainer.has(TKeys.MONSTER_TYPE, ToastRPG.getAdapterManager()!!.monsterTypeAdapter)
    }

    /**
     * Gets the monster from a living entity.
     * @param livingEntity The living entity to get the monster from.
     */
    fun getMonster(livingEntity: LivingEntity): Monster {
        val monsterType: MonsterType = livingEntity.persistentDataContainer.get(TKeys.MONSTER_TYPE, ToastRPG.getAdapterManager()!!.monsterTypeAdapter)!!
        for (monster in monsters.keys) {
            if (monster.getType() == monsterType) {
                return monster
            }
        }

        throw NullPointerException("Monster not found")

    }



    private fun handleMonsterSpawn(event: MonsterSpawnEvent) {
        val handler = event.getMonsterType().getMonsterHandlerClass().getDeclaredConstructor().newInstance()
        handler?.onMonsterSpawn(event)
    }

    private fun handleMonsterDeath(event: MonsterDeathEvent) {
        val handler = event.getMonsterType().getMonsterHandlerClass().getDeclaredConstructor().newInstance()
        handler?.onMonsterDeath(event)
    }

    private fun handlerMonsterTarget(event: MonsterTargetPlayerEvent) {
        val handler = event.getMonsterType().getMonsterHandlerClass().getDeclaredConstructor().newInstance()
        handler?.onMonsterTarget(event)
    }


    @EventHandler
    private fun onMonsterSpawn(event: MonsterSpawnEvent) {
        ToastRPG.getMonsterFactory()!!.handleMonsterSpawn(event)
    }

    @EventHandler
    private fun onMonsterDeath(event: MonsterDeathEvent) {
        ToastRPG.getMonsterFactory()!!.handleMonsterDeath(event)
    }


    @EventHandler
    private fun onMonsterTarget(event: MonsterTargetPlayerEvent) {
        ToastRPG.getMonsterFactory()!!.handlerMonsterTarget(event)
    }

}