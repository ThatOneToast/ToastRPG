package toast.pine.Entities;

import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;
import toast.pine.Events.MonsterTargetPlayerEvent;


public interface EntityHandler {

        void onMonsterSpawn(MonsterSpawnEvent event);

        void onMonsterDeath(MonsterDeathEvent event);

        void onMonsterTarget(MonsterTargetPlayerEvent event);



}

