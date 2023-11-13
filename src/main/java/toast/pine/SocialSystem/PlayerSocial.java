package toast.pine.SocialSystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
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
    protected String rank;
    protected List<String> permissions;
    protected List<PlayerSocial> friendInvites;
    protected List<PlayerSocial> groupInvites;

    public PlayerSocial(Group group, Player player, String rank, List<String> permissions) {
        this.group = group;
        this.player = player;
        this.rank = rank;
        this.permissions = permissions;
        this.friendInvites = new ArrayList<>();
        this.groupInvites = new ArrayList<>();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    void sendGroupInvite(PlayerSocial receiverSocial) {
        if (!receiverSocial.groupInvites.contains(this) && !receiverSocial.getGroup().equals(this.group)) {
            Player sender = this.player;
            Player receiver = receiverSocial.getPlayer();
            String senderName = sender.getName();
            String receiverName = receiver.getName();

            String formattedMessage = senderName + " has invited you to join their group!";
            receiver.sendMessage(formattedMessage);

            receiverSocial.groupInvites.add(this);
            receiver.sendMessage("Please say 'accept " + group.groupName + "' to join the group. ");
            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerSendGroupInviteEvent(this, receiverSocial, group));
            PersistentDataContainer receiverData = receiver.getPersistentDataContainer();
            PersistentDataContainer senderData = sender.getPersistentDataContainer();

            PlayerSocial senderSocial = senderData.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());


            prepareResponse(player, message -> {
                if (message.equalsIgnoreCase("group accept")) {
                    Group group = senderSocial.getGroup();
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRespondGroupInvite(this, receiverSocial, group, true));
                    joinGroup(receiverSocial);
                    receiver.sendMessage(ChatColor.GRAY + "You have joined the group " + ChatColor.GREEN + group.groupName + "!");
                    sender.sendMessage(ChatColor.GRAY + receiverName + " has accepted your group invitation!");
                } else {
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRespondGroupInvite(this, receiverSocial, group, false));
                    receiver.sendMessage(ChatColor.GRAY + "You have declined the group invitation.");
                    sender.sendMessage(ChatColor.GRAY + receiverName + " has declined your group invitation.");
                }
            });
        } else {
            this.player.sendMessage(ChatColor.GRAY + "This player has already been invited to a group.");
        }

    }

    public void joinGroup(PlayerSocial newMember) {
        Set<PlayerSocial> members = this.group.getMembers();
        for (PlayerSocial member : members) {
            if(!member.getPlayer().isOnline()) return;
            member.getPlayer().sendMessage(ChatColor.GREEN + newMember.getPlayer().getName() +  ChatColor.GRAY + " has joined the group.");
        }
        newMember.setGroup(this.group);
        this.group.addMember(newMember);
    }

    public void leaveGroup() {
        Set<PlayerSocial> members = this.group.getMembers();
        for (PlayerSocial member : members) {
            if(!member.getPlayer().isOnline()) return;
            member.getPlayer().sendMessage(ChatColor.GRAY + this.player.getName() + " has left the group.");
        }
        this.group.removeMember(this);
        this.group = null;
    }

    public void prepareResponse(Player receiver, Consumer<String> callback) {
        UUID receiverId = receiver.getUniqueId();
        ToastRPG.getSocialManager().addNextMessageCallback(receiverId, callback);
        receiver.sendMessage(ChatColor.GRAY + "Please respond to the invitation...");
    }



    void sendPrivateMessage(PlayerSocial receiverSocial, String message) {
        Player sender = this.player;
        Player receiver = receiverSocial.getPlayer();
        String senderName = sender.getName();
        String receiverName = receiver.getName();

        String formattedMessage = ChatColor.DARK_PURPLE + "You -> " + ChatColor.GRAY + receiverName + " : " + message;
        sender.sendMessage(formattedMessage);

        formattedMessage = ChatColor.DARK_PURPLE + senderName + ChatColor.GRAY + " -> you: " + message;
        receiver.sendMessage(formattedMessage);
    }
}
