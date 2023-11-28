package pine.toast.toastrpg.core.classes.items

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.colorapi.Colors
import pine.toast.toastrpg.core.events.PlayerLeftClickEvent
import pine.toast.toastrpg.core.events.PlayerRightClickEvent

class ItemManager : Listener {

    init {
        ToastRPG.getPassedPlugin()!!.logger.info(" - ItemManager ~ Started")
    }

    /**
     * This function will create an item and register it with the item manager.
     * @param itemMaterialClass The item material class to create the item from.
     * @return The item stack of the created item.
     */
    fun create(itemMaterialClass: Class<out ItemMaterial>): ItemStack {
        val itemMaterial: ItemMaterial = itemMaterialClass.getDeclaredConstructor().newInstance()
        val itemClass: Class<out Item> = itemMaterial.getItem()
        val item: Item = itemClass.getDeclaredConstructor().newInstance()
        val material: Material = itemMaterial.getMaterial()
        val itemStack = ItemStack(material)
        val itemMeta: ItemMeta = itemStack.itemMeta

        itemMeta.setCustomModelData(item.getCustomModelData())
        itemMeta.setDisplayName(item.getItemName())
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        
        val lore = ArrayList<String>()
        lore.add(Colors.GOLD + "--- Item Description ---" + Colors.RESET)
        lore.add("")
        lore.add("")
        lore.add("")
        lore.add(Colors.GOLD + "--- Item Stats ---" + Colors.RESET)

        lore.add(item.getItemStats().toString())

        val container: PersistentDataContainer = itemMeta.persistentDataContainer
        container.set(TKeys.ITEM, ToastRPG.getAdapterManager()!!.itemAdapter, item)

        val damageModifier = AttributeModifier("damage", itemMaterial.getDamage(), AttributeModifier.Operation.ADD_NUMBER)
        val attackSpeedModifier = AttributeModifier("attackSpeed", itemMaterial.getAttackSpeed(), AttributeModifier.Operation.ADD_NUMBER)
        val armorModifier = AttributeModifier("armor", itemMaterial.getArmor(), AttributeModifier.Operation.ADD_NUMBER)
        val healthModifier = AttributeModifier("health", itemMaterial.getHealth(), AttributeModifier.Operation.ADD_NUMBER)
        val movementModifier = AttributeModifier("movement", itemMaterial.getMovementSpeed(), AttributeModifier.Operation.ADD_NUMBER)

        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementModifier)

        itemStack.setItemMeta(itemMeta)

        return itemStack
    }

    private fun handleRightClick(item: ItemStack, event: PlayerRightClickEvent) {
        val itemClass = item.itemMeta.persistentDataContainer.get(TKeys.ITEM, ToastRPG.getAdapterManager()!!.itemAdapter)
        val itemHandler = itemClass?.getEventHandlerClass();

        if (itemHandler != null) {
            val handler = itemHandler.getDeclaredConstructor().newInstance()
            handler?.onPlayerRightClick(event)
        }

    }

    private fun handleLeftClick(item: ItemStack, event: PlayerLeftClickEvent) {
        val itemClass = item.itemMeta.persistentDataContainer.get(TKeys.ITEM, ToastRPG.getAdapterManager()!!.itemAdapter)
        val itemHandler = itemClass?.getEventHandlerClass();

        if (itemHandler != null) {
            val handler = itemHandler.getDeclaredConstructor().newInstance()
            handler?.onPlayerLeftClick(event)
        }
    }

    @EventHandler
    private fun onRightClick(event: PlayerRightClickEvent) {
        val item: ItemStack = event.getMainHand()
        val itemContainer: PersistentDataContainer = item.itemMeta.persistentDataContainer

        if (itemContainer.has(TKeys.ITEM)) {
            val itemObject: Item? = ToastRPG.getAdapterManager()?.let { itemContainer.get(TKeys.ITEM, it.itemAdapter) }
            if (itemObject != null) {
                this.handleRightClick(item, event)
            }
        }
    }

    @EventHandler
    private fun onLeftClick(event: PlayerLeftClickEvent) {
        val item: ItemStack = event.getMainHand()
        val container: PersistentDataContainer = item.itemMeta.persistentDataContainer
        if (container.has(TKeys.ITEM)) {
            val itemObject = container.get(TKeys.ITEM, ToastRPG.getAdapterManager()!!.itemAdapter)
            if (itemObject != null) {
                this.handleLeftClick(item, event)
            }
        }
    }
}
