package pine.toast.toastrpg

import org.bukkit.plugin.Plugin
import pine.toast.toastrpg.adapters.AdapterManager
import pine.toast.toastrpg.classes.items.ItemManager
import pine.toast.toastrpg.classes.skills.SkillListener
import pine.toast.toastrpg.entities.EntityManager
import pine.toast.toastrpg.entities.HandleEntities
import pine.toast.toastrpg.level.LevelManager
import pine.toast.toastrpg.monsters.MonsterFactory
import pine.toast.toastrpg.playerutils.PlayerJoin
import pine.toast.toastrpg.socialsystem.SocialManager
import pine.toast.toastrpg.worldevents.WorldEventManager

object ToastRPG {
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
     * Adds the following commands: addFriend, removeFriend
     */
    fun passPluginToToast(plugin: Plugin) {
        passedPlugin = plugin
        entityManager = EntityManager()
        monsterFactory = MonsterFactory()
        manaRegen = ManaRegen()
        levelManager = LevelManager()
        adapterManager = AdapterManager()
        itemManager = ItemManager()
        socialManager = SocialManager()
        worldEventManager = WorldEventManager()

        passedPlugin!!.server.pluginManager.registerEvents(itemManager!!, this.passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(monsterFactory!!, this.passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(socialManager!!, this.passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(PlayerJoin(), this.passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(HandleEntities(), this.passedPlugin!!)
        passedPlugin!!.server.pluginManager.registerEvents(SkillListener(), this.passedPlugin!!)
    }

    /**
     * Run this method in your onDisable()
     */
    fun takePluginFromToast() {
        if (!worldEventManager!!.unRegisterAllWorldEvents()) {
            println("Successfully saved all world events.")
        }
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
