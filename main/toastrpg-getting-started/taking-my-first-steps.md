---
description: >-
  Getting familiar with the Library. In this page we will go over everything in
  the library based off the current time this was written.
---

# Taking My First Steps

First things first is that we need to pass our plugin instance to the library, doing so will initialize the library and all of its managers.

* [EntityManager](../../managers/entitymanager.md)
* [MonsterFactory](../../managers/monsterfactory.md)
* [LevelManager](../../managers/levelmanager.md)
* [AdapaterManager](../../managers/adaptermanager.md)
* [ItemManager](../../managers/itemmanager.md)
* [SocialManager](../../managers/socialmanager.md)
* [WorldEventManager](../../managers/worldeventmanager.md)

```kotlin
import pine.toast.toastrpg.core.ToastRPG

class Demo : JavaPlugin() {

    private val plugin: Plugin = this
    
    override fun onEnable() {
        // Startup Logic
        ToastRPG.passPluginToToast(plugin)
        
    }
    
    override fun onDisable() {
        // Shutdown Logic
        ToastRPG.takePluginFromToast()
    }
    
    fun getPlugin(): Plugin {
        return plugin
    }

}
```

This gets you up and running. Anything you need from the library you can retrieve with `ToastRPG().function`

