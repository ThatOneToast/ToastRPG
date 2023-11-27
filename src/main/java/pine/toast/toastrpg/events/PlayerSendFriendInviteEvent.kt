package pine.toast.toastrpg.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.socialsystem.PlayerSocial

class PlayerSendFriendInviteEvent(private val sender: PlayerSocial, private val receiver: PlayerSocial) : Event() {

    private val handlers = HandlerList()
    fun getSender(): PlayerSocial {
        return sender
    }

    fun getReceiver(): PlayerSocial {
        return receiver
    }

    override fun getHandlers(): HandlerList {
        return handlers
    }


}
