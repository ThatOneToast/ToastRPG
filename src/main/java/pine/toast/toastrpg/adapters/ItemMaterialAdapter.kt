package pine.toast.toastrpg.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.classes.items.ItemMaterial
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ItemMaterialAdapter : PersistentDataType<ByteArray, ItemMaterial> {
    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<ItemMaterial> {
        return ItemMaterial::class.java
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): ItemMaterial {
        val byteArrayInputStream = ByteArrayInputStream(primitive)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        return objectInputStream.readObject() as ItemMaterial
    }

    override fun toPrimitive(complex: ItemMaterial, context: PersistentDataAdapterContext): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(complex)
        return byteArrayOutputStream.toByteArray()
    }
}