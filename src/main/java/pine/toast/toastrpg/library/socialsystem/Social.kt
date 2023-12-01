package pine.toast.toastrpg.library.socialsystem

import org.bukkit.entity.Player
import pine.toast.toastrpg.library.Colors
import pine.toast.toastrpg.library.ToastRPG
import pine.toast.toastrpg.library.events.PlayerLevelUpEvent
import java.io.Serializable

class Social(
    private var player: Player,
    private var level: Int,
    private var maxLevel: Int,
    private var experience: Int,
    private var expToLevel: Int,

) : Serializable {


    private lateinit var friends: MutableList<Social>
    private lateinit var pendingInvites: MutableList<Social>
    private lateinit var clan: Clan

    /**
     * Returns a copy of the players, friends
     */
    fun getFriends(): MutableList<Social> {
        return friends.toMutableList()
    }

    fun getNextFriendRequest(): Social {
        return pendingInvites[0]
    }

    /**
     * Adds a friend to the players friends list.
     * @param friend The friend to add.
     */
    private fun addFriend(friend: Social) {
        friends.add(friend)
    }

    /**
     * Removes a friend from the players friends list.
     * @param friend The friend to remove.
     */
    private fun removeFriend(friend: Social) {
        friends.remove(friend)
    }


    fun sendFriendInvite(friend: Social) {
        friend.pendingInvites.add(this)
        friend.player.sendMessage(Colors.GRAY + "You have been invited to be friends with " + this.player.name)
        friend.player.sendMessage(Colors.GRAY + " /frs - to show your next pending friend requests")
        friend.player.sendMessage(Colors.GRAY + " /frs accept - to accept the current friend request")
        friend.player.sendMessage(Colors.GRAY + " /frs deny - to deny the current friend request")
        this.player.sendMessage(Colors.GRAY + "Friend request sent to" + Colors.GREEN + friend.player.name)
    }

    fun acceptFriendRequest(acceptAll: Boolean) {
        if (acceptAll) {
            for (friend in this.pendingInvites) {
                friend.player.sendMessage(Colors.GREEN + "Your friend request to " + this.player.name + " has been accepted.")
                this.addFriend(friend)
                friend.addFriend(this)
            }
            this.pendingInvites.clear()
        } else {
            this.pendingInvites[0].player.sendMessage(Colors.GREEN + "Your friend request to " + this.player.name + " has been accepted.")
            this.addFriend(this.pendingInvites[0])
            this.pendingInvites[0].addFriend(this)
            this.pendingInvites.removeAt(0)
        }

    }

    fun denyFriendRequest(denyAll: Boolean) {
        if (denyAll) {
            for (friend in this.pendingInvites) {
                friend.player.sendMessage(Colors.GREEN + "Your friend request to " + this.player.name + " has been denied.")
            }
            this.pendingInvites.clear()
        } else {
            this.pendingInvites[0].player.sendMessage(Colors.GREEN + "Your friend request to " + this.player.name + " has been denied.")
            this.pendingInvites.removeAt(0)
        }
    }

    fun joinClan(clan: Clan, joinCode: String) {
        if (clan.getJoinCode() != null && clan.getJoinCode() == joinCode) {

            clan.addMember(this)
            val members = clan.getMembers()
            for (member in members) {
                if (!member.getPlayer().isOnline) return
                member.getPlayer().sendMessage(
                    (Colors.GREEN + this.getPlayer().name + Colors.GRAY) + " has joined the clan."
                )
            }
            this.clan = clan
        } else {
            this.player.sendMessage(Colors.GRAY + "The join code you entered is incorrect.")

        }
    }

    fun leaveClan() {
        for (member in this.clan.getMembers()) {
            if (!member.getPlayer().isOnline) return
            member.getPlayer().sendMessage(
                (Colors.GREEN + this.getPlayer().name + Colors.GRAY) + " has left the clan."
            )
        }
        clan.removeMember(this); this.clan
    }




    /**
     * Gets the player.
     * @return The player.
     */
    fun getPlayer(): Player {
        return player
    }

    fun getLevel(): Int {
        return level
    }

    fun getMaxLevel(): Int {
        return maxLevel
    }

    fun getExperience(): Int {
        return experience
    }

    fun setLevel(level: Int) {
        this.level = level
        this.experience = 0
    }

    fun setMaxLevel(maxLevel: Int) {
        this.maxLevel = maxLevel
    }

    fun setExperience(experience: Int) {
        var xp = experience
        while(xp >= expToLevel) {
            this.level++
            ToastRPG.getPlugin()!!.server.pluginManager.callEvent(PlayerLevelUpEvent(this.player, this.level, this.maxLevel, this.experience))
            this.experience -= expToLevel
            this.player.sendMessage(Colors.GREEN + "You leveled up!")
            xp -= expToLevel
        }
    }

    fun addExperience(addXP: Int) {

        this.experience += addXP
        if (addXP >= expToLevel) {
            this.level++
            ToastRPG.getPlugin()!!.server.pluginManager.callEvent(PlayerLevelUpEvent(this.player, this.level, this.maxLevel, this.experience))
            this.experience -= expToLevel
            this.player.sendMessage(Colors.GREEN + "You leveled up!")
        }


    }


}