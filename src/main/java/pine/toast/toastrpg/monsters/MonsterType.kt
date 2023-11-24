package pine.toast.toastrpg.monsters

import com.google.gson.JsonObject
import pine.toast.toastrpg.ToastRPG


abstract class MonsterType(
    private var typeName: String,
    private var health: Int,
    private var damage: Double,
    private var defense: Double,
    private var speed: Float
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
