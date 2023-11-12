package toast.pine.Monsters;

public abstract class Monster extends MonsterType {

    protected MonsterType type;
    protected String monsterName;

    public Monster(MonsterType type, String monsterName) {
        super(type.getEntity(), type.getName(), type.getProgressionLevel(), type.getHealth(), type.getDamage(), type.getDefense(), type.getSpeed());
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






}
