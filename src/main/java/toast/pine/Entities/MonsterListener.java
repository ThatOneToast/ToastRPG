package toast.pine.Entities;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;
import toast.pine.Events.MonsterTargetPlayerEvent;

public class MonsterListener implements Listener {

    private final EntityManager manager;

    public MonsterListener(EntityManager manager) {
        this.manager = manager;
    }


    @EventHandler
    public void onMonsterSpawn(MonsterSpawnEvent event) {
        this.manager.handleMonsterSpawn(event);
    }

    @EventHandler
    public void onMonsterDeath(MonsterDeathEvent event){
        this.manager.handleMonsterDeath(event);
    }

    @EventHandler
    public void onMonsterTarget(MonsterTargetPlayerEvent event){
        this.manager.handlerMonsterTarget(event);
    }

}
