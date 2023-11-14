package toast.pine.Classes.Items

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer
import toast.pine.Events.PlayerLeftClickEvent
import toast.pine.Events.PlayerRightCLickEvent
import pine.toast.Keys
import pine.toast.ToastRPG

class ItemListener(private val manager: ItemManager) : Listener {

    @EventHandler
    fun onRightClick(event: PlayerRightCLickEvent) {
        val item: ItemStack = event.getMainHand()
        val container: PersistentDataContainer = item.itemMeta.persistentDataContainer
        if (container.has(Keys.ITEM)) {
            val itemObject: Item? = ToastRPG.getAdapterManager()?.let { container.get(Keys.ITEM, it.itemAdapter) }
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
