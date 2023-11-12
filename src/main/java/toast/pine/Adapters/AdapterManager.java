package toast.pine.Adapters;

public class AdapterManager {

    private static final MonsterAdapter monsterAdapter = new MonsterAdapter();
    private static final MonsterTypeAdapter monsterTypeAdapter = new MonsterTypeAdapter();

    /**
     * This is an internal Adapter
     * @return the MonsterAdapter
     Please do not use this adapter, create your own.
     */
    public static MonsterAdapter getMonsterAdapter() {
        return monsterAdapter;
    }

    /**
     * This is an internal Adapter
     * @return the MonsterTypeAdapter
    Please do not use this adapter, create your own.
     */
    public static MonsterTypeAdapter getMonsterTypeAdapter() {
        return monsterTypeAdapter;
    }

}
