package pine.toast.toastrpg.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.classes.items.ItemMaterial


class ItemMaterialAdapter : PersistentDataType<ItemMaterial, ItemMaterial> {
    @get:JvmName("getPrimitiveTypeItemMaterial")
    val primitiveType: Class<ItemMaterial>
        get() = ItemMaterial::class.java
    @get:JvmName("getComplexTypeItemMaterial")
    val complexType: Class<ItemMaterial>
        get() = ItemMaterial::class.java

    override fun toPrimitive(
        item: ItemMaterial,
        persistentDataAdapterContext: PersistentDataAdapterContext
    ): ItemMaterial {
        return item
    }

    override fun fromPrimitive(
        item: ItemMaterial,
        persistentDataAdapterContext: PersistentDataAdapterContext
    ): ItemMaterial {
        return item
    }

    override fun getPrimitiveType(): Class<ItemMaterial> {
        return primitiveType
    }

    override fun getComplexType(): Class<ItemMaterial> {
        return complexType
    }
}