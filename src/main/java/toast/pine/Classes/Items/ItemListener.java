package toast.pine.Classes.Items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.Events.PlayerLeftClickEvent;
import toast.pine.Events.PlayerRightCLickEvent;
import toast.pine.Keys;
import toast.pine.ToastRPG;

public class ItemListener implements Listener {

    private final ItemManager manager;

    public ItemListener(ItemManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onRightClick(PlayerRightCLickEvent event) {
        ItemStack item = event.getMainHand();
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(Keys.ITEM)) {
            Item itemObject = container.get(Keys.ITEM, ToastRPG.getAdapterManager().getItemAdapter());
            this.manager.handleRightClick(itemObject, event);
        }

    }

    @EventHandler
    public void onLeftClick(PlayerLeftClickEvent event) {
        ItemStack item = event.getMainHand();
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(Keys.ITEM)) {
            Item itemObject = container.get(Keys.ITEM, ToastRPG.getAdapterManager().getItemAdapter());
            this.manager.handleLeftClick(itemObject, event);
        }
    }


}
