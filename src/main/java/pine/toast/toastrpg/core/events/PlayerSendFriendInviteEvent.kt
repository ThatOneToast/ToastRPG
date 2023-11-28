package pine.toast.toastrpg.core.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.core.socialsystem.PlayerSocial

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

    companion object {
        private val HANDLERS: HandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }
}
