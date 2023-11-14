package toast.pine.socialSystem

import org.bukkit.entity.Player
import toast.pine.ColorAPI.Colors
import toast.pine.Events.PlayerRespondFriendInvite
import toast.pine.Events.PlayerSendFriendInviteEvent
import pine.toast.ToastRPG
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
    private fun addFriend(friend: PlayerSocial) {
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

    /**
     * Sends a friend invite to the player.
     * @param receiverSocial The player to send the friend invite to.
     * Note: PlayerSendFriendInvite, and PlayerRespondFriendInvite events will be called due to this.
     * Manage them how you wish.
     */
    fun sendInvite(receiverSocial: PlayerSocial) {
        if (!receiverSocial.friendInvites.contains(player)) {
            val receiver: Player = receiverSocial.getPlayer()
            val senderName = player.getPlayer().name
            val receiverName: String = receiver.name
            val formattedMessage = "$senderName has requested to be your friend!"

            receiver.sendMessage(formattedMessage)
            friendInvites.add(receiverSocial)
            ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerSendFriendInviteEvent(player, receiverSocial))
            receiverSocial.friendInvites.add(player)
            receiver.sendMessage("Please say 'friend accept' to join the group. ")

            prepareResponse(receiver) { message: String ->
                if (message.equals("friend accept", ignoreCase = true)) {
                    addFriend(receiverSocial)
                    receiverSocial.getFriends().addFriend(player)
                    receiverSocial.friendInvites.remove(player)
                    ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerRespondFriendInvite(player, receiverSocial, true))
                    receiver.sendMessage(Colors.GRAY + "You have accepted the friend invitation.")
                    player.getPlayer().sendMessage(Colors.GRAY + receiverName + " has accepted your friend invitation.")
                    friendInvites.remove(receiverSocial)
                } else {
                    receiverSocial.friendInvites.remove(player)
                    ToastRPG.getPassedPlugin()!!.server.pluginManager
                        .callEvent(PlayerRespondFriendInvite(player, receiverSocial, false))
                    receiver.sendMessage(Colors.GRAY + "You have declined the friend invitation.")
                    player.getPlayer().sendMessage(Colors.GRAY + receiverName + " has declined your friend invitation.")
                    friendInvites.remove(receiverSocial)
                }
            }
        } else {
            player.getPlayer().sendMessage(Colors.GRAY + "You have already sent a friend request to this player.")
        }
    }

    private fun prepareResponse(receiver: Player?, callback: Consumer<String>?) {
        val receiverId: UUID = receiver!!.uniqueId
        ToastRPG.getSocialManager()!!.addNextMessageCallback(receiverId, callback)
        receiver.sendMessage(Colors.GRAY + "Please respond to the invitation...")
    }
}