package pine.toast.toastrpg.library

import org.bukkit.plugin.Plugin
import pine.toast.toastrpg.library.entitymanagment.EntityManager
import pine.toast.toastrpg.library.persistentdata.AdapterManager


object ToastRPG {
    
    private var plugin: Plugin? = null
    private var adapters: AdapterManager? = null
    
    
    fun passPluginToToast(passedPlugin: Plugin) {
        plugin = passedPlugin
        adapters = AdapterManager()

        plugin!!.server.pluginManager.registerEvents(EntityManager, plugin!!)

    }
    
    fun getPlugin(): Plugin? {
        return plugin
    }

    fun getAdapters(): AdapterManager? {
        return adapters
    }
}

