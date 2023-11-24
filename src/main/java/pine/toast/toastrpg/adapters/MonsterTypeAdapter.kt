package pine.toast.toastrpg.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.monsters.MonsterType


class MonsterTypeAdapter : PersistentDataType<MonsterType, MonsterType> {
    @get:JvmName("getPrimitiveTypeMonsterType")
    private val primitiveType: Class<MonsterType>
        get() = MonsterType::class.java
    @get:JvmName("getComplexTypeMonsterType")
    private val complexType: Class<MonsterType>
        get() = MonsterType::class.java

    override fun fromPrimitive(complex: MonsterType, context: PersistentDataAdapterContext): MonsterType {
        return complex // Implement this based on your requirement to retrieve MonsterType from a primitive form if needed.
    }

    override fun toPrimitive(complex: MonsterType, context: PersistentDataAdapterContext): MonsterType {
        return complex // Implement this based on your requirement to convert MonsterType to a primitive form if needed.
    }

    override fun getPrimitiveType(): Class<MonsterType> {
        return primitiveType
    }

    override fun getComplexType(): Class<MonsterType> {
        return complexType
    }
}
