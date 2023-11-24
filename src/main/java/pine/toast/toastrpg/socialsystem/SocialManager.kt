package pine.toast.toastrpg.socialsystem

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.*
import java.util.function.Consumer

class SocialManager : Listener {
    private val groups: MutableSet<Group> = HashSet()
    private val nextMessageCallbacks: MutableMap<UUID, Consumer<String>?> = HashMap()

    fun getNextMessageCallbacks(): Map<UUID, Consumer<String>?> = nextMessageCallbacks.toMap()

    fun addNextMessageCallback(playerId: UUID, callback: Consumer<String>?) {
        nextMessageCallbacks[playerId] = callback
    }

    fun removeNextMessageCallback(playerId: UUID) {
        nextMessageCallbacks.remove(playerId)
    }

    fun findGroupByName(groupName: String): Group? = groups.find { it.groupName == groupName }

    fun addGroup(group: Group) {
        groups.add(group)
    }

    fun deleteGroup(group: Group) {
        group.clearGroup()
        groups.remove(group)
    }


    @EventHandler
    private fun onChat(event: AsyncPlayerChatEvent) {
        val receiver: Player = event.player
        val playerId: UUID = receiver.uniqueId
        if (nextMessageCallbacks.containsKey(playerId)) {
            val message: String = event.message
            val callback = nextMessageCallbacks.remove(playerId)!!
            callback.accept(message)
        }
    }
}
