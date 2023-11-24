package pine.toast.toastrpg.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.socialsystem.PlayerSocial

class SocialProfileAdapter : PersistentDataType<PlayerSocial, PlayerSocial> {
    @get:JvmName("getPrimitiveTypePlayerSocial")
    private val primitiveType: Class<PlayerSocial>
        get() = PlayerSocial::class.java
    @get:JvmName("getComplexTypePlayerSocial")
    private val complexType: Class<PlayerSocial>
        get() = PlayerSocial::class.java

    override fun toPrimitive(complex: PlayerSocial, context: PersistentDataAdapterContext): PlayerSocial {
        return complex
    }

    override fun fromPrimitive(primitive: PlayerSocial, context: PersistentDataAdapterContext): PlayerSocial {
        return primitive
    }

    override fun getPrimitiveType(): Class<PlayerSocial> {
        return primitiveType
    }

    override fun getComplexType(): Class<PlayerSocial> {
        return complexType
    }
}
