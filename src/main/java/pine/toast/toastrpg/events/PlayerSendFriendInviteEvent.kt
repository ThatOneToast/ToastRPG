package pine.toast.toastrpg.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.socialsystem.PlayerSocial

class PlayerSendFriendInviteEvent(sender: PlayerSocial, receiver: PlayerSocial) : Event() {
    private val sender: PlayerSocial
    private val receiver: PlayerSocial

    init {
        this.sender = sender
        this.receiver = receiver
    }

    fun getSender(): PlayerSocial {
        return sender
    }

    fun getReceiver(): PlayerSocial {
        return receiver
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    companion object {
        private val HANDLERS: HandlerList = HandlerList()
    }
}
