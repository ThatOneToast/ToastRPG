package toast.pine.commands

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Default
import pine.toast.Keys
import toast.pine.socialSystem.PlayerSocial
import pine.toast.ToastRPG

class RemoveFriend {


    @Command("removefriend", "rfriend", "removef")
    fun removeFriend(friend: Player, @Default("self") sender: Player) {
        val friendPDC: PersistentDataContainer = friend.persistentDataContainer
        val senderPDC: PersistentDataContainer = sender.persistentDataContainer
        val friendSocial: PlayerSocial = friendPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager()!!.socialProfileAdapter)!!
        val senderSocial: PlayerSocial = senderPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager()!!.socialProfileAdapter)!!
        senderSocial.getFriends().removeFriend(friendSocial)
        friendSocial.getFriends().removeFriend(senderSocial)
    }
}
