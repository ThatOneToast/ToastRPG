package toast.pine.Entities;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;

import java.util.HashMap;
import java.util.Map;

public class EntityManager {

    private final Map<LivingEntity, EntityHandler> activeLivingEntities = new HashMap<>();

    public void registerHandledEntity(LivingEntity Entity, EntityHandler handler) {
        this.activeLivingEntities.put(Entity, handler);
    }

    public void unregisterHandledEntity(LivingEntity entity) {
        this.activeLivingEntities.remove(entity);
    }

    public void handleTargetLiving(EntityTargetLivingEntityEvent event) {
        EntityHandler handler = this.activeLivingEntities.get(event.getEntity());
        if (handler != null) {
            handler.onTarget(event);
        }
    }

    public void handleDamage(EntityDamageByEntityEvent event) {
        EntityHandler handler = this.activeLivingEntities.get(event.getEntity());
        if (handler != null) {
            handler.onAttack(event);
        }
    }

    public void handleMonsterSpawn(MonsterSpawnEvent event) {
        EntityHandler handler = this.activeLivingEntities.get(event.getEntity());
        if (handler != null) {
            handler.onMonsterSpawn(event);
        }
    }

    public void handleMonsterDeath(MonsterDeathEvent event){
        EntityHandler handler = this.activeLivingEntities.get(event.getEntity());
        if (handler != null) {
            handler.onMonsterDeath(event);
        }
    }

    public void handleEntitySpawn(EntitySpawnEvent event) {
        EntityHandler handler = this.activeLivingEntities.get(event.getEntity());
        if (handler != null) {
            handler.onEntitySpawn(event);
        }
    }

    public void handleEntityDeath(EntityDeathEvent event) {
        EntityHandler handler = this.activeLivingEntities.get(event.getEntity());
        if (handler != null) {
            handler.onEntityDeath(event);
        }
    }




}
