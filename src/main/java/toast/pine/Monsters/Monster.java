package toast.pine.Monsters;

public class Monster  {

    protected MonsterType type;
    protected String monsterName;

    public Monster(MonsterType type, String monsterName) {
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
