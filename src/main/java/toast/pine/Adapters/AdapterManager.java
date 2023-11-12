package toast.pine.Adapters;

public class AdapterManager {

    private final MonsterAdapter monsterAdapter = new MonsterAdapter();
    private final MonsterTypeAdapter monsterTypeAdapter = new MonsterTypeAdapter();

    /**
     * This is an internal Adapter
     * @return the MonsterAdapter
     Please do not use this adapter, create your own.
     */
    public MonsterAdapter getMonsterAdapter() {
        return monsterAdapter;
    }

    /**
     * This is an internal Adapter
     * @return the MonsterTypeAdapter
    Please do not use this adapter, create your own.
     */
    public MonsterTypeAdapter getMonsterTypeAdapter() {
        return monsterTypeAdapter;
    }

}
