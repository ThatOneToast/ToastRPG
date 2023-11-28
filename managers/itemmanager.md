---
description: ItemManger, What is it?
---

# ItemManager

Want to know how to create an item super easy? ToatRPG has a great way of creating items. The `ItemManager` Handles  a user `Right` or `Left` click events the same way we handled the entities, you Will create a `ItemHandler` and for whatever item do what you want. This all gets tracked and managed behind the scenes.

The `ItemManager` also has a function for creating the item

This is the function as shown:&#x20;

```kotlin
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
```

