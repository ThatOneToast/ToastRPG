package pine.toast.toastrpg.core.entities

import pine.toast.toastrpg.core.events.MonsterDeathEvent
import pine.toast.toastrpg.core.events.MonsterSpawnEvent
import pine.toast.toastrpg.core.events.MonsterTargetPlayerEvent

interface EntityHandler {
    fun onMonsterSpawn(event: MonsterSpawnEvent?)
    fun onMonsterDeath(event: MonsterDeathEvent?)
    fun onMonsterTarget(event: MonsterTargetPlayerEvent?)
}
