package toast.pine.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.SocialSystem.PlayerSocial;

public class PlayerSendFriendInviteEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final PlayerSocial sender;
    private final PlayerSocial receiver;

    public PlayerSendFriendInviteEvent(PlayerSocial sender, PlayerSocial receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public PlayerSocial getSender() {
        return this.sender;
    }

    public PlayerSocial getReceiver() {
        return this.receiver;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
