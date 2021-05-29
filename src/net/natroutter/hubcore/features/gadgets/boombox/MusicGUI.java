package net.natroutter.hubcore.features.gadgets.boombox;

import org.bukkit.Material;
import org.bukkit.Sound;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.handlers.gui.GUIWindow.Rows;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.Utilities;
import org.bukkit.entity.Player;

public class MusicGUI {
	
	private static final Lang lang = HubCore.getLang();
	private static final Utilities utils = HubCore.getUtilities();
	private static final GUIWindow gui = new GUIWindow(lang.Guis.Gadgets.boomBox.Title, Rows.row5, true);

    //Notes
	private static final Note[] Notes = new Note[] {
			new Note(10, Sound.BLOCK_NOTE_BLOCK_BASS, lang.Guis.Gadgets.boomBox.Bass),
		    new Note(11, Sound.BLOCK_NOTE_BLOCK_SNARE, lang.Guis.Gadgets.boomBox.SnareDrum),
		    new Note(12, Sound.BLOCK_NOTE_BLOCK_HAT, lang.Guis.Gadgets.boomBox.Hat),
		    new Note(13, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, lang.Guis.Gadgets.boomBox.BassDrum),
		    new Note(14, Sound.BLOCK_NOTE_BLOCK_BELL, lang.Guis.Gadgets.boomBox.Bells),
		    new Note(15, Sound.BLOCK_NOTE_BLOCK_FLUTE, lang.Guis.Gadgets.boomBox.Flute),
		    new Note(16, Sound.BLOCK_NOTE_BLOCK_CHIME, lang.Guis.Gadgets.boomBox.Chimes),
		    new Note(19, Sound.BLOCK_NOTE_BLOCK_GUITAR, lang.Guis.Gadgets.boomBox.Guitar),
		    new Note(20, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, lang.Guis.Gadgets.boomBox.Xylophone),
		    new Note(21, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, lang.Guis.Gadgets.boomBox.Vibraphone),
		    new Note(22, Sound.BLOCK_NOTE_BLOCK_COW_BELL, lang.Guis.Gadgets.boomBox.CowBell),
		    new Note(23, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, lang.Guis.Gadgets.boomBox.Didgeridoo),
		    new Note(24, Sound.BLOCK_NOTE_BLOCK_BIT, lang.Guis.Gadgets.boomBox.Squarewave),
		    new Note(25, Sound.BLOCK_NOTE_BLOCK_BANJO, lang.Guis.Gadgets.boomBox.Banjo),
		    new Note(30, Sound.BLOCK_NOTE_BLOCK_PLING, lang.Guis.Gadgets.boomBox.Electricpiano),
		    new Note(32, Sound.BLOCK_NOTE_BLOCK_HARP, lang.Guis.Gadgets.boomBox.Harp)
	};
	

    
	public static void show(Player p) {
		NoteSelector(p).show(p);
	}
		
	
	private static GUIWindow NoteSelector(Player p) {
		for (Note note : Notes) {
			
			BaseItem item = Items.ServerIcon(Material.NOTE_BLOCK, note.getName());
			
			if (MusicPlayer.selectedSlot.containsKey(p.getUniqueId())) {
				Integer slot = MusicPlayer.selectedSlot.get(p.getUniqueId());
			
				if (note.getSlot() == slot) {
					utils.glint(item, true);
				}
			}
			
			
			gui.setItem(new GUIItem(item, (e)-> {
				Player bp = (Player)e.getWhoClicked();
				MusicPlayer.selectedSound.put(bp.getUniqueId(), note.getSound());
				MusicPlayer.selectedSlot.put(bp.getUniqueId(), note.getSlot());
				bp.closeInventory();
				
			}), note.getSlot());
			
		}
		return gui;
	}

	
	
}
