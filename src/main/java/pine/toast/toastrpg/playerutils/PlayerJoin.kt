package pine.toast.toastrpg.playerutils

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.persistence.PersistentDataContainer
import pine.toast.toastrpg.Keys
import pine.toast.toastrpg.ToastRPG
import pine.toast.toastrpg.socialsystem.PlayerSocial

class PlayerJoin : Listener {

    @EventHandler
    private fun onPlayerJoin(event: PlayerJoinEvent) {
        val playerData: PersistentDataContainer = event.player.persistentDataContainer
        if (!event.player.hasPlayedBefore()) {
            ToastRPG.getAdapterManager()?.let {
                playerData.set(
                   Keys.SOCIAL_PROFILE,
                    it.socialProfileAdapter,
                    PlayerSocial(null, event.player, null, null)
                )
            }
        }
    }
}
