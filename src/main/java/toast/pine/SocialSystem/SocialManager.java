package toast.pine.SocialSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import toast.pine.Classes.Quests.Quest;

import java.util.*;
import java.util.function.Consumer;

public class SocialManager implements Listener {

    private final Set<Group> groups = new HashSet<>();
    private final Map<UUID, Consumer<String>> nextMessageCallbacks = new HashMap<>();
    private final Set<Quest> questSet = new HashSet<>();


    public Map<UUID, Consumer<String>> getNextMessageCallbacks() {
        return Map.copyOf(nextMessageCallbacks);
    }


    public void addNextMessageCallback(UUID playerId, Consumer<String> callback) {
        nextMessageCallbacks.put(playerId, callback);
    }

    public void removeNextMessageCallback(UUID playerId) {
        nextMessageCallbacks.remove(playerId);
    }

    /**
     * Gets a group by String name
     * @param groupName The name of the group.
     * @return The group.
     */
    public Group findGroupByName(String groupName) {
        for (Group group : groups) {
            if (group.groupName.equals(groupName)) {
                return group;
            }
        }
        return null;
    }


    void addGroup(Group group) {
        groups.add(group);
    }


    void deleteGroup(Group group) {
        group.clearGroup();
        groups.remove(group);
    }

    public void addQuest(Quest quest) {
        questSet.add(quest);
    }

    public void removeQuest(Quest quest) {
        questSet.remove(quest);
    }

    public Set<Quest> getQuests() {
        return Set.copyOf(questSet);
    }



    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        Player receiver = event.getPlayer();
        UUID playerId = receiver.getUniqueId();

        if (nextMessageCallbacks.containsKey(playerId)) {
            String message = event.getMessage();

            Consumer<String> callback = nextMessageCallbacks.remove(playerId);
            callback.accept(message);
        }
    }

}
