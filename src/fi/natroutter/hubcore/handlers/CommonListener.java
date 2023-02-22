package fi.natroutter.hubcore.handlers;

import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.Handler;
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



    private FWSHandler fwsHandler;
    private MusicGUI musicGUI;
    private GadgetHandler gadgetHandler;
    private ParticleGUI particleGUI;
    private PlayerDataHandler pdh;
    private SelectorItemHandler selectorItemHandler;

    public CommonListener(Handler handler) {
        this.fwsHandler = handler.getFwsHandler();
        this.musicGUI = handler.getMusicGUI();
        this.gadgetHandler = handler.getGadgetHandler();
        this.particleGUI = handler.getParticleGUI();
        this.pdh = handler.getDataHandler();
        this.selectorItemHandler = handler.getSelectorItemHandler();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        fwsHandler.cooldown.remove(p.getUniqueId());
        fwsHandler.colortype.remove(p.getUniqueId());
        gadgetHandler.SelectedGadget.remove(p.getUniqueId());
        musicGUI.selectedSlot.remove(p.getUniqueId());
        musicGUI.selectedSound.remove(p.getUniqueId());
        particleGUI.GUIS.remove(p.getUniqueId());
        particleGUI.pmode.remove(p.getUniqueId());
        pdh.save(p.getUniqueId());
        pdh.remove(p.getUniqueId());
        selectorItemHandler.hubItemMap.remove(p.getUniqueId());

    }


}
