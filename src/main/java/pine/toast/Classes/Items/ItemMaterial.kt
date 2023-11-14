package toast.pine.Classes.Items

import org.bukkit.Material

abstract class ItemMaterial(
    private var itemObj: Item,
    private val material: Material,
    private val itemDamage: Double,
    private val itemAttackSpeed: Double,
    private val itemArmor: Double,
    private val itemHealth: Double,
    private val movementSpeed: Double
)
{
    abstract fun getItem(): Item
    abstract fun getMaterial(): Material
    abstract fun getItemDamage(): Double
    abstract fun getItemAttackSpeed(): Double
    abstract fun getItemArmor(): Double
    abstract fun getItemHealth(): Double
    abstract fun getMovementSpeed(): Double

}
