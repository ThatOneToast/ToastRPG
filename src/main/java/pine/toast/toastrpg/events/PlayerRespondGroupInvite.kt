package pine.toast.toastrpg.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import pine.toast.toastrpg.socialsystem.Group
import pine.toast.toastrpg.socialsystem.PlayerSocial

class PlayerRespondGroupInvite(
    private val sender: PlayerSocial,
    private val receiver: PlayerSocial,
    private val group: Group?,
    private val response: Boolean
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

    fun getResponse(): Boolean {
        return response
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
