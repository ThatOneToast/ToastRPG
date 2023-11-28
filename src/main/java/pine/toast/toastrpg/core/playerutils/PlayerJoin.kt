package pine.toast.toastrpg.core.playerutils

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import pine.toast.toastrpg.core.TKeys
import pine.toast.toastrpg.core.ToastRPG
import pine.toast.toastrpg.core.socialsystem.PlayerSocial

class PlayerJoin : Listener {

    @EventHandler
    private fun onPlayerJoin(event: PlayerJoinEvent) {
        val playerData: PersistentDataContainer = event.player.persistentDataContainer
        if (!event.player.hasPlayedBefore()) {
            ToastRPG.getAdapterManager()?.let {
                playerData.set(
                   TKeys.SOCIAL_PROFILE,
                    it.socialProfileAdapter,
                    PlayerSocial(null, event.player, null, null)
                )
            }

            playerData.set(TKeys.EXP, PersistentDataType.INTEGER, 0)
            playerData.set(TKeys.LEVEL, PersistentDataType.INTEGER, 1)
            playerData.set(TKeys.MAX_LEVEL, PersistentDataType.INTEGER, 100)
            playerData.set(TKeys.MAX_MANA, PersistentDataType.INTEGER, 100)
            playerData.set(TKeys.MANA_PER, PersistentDataType.INTEGER, 1)
        }
    }
}
