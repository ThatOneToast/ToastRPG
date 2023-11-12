package toast.pine.Classes.Items;

import org.bukkit.Material;

public abstract class ItemMaterial extends Item{

    Item item;
    protected final Material material;
    protected final double itemDamage;
    protected final double itemAttackSpeed;
    protected final double itemArmor;
    protected final double itemHealth;
    protected final double movementSpeed;

    public ItemMaterial(Item item, Material material, double itemDamage, double itemAttackSpeed, double itemArmor, double itemHealth, double movementSpeed) {
        super(
                item.getItemName(),
                item.getIntendedClassUse(),
                item.getCustomModelData(),
                item.getItemLevel(),
                item.getItemMaxLevel(),
                item.getItemLore(),
                item.getItemStats(),
                item.getItemEventHandlerClass()
        );

        this.item = item;
        this.material = material;
        this.itemDamage = itemDamage;
        this.itemAttackSpeed = itemAttackSpeed;
        this.itemArmor = itemArmor;
        this.itemHealth = itemHealth;
        this.movementSpeed = movementSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }
    public double getItemDamage() {
        return itemDamage;
    }

    public double getItemAttackSpeed() {
        return itemAttackSpeed;
    }

    public double getItemArmor() {
        return itemArmor;
    }

    public double getItemHealth() {
        return itemHealth;
    }

    public Item getItem() {
        return item;
    }

    public Material getMaterial() {
        return material;
    }

}
