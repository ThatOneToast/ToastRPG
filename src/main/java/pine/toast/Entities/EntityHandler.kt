package toast.pine.Entities

import toast.pine.Events.MonsterDeathEvent
import toast.pine.Events.MonsterSpawnEvent
import toast.pine.Events.MonsterTargetPlayerEvent

interface EntityHandler {
    fun onMonsterSpawn(event: MonsterSpawnEvent?)
    fun onMonsterDeath(event: MonsterDeathEvent?)
    fun onMonsterTarget(event: MonsterTargetPlayerEvent?)
}
