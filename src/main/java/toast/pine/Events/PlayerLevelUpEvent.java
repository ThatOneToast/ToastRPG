package toast.pine.Events;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerLevelUpEvent extends Event {

    private final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Integer level;
    private final Integer maxLevel;
    private final Integer experience;

    public PlayerLevelUpEvent(Player player, Integer level, Integer maxLevel, Integer experience) {
        this.player = player;
        this.level = level;
        this.maxLevel = maxLevel;
        this.experience = experience;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public Integer getExperience() {
        return experience;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
