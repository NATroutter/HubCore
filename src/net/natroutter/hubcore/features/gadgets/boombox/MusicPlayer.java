package net.natroutter.hubcore.features.gadgets.boombox;

import java.util.HashMap;
import java.util.UUID;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.utilities.Utilities;
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
import net.natroutter.natlibs.objects.BaseItem;


public class MusicPlayer implements Listener { 

	private final Lang lang = HubCore.getLang();
	private final YamlDatabase database = HubCore.getYamlDatabase();

    public static HashMap<UUID, Sound> selectedSound = new HashMap<>();
    public static HashMap<UUID, Integer> selectedSlot = new HashMap<>();
	
    @EventHandler
    public void interactNoteblock(PlayerInteractEvent e) {
    	//if (1 == 1) {return;}
        Player p = e.getPlayer();
        Utilities utils = HubCore.getUtilities();
        
        Action act = e.getAction();
        
        if (e.hasItem() && (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK))) {
        	BaseItem item = BaseItem.from(e.getItem());
        	
            if (utils.nameMatch(item, Items.Gadgets.BoomBox())) {
                if (p.isSneaking()) {
                    MusicGUI.show(p);
                    return;
                }

                if (selectedSound.containsKey(p.getUniqueId())) {
                    Sound sound = selectedSound.get(p.getUniqueId());
                    float pitch = utils.pitchToFloat(p);

                    for (Entity ent : p.getWorld().getNearbyEntities(p.getLocation(), 10,10,10)) {
                        if (ent instanceof Player playTo) {
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
