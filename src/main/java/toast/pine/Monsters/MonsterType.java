package toast.pine.Monsters;

import org.bukkit.entity.LivingEntity;
import toast.pine.ToastRPG;
public abstract class MonsterType<T extends LivingEntity> {
    protected Class<T> entity;
    protected String typeName;
    protected int progressionLevel;
    protected int health;
    protected double damage;
    protected double defense;
    protected float speed;

    public MonsterType(
            Class<T> entity,
            String typeName,
            int progressionLevel,
            int health,
            double damage,
            double defense,
            float speed
    ) {
        this.entity = entity;
        this.typeName = typeName;
        this.progressionLevel = progressionLevel;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;

        ToastRPG.getMonsterManager().addMonsterType(this);
    }

    public Class<T> getEntityClass() {
        return entity;
    }

    public LivingEntity getEntity() {
        try {
            return entity.getConstructor().newInstance();
        } catch (Exception e) {
            ToastRPG.getPassedPlugin().getLogger().log(java.util.logging.Level.SEVERE, "Failed to create entity of type " + entity.getName() + "! ", e);
            return null;
        }
    }

    public String getName() {
        return typeName;
    }

    public int getProgressionLevel() {
        return progressionLevel;
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

    public void setProgressionLevel(int value) {
        progressionLevel = value;
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
