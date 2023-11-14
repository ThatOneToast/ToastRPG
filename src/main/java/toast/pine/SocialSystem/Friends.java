package toast.pine.SocialSystem;

import org.bukkit.entity.Player;
import toast.pine.ColorAPI.Colors;
import toast.pine.Events.PlayerRespondFriendInvite;
import toast.pine.Events.PlayerSendFriendInviteEvent;
import toast.pine.ToastRPG;

import java.util.*;
import java.util.function.Consumer;

public class Friends {
    private final Set<PlayerSocial> friends;
    private final List<PlayerSocial> friendInvites;
    private final PlayerSocial player;

    /**
     * This class shouldn't really be called as it's handled by the PlayerSocial class.
     * If you are making your management, pass the player who is managing the friends.
     * @param actionUser The player who is managing the friends.
     */
    public Friends(PlayerSocial actionUser) {
        this.friends = new HashSet<>();
        this.friendInvites = new ArrayList<>();
        this.player = actionUser;
    }

    /**
     * Adds a friend to the player's friend list.
     * @param friend The friend to add.
     */
    public void addFriend(PlayerSocial friend) {
        friends.add(friend);
    }

    /**
     * Removes a friend from the player's friend list.
     * @param friend The friend to remove.
     */
    public void removeFriend(PlayerSocial friend) {
        friends.remove(friend);
    }

    /**
     *
     * @return All of the players friends.
     */
    public Set<PlayerSocial> getFriends() {
        return Set.copyOf(friends);
    }

    /**
     * Checks if the player is friends with the player.
     * @param friend The player to check if they are friends with.
     * @return True if the player is friends with the player.
     */
    public boolean isFriendsWith(PlayerSocial friend) {
        return friends.contains(friend);
    }

    /**
     * Sends a friend invite to the player.
     * @param receiverSocial The player to send the friend invite to.
     Note: PlayerSendFriendInvite, and PlayerRespondFriendInvite events will be called due to this.
     Manage them how you wish.
     */
    public void sendInvite(PlayerSocial receiverSocial) {
        if (!receiverSocial.friendInvites.contains(player)) {
            Player receiver = receiverSocial.getPlayer();
            String senderName = player.getPlayer().getName();
            String receiverName = receiver.getName();

            String formattedMessage = senderName + " has requested to be your friend!";
            receiver.sendMessage(formattedMessage);
            friendInvites.add(receiverSocial);

            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerSendFriendInviteEvent(player, receiverSocial));
            receiverSocial.friendInvites.add(player);
            receiver.sendMessage("Please say 'friend accept' to join the group. ");

            prepareResponse(receiver, message -> {
                if (message.equalsIgnoreCase("friend accept")) {

                    addFriend(receiverSocial);
                    receiverSocial.getFriends().addFriend(player);
                    receiverSocial.friendInvites.remove(player);
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRespondFriendInvite(player, receiverSocial, true));

                    receiver.sendMessage(Colors.GRAY + "You have accepted the friend invitation.");
                    player.getPlayer().sendMessage(Colors.GRAY + receiverName + " has accepted your friend invitation.");
                    friendInvites.remove(receiverSocial);
                } else {
                    receiverSocial.friendInvites.remove(player);
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRespondFriendInvite(player, receiverSocial, false));
                    receiver.sendMessage(Colors.GRAY + "You have declined the friend invitation.");
                    player.getPlayer().sendMessage(Colors.GRAY + receiverName + " has declined your friend invitation.");
                    friendInvites.remove(receiverSocial);
                }
            });
        } else {
            player.getPlayer().sendMessage(Colors.GRAY + "You have already sent a friend request to this player.");
        }

    }

    void prepareResponse(Player receiver, Consumer<String> callback) {
        UUID receiverId = receiver.getUniqueId();
        ToastRPG.getSocialManager().addNextMessageCallback(receiverId, callback);
        receiver.sendMessage(Colors.GRAY + "Please respond to the invitation...");
    }
}