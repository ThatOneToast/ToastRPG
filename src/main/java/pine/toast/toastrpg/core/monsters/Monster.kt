package pine.toast.toastrpg.core.monsters

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.entities.EntityHandler

abstract class Monster (
    livingEntityClass: Class<out LivingEntity>,
    private var type: MonsterType,
    private var monsterName: String

) : MonsterType(type.getName(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed(), type.getMonsterHandlerClass()) {

    private var livingEntity: LivingEntity = livingEntityClass.getDeclaredConstructor().newInstance()
    private var monsterHandler: EntityHandler = getMonsterHandlerClass().getDeclaredConstructor().newInstance()

    fun create(): LivingEntity {
        val monsterType = type
        val health = monsterType.getHealth()
        val damage = monsterType.getDamage()
        val defense = monsterType.getDefense()
        val speed = monsterType.getSpeed()
        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = health.toDouble()
        livingEntity.health = health.toDouble()

        livingEntity.customName = monsterName
        livingEntity.isCustomNameVisible = true
        livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.baseValue = damage
        livingEntity.getAttribute(Attribute.GENERIC_ARMOR)?.baseValue = defense
        if (speed != 0f) livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue = speed.toDouble()
        livingEntity.persistentDataContainer.set(TKeys.MONSTER_TYPE, ToastRPG.getAdapterManager()!!.monsterTypeAdapter, monsterType)
        ToastRPG.getMonsterFactory()!!.markMonster(this)

        return livingEntity
    }


    fun getLivingEntity(): LivingEntity {
        return livingEntity
    }

    fun getType(): MonsterType {
        return type
    }

    fun getMonsterName(): String {
        return monsterName
    }

    fun getMonsterHandler(): EntityHandler {
        return monsterHandler
    }
}
