package pine.toast.toastrpg.core.monsters

import com.google.gson.JsonObject
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.entities.EntityHandler


abstract class MonsterType(
    private var typeName: String,
    private var health: Int,
    private var damage: Double,
    private var defense: Double,
    private var speed: Float,
    private var monsterHandlerClass: Class<out EntityHandler>
) {

    init {
        ToastRPG.getPassedPlugin()!!.logger.info("Pass -> MonsterType: $typeName")
    }

    fun getName(): String {
        return typeName
    }

    fun getHealth(): Int {
        return health
    }

    fun getDamage(): Double {
        return damage
    }

    fun getDefense(): Double {
        return defense
    }

    fun getSpeed(): Float {
        return speed
    }

    fun getMonsterHandlerClass(): Class<out EntityHandler> {
        return monsterHandlerClass
    }

    fun toJson(): JsonObject {
        val json = JsonObject()
        json.addProperty("name", typeName)
        json.addProperty("health", health)
        json.addProperty("damage", damage)
        json.addProperty("defense", defense)
        json.addProperty("speed", speed)
        return json
    }







}
