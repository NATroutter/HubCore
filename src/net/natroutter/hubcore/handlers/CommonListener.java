package net.natroutter.hubcore.handlers;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.PlayerCarry;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemListener;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.ColorGUI;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.CustomColorGUI;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.SettingsGUI;
import net.natroutter.hubcore.features.gadgets.GadgetGUI;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.features.gadgets.GadgetListener;
import net.natroutter.hubcore.features.gadgets.boombox.MusicPlayer;
import net.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import net.natroutter.hubcore.features.particles.ParticleGUI;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CommonListener implements Listener {

    private static final PlayerDataHandler pdh = HubCore.getDataHandler();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        PlayerCarry.cooldown.remove(p.getUniqueId());
        SelectorItemListener.cooldown.remove(p.getUniqueId());
        FWSHandler.cooldown.remove(p.getUniqueId());
        ColorGUI.GUIS.remove(p.getUniqueId());
        ColorGUI.colortype.remove(p.getUniqueId());
        CustomColorGUI.GUIS.remove(p.getUniqueId());
        SettingsGUI.GUIS.remove(p.getUniqueId());
        GadgetGUI.GUIS.remove(p.getUniqueId());
        GadgetHandler.SelectedGadget.remove(p.getUniqueId());
        GadgetListener.cooldown.remove(p.getUniqueId());
        MusicPlayer.selectedSlot.remove(p.getUniqueId());
        MusicPlayer.selectedSound.remove(p.getUniqueId());
        ParticleGUI.GUIS.remove(p.getUniqueId());
        ParticleGUI.pmode.remove(p.getUniqueId());
        pdh.save(p.getUniqueId());
        pdh.remove(p.getUniqueId());
        SelectorItemHandler.hubItemMap.remove(p.getUniqueId());

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        AdminModeHandler.setAdminMode(p, false);
    }



}
