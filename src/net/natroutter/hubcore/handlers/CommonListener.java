package net.natroutter.hubcore.handlers;

import net.natroutter.hubcore.features.PlayerCarry;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemListener;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSListener;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.ColorGUI;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.CustomColorGUI;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.SettingsGUI;
import net.natroutter.hubcore.features.gadgets.Gadget;
import net.natroutter.hubcore.features.gadgets.GadgetGUI;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.features.gadgets.GadgetListener;
import net.natroutter.hubcore.features.gadgets.boombox.MusicPlayer;
import net.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import net.natroutter.hubcore.features.particles.ParticleGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class CommonListener implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        CustomColorGUI.GUIS.remove(p.getUniqueId());
        SelectorItemListener.cooldown.remove(p.getUniqueId());
        FWSHandler.cooldown.remove(p.getUniqueId());
        ColorGUI.GUIS.remove(p.getUniqueId());
        SettingsGUI.GUIS.remove(p.getUniqueId());
        GadgetGUI.GUIS.remove(p.getUniqueId());
        GadgetListener.cooldown.remove(p.getUniqueId());
        MusicPlayer.selectedSlot.remove(p.getUniqueId());
        MusicPlayer.selectedSound.remove(p.getUniqueId());
        SnowCannonHandler.particleTasks.remove(p.getUniqueId());
        ParticleGUI.GUIS.remove(p.getUniqueId());
        PlayerCarry.cooldown.remove(p.getUniqueId());
        for (Map.Entry<Player, Gadget> entry : GadgetHandler.SelectedGadget.entrySet()) {
            if (entry.getKey().getUniqueId().equals(p.getUniqueId())) {
                GadgetHandler.SelectedGadget.remove(entry.getKey());
            }
        }
    }



}
