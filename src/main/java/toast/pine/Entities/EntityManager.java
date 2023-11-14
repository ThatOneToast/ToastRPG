package toast.pine.Entities;

import org.bukkit.entity.LivingEntity;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;
import toast.pine.Events.MonsterTargetPlayerEvent;

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

    public void handlerMonsterTarget(MonsterTargetPlayerEvent event) {
        EntityHandler handler = this.activeLivingEntities.get(event.getMonster().getLivingEntity());
        if (handler != null) {
            handler.onMonsterTarget(event);
        }
    }





}
