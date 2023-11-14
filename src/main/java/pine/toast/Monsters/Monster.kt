package toast.pine.Monsters

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import pine.toast.Keys
import pine.toast.ToastRPG

abstract class Monster(
    private var type: MonsterType,
    private var monsterName: String,
    private var livingEntity: LivingEntity

) : MonsterType(type.getName(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed()) {

    init {
        livingEntity = create()

    }

    fun getLivingEntity(): LivingEntity {
        return livingEntity
    }


    private fun create(): LivingEntity {

        val monsterType = type
        val health = monsterType.getHealth()
        val damage = monsterType.getDamage()
        val defense = monsterType.getDefense()
        val speed = monsterType.getSpeed()
        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.setBaseValue(health.toDouble())
        livingEntity.health = health.toDouble()
        livingEntity.setCustomName(monsterName)
        livingEntity.isCustomNameVisible = true
        livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.baseValue = damage
        livingEntity.getAttribute(Attribute.GENERIC_ARMOR)?.baseValue = defense
        if (speed != 0f) livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue = speed.toDouble()
        livingEntity.persistentDataContainer.set(Keys.MONSTER_TYPE, ToastRPG.getAdapterManager()!!.monsterTypeAdapter, monsterType)
        ToastRPG.getMonsterManager()!!.addMonster(this)
        return livingEntity
    }
}
