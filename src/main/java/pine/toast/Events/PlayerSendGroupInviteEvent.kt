package toast.pine.Events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import toast.pine.socialSystem.Group
import toast.pine.socialSystem.PlayerSocial

class PlayerSendGroupInviteEvent(sender: PlayerSocial, receiver: PlayerSocial, group: Group?) : Event() {
    private val sender: PlayerSocial
    private val receiver: PlayerSocial
    private val group: Group?

    init {
        this.sender = sender
        this.receiver = receiver
        this.group = group
    }

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
        return HANDLERS
    }

    companion object {
        private val HANDLERS: HandlerList = HandlerList()
    }
}
