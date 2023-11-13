package toast.pine.Adapters;

public class AdapterManager {

    private final MonsterAdapter monsterAdapter = new MonsterAdapter();
    private final MonsterTypeAdapter monsterTypeAdapter = new MonsterTypeAdapter();
    private final ItemAdapter itemAdapter = new ItemAdapter();
    private final ItemMaterialAdapter itemTypeAdapter = new ItemMaterialAdapter();


    public MonsterAdapter getMonsterAdapter() {
        return monsterAdapter;
    }


    public MonsterTypeAdapter getMonsterTypeAdapter() {
        return monsterTypeAdapter;
    }

    public ItemAdapter getItemAdapter() {
        return itemAdapter;
    }

    public ItemMaterialAdapter getItemMaterialAdapter() {
        return itemTypeAdapter;
    }

}
