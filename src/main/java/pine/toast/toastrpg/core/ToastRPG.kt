package pine.toast.toastrpg.core

import org.bukkit.plugin.Plugin
import pine.toast.toastrpg.core.adapters.AdapterManager
import pine.toast.toastrpg.core.classes.items.ItemManager
import pine.toast.toastrpg.core.classes.skills.SkillListener
import pine.toast.toastrpg.core.entities.EntityManager
import pine.toast.toastrpg.core.entities.HandleEntities
import pine.toast.toastrpg.core.level.LevelManager
import pine.toast.toastrpg.core.monsters.MonsterFactory
import pine.toast.toastrpg.core.playerutils.PlayerJoin
import pine.toast.toastrpg.core.socialsystem.SocialManager
import pine.toast.toastrpg.core.worldevents.WorldEventManager

object ToastRPG {

    private const val VERSION = "v1.0.6-ALPHA-pretest-patch12"

    private var passedPlugin: Plugin? = null
    private var entityManager: EntityManager? = null
    private var monsterFactory: MonsterFactory? = null
    private var manaRegen: ManaRegen? = null
    private var levelManager: LevelManager? = null
    private var adapterManager: AdapterManager? = null
    private var itemManager: ItemManager? = null
    private var socialManager: SocialManager? = null
    private var worldEventManager: WorldEventManager? = null

    /**
     * Passes the plugin to the ToastRPG class
     * @param plugin The plugin that is passed to the ToastRPG class
     * Note: This will also create the following managers: EntityManager, MonsterManager, LevelManager, AdapterManager also starts
     * the mana regen task.
     * PlayerData is populated with dummy data. For the keys used in the player data:
     * @see TKeys
     * 
     */
    fun passPluginToToast(plugin: Plugin) {
        passedPlugin = plugin


        print(" - Loading... ")

        plugin.logger.info("~~~~ ToastRPG: $VERSION ~~~~")
        plugin.logger.info(" - Loading... ")


        entityManager = EntityManager()
        monsterFactory = MonsterFactory()
        manaRegen = ManaRegen()
        levelManager = LevelManager()
        adapterManager = AdapterManager()
        itemManager = ItemManager()
        socialManager = SocialManager()
        worldEventManager = WorldEventManager()

        plugin.logger.info("~~~ ToastRPG: Validated! ~~~")

        passedPlugin!!.server.pluginManager.registerEvents(itemManager!!, passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(monsterFactory!!, passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(socialManager!!, passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(PlayerJoin(), passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(HandleEntities(), passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(SkillListener(), passedPlugin!!)
    }

    /**
     * Run this method in your onDisable()
     */
    fun takePluginFromToast() {

        manaRegen!!.stopManaUpdateTask()
        
    }

    fun getEntityManager(): EntityManager? {
        return entityManager
    }

    fun getMonsterFactory(): MonsterFactory? {
        return monsterFactory
    }

    fun getManaRegen(): ManaRegen? {
        return manaRegen
    }

    fun getLevelManager(): LevelManager? {
        return levelManager
    }

    fun getAdapterManager(): AdapterManager? {
        return adapterManager
    }

    fun getItemManager(): ItemManager? {
        return itemManager
    }

    fun getSocialManager(): SocialManager? {
        return socialManager
    }

    fun getWorldEventManager(): WorldEventManager? {
        return worldEventManager
    }

    fun getPassedPlugin(): Plugin? {
        return passedPlugin
    }


}
