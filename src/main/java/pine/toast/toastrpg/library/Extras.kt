package pine.toast.toastrpg.library

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import pine.toast.toastrpg.library.socialsystem.Social

class Extras : Listener {

    @EventHandler
    private fun onPlayerJoin(event: PlayerJoinEvent) {
        if (!event.player.hasPlayedBefore()) {
            val social = Social(event.player,
                1,
                100,
                0,
                300
            )

            event.player.persistentDataContainer.set(Keys.social, ToastRPG.getAdapters()!!.socialAdapter, social)
        }
    }
}