package pine.toast.toastrpg.events

import org.bukkit.entity.LivingEntity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.monsters.Monster
import pine.toast.toastrpg.monsters.MonsterType

class MonsterDeathEvent(entity: LivingEntity, monster: Monster, monsterType: MonsterType) : Event(), Cancellable {
    private var entity: LivingEntity
    private var monster: Monster
    private var monsterType: MonsterType
    private var cancelled = false
    private val handlers = HandlerList()

    init {
        this.entity = entity
        this.monster = monster
        this.monsterType = monsterType
    }

    fun getEntity(): LivingEntity {
        return entity
    }

    fun setEntity(entity: LivingEntity) {
        this.entity = entity
    }

    fun getMonster(): Monster {
        return monster
    }

    fun setMonster(monster: Monster) {
        this.monster = monster
    }

    fun getMonsterType(): MonsterType {
        return monsterType
    }

    fun setMonsterType(monsterType: MonsterType) {
        this.monsterType = monsterType
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(b: Boolean) {
        cancelled = b
    }

    override fun getHandlers(): HandlerList {
        return handlers
    }

}
