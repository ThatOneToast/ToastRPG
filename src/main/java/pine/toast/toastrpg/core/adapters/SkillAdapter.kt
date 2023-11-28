package pine.toast.toastrpg.core.adapters

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.core.classes.skills.Skill

class SkillAdapter : PersistentDataType<Skill, Skill> {
    override fun getPrimitiveType(): Class<Skill> {
        return Skill::class.java
    }

    override fun getComplexType(): Class<Skill> {
        return Skill::class.java
    }

    override fun fromPrimitive(p0: Skill, p1: PersistentDataAdapterContext): Skill {
        return p0
    }

    override fun toPrimitive(p0: Skill, p1: PersistentDataAdapterContext): Skill {
        return p0
    }


}