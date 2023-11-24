package pine.toast.toastrpg.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.classes.items.Item


class ItemAdapter : PersistentDataType<Item, Item> {
    @get:JvmName("getPrimitiveTypeItem")
    private val primitiveType: Class<Item>
        get() = Item::class.java
    @get:JvmName("getComplexTypeItem")
    private val complexType: Class<Item>
        get() = Item::class.java

    override fun toPrimitive(item: Item, persistentDataAdapterContext: PersistentDataAdapterContext): Item {
        return item
    }

    override fun fromPrimitive(item: Item, persistentDataAdapterContext: PersistentDataAdapterContext): Item {
        return item
    }

    override fun getPrimitiveType(): Class<Item> {
        return primitiveType
    }

    override fun getComplexType(): Class<Item> {
        return complexType
    }
}
