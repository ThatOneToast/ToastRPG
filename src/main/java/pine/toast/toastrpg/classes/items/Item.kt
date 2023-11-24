package pine.toast.toastrpg.classes.items

import pine.toast.toastrpg.classes.CharacterClass


abstract class  Item(
    itemName: String,
    intendedClassUse: Class<out CharacterClass?>,
    customModelData: Int,
    itemLevel: Int,
    itemMaxLevel: Int,
    itemLore: List<String>,
    itemStats: List<String>,
    itemEventHandlerClass: Class<out ItemHandler?>
) {
    private val itemName: String
    private val intendedClassUse: Class<out CharacterClass?>
    private var customModelData: Int
    private var itemLevel: Int
    private val itemMaxLevel: Int
    private val itemLore: List<String>
    private val itemStats: List<String>
    private var itemEventHandlerClass: Class<out ItemHandler?>

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


    fun setCustomModelData(customModelData: Int) {
        this.customModelData = customModelData
    }

    fun getIntendedClassUse(): Class<out CharacterClass?> {
        return intendedClassUse
    }

    fun getLevel(): Int {
        return itemLevel
    }

    fun getEventHandlerClass(): Class<out ItemHandler?> {
        return itemEventHandlerClass
    }

    fun setEventHandlerClass(itemEventHandlerClass: Class<out ItemHandler?>) {
        this.itemEventHandlerClass = itemEventHandlerClass
    }

    fun getItemStats(): List<String> {
        return itemStats
    }

    fun getItemLore(): List<String> {
        return itemLore
    }

    fun getItemName(): String {
        return itemName
    }

    fun getCustomModelData(): Int {
        return customModelData
    }

    fun getMaxLevel(): Int {
        return itemMaxLevel
    }





}
