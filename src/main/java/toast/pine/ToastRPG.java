package toast.pine;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import toast.pine.Adapters.AdapterManager;
import toast.pine.Entities.EntityListener;
import toast.pine.Entities.EntityManager;
import toast.pine.LevelSystem.LevelManager;
import toast.pine.Monsters.MonsterManager;

public class ToastRPG {

    private static Plugin passedPlugin;
    private static EntityManager entityManager;
    private static EntityListener entityListener;
    private static MonsterManager monsterManager;
    private static ManaRegen manaRegen;
    private static LevelManager levelManager;
    private static AdapterManager adapterManager;


    public static void passPluginToToast(final JavaPlugin passedPlugin) {
        ToastRPG.passedPlugin = passedPlugin;
        ToastRPG.entityManager = new EntityManager();
        ToastRPG.entityListener = new EntityListener(entityManager);
        ToastRPG.monsterManager = new MonsterManager();
        ToastRPG.manaRegen = new ManaRegen();
        ToastRPG.levelManager = new LevelManager();
        ToastRPG.adapterManager = new AdapterManager();

        getManaRegen().startManaUpdateTask();


    }



    public static Plugin getPassedPlugin() {
        return passedPlugin;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static EntityListener getEntityListener() {
        return entityListener;
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



}