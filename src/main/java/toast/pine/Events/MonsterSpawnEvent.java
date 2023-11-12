package toast.pine.Events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.Monsters.Monster;
import toast.pine.Monsters.MonsterType;


public class MonsterSpawnEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private static Monster monster;
    private final MonsterType monsterType;
    private static LivingEntity livingEntity;
    private Boolean cancelled = false;


    public LivingEntity getEntity() {
        return livingEntity;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public MonsterSpawnEvent(LivingEntity livingEntity, Monster monster, MonsterType monsterType) {
        MonsterSpawnEvent.livingEntity = livingEntity;
        MonsterSpawnEvent.monster = monster;
        this.monsterType = monsterType;
    }

    public Monster getMonster() {
        return monster;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
