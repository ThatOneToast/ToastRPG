package pine.toast.toastrpg.core.socialsystem

import org.bukkit.entity.Player
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.colorapi.Colors
import java.util.*
import java.util.function.Consumer

class Friends(actionUser: PlayerSocial) {
    private val friends: MutableSet<PlayerSocial>
    private val friendInvites: MutableList<PlayerSocial>
    private val player: PlayerSocial

    init {
        friends = HashSet()
        friendInvites = ArrayList()
        player = actionUser
    }

    /**
     * Adds a friend to the player's friend list.
     * @param friend The friend to add.
     */
    fun addFriend(friend: PlayerSocial) {
        friends.add(friend)
    }

    /**
     * Removes a friend from the player's friend list.
     * @param friend The friend to remove.
     */
    fun removeFriend(friend: PlayerSocial) {
        friends.remove(friend)
    }

    /**
     *
     * @return All the players friends.
     */
    fun getFriends(): Set<PlayerSocial> {
        return java.util.Set.copyOf(friends)
    }

    /**
     * Checks if the player is friends with the player.
     * @param friend The player to check if they are friends with.
     * @return True if the player is friends with the player.
     */
    fun isFriendsWith(friend: PlayerSocial): Boolean {
        return friends.contains(friend)
    }


    private fun prepareResponse(receiver: Player?, callback: Consumer<String>?) {
        val receiverId: UUID = receiver!!.uniqueId
        ToastRPG.getSocialManager()!!.addNextMessageCallback(receiverId, callback)
        receiver.sendMessage(Colors.GRAY + "Please respond to the invitation...")
    }
}