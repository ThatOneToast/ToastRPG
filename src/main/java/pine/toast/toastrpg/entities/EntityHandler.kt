package pine.toast.toastrpg.entities

import pine.toast.toastrpg.events.MonsterDeathEvent
import pine.toast.toastrpg.events.MonsterSpawnEvent
import pine.toast.toastrpg.events.MonsterTargetPlayerEvent

interface EntityHandler {
    fun onMonsterSpawn(event: MonsterSpawnEvent?)
    fun onMonsterDeath(event: MonsterDeathEvent?)
    fun onMonsterTarget(event: MonsterTargetPlayerEvent?)
}
