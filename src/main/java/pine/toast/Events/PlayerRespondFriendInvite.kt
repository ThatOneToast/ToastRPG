package toast.pine.Events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import toast.pine.socialSystem.PlayerSocial

class PlayerRespondFriendInvite(sender: PlayerSocial, receiver: PlayerSocial, response: Boolean) : Event() {
    private val sender: PlayerSocial
    private val receiver: PlayerSocial
    private val response: Boolean

    init {
        this.sender = sender
        this.receiver = receiver
        this.response = response
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
