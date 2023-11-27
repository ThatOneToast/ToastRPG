package pine.toast.toastrpg.events

import org.bukkit.entity.LivingEntity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.monsters.Monster
import pine.toast.toastrpg.monsters.MonsterType

class MonsterSpawnEvent(
    private val livingEntity: LivingEntity?,
    private val monster: Monster,
    private val monsterType: MonsterType
) : Event(), Cancellable {
    private var cancelled = false
    private val handlers = HandlerList()

    fun getCancelled(): Boolean {
        return cancelled
    }

    fun getMonsterType(): MonsterType {
        return monsterType
    }

    fun getMonster(): Monster {
        return monster
    }

    override fun getHandlers(): HandlerList {
        return handlers
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
}
