package fi.natroutter.hubcore.features;

import fi.natroutter.betterparkour.events.ParkourJoinEvent;
import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
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
