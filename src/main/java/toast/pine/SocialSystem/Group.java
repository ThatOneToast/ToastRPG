package toast.pine.SocialSystem;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Group {

    protected PlayerSocial leader;
    protected Set<PlayerSocial> members;
    protected Date dateCreated;
    protected String groupName;
    protected List<String> groupDescription;
    protected Map<PlayerSocial, Date> playerJoinDate;
    protected Map<PlayerSocial, Date> playerLeaveDate;

    public Group(PlayerSocial leader, Set<PlayerSocial> members, Date dateCreated, String groupName, List<String> groupDescription, Map<PlayerSocial, Date> playerJoinDate, Map<PlayerSocial, Date> playerLeaveDate) {
        this.leader = leader;
        this.members = members;
        this.dateCreated = dateCreated;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.playerJoinDate = playerJoinDate;
        this.playerLeaveDate = playerLeaveDate;
    }

    public void setLeader(PlayerSocial leader) {
        this.leader = leader;
    }

    public void removeMember(PlayerSocial member) {
        this.members.remove(member);
    }
    public void addMember(PlayerSocial member) {
        this.members.add(member);
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupDescription(List<String> groupDescription) {
        this.groupDescription = groupDescription;
    }

    public void setPlayerJoinDate(Map<PlayerSocial, Date> playerJoinDate) {
        this.playerJoinDate = playerJoinDate;
    }

    public void setPlayerLeaveDate(Map<PlayerSocial, Date> playerLeaveDate) {
        this.playerLeaveDate = playerLeaveDate;
    }

    public PlayerSocial getLeader() {
        return leader;
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

    public Map<PlayerSocial, Date> getPlayerJoinDate() {
        return Map.copyOf(playerJoinDate);
    }

    public Map<PlayerSocial, Date> getPlayerLeaveDate() {
        return Map.copyOf(playerLeaveDate);
    }


    void clearGroup() {
        this.leader = null;
        this.members = null;
        this.dateCreated = null;
        this.groupName = null;
        this.groupDescription = null;
        this.playerJoinDate = null;
        this.playerLeaveDate = null;
    }


}
