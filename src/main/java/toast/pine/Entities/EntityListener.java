package toast.pine.Entities;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;

public class EntityListener implements Listener {

    private final EntityManager manager;

    public EntityListener(EntityManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        this.manager.handleDamage(event);
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        this.manager.handleTargetLiving(event);
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
    public void onEntitySpawn(EntitySpawnEvent event) {
        this.manager.handleEntitySpawn(event);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        this.manager.handleEntityDeath(event);
    }
}
