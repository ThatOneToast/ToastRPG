package pine.toast.toastrpg.core.socialsystem

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.colorapi.Colors
import pine.toast.toastrpg.core.events.PlayerRespondFriendInvite
import pine.toast.toastrpg.core.events.PlayerRespondGroupInvite
import pine.toast.toastrpg.core.events.PlayerSendFriendInviteEvent
import pine.toast.toastrpg.core.events.PlayerSendGroupInviteEvent
import java.util.*
import java.util.function.Consumer

class PlayerSocial(

    private var group: Group?, player: Player, rank: String?, permissions: MutableList<String>?

) {
    private var player: Player
    private var friends: Friends
    private var rank: String?
    private var permissions: MutableList<String>?
    private var friendInvites: MutableList<PlayerSocial?>
    private var groupInvites: MutableList<PlayerSocial>

    init {
        this.player = player
        this.rank = rank
        this.permissions = permissions
        friendInvites = ArrayList()
        groupInvites = ArrayList()
        friends = Friends(this)
    }

    fun getFriends(): Friends {
        return friends
    }

    /**
     * Gets the player.
     * @return The player.
     */
    fun getPlayer(): Player {
        return player
    }

    /**
     * Sets the player.
     * @param player The player.
     */
    fun setPlayer(player: Player) {
        this.player = player
    }

    /**
     * Gets the permissions of the player. for groups.
     * @return The permissions of the player.
     */
    fun getPermissions(): List<String>? {
        return permissions
    }

    /**
     * Sets the permissions of the player. for groups.
     * @param permissions The permissions of the player.
     */
    fun setPermissions(permissions: MutableList<String>) {
        this.permissions = permissions
    }

    /**
     * adds a permission. for groups.
     * @param permission the permission.
     */
    fun addPermission(permission: String) {
        permissions?.add(permission)
    }

    /**
     * sends an invitation to a player to join the group.
     * @param receiverSocial The player to send the invite to.
     */
    fun sendGroupInvite(receiverSocial: PlayerSocial) {
        if (!receiverSocial.groupInvites.contains(this) && receiverSocial.group != group) {
            val sender: Player = player
            val receiver: Player = receiverSocial.getPlayer()
            val senderName: String = sender.name
            val receiverName: String = receiver.name
            val formattedMessage = "$senderName has invited you to join their group!"

            receiver.sendMessage(formattedMessage)
            receiverSocial.groupInvites.add(this)
            receiver.sendMessage("Please say 'group accept'  to join the group. ")
            ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerSendGroupInviteEvent(this, receiverSocial, group))

            val senderData: PersistentDataContainer = sender.persistentDataContainer
            val senderSocial: PlayerSocial? = ToastRPG.getAdapterManager()?.let { senderData.get(TKeys.SOCIAL_PROFILE, it.socialProfileAdapter) }

            prepareResponse(player) { message: String ->
                if (message.equals("group accept", ignoreCase = true)) {
                    val group = senderSocial?.group
                    ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerRespondGroupInvite(this, receiverSocial, group, true))
                    joinGroup(receiverSocial)
                    receiver.sendMessage((Colors.GRAY + "You have joined the group " + Colors.GREEN) + group!!.groupName + "!")
                    sender.sendMessage(Colors.GRAY + receiverName + " has accepted your group invitation!")
                } else {
                    ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerRespondGroupInvite(this, receiverSocial, group, false))
                    receiver.sendMessage(Colors.GRAY + "You have declined the group invitation.")
                    sender.sendMessage(Colors.GRAY + receiverName + " has declined your group invitation.")
                }
            }
        } else {
            player.sendMessage(Colors.GRAY + "This player has already been invited to a group.")
        }
    }

    /**
     * Sends a friend invite to the player.
     * @param receiverSocial The player to send the friend invite to.
     * Note: PlayerSendFriendInvite, and PlayerRespondFriendInvite events will be called due to this.
     * Manage them how you wish.
     */
    fun sendFriendRequest(sender: PlayerSocial, receiverSocial: PlayerSocial) {
        if (!receiverSocial.friendInvites.contains(sender)) {
            val receiver: Player = receiverSocial.getPlayer()
            val senderName = sender.getPlayer().name
            val receiverName: String = receiver.name
            val formattedMessage = "$senderName has requested to be your friend!"

            receiver.sendMessage(formattedMessage)
            friendInvites.add(receiverSocial)
            receiverSocial.friendInvites.add(sender)
            receiver.sendMessage("Please say 'friend accept' to join the group. ")

            prepareResponse(receiver) { message: String ->
                if (message.equals("friend accept", ignoreCase = true)) {
                    sender.getFriends().addFriend(receiverSocial)
                    receiverSocial.getFriends().addFriend(sender)
                    receiverSocial.friendInvites.remove(sender)
                    ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerRespondFriendInvite(sender, receiverSocial, true))
                    receiver.sendMessage(Colors.GRAY + "You have accepted the friend invitation.")
                    sender.getPlayer().sendMessage(Colors.GRAY + receiverName + " has accepted your friend invitation.")
                    friendInvites.remove(receiverSocial)
                } else {
                    receiverSocial.friendInvites.remove(sender)
                    ToastRPG.getPassedPlugin()!!.server.pluginManager
                        .callEvent(PlayerRespondFriendInvite(sender, receiverSocial, false))
                    receiver.sendMessage(Colors.GRAY + "You have declined the friend invitation.")
                    sender.getPlayer().sendMessage(Colors.GRAY + receiverName + " has declined your friend invitation.")
                    friendInvites.remove(receiverSocial)
                }
            }
        } else {
            sender.getPlayer().sendMessage(Colors.GRAY + "You have already sent a friend request to this player.")
        }

        ToastRPG.getPassedPlugin()!!.server.pluginManager.callEvent(PlayerSendFriendInviteEvent(sender, receiverSocial))
    }


    /**
     * The user who will join this users current group
     * @param newMember The person to join this users group.
     */
    private fun joinGroup(newMember: PlayerSocial) {
        if (this.group == null) {
            for (member in group!!.getMembers()) {
                if (!member.getPlayer().isOnline) return
                member.getPlayer().sendMessage(
                    (Colors.GREEN + newMember.getPlayer().name + Colors.GRAY) + " has joined the group."
                )
            }
            newMember.group = group
            group!!.addMember(newMember)
        } else {
            player.sendMessage(Colors.GRAY + "You are already in a group.")
        }
    }

    /**
     * Leaves the current group
     */
    fun leaveGroup() {
        if (group == null) player.sendMessage(Colors.GRAY + "You are not in a group.")
        else {
            val members = group!!.getMembers()
            for (member in members) {
                if (!member.getPlayer().isOnline) return
                member.getPlayer().sendMessage(Colors.GRAY + player.name + " has left the group.")
            }
            group!!.removeMember(this)
            group = null
        }
    }

    /**
     * Internal method to prepare a response from a player. please do not use.
     */
    private fun prepareResponse(receiver: Player, callback: Consumer<String>?) {
        val receiverId: UUID = receiver.uniqueId
        ToastRPG.getSocialManager()?.addNextMessageCallback(receiverId, callback)
        receiver.sendMessage(Colors.GRAY + "Please respond to the invitation...")
    }

    /**
     * Sends a private message to the player.
     * @param receiverSocial The player to send the message to.
     * @param message The message to send.
     */
    fun sendPrivateMessage(receiverSocial: PlayerSocial, message: String) {
        val sender: Player = player
        val receiver: Player = receiverSocial.getPlayer()
        val senderName: String = sender.name
        val receiverName: String = receiver.name
        var formattedMessage: String = (Colors.DARK_PURPLE + "You -> " + Colors.GRAY) + receiverName + " : " + message
        sender.sendMessage(formattedMessage)
        formattedMessage = (Colors.DARK_PURPLE + senderName + Colors.GRAY) + " -> you: " + message
        receiver.sendMessage(formattedMessage)
    }
}
