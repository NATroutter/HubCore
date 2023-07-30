package fi.natroutter.hubcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.*;

public class AdminModeToggleEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final boolean state;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public AdminModeToggleEvent(Player player, boolean state) {
        this.player = player;
        this.state = state;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public boolean getState() {
        return this.state;
    }

    public Player getPlayer() {
        return this.player;
    }

}
