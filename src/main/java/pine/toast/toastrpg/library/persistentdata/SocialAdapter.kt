package pine.toast.toastrpg.library.persistentdata

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.library.socialsystem.Social
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SocialAdapter : PersistentDataType<ByteArray, Social> {
    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<Social> {
        return Social::class.java
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): Social {
        val byteArrayInputStream = ByteArrayInputStream(primitive)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)

        return objectInputStream.readObject() as Social
    }

    override fun toPrimitive(complex: Social, context: PersistentDataAdapterContext): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)

        objectOutputStream.writeObject(complex)
        objectOutputStream.flush()

        return byteArrayOutputStream.toByteArray()
    }
}