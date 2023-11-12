package toast.pine.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import toast.pine.PlayerDirection;

public class PlayerRightCLickEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemStack mainHand;
    private final ItemStack offHand;
    private final Block block;
    private final PlayerDirection direction;
    private final Boolean isSneaking;

    public PlayerRightCLickEvent(Player player, ItemStack mainHand, ItemStack offHand, Block block, PlayerDirection direction, Boolean isSneaking) {
        this.player = player;
        this.mainHand = mainHand;
        this.offHand = offHand;
        this.block = block;
        this.direction = direction;
        this.isSneaking = isSneaking;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getMainHand() {
        return mainHand;
    }

    public ItemStack getOffHand() {
        return offHand;
    }

    public Block getBlock() {
        return block;
    }

    public PlayerDirection getDirection() {
        return direction;
    }

    public Boolean isSneaking() {
        return isSneaking;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
