package pine.toast.toastrpg.classes.items

import org.bukkit.Material

abstract class ItemMaterial(
    private var itemObj: Class<out Item>,
    private val material: Material,
    private val itemDamage: Double,
    private val itemAttackSpeed: Double,
    private val itemArmor: Double,
    private val itemHealth: Double,
    private val movementSpeed: Double
)
{

    fun getItem(): Class<out Item> {
        return itemObj
    }

    fun getMaterial(): Material {
        return material
    }

    fun getDamage(): Double {
        return itemDamage
    }

    fun getAttackSpeed(): Double {
        return itemAttackSpeed
    }

    fun getArmor(): Double {
        return itemArmor
    }

    fun getHealth(): Double {
        return itemHealth
    }

    fun getMovementSpeed(): Double {
        return movementSpeed
    }


}
