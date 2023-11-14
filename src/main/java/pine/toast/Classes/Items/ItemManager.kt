package toast.pine.Classes.Items

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer
import toast.pine.ColorAPI.Colors
import toast.pine.Events.PlayerLeftClickEvent
import toast.pine.Events.PlayerRightCLickEvent
import pine.toast.Keys
import pine.toast.ToastRPG

class ItemManager : Listener {
    private val activeItems: HashMap<ItemStack, ItemHandler> = HashMap()

    /**
     * This function will register an item with the item manager.
     * @param itemStack The item to register.
     * @param handler The handler class for the item.
     * @see ItemHandler
     */
    private fun registerHandledItem(itemStack: ItemStack, handler: ItemHandler) {
        activeItems[itemStack] = handler
    }

    /**
     * This function will unregister an item with the item manager.
     * @param item The item to unregister.
     */
    private fun unregisterHandledItem(item: ItemStack) {
        activeItems.remove(item)
    }

    /**
     * This function will check if an item is registered with the item manager.
     * @param item The item to check.
     * @return True if the item is registered, false otherwise.
     */
    private fun isItemRegistered(item: ItemStack): Boolean {
        return activeItems.containsKey(item)
    }

    /**
     * This is a provided function that will create an item and register it with the item manager.
     * If you do not like the format, than you will have to create your own function for creating items from Item and ItemMaterial Class
     * Make sure to register it with the item Handler as well.
     * @param itemMaterial The material class of your item.
     * @param handler The handler class of your item.
     * @return The itemstack of your item.
     * @see Item
     *
     * @see ItemMaterial
     *
     * @see ItemHandler
     */
    fun createAndRegisterItemWithHandler(itemMaterial: ItemMaterial, handler: ItemHandler): ItemStack {
        val item = itemMaterial.getItem()
        val material: Material = itemMaterial.getMaterial()
        val itemStack = ItemStack(material)
        val itemMeta: ItemMeta = itemStack.itemMeta
        itemMeta.setCustomModelData(item.getCustomModelData())
        itemMeta.setDisplayName(item.getItemName())
        val lore = ArrayList<String>()
        lore.add(Colors.GOLD + "--- Item Description ---" + Colors.RESET)
        lore.add("")
        lore.add("")
        lore.add("")
        lore.add(Colors.GOLD + "--- Item Stats ---" + Colors.RESET)
        val itemStats = item.getItemStats()

        if (itemStats !=  null) {
            for (stat in itemStats) {
                if (stat != null) {
                    lore.add(stat)
                }
            }
        }

        val container: PersistentDataContainer = itemMeta.persistentDataContainer
        ToastRPG.getAdapterManager()?.let { container.set(Keys.ITEM, it.itemAdapter, item) }
        val damageModifier =
            AttributeModifier("damage", itemMaterial.getItemDamage(), AttributeModifier.Operation.ADD_NUMBER)
        val attackSpeedModifier =
            AttributeModifier("attackSpeed", itemMaterial.getItemAttackSpeed(), AttributeModifier.Operation.ADD_NUMBER)
        val armorModifier =
            AttributeModifier("armor", itemMaterial.getItemArmor(), AttributeModifier.Operation.ADD_NUMBER)
        val healthModifier =
            AttributeModifier("health", itemMaterial.getItemHealth(), AttributeModifier.Operation.ADD_NUMBER)
        val movementModifier =
            AttributeModifier("movement", itemMaterial.getMovementSpeed(), AttributeModifier.Operation.ADD_NUMBER)
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthModifier)
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementModifier)
        itemStack.setItemMeta(itemMeta)


        // Register the item and its handler
        registerHandledItem(itemStack, handler)
        return itemStack
    }

    fun handleRightClick(item: ItemStack, event: PlayerRightCLickEvent?) {
        val handler = activeItems[item]
        handler?.onPlayerRightClick(event)
    }

    fun handleLeftClick(item: ItemStack, event: PlayerLeftClickEvent?) {
        val handler = activeItems[item]
        handler?.onPlayerLeftClick(event)
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private fun onItemBreak(event: PlayerItemBreakEvent) {
        val item: ItemStack = event.brokenItem
        if (isItemRegistered(item)) unregisterHandledItem(item)
    }
}
