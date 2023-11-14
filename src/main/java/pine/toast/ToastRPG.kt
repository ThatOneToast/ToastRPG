package pine.toast

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import revxrsal.commands.command.CommandActor
import revxrsal.commands.command.ExecutableCommand
import toast.pine.Adapters.AdapterManager
import toast.pine.Classes.Items.ItemListener
import toast.pine.Classes.Items.ItemManager
import toast.pine.Entities.EntityManager
import toast.pine.LevelSystem.LevelManager
import toast.pine.Monsters.MonsterListener
import toast.pine.Monsters.MonsterManager
import toast.pine.commands.AddFriend
import toast.pine.commands.RemoveFriend
import toast.pine.socialSystem.PlayerSocial
import toast.pine.socialSystem.SocialManager

object ToastRPG {
    private var passedPlugin: Plugin? = null
    private var entityManager: EntityManager? = null
    private var monsterManager: MonsterManager? = null
    private var manaRegen: ManaRegen? = null
    private var levelManager: LevelManager? = null
    private var adapterManager: AdapterManager? = null
    private var itemManager: ItemManager? = null
    private var socialManager: SocialManager? = null

    /**
     * Passes the plugin to the ToastRPG class
     * @param passedPlugin The plugin that is passed to the ToastRPG class
     * Note: This will also create the following managers: EntityManager, MonsterManager, LevelManager, AdapterManager also starts
     * the mana regen task.
     * Adds the following commands: addFriend, removeFriend
     */
    fun passPluginToToast(passedPlugin: JavaPlugin?) {
        ToastRPG.passedPlugin = passedPlugin
        entityManager = EntityManager()
        monsterManager = MonsterManager()
        manaRegen = ManaRegen()
        levelManager = LevelManager()
        adapterManager = AdapterManager()
        itemManager = ItemManager()
        socialManager = SocialManager()

        val commands: BukkitCommandHandler = BukkitCommandHandler.create(passedPlugin!!)
        commands.register(AddFriend())
        commands.register(RemoveFriend())
        commands.autoCompleter.registerParameterSuggestions(RemoveFriend::class.java) { _: List<String?>?, sender: CommandActor, _: ExecutableCommand? ->
            val stranger: Player = sender as Player
            val senderPDC: PersistentDataContainer = stranger.persistentDataContainer
            val senderSocial: PlayerSocial =
                senderPDC.get(Keys.SOCIAL_PROFILE, getAdapterManager()!!.socialProfileAdapter)!!
            val friends: MutableList<String> = ArrayList()
            for (friend in senderSocial.getFriends().getFriends()) {
                friends.add(friend.getPlayer().name)
            }
            friends
        }

        ToastRPG.passedPlugin!!.server.pluginManager.registerEvents(ItemListener(itemManager!!), ToastRPG.passedPlugin!!)
        ToastRPG.passedPlugin!!.server.pluginManager.registerEvents(MonsterListener(entityManager!!), ToastRPG.passedPlugin!!)
        ToastRPG.passedPlugin!!.server.pluginManager.registerEvents(SocialManager(), ToastRPG.passedPlugin!!)
    }

    fun getPassedPlugin(): Plugin? {
        return passedPlugin
    }

    fun getEntityManager(): EntityManager? {
        return entityManager
    }

    fun getMonsterManager(): MonsterManager? {
        return monsterManager
    }

    fun getLevelManager(): LevelManager? {
        return levelManager
    }

    @JvmStatic
    fun getAdapterManager(): AdapterManager? {
        return adapterManager
    }

    fun getItemManager(): ItemManager? {
        return itemManager
    }

    fun getSocialManager(): SocialManager? {
        return socialManager
    }
}
