package toast.pine.Adapters;

public class AdapterManager {

    private final MonsterAdapter monsterAdapter = new MonsterAdapter();
    private final MonsterTypeAdapter monsterTypeAdapter = new MonsterTypeAdapter();
    private final ItemAdapter itemAdapter = new ItemAdapter();
    private final ItemMaterialAdapter itemTypeAdapter = new ItemMaterialAdapter();
    private final SocialProfileAdapter socialProfileAdapter = new SocialProfileAdapter();


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

    public SocialProfileAdapter getSocialProfileAdapter() {
        return socialProfileAdapter;
    }

}
