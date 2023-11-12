package toast.pine.Monsters;

import org.bukkit.entity.LivingEntity;
import toast.pine.ToastRPG;

public abstract class MonsterType {

    protected LivingEntity entity;
    protected String typeName;
    protected int ProgressionLevel;
    protected int health;
    protected double damage;
    protected double defense;
    protected float speed;



    public MonsterType(
            LivingEntity entity,
            String typeName,
            int ProgressionLevel,
            int health,
            double damage,
            double defense,
            float speed
    ) {

        this.entity = entity;
        this.typeName = typeName;
        this.ProgressionLevel = ProgressionLevel;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;

        ToastRPG.getMonsterManager().addMonsterType(this);
    }


    public LivingEntity getEntity() {
        return entity;
    }
    public String getName() {
        return typeName;
    }

    public int getProgressionLevel() {
        return ProgressionLevel;
    }

    public int getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public double getDefense() {
        return defense;
    }

    public float getSpeed() {
        return speed;
    }

    public void setProgressionLevel(int progressionLevel) {
        ProgressionLevel = progressionLevel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
