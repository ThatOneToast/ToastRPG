package toast.pine;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import toast.pine.Adapters.AdapterManager;
import toast.pine.Classes.Items.ItemListener;
import toast.pine.Classes.Items.ItemManager;
import toast.pine.Commands.addFriend;
import toast.pine.Commands.removeFriend;
import toast.pine.Entities.EntityManager;
import toast.pine.LevelSystem.LevelManager;
import toast.pine.Monsters.MonsterListener;
import toast.pine.Monsters.MonsterManager;
import toast.pine.SocialSystem.PlayerSocial;
import toast.pine.SocialSystem.SocialManager;

import java.util.ArrayList;
import java.util.List;


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
    Adds the following commands: addFriend, removeFriend
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

        BukkitCommandHandler commands = BukkitCommandHandler.create(passedPlugin);
        commands.register(new addFriend());
        commands.register(new removeFriend());
        commands.getAutoCompleter().registerParameterSuggestions(removeFriend.class, (args, sender, command) -> {

            Player stranger = (Player) sender;
            PersistentDataContainer senderPDC = stranger.getPersistentDataContainer();
            PlayerSocial senderSocial = senderPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());
            List<String> friends = new ArrayList<>();

            for (PlayerSocial friend : senderSocial.getFriends().getFriends()) {
                friends.add(friend.getPlayer().getName());
            }

            return friends;
        });


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
