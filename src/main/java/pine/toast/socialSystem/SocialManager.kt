package toast.pine.socialSystem

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import toast.pine.Classes.Quests.Quest
import java.util.*
import java.util.function.Consumer

class SocialManager : Listener {
    private val groups: MutableSet<Group> = HashSet()
    private val nextMessageCallbacks: MutableMap<UUID, Consumer<String>?> = HashMap<UUID, Consumer<String>?>()
    private val questSet: MutableSet<Quest> = HashSet<Quest>()

    fun getNextMessageCallbacks(): Map<UUID, Consumer<String>?> {
        return java.util.Map.copyOf<UUID, Consumer<String>>(nextMessageCallbacks)
    }

    fun addNextMessageCallback(playerId: UUID, callback: Consumer<String>?) {
        nextMessageCallbacks[playerId] = callback
    }

    fun removeNextMessageCallback(playerId: UUID) {
        nextMessageCallbacks.remove(playerId)
    }

    /**
     * Gets a group by String name
     * @param groupName The name of the group.
     * @return The group.
     */
    fun findGroupByName(groupName: String): Group? {
        for (group in groups) {
            if (group.groupName == groupName) {
                return group
            }
        }
        return null
    }

    fun addGroup(group: Group) {
        groups.add(group)
    }

    fun deleteGroup(group: Group) {
        group.clearGroup()
        groups.remove(group)
    }

    fun addQuest(quest: Quest) {
        questSet.add(quest)
    }

    fun removeQuest(quest: Quest) {
        questSet.remove(quest)
    }

    val quests: Set<Any>
        get() = java.util.Set.copyOf(questSet)


    @EventHandler
    private fun onChat(event: AsyncPlayerChatEvent) {
        val receiver: Player = event.getPlayer()
        val playerId: UUID = receiver.getUniqueId()
        if (nextMessageCallbacks.containsKey(playerId)) {
            val message: String = event.getMessage()
            val callback = nextMessageCallbacks.remove(playerId)!!
            callback.accept(message)
        }
    }
}
