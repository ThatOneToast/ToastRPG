package toast.pine.SocialSystem;

import toast.pine.ToastRPG;

import java.util.*;

public class Group {

    protected PlayerSocial leader;
    protected Set<PlayerSocial> members;
    protected Date dateCreated;
    protected String groupName;
    protected List<String> groupDescription;
    protected Map<PlayerSocial, Date> playerJoinDate;
    protected Map<PlayerSocial, Date> playerLeaveDate;

    /**
     * Creates a new group.
     * @param leader The leader of the group.
     * @param groupName The name of the group.
     * @param groupDescription The description of the group.
     */
    public Group(PlayerSocial leader, String groupName, List<String> groupDescription) {
        this.leader = leader;
        this.groupName = groupName;
        this.groupDescription = groupDescription;

        this.members = new HashSet<>();
        this.dateCreated = new Date();
        this.playerJoinDate = new HashMap<>();
        this.playerLeaveDate = new HashMap<>();


        members.add(leader);
        leader.addPermission("group.manage");
        playerJoinDate.put(leader, new Date());
        ToastRPG.getSocialManager().addGroup(this);
    }

    /**
     * Gets the leader of the group.
     * @return The leader of the group.
     */
    public PlayerSocial getLeader() {
        return leader;
    }

    /**
     * Sets the leader of the group.
     * @param leader The new leader of the group.
     */
    public void setLeader(PlayerSocial leader) {
        this.leader = leader;
    }

    /**
     * Removes a member from the group.
     * @param member The member to remove.
     */
    public void removeMember(PlayerSocial member) {
        this.members.remove(member);
        setPlayerLeaveDate(member);
    }

    /**
     * Adds a member to the group.
     * @param member The member to add.
     */
    public void addMember(PlayerSocial member) {
        this.members.add(member);
        setPlayerJoinDate(member);
    }

    /**
     * Sets the name of the group
     * @param groupName  The new name of the group.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Sets the description of the group.
     * @param groupDescription The new description of the group.
     */
    public void setGroupDescription(List<String> groupDescription) {
        this.groupDescription = groupDescription;
    }


    void setPlayerJoinDate(PlayerSocial player) {
        playerJoinDate.put(player, new Date());
    }

    void setPlayerLeaveDate(PlayerSocial player) {
        playerLeaveDate.put(player, new Date());
    }



    public Set<PlayerSocial> getMembers() {
        return Set.copyOf(members);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getGroupName() {
        return groupName;
    }


    public List<String> getGroupDescription() {
        return List.copyOf(groupDescription);
    }

    /**
     * Gets the date the player joined the group.
     * @param player The player to get the join date of.
     * @return The date the player joined the group.
     */
    public Date getPlayerJoinDate(PlayerSocial player) {
        return playerJoinDate.get(player);
    }

    /**
     * Gets the date the player left the group.
     * @param player The player to get the leave date of.
     * @return The date the player left the group.
     */
    public Date getPlayerLeaveDate(PlayerSocial player) {
        return playerLeaveDate.get(player);
    }


    void clearGroup() {
        this.leader = null;
        this.members = null;
        this.dateCreated = null;
        this.groupName = null;
        this.groupDescription = null;
        this.playerJoinDate = null;
        this.playerLeaveDate = null;
        ToastRPG.getSocialManager().deleteGroup(this);
    }

    public boolean isInGroupWith(PlayerSocial player) {
        return this.members.contains(player);
    }


}
