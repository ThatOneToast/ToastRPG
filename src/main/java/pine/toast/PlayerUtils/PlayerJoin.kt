package pine.toast.PlayerUtils

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.persistence.PersistentDataContainer
import pine.toast.Keys
import pine.toast.ToastRPG
import toast.pine.socialSystem.PlayerSocial

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
