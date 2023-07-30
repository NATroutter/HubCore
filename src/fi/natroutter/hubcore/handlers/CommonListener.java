package fi.natroutter.hubcore.handlers;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
import fi.natroutter.hubcore.features.gadgets.boombox.MusicGUI;
import fi.natroutter.hubcore.features.particles.ParticleGUI;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class CommonListener implements Listener {



    private FWSHandler fwsHandler = HubCore.getFwsHandler();
    private MusicGUI musicGUI = HubCore.getMusicGUI();
    private GadgetHandler gadgetHandler = HubCore.getGadgetHandler();
    private ParticleGUI particleGUI = HubCore.getParticleGUI();
    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private SelectorItemHandler selectorItemHandler = HubCore.getSelectorItemHandler();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        fwsHandler.cooldown.remove(p.getUniqueId());
        fwsHandler.colortype.remove(p.getUniqueId());
        gadgetHandler.SelectedGadget.remove(p.getUniqueId());
        musicGUI.selectedSlot.remove(p.getUniqueId());
        musicGUI.selectedSound.remove(p.getUniqueId());
        particleGUI.pmode.remove(p.getUniqueId());
        pdh.save(p.getUniqueId());
        pdh.remove(p.getUniqueId());
        selectorItemHandler.hubItemMap.remove(p.getUniqueId());

    }


}
