package pine.toast.toastrpg.classes.items

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer
import pine.toast.toastrpg.Keys
import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.events.PlayerLeftClickEvent
import pine.toast.toastrpg.events.PlayerRightCLickEvent

class ItemListener(private val manager: ItemManager) : Listener {

    @EventHandler
    fun onRightClick(event: PlayerRightCLickEvent) {
        val item: ItemStack = event.getMainHand()
        val itemContainer: PersistentDataContainer = item.itemMeta.persistentDataContainer

        if (itemContainer.has(Keys.ITEM)) {
            val itemObject: Item? = ToastRPG.getAdapterManager()?.let { itemContainer.get(Keys.ITEM, it.itemAdapter) }
            if (itemObject != null) {
                manager.handleRightClick(item, event)
            }
        }
    }

    @EventHandler
    fun onLeftClick(event: PlayerLeftClickEvent) {
        val item: ItemStack = event.getMainHand()
        val container: PersistentDataContainer = item.itemMeta.persistentDataContainer
        if (container.has(Keys.ITEM)) {
            val itemObject: Item? = ToastRPG.getAdapterManager()?.let { container.get(Keys.ITEM, it.itemAdapter) }
            if (itemObject != null) {
                manager.handleLeftClick(item, event)
            }
        }
    }
}
