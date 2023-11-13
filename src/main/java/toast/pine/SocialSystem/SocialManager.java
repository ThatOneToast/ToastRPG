package toast.pine.SocialSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;
import java.util.function.Consumer;

public class SocialManager implements Listener {

    private final Set<Group> groups = new HashSet<>();
    private final Map<UUID, Consumer<String>> nextMessageCallbacks = new HashMap<>();

    public Group createGroup(PlayerSocial leader, String groupName, List<String> groupDescription) {
        Map<PlayerSocial, Date> playerJoinDate = new HashMap<>();
        Map<PlayerSocial, Date> playerLeaveDate = new HashMap<>();
        Set<PlayerSocial> members = new HashSet<>();
        List<String> permissions = new ArrayList<>();
        Date dateCreated = new Date();

        permissions.add("group.manage");
        leader.setPermissions(permissions);

        playerJoinDate.put(leader, dateCreated);

        return new Group(leader, members, dateCreated, groupName, groupDescription, playerJoinDate, playerLeaveDate);

    }

    public Map<UUID, Consumer<String>> getNextMessageCallbacks() {
        return Map.copyOf(nextMessageCallbacks);
    }

    public void addNextMessageCallback(UUID playerId, Consumer<String> callback) {
        nextMessageCallbacks.put(playerId, callback);
    }

    public void removeNextMessageCallback(UUID playerId) {
        nextMessageCallbacks.remove(playerId);
    }

    public Group findGroupByName(String groupName) {
        for (Group group : groups) {
            if (group.groupName.equals(groupName)) {
                return group;
            }
        }
        return null;
    }


    public void addGroup(Group group) {
        groups.add(group);
    }


    public void deleteGroup(Group group) {
        group.clearGroup();
        groups.remove(group);
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player receiver = event.getPlayer();
        UUID playerId = receiver.getUniqueId();

        if (nextMessageCallbacks.containsKey(playerId)) {
            String message = event.getMessage();

            Consumer<String> callback = nextMessageCallbacks.remove(playerId);
            callback.accept(message);
        }
    }

}
