package toast.pine.Monsters;

import toast.pine.ToastRPG;

public class MonsterType {

    protected String typeName;
    protected int ProgressionLevel;
    protected int health;
    protected double damage;
    protected double defense;
    protected float speed;



    public MonsterType(
            String typeName,
            int ProgressionLevel,
            int health,
            double damage,
            double defense,
            float speed
    ) {

        this.typeName = typeName;
        this.ProgressionLevel = ProgressionLevel;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;

        ToastRPG.getMonsterManager().addMonsterType(this);
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

    public MonsterType getTypeFromString(String name) {
        // Assuming you have a collection of MonsterTypes (monsterTypeList)
        for (MonsterType type : ToastRPG.getMonsterManager().getMonsterTypes() ) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null; // Return null if the name doesn't match any MonsterType
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
