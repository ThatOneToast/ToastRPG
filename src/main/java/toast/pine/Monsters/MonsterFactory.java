package toast.pine.Monsters;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import toast.pine.Adapters.AdapterManager;
import toast.pine.Keys;
import toast.pine.ToastRPG;

public class MonsterFactory {


    public static LivingEntity createMonster(String monsterName, Monster monster, LivingEntity livingEntity) {
        MonsterType monsterType = monster.getType();

        int health = monsterType.getHealth();
        double damage = monsterType.getDamage();
        double defense = monsterType.getDefense();
        float speed = monsterType.getSpeed();

        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        livingEntity.setHealth(health);

        livingEntity.setCustomName(monsterName);
        livingEntity.setCustomNameVisible(true);

        livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
        livingEntity.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(defense);
        if (speed != 0) livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);

        livingEntity.getPersistentDataContainer().set(Keys.MONSTER_TYPE, AdapterManager.getMonsterAdapter(), monster);
        ToastRPG.getMonsterManager().addMonster(livingEntity, monster);

        return livingEntity;


    }

}
