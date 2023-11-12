package toast.pine.Adapters;

public class AdapterManager {

    private final MonsterAdapter monsterAdapter = new MonsterAdapter();
    private final MonsterTypeAdapter monsterTypeAdapter = new MonsterTypeAdapter();
    private final ItemAdapter itemAdapter = new ItemAdapter();
    private final ItemMaterialAdapter itemTypeAdapter = new ItemMaterialAdapter();

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

    /**
     * This is an internal Adapter
     * @return the ItemAdapter
     * Please do not use this adapter, create your own.
     */
    public ItemAdapter getItemAdapter() {
        return itemAdapter;
    }

    /**
     * This is an internal Adapter
     * @return the ItemMaterialAdapter
     * Please do not use this adapter, create your own.
     */
    public ItemMaterialAdapter getItemMaterialAdapter() {
        return itemTypeAdapter;
    }

}
