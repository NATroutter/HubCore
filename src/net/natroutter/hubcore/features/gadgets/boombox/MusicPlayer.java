package net.natroutter.hubcore.features.gadgets.boombox;

import java.util.HashMap;
import java.util.UUID;

import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.Utilities;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.utilities.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Items;


public class MusicPlayer implements Listener { 

    private MusicGUI musicGUI;
    private LangManager lang;
    private Utilities utilities;
    private Items items;
    private Utils utils;

    public MusicPlayer(Handler handler) {
        this.musicGUI = handler.getMusicGUI();
        this.lang = handler.getLang();
        this.utilities = handler.getUtilities();
        this.items = handler.getItems();
        this.utils = handler.getUtils();
    }

    @EventHandler
    public void interactNoteblock(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action act = e.getAction();
        
        if (e.hasItem() && (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK))) {
        	BaseItem item = BaseItem.from(e.getItem());
        	
            if (utils.nameMatch(item, items.gadged_BoomBox())) {
                if (p.isSneaking()) {
                    musicGUI.show(p);
                    return;
                }

                if (musicGUI.selectedSound.containsKey(p.getUniqueId())) {
                    Sound sound = musicGUI.selectedSound.get(p.getUniqueId());
                    float pitch = utilities.pitchToFloat(p);

                    for (Entity ent : p.getWorld().getNearbyEntities(p.getLocation(), 10,10,10)) {
                        if (ent instanceof Player playTo) {
                            playTo.playSound(p.getLocation(), sound, 100, pitch);
                        }
                    }

                } else {
                    lang.send(p, Translations.Prefix, Translations.Gadgets_BoomBox_NoteNotSelected);
                }
            }
        }
    }

	
}
