package pine.toast.toastrpg.library.persistentdata

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.library.entitymanagment.EntityHandler
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class EntityHandlerAdapter : PersistentDataType<ByteArray, EntityHandler> {

    override fun getPrimitiveType(): Class<ByteArray> {
        return ByteArray::class.java
    }

    override fun getComplexType(): Class<EntityHandler> {
        return EntityHandler::class.java
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): EntityHandler {
        val byteArrayInputStream = ByteArrayInputStream(primitive)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)

        // Read the object from the stream
        val entityHandler = objectInputStream.readObject() as EntityHandler

        // Close the streams
        objectInputStream.close()
        byteArrayInputStream.close()

        return entityHandler
    }

    override fun toPrimitive(complex: EntityHandler, context: PersistentDataAdapterContext): ByteArray {
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