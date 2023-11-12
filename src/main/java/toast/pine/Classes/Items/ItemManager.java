package toast.pine.Classes.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.Events.PlayerLeftClickEvent;
import toast.pine.Events.PlayerRightCLickEvent;
import toast.pine.Keys;
import toast.pine.ToastRPG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager implements Listener {

    private final Map<ItemStack, ItemHandler> activeItems = new HashMap<>();

    /**
     * This function will register an item with the item manager.
     * @param itemStack The item to register.
     * @param handler The handler class for the item.
     * @see ItemHandler
     */
    public void registerHandledItem(ItemStack itemStack, ItemHandler handler) {
        this.activeItems.put(itemStack, handler);
    }

    /**
     * This function will unregister an item with the item manager.
     * @param item The item to unregister.
     */
    public void unregisterHandledItem(ItemStack item) {
        this.activeItems.remove(item);
    }

    /**
     * This function will check if an item is registered with the item manager.
     * @param item The item to check.
     * @return True if the item is registered, false otherwise.
     */
    public boolean isItemRegistered(ItemStack item) {
        return this.activeItems.containsKey(item);
    }

    /**
     * This is a provided function that will create an item and register it with the item manager.
     * If you do not like the format, than you will have to create your own function for creating items from Item and ItemMaterial Class
     * Make sure to register it with the item Handler as well.
     * @param itemMaterial The material class of your item.
     * @param handler The handler class of your item.
     * @return The itemstack of your item.
     * @see Item
     * @see ItemMaterial
     * @see ItemHandler
     *
     */
    public ItemStack createAndRegisterItemWithHandler(ItemMaterial itemMaterial, ItemHandler handler) {
        Item item = itemMaterial.getItem();

        Material material = itemMaterial.getMaterial();
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setCustomModelData(item.getCustomModelData());
        itemMeta.setDisplayName(item.getItemName());


        List<String> lore = item.getItemLore();
        lore.addFirst(ChatColor.GOLD + "--- Item Description ---" + ChatColor.RESET);
        lore.addFirst("");

        lore.add("");
        lore.add("");

        lore.add(ChatColor.GOLD + "--- Item Stats ---" + ChatColor.RESET);
        lore.addAll(item.getItemStats());

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(Keys.ITEM, ToastRPG.getAdapterManager().getItemAdapter(), item);

        AttributeModifier damageModifier = new AttributeModifier("damage", itemMaterial.getItemDamage(), AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier attackSpeedModifier = new AttributeModifier("attackSpeed", itemMaterial.getItemAttackSpeed(), AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armorModifier = new AttributeModifier("armor", itemMaterial.getItemArmor(), AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier healthModifier = new AttributeModifier("health", itemMaterial.getItemHealth(), AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier movementModifier = new AttributeModifier("movement", itemMaterial.getMovementSpeed(), AttributeModifier.Operation.ADD_NUMBER);

        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifier);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifier);
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthModifier);
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementModifier);


        itemStack.setItemMeta(itemMeta);


        // Register the item and its handler
        registerHandledItem(itemStack, handler);
        return itemStack;
    }



    public void handleRightClick(Item item, PlayerRightCLickEvent event) {
        ItemHandler handler = this.activeItems.get(item);
        if (handler != null) {
            handler.onPlayerRightClick(event);
        }
    }

    public void handleLeftClick(Item item, PlayerLeftClickEvent event) {
        ItemHandler handler = this.activeItems.get(item);
        if (handler != null) {
            handler.onPlayerLeftClick(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onItemBreak(PlayerItemBreakEvent event) {
        ItemStack item = event.getBrokenItem();
        if (isItemRegistered(item)) unregisterHandledItem(item);
    }

}
