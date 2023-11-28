---
description: AdapterManager, What is it?
---

# AdapterManager

The `AdapterManager` is what allows the library to store custom data types into a player or item's `PersistentDataContainer`

Here the following Adapater that can be used.

<pre class="language-kotlin"><code class="lang-kotlin"><strong>    val monsterAdapter = MonsterAdapter()
</strong>    val monsterTypeAdapter = MonsterTypeAdapter()
    val itemAdapter = ItemAdapter()
    val itemMaterialAdapter = ItemMaterialAdapter()
    val socialProfileAdapter = SocialProfileAdapter()
    val skillAdapter = SkillAdapter()    
</code></pre>

These adapaters allow PersistentDataContainer to store the following information^.



Here is some example usage:

<pre class="language-kotlin"><code class="lang-kotlin">class DemoTask : Listener {
<strong>    @EventHandler
</strong>    private fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("Welcome to the server!")

        event.player.persistentDataContainer.get(TKeys.SOCIAL_PROFILE, ToastRPG.getAdapterManager()!!.socialProfileAdapter)

    }
}
</code></pre>

The adapater can be used like that^, We are using `TKeys` because that is where all the offical Keys used by ToastRPG are located. We don't want to create duplicates, so if modifying you must use the keys from TKeys.
