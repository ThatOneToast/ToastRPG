package pine.toast.toastrpg.events

import org.bukkit.entity.LivingEntity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.monsters.Monster
import pine.toast.toastrpg.monsters.MonsterType

class MonsterSpawnEvent(livingEntity: LivingEntity?, monster: Monster, monsterType: MonsterType) : Event(),

    Cancellable {
    private val monsterType: MonsterType
    private var cancelled = false
    val entity: LivingEntity?
        get() = livingEntity

    fun getCancelled(): Boolean {
        return cancelled
    }

    init {
        Companion.livingEntity = livingEntity
        Companion.monster = monster
        this.monsterType = monsterType
    }

    val monster: Monster?
        get() = Companion.monster

    fun getMonsterType(): MonsterType {
        return monsterType
    }

    override fun getHandlers(): HandlerList {
        return Companion.handlers
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    companion object {
        private val handlers: HandlerList = HandlerList()
        private var monster: Monster? = null
        private var livingEntity: LivingEntity? = null
        val handlerList: HandlerList
            get() = handlers
    }
}
