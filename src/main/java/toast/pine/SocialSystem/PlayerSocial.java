package toast.pine.SocialSystem;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.ColorAPI.Colors;
import toast.pine.Events.PlayerRespondGroupInvite;
import toast.pine.Events.PlayerSendGroupInviteEvent;
import toast.pine.Keys;
import toast.pine.ToastRPG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public class PlayerSocial {

    protected Group group;
    protected Player player;
    protected Friends friends;
    protected String rank;
    protected List<String> permissions;
    protected List<PlayerSocial> friendInvites;
    protected List<PlayerSocial> groupInvites;

    /**
     * This should really be called, this is handled by me when the the player joins the server.
     */
    public PlayerSocial(Group group, Player player, String rank, List<String> permissions) {
        this.group = group;
        this.player = player;
        this.rank = rank;
        this.permissions = permissions;
        this.friendInvites = new ArrayList<>();
        this.groupInvites = new ArrayList<>();
        this.friends = new Friends(this);
    }

    /**
     * Gets the group the player is in.
     * @return The group the player is in.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the group the player is in.
     * @param group The group the player is in.
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Gets the player.
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the friends of the player.
     * @return The friends of the player.
     */
    public Friends getFriends() {
        return friends;
    }

    /**
     * Sets the player.
     * @param player The player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the rank of the player.
     * @return The rank of the player.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Sets the rank of the player.
     * @param rank The rank of the player.
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Gets the permissions of the player. for groups.
     * @return The permissions of the player.
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions of the player. for groups.
     * @param permissions The permissions of the player.
     */
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    /**
     * adds a permission. for groups.
     * @param permission the permission.
     */
    public void addPermission(String permission) {
        this.permissions.add(permission);
    }


    /**
     * sends an invite to a player to join the group.
     * @param receiverSocial The player to send the invite to.
     */
    void sendGroupInvite(PlayerSocial receiverSocial) {
        if (!receiverSocial.groupInvites.contains(this) && !receiverSocial.getGroup().equals(this.group)) {
            Player sender = this.player;
            Player receiver = receiverSocial.getPlayer();
            String senderName = sender.getName();
            String receiverName = receiver.getName();

            String formattedMessage = senderName + " has invited you to join their group!";
            receiver.sendMessage(formattedMessage);

            receiverSocial.groupInvites.add(this);
            receiver.sendMessage("Please say 'group accept'  to join the group. ");
            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerSendGroupInviteEvent(this, receiverSocial, group));
            PersistentDataContainer receiverData = receiver.getPersistentDataContainer();
            PersistentDataContainer senderData = sender.getPersistentDataContainer();

            PlayerSocial senderSocial = senderData.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());


            prepareResponse(player, message -> {
                if (message.equalsIgnoreCase("group accept")) {
                    Group group = senderSocial.getGroup();
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRespondGroupInvite(this, receiverSocial, group, true));
                    joinGroup(receiverSocial);
                    receiver.sendMessage(Colors.GRAY + "You have joined the group " + Colors.GREEN + group.groupName + "!");
                    sender.sendMessage(Colors.GRAY + receiverName + " has accepted your group invitation!");
                } else {
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRespondGroupInvite(this, receiverSocial, group, false));
                    receiver.sendMessage(Colors.GRAY + "You have declined the group invitation.");
                    sender.sendMessage(Colors.GRAY + receiverName + " has declined your group invitation.");
                }
            });

        } else {
            this.player.sendMessage(Colors.GRAY + "This player has already been invited to a group.");
        }

    }

    /**
     * The user who will join this users current group
     * @param newMember The person to join this users group.
     */
    public void joinGroup(PlayerSocial newMember) {
        if (this.group == null) {

            Set<PlayerSocial> members = this.group.getMembers();
            for (PlayerSocial member : members) {
                if (!member.getPlayer().isOnline()) return;
                member.getPlayer().sendMessage(Colors.GREEN + newMember.getPlayer().getName() + Colors.GRAY + " has joined the group.");
            }
            newMember.setGroup(this.group);
            this.group.addMember(newMember);
        } else {
            this.player.sendMessage(Colors.GRAY + "You are already in a group.");
        }
    }

    /**
     * Leaves the current group
     */
    public void leaveGroup() {
        if (this.group == null) {
            this.player.sendMessage(Colors.GRAY + "You are not in a group.");
            return;
        } else {
            Set<PlayerSocial> members = this.group.getMembers();
            for (PlayerSocial member : members) {
                if (!member.getPlayer().isOnline()) return;
                member.getPlayer().sendMessage(Colors.GRAY + this.player.getName() + " has left the group.");
            }
            this.group.removeMember(this);
            this.group = null;
        }
    }

    /**
     * Internal method to prepare a response from a player. please do not use.
     */
    void prepareResponse(Player receiver, Consumer<String> callback) {
        UUID receiverId = receiver.getUniqueId();
        ToastRPG.getSocialManager().addNextMessageCallback(receiverId, callback);
        receiver.sendMessage(Colors.GRAY + "Please respond to the invitation...");
    }


    /**
     * Sends a private message to the player.
     * @param receiverSocial The player to send the message to.
     * @param message The message to send.
     */
    void sendPrivateMessage(PlayerSocial receiverSocial, String message) {
        Player sender = this.player;
        Player receiver = receiverSocial.getPlayer();
        String senderName = sender.getName();
        String receiverName = receiver.getName();

        String formattedMessage = Colors.DARK_PURPLE + "You -> " + Colors.GRAY + receiverName + " : " + message;
        sender.sendMessage(formattedMessage);

        formattedMessage = Colors.DARK_PURPLE + senderName + Colors.GRAY + " -> you: " + message;
        receiver.sendMessage(formattedMessage);
    }
}
