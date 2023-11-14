package toast.pine.Adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import toast.pine.Monsters.Monster

class MonsterAdapter : PersistentDataType<Monster, Monster> {
    @get:JvmName("getPrimitiveTypeMonster")
    private val primitiveType: Class<Monster>
        get() = Monster::class.java
    @get:JvmName("getComplexTypeMonster")
    private val complexType: Class<Monster>
        get() = Monster::class.java

    override fun fromPrimitive(complex: Monster, context: PersistentDataAdapterContext): Monster {
        return complex // Implement this based on your requirement to retrieve Monster from a primitive form if needed.
    }

    override fun toPrimitive(complex: Monster, context: PersistentDataAdapterContext): Monster {
        return complex // Implement this based on your requirement to convert Monster to a primitive form if needed.
    }

    override fun getPrimitiveType(): Class<Monster> {
        return primitiveType
    }

    override fun getComplexType(): Class<Monster> {
        return complexType
    }
}
