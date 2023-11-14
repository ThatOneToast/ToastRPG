package toast.pine.commands

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Default
import pine.toast.Keys
import toast.pine.socialSystem.PlayerSocial
import pine.toast.ToastRPG

class AddFriend {


    @Command("addfriend", "af", "addf")
    fun addFriend(stranger: Player, @Default("self") sender: Player) {
        val strangerPDC: PersistentDataContainer = stranger.persistentDataContainer
        val senderPDC: PersistentDataContainer = sender.persistentDataContainer
        val strangerSocial: PlayerSocial = strangerPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager()!!.socialProfileAdapter)!!
        val senderSocial: PlayerSocial = senderPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager()!!.socialProfileAdapter)!!
        senderSocial.getFriends().sendInvite(strangerSocial)

    }
}
