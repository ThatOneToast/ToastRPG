package pine.toast.toastrpg.library.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pine.toast.toastrpg.library.Keys
import pine.toast.toastrpg.library.ToastRPG
import pine.toast.toastrpg.library.socialsystem.Social

class SendFriendRequest : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender !is Player) sender.sendMessage("You must be a player to use this command!")

        val player: Player = sender as Player

        if (args == null) {
            player.sendMessage("You must specify a player to send a friend request to!")
            return false
        }

        val target: Player? = player.server.getPlayer(args[0])

        val playerSocial: Social = player.persistentDataContainer.get(Keys.social, ToastRPG.getAdapters()!!.socialAdapter)
            ?: throw NullPointerException("Player social data is null!")

        val targetSocial: Social = target!!.persistentDataContainer.get(Keys.social, ToastRPG.getAdapters()!!.socialAdapter)
            ?: throw NullPointerException("Target social data is null!")

        playerSocial.sendFriendInvite(targetSocial)


        return false
    }
}