package pine.toast.toastrpg.monsters

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import pine.toast.toastrpg.Keys
import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.entities.EntityHandler

abstract class Monster (
    private var type: MonsterType,
    private var monsterName: String,
    livingEntityClass: Class<out LivingEntity>,
    monsterHandlerClass: Class<out EntityHandler>

) : MonsterType(type.getName(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed()) {

    private lateinit var livingEntity: LivingEntity
    private lateinit var monsterHandler: EntityHandler

    init {
        try {
            livingEntity = livingEntityClass.getDeclaredConstructor().newInstance()
            monsterHandler = monsterHandlerClass.getDeclaredConstructor().newInstance()
            ToastRPG.getPassedPlugin()!!.logger.info("Pass -> $monsterName")
        } catch (e: Exception) {
            ToastRPG.getPassedPlugin()!!.logger.warning("Fail -> $monsterName")
            ToastRPG.getPassedPlugin()!!.logger.warning(e.message)
        }
    }

    fun create(): LivingEntity {
        val monsterType = type
        val health = monsterType.getHealth()
        val damage = monsterType.getDamage()
        val defense = monsterType.getDefense()
        val speed = monsterType.getSpeed()
        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = health.toDouble()
        livingEntity.health = health.toDouble()
        livingEntity.setCustomName(monsterName)
        livingEntity.isCustomNameVisible = true
        livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.baseValue = damage
        livingEntity.getAttribute(Attribute.GENERIC_ARMOR)?.baseValue = defense
        if (speed != 0f) livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue = speed.toDouble()
        livingEntity.persistentDataContainer.set(Keys.MONSTER_TYPE, ToastRPG.getAdapterManager()!!.monsterTypeAdapter, monsterType)
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
