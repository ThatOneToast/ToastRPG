package toast.pine.Monsters;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import toast.pine.Keys;
import toast.pine.ToastRPG;

public abstract class Monster<T extends LivingEntity> extends MonsterType {

    protected Class<T> entity;
    protected LivingEntity livingEntity;
    protected MonsterType type;
    protected String monsterName;


    /**
     * Creating the monster class
     * @param entityClass The entity class
     * @param type The monster type
     * @param monsterName The monster name
        Note: This will also create the monster's living entity,
     you can access it by using the getLivingEntity() method.
     @see toast.pine.Monsters.Monster#getLivingEntity()
     */
    public Monster(Class<T> entityClass, MonsterType type, String monsterName) {
        super(type.getName(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed());
        this.entity = entityClass;
        this.type = type;
        this.monsterName = monsterName;


        this.livingEntity = create();

    }


    /**
     * Gets the living entity of the monster
     * @return The living entity of the monster
     */
    public LivingEntity getLivingEntity() {
        return livingEntity;
    }


    /**
     * Gets the monster type
     * @return The monster type
     */
    public MonsterType getType() {
        return type;
    }

    /**
     * Gets the monster name
     * @return The monster name
     */
    public String getName() {
        return monsterName;
    }

    /**
     * Sets the monster type
     * @param type The monster type
     */
    public void setType(MonsterType type) {
        this.type = type;
    }

     LivingEntity create() {
        MonsterType monsterType = this.getType();

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
