package toast.pine.Classes.Items

import toast.pine.Classes.CharacterClass


abstract class Item(
    itemName: String,
    intendedClassUse: CharacterClass?,
    customModelData: Int,
    itemLevel: Int,
    itemMaxLevel: Int,
    itemLore: MutableList<String?>?,
    itemStats: List<String?>?,
    itemEventHandlerClass: Class<out ItemHandler?>?
) {
    private val itemName: String
    private val intendedClassUse: CharacterClass?
    private var customModelData: Int
    private var itemLevel: Int
    private val itemMaxLevel: Int
    private val itemLore: MutableList<String?>?
    private val itemStats: List<String?>?
    private var itemEventHandlerClass: Class<out ItemHandler?>?

    init {
        this.itemName = itemName
        this.intendedClassUse = intendedClassUse
        this.customModelData = customModelData
        this.itemLevel = itemLevel
        this.itemMaxLevel = itemMaxLevel
        this.itemLore = itemLore
        this.itemStats = itemStats
        this.itemEventHandlerClass = itemEventHandlerClass
    }

    fun setLevel(level: Int) {
        itemLevel = level
    }

    fun getIntendedClassUse(): CharacterClass? {
        return intendedClassUse
    }

    fun getName(): String {
        return itemName
    }

    fun getLevel(): Int {
        return itemLevel
    }

    fun getEventHandlerClass(): Class<out ItemHandler?>? {
        return itemEventHandlerClass
    }

    fun setEventHandlerClass(itemEventHandlerClass: Class<out ItemHandler?>?) {
        this.itemEventHandlerClass = itemEventHandlerClass
    }

    abstract fun getItemName(): String
    abstract fun getItem(): Item
    abstract fun getItemLevel(): Int
    abstract fun getItemMaxLevel(): Int
    abstract fun getItemLore(): MutableList<String?>?
    abstract fun getItemStats(): List<String?>?
    abstract fun getItemEventHandlerClass(): Class<out ItemHandler?>?
    abstract fun getCustomModelData(): Int


}
