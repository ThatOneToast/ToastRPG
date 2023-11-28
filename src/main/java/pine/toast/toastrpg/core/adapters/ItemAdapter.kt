package pine.toast.toastrpg.core.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.core.classes.items.Item
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class ItemAdapter : PersistentDataType<ByteArray, Item> {
    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<Item> {
        return Item::class.java
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): Item {
        val byteArrayInputStream = ByteArrayInputStream(primitive)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        return objectInputStream.readObject() as Item
    }

    override fun toPrimitive(complex: Item, context: PersistentDataAdapterContext): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(complex)
        return byteArrayOutputStream.toByteArray()
    }
}