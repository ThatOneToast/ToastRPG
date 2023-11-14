package toast.pine.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import toast.pine.SocialSystem.Group;
import toast.pine.SocialSystem.PlayerSocial;

public class PlayerSendGroupInviteEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final PlayerSocial sender;
    private final PlayerSocial receiver;
    private final Group group;

    public PlayerSendGroupInviteEvent(PlayerSocial sender, PlayerSocial receiver, Group group) {
        this.sender = sender;
        this.receiver = receiver;
        this.group = group;
    }



    public PlayerSocial getSender() {
        return this.sender;
    }

    public PlayerSocial getReceiver() {
        return this.receiver;
    }

    public Group getGroup() {
        return this.group;
    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }




}
