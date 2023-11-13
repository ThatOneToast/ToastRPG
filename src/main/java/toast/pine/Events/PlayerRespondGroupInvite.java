package toast.pine.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.SocialSystem.Group;
import toast.pine.SocialSystem.PlayerSocial;

public class PlayerRespondGroupInvite extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final PlayerSocial sender;
    private final PlayerSocial receiver;
    private final Group group;
    private final Boolean response;
    private Boolean cancelled;


    public PlayerRespondGroupInvite(PlayerSocial sender, PlayerSocial receiver, Group group, Boolean response) {
        this.sender = sender;
        this.receiver = receiver;
        this.group = group;
        this.response = response;
        this.cancelled = false;
    }

    public PlayerSocial getSender() {
        return sender;
    }

    public PlayerSocial getReceiver() {
        return receiver;
    }

    public Group getGroup() {
        return group;
    }

    public Boolean getResponse() {
        return response;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
