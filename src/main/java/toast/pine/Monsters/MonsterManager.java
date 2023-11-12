package toast.pine.Monsters;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonsterManager {

    private final Map<LivingEntity, Monster> monsters = new HashMap<>();
    private final Set<MonsterType> monsterTypes = new HashSet<>();


    public void addMonster(LivingEntity entity, Monster monster) {
        monsters.put(entity, monster);
    }

    public void removeMonster(LivingEntity entity) {
        monsters.remove(entity);
    }

    public void addMonsterType(MonsterType monsterType) {
        monsterTypes.add(monsterType);
    }

    public void removeMonsterType(MonsterType monsterType) {
        monsterTypes.remove(monsterType);
    }

    public Monster getMonster(LivingEntity entity) {
       return monsters.get(entity);
    }

    public Set<MonsterType> getMonsterTypes() {
        return monsterTypes;
    }

    public Map<LivingEntity, Monster> getMonsters() {
        return monsters;
    }





}
