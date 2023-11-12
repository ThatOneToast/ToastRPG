package toast.pine.Classes.Items;

import toast.pine.Classes.CharacterClass;

import java.util.List;

public abstract class Item {
    protected final String itemName;
    protected final CharacterClass intendedClassUse;
    protected int customModelData;
    protected int itemLevel;
    protected final int itemMaxLevel;
    protected final List<String> itemLore;
    protected final List<String> itemStats;


    protected Class<? extends ItemHandler> itemEventHandlerClass;

    public Item(
            String itemName,
            CharacterClass intendedClassUse,
            int customModelData,
            int itemLevel,
            int itemMaxLevel,
            List<String> itemLore,
            List<String> itemStats,
            Class<? extends ItemHandler> itemEventHandlerClass

    ) {


        this.itemName = itemName;
        this.intendedClassUse = intendedClassUse;
        this.customModelData = customModelData;
        this.itemLevel = itemLevel;
        this.itemMaxLevel = itemMaxLevel;
        this.itemLore = itemLore;
        this.itemStats = itemStats;
        this.itemEventHandlerClass = itemEventHandlerClass;
    }

    public List<String> getItemLore() {
        return itemLore;
    }

    public List<String> getItemStats() {
        return itemStats;
    }

    public Class<? extends ItemHandler> getItemEventHandlerClass() {
        return itemEventHandlerClass;
    }

    public void setCustomModelData(int data) {
        this.customModelData = data;
    }

    public void setLevel(int level) {
        this.itemLevel = level;
    }

    public void setItemEventHandlerClass(Class<? extends ItemHandler> itemEventHandlerClass) {
        this.itemEventHandlerClass = itemEventHandlerClass;
    }

    public String getItemName() {
        return itemName;
    }

    public CharacterClass getIntendedClassUse() {
        return intendedClassUse;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public int getItemMaxLevel() {
        return itemMaxLevel;
    }
}
