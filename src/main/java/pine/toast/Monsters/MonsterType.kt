package toast.pine.Monsters

import pine.toast.ToastRPG

abstract class MonsterType(
    private var name: String,
    private var health: Int,
    private var damage: Double,
    private var defense: Double,
    private var speed: Float
) {

    init {
        ToastRPG.getMonsterManager()?.addMonsterType(this)
    }

    fun getName(): String {
        return name;
    }

    fun getHealth(): Int {
        return health
    }

    fun getDamage(): Double {
        return damage
    }

    fun getDefense(): Double {
        return defense;
    }

    fun getSpeed(): Float {
        return speed;
    }
}
