package toast.pine.Events

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import toast.pine.Monsters.Monster
import toast.pine.Monsters.MonsterType

class MonsterTargetPlayerEvent(monster: Monster, monsterType: MonsterType, distance: Double, target: Player) : Event(),
    Cancellable {
    private var cancelled: Boolean
    private var monster: Monster
    private var monsterType: MonsterType
    private var distance: Double
    private var target: Player

    init {
        this.monster = monster
        this.monsterType = monsterType
        this.distance = distance
        cancelled = false
        this.target = target
    }

    fun getTarget(): Player {
        return target
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

    override fun setCancelled(bool: Boolean) {
        cancelled = bool
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    companion object {
        private val HANDLERS: HandlerList = HandlerList()
    }
}
