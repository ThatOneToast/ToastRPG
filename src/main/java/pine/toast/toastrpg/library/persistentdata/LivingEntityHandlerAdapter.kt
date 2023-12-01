package pine.toast.toastrpg.library.persistentdata

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.library.entitymanagment.LivingEntityHandler
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class LivingEntityHandlerAdapter : PersistentDataType<ByteArray, LivingEntityHandler> {

    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<LivingEntityHandler> {
        return LivingEntityHandler::class.java
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): LivingEntityHandler {
        val byteArrayInputStream = ByteArrayInputStream(primitive)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)

        // Read the object from the stream
        val livingEntityHandler = objectInputStream.readObject() as LivingEntityHandler

        // Close the streams
        objectInputStream.close()
        byteArrayInputStream.close()

        return livingEntityHandler
    }

    override fun toPrimitive(complex: LivingEntityHandler, context: PersistentDataAdapterContext): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)

        // Write the object to the stream
        objectOutputStream.writeObject(complex)

        // Close the streams
        objectOutputStream.close()
        byteArrayOutputStream.close()

        return byteArrayOutputStream.toByteArray()
    }
}