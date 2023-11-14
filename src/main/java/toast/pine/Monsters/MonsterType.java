package toast.pine.Monsters;

import toast.pine.ToastRPG;
public abstract class MonsterType {
    protected String typeName;
    protected int health;
    protected double damage;
    protected double defense;
    protected float speed;

    public MonsterType(
            String typeName,
            int health,
            double damage,
            double defense,
            float speed
    ) {
        this.typeName = typeName;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;

        ToastRPG.getMonsterManager().addMonsterType(this);
    }


    public String getName() {
        return typeName;
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
