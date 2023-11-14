package toast.pine.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.SocialSystem.PlayerSocial;

public class PlayerRespondFriendInvite extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final PlayerSocial sender;
    private final PlayerSocial receiver;
    private final Boolean response;

    public PlayerRespondFriendInvite(PlayerSocial sender, PlayerSocial receiver, Boolean response) {
        this.sender = sender;
        this.receiver = receiver;
        this.response = response;
    }

    public PlayerSocial getSender() {
        return this.sender;
    }

    public PlayerSocial getReceiver() {
        return this.receiver;
    }

    public Boolean getResponse() {
        return this.response;
    }


    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
