package toast.pine.socialSystem

import pine.toast.ToastRPG
import java.util.*

class Group(leader: PlayerSocial, groupName: String?, groupDescription: List<String>?) {

    private var leader: PlayerSocial?
    private var members: MutableSet<PlayerSocial>?
    private var dateCreated: Date
    var groupName: String
    private var groupDescription: List<String>?
    private var playerJoinDate: MutableMap<PlayerSocial, Date>?
    private var playerLeaveDate: MutableMap<PlayerSocial, Date>?


    init {
        this.leader = leader
        this.groupName = groupName!!
        this.groupDescription = groupDescription
        members = HashSet()
        dateCreated = Date()
        playerJoinDate = HashMap()
        playerLeaveDate = HashMap()
        (members as HashSet<PlayerSocial>).add(leader)
        leader.addPermission("group.manage")
        (playerJoinDate as HashMap<PlayerSocial, Date>)[leader] = Date()
        ToastRPG.getSocialManager()?.addGroup(this)
    }

    /**
     * Removes a member from the group.
     * @param member The member to remove.
     */
    fun removeMember(member: PlayerSocial) {
        members!!.remove(member)
        setPlayerLeaveDate(member)
    }

    /**
     * Adds a member to the group.
     * @param member The member to add.
     */
    fun addMember(member: PlayerSocial) {
        members!!.add(member)
        setPlayerJoinDate(member)
    }

    /**
     * Sets the description of the group.
     * @param groupDescription The new description of the group.
     */
    fun setGroupDescription(groupDescription: List<String>?) {
        this.groupDescription = groupDescription
    }

    private fun setPlayerJoinDate(player: PlayerSocial) {
        playerJoinDate!![player] = Date()
    }

    private fun setPlayerLeaveDate(player: PlayerSocial) {
        playerLeaveDate!![player] = Date()
    }

    fun getMembers(): Set<PlayerSocial> {
        return java.util.Set.copyOf(members)
    }

    fun getGroupDescription(): List<String> {
        return java.util.List.copyOf(groupDescription)
    }

    /**
     * Gets the date the player joined the group.
     * @param player The player to get the join date of.
     * @return The date the player joined the group.
     */
    fun getPlayerJoinDate(player: PlayerSocial): Date? {
        return playerJoinDate!![player]
    }

    /**
     * Gets the date the player left the group.
     * @param player The player to get the leave date of.
     * @return The date the player left the group.
     */
    fun getPlayerLeaveDate(player: PlayerSocial): Date? {
        return playerLeaveDate!![player]
    }

    fun clearGroup() {
        leader = null
        members = null
        dateCreated = Date()
        groupName = ""
        groupDescription = null
        playerJoinDate = null
        playerLeaveDate = null
        ToastRPG.getSocialManager()?.deleteGroup(this)
    }

    fun isInGroupWith(player: PlayerSocial): Boolean {
        return members!!.contains(player)
    }
}
