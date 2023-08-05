package fi.natroutter.hubcore.features.gadgets.boombox;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.hubcore.utilities.Utils;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.Utilities;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class MusicPlayer implements Listener { 

    private MusicGUI musicGUI = HubCore.getMusicGUI();
    private Utils utils = HubCore.getUtils();


    @EventHandler
    public void interactNoteblock(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action act = e.getAction();
        
        if (e.hasItem() && (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK))) {
        	BaseItem item = BaseItem.from(e.getItem());
        	
            if (Items.gadged_BoomBox().isSimilar(item)) {
                if (p.isSneaking()) {
                    musicGUI.show(p);
                    return;
                }

                if (musicGUI.selectedSound.containsKey(p.getUniqueId())) {
                    Sound sound = musicGUI.selectedSound.get(p.getUniqueId());
                    float pitch = Utilities.pitchToFloat(p);

                    for (Entity ent : p.getWorld().getNearbyEntities(p.getLocation(), 10,10,10)) {
                        if (ent instanceof Player playTo) {
                            playTo.playSound(p.getLocation(), sound, 100, pitch);
                        }
                    }

                } else {
                    p.sendMessage(Lang.Gadgets_BoomBox_NoteNotSelected.prefixed());
                }
            }
        }
    }

	
}
