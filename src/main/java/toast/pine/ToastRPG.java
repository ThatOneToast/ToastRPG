package toast.pine;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import toast.pine.Adapters.AdapterManager;
import toast.pine.Classes.Items.ItemListener;
import toast.pine.Classes.Items.ItemManager;
import toast.pine.Entities.EntityManager;
import toast.pine.LevelSystem.LevelManager;
import toast.pine.Monsters.MonsterListener;
import toast.pine.Monsters.MonsterManager;
import toast.pine.SocialSystem.SocialManager;

public class ToastRPG {

    private static Plugin passedPlugin;
    private static EntityManager entityManager;
    private static MonsterManager monsterManager;
    private static ManaRegen manaRegen;
    private static LevelManager levelManager;
    private static AdapterManager adapterManager;
    private static ItemManager itemManager;
    private static SocialManager socialManager;


    /**
     * Passes the plugin to the ToastRPG class
     * @param passedPlugin The plugin that is passed to the ToastRPG class
    Note: This will also create the following managers: EntityManager, MonsterManager, LevelManager, AdapterManager also starts
     the mana regen task.
     */
    public static void passPluginToToast(final JavaPlugin passedPlugin) {
        ToastRPG.passedPlugin = passedPlugin;
        ToastRPG.entityManager = new EntityManager();
        ToastRPG.monsterManager = new MonsterManager();
        ToastRPG.manaRegen = new ManaRegen();
        ToastRPG.levelManager = new LevelManager();
        ToastRPG.adapterManager = new AdapterManager();
        ToastRPG.itemManager = new ItemManager();
        ToastRPG.socialManager = new SocialManager();

        getManaRegen().startManaUpdateTask();
        ToastRPG.getPassedPlugin().getServer().getPluginManager().registerEvents(new ItemListener(itemManager), getPassedPlugin());
        ToastRPG.getPassedPlugin().getServer().getPluginManager().registerEvents(new MonsterListener(entityManager), getPassedPlugin());
        ToastRPG.getPassedPlugin().getServer().getPluginManager().registerEvents(new SocialManager(), getPassedPlugin());


    }



    public static Plugin getPassedPlugin() {
        return passedPlugin;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }


    public static MonsterManager getMonsterManager() {
        return monsterManager;
    }

    public static ManaRegen getManaRegen() {
        return manaRegen;
    }

    public static LevelManager getLevelManager() {
        return levelManager;
    }


    public static AdapterManager getAdapterManager() {
        return adapterManager;
    }

    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static SocialManager getSocialManager() {
        return socialManager;
    }



}