package toast.pine.Monsters;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import toast.pine.Keys;
import toast.pine.ToastRPG;

public abstract class Monster extends MonsterType {

    protected MonsterType type;
    protected String monsterName;

    public Monster(MonsterType type, String monsterName) {
        super(type.getEntityClass(), type.getName(), type.getProgressionLevel(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed());
        this.type = type;
        this.monsterName = monsterName;
    }

    public MonsterType getType() {
        return type;
    }

    public String getName() {
        return monsterName;
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    public LivingEntity create() {
        MonsterType monsterType = this.getType();
        LivingEntity livingEntity = monsterType.getEntity();

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

        livingEntity.getPersistentDataContainer().set(Keys.MONSTER_TYPE, ToastRPG.getAdapterManager().getMonsterAdapter(), this);
        ToastRPG.getMonsterManager().addMonster(livingEntity, this);

        return livingEntity;


    }





}
