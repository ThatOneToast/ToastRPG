package toast.pine.Events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.Monsters.Monster;
import toast.pine.Monsters.MonsterType;

public class MonsterDeathEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private static boolean cancelled = false;

    private LivingEntity entity;
    private Monster monster;
    private MonsterType monsterType;



    public MonsterDeathEvent(LivingEntity entity, Monster monster, MonsterType monsterType) {
        this.entity = entity;
        this.monster = monster;
        this.monsterType = monsterType;

    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
