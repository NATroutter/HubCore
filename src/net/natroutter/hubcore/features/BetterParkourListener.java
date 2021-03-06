package net.natroutter.hubcore.features;

import net.natroutter.betterparkour.events.ParkourJoinEvent;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BetterParkourListener implements Listener {

    private GadgetHandler gadgetHandler;
    private SelectorItemHandler selectorItemHandler;

    public BetterParkourListener(Handler handler) {
        this.gadgetHandler = handler.getGadgetHandler();
        this.selectorItemHandler = handler.getSelectorItemHandler();
    }

    @EventHandler
    public void onParkourJoin(ParkourJoinEvent e) {
        Player p = e.getPlayer();
        gadgetHandler.setGadget(p, null);
        selectorItemHandler.update(p);
    }

}
