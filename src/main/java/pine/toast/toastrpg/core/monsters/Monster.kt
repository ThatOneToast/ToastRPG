package pine.toast.toastrpg.core.monsters

import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.entities.EntityHandler

abstract class Monster (
    private var entity: EntityType,
    private var type: MonsterType,
    private var monsterName: String

) : MonsterType(type.getName(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed(), type.getMonsterHandlerClass()) {

    private var monsterHandler: EntityHandler = getMonsterHandlerClass().getDeclaredConstructor().newInstance()

    fun create(): LivingEntity {
        val monsterType = type
        val dummyLocation = Location(null, 0.0, 0.0, 0.0)
        val livingEntity: LivingEntity = dummyLocation.world?.spawnEntity(dummyLocation, entity) as LivingEntity


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


    fun getEntityType(): EntityType {
        return entity
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
