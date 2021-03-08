package net.natroutter.hubcore.features.gadgets.boombox;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.Utilities;


public class MusicPlayer implements Listener { 

	Lang lang = HubCore.getLang();
	
	protected static HashMap<UUID, Sound> selectedSound = new HashMap<UUID, Sound>();
	protected static HashMap<UUID, Integer> selectedSlot = new HashMap<UUID, Integer>();
	
    @EventHandler
    public void interactNoteblock(PlayerInteractEvent e) {
    	//if (1 == 1) {return;}
        BasePlayer p = BasePlayer.from(e.getPlayer());
        Utilities utils = NATLibs.getUtilities();
        
        Action act = e.getAction();
        
        if (e.hasItem() && (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK))) {
        	BaseItem item = BaseItem.from(e.getItem());
        	
            if (utils.NameMatch(item, Items.Gadgets.BoomBox())) {
                if (p.isSneaking()) {
                    MusicGUI.show(p);
                    return;
                }

                if (selectedSound.containsKey(p.getUniqueId())) {
                    Sound sound = selectedSound.get(p.getUniqueId());
                    float pitch = utils.pitchToFloat(p);

                    for (Entity ent : p.getWorld().getNearbyEntities(p.getLocation(), 10,10,10)) {
                        if (ent instanceof Player) {
                            Player playTo = (Player)ent;
                            playTo.playSound(p.getLocation(), sound, 100, pitch);
                        }
                    }

                } else {
                    p.sendMessage(lang.Prefix + lang.Gadgets.BoomBox.NoteNotSelected);
                }
            }
        }
    }

	
}
