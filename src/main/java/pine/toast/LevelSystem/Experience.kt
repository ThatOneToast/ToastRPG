package toast.pine.LevelSystem

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerExpChangeEvent
import pine.toast.ToastRPG

class Experience : Listener {
    @EventHandler
    fun onExperiencePickUp(event: PlayerExpChangeEvent) {
        val player: Player = event.player
        ToastRPG.getLevelManager()?.addExperience(player, event.amount)
    }

}
