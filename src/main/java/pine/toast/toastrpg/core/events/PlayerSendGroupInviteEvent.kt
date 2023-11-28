package pine.toast.toastrpg.core.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.core.socialsystem.Group
import pine.toast.toastrpg.core.socialsystem.PlayerSocial

class PlayerSendGroupInviteEvent(
    private val sender: PlayerSocial, private val receiver: PlayerSocial,
    private val group: Group?
) : Event() {
    private val handlers = HandlerList()

    fun getSender(): PlayerSocial {
        return sender
    }

    fun getReceiver(): PlayerSocial {
        return receiver
    }

    fun getGroup(): Group? {
        return group
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
