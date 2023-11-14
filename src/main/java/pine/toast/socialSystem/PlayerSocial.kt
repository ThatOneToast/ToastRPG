package toast.pine.socialSystem

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import toast.pine.ColorAPI.Colors
import toast.pine.Events.PlayerRespondGroupInvite
import toast.pine.Events.PlayerSendGroupInviteEvent
import pine.toast.Keys
import pine.toast.ToastRPG
import java.util.*
import java.util.function.Consumer

class PlayerSocial(

   private var group: Group?, player: Player, rank: String?, permissions: MutableList<String>?

) {
    private var player: Player
    private var friends: Friends
    private lateinit var rank: String
    private lateinit var permissions: MutableList<String>
    var friendInvites: MutableList<PlayerSocial?>
    private var groupInvites: MutableList<PlayerSocial>

    /**
     * This should really be called, this is handled by me when the the player joins the server.
     */
    init {
        this.player = player
        if (rank != null) {
            this.rank = rank
        }
        if (permissions != null) {
            this.permissions = permissions
        }
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
    fun getPermissions(): List<String> {
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
        permissions.add(permission)
    }

    /**
     * sends an invite to a player to join the group.
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
            val senderSocial: PlayerSocial? = ToastRPG.getAdapterManager()?.let { senderData.get(Keys.SOCIAL_PROFILE, it.socialProfileAdapter) }

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
     * The user who will join this users current group
     * @param newMember The person to join this users group.
     */
    private fun joinGroup(newMember: PlayerSocial) {
        if (this.group == null) {
            for (member in group!!.getMembers()) {
                if (!member.getPlayer().isOnline) return
                member.getPlayer().sendMessage(
                    (Colors.GREEN + newMember.getPlayer().name + Colors.GRAY).toString() + " has joined the group."
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
        if (group == null) {
            player.sendMessage(Colors.GRAY + "You are not in a group.")
            return
        } else {
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
