package toast.pine.Entities;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;


public interface EntityHandler {

        void onTarget(EntityTargetLivingEntityEvent event);

        void onAttack(EntityDamageByEntityEvent event);

        void onMonsterSpawn(MonsterSpawnEvent event);

        void onMonsterDeath(MonsterDeathEvent event);

        void onEntitySpawn(EntitySpawnEvent event);

        void onEntityDeath(EntityDeathEvent event);



}

