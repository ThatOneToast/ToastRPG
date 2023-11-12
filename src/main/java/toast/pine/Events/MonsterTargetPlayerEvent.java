package toast.pine.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.Monsters.Monster;
import toast.pine.Monsters.MonsterType;

public class MonsterTargetPlayerEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    Monster monster;
    MonsterType monsterType;
    Double distance;
    Player target;


    public MonsterTargetPlayerEvent(Monster monster, MonsterType monsterType, Double distance, Player target) {
        this.monster = monster;
        this.monsterType = monsterType;
        this.distance = distance;
        this.cancelled = false;
        this.target = target;
    }


    public Player getTarget() {
        return target;
    }
    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    /**
     * @return the distance between the monster and the player.
     */
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean bool) {
        cancelled = bool;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
