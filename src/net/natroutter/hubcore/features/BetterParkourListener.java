package net.natroutter.hubcore.features;

import net.natroutter.betterparkour.events.ParkourJoinEvent;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BetterParkourListener implements Listener {

    @EventHandler
    public void onParkourJoin(ParkourJoinEvent e) {
        Player p = e.getPlayer();
        GadgetHandler.setGadget(p, null);
    }

}
