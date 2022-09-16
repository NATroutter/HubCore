package net.natroutter.hubcore.features.gadgets.boombox;

import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.natlibs.handlers.gui.GUIRow;
import net.natroutter.natlibs.handlers.langHandler.language.LangManager;
import org.bukkit.Material;
import org.bukkit.Sound;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.Utilities;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MusicGUI {

	public HashMap<UUID, Sound> selectedSound = new HashMap<>();
	public HashMap<UUID, Integer> selectedSlot = new HashMap<>();
	private Note[] Notes;

	private LangManager lang;
	private Utilities utilities;
	private Items items;

	public MusicGUI(Handler handler) {
		this.lang = handler.getLang();
		this.utilities = handler.getUtilities();
		this.items = handler.getItems();
		Notes = new Note[] {
				new Note(10, Sound.BLOCK_NOTE_BLOCK_BASS, lang.get(Translations.Guis_Gadgets_BoomBox_Bass)),
				new Note(11, Sound.BLOCK_NOTE_BLOCK_SNARE, lang.get(Translations.Guis_Gadgets_BoomBox_SnareDrum)),
				new Note(12, Sound.BLOCK_NOTE_BLOCK_HAT, lang.get(Translations.Guis_Gadgets_BoomBox_Hat)),
				new Note(13, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, lang.get(Translations.Guis_Gadgets_BoomBox_BassDrum)),
				new Note(14, Sound.BLOCK_NOTE_BLOCK_BELL, lang.get(Translations.Guis_Gadgets_BoomBox_Bells)),
				new Note(15, Sound.BLOCK_NOTE_BLOCK_FLUTE, lang.get(Translations.Guis_Gadgets_BoomBox_Flute)),
				new Note(16, Sound.BLOCK_NOTE_BLOCK_CHIME, lang.get(Translations.Guis_Gadgets_BoomBox_Chimes)),
				new Note(19, Sound.BLOCK_NOTE_BLOCK_GUITAR, lang.get(Translations.Guis_Gadgets_BoomBox_Guitar)),
				new Note(20, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, lang.get(Translations.Guis_Gadgets_BoomBox_Xylophone)),
				new Note(21, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, lang.get(Translations.Guis_Gadgets_BoomBox_Vibraphone)),
				new Note(22, Sound.BLOCK_NOTE_BLOCK_COW_BELL, lang.get(Translations.Guis_Gadgets_BoomBox_CowBell)),
				new Note(23, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, lang.get(Translations.Guis_Gadgets_BoomBox_Didgeridoo)),
				new Note(24, Sound.BLOCK_NOTE_BLOCK_BIT, lang.get(Translations.Guis_Gadgets_BoomBox_SquareWave)),
				new Note(25, Sound.BLOCK_NOTE_BLOCK_BANJO, lang.get(Translations.Guis_Gadgets_BoomBox_Banjo)),
				new Note(30, Sound.BLOCK_NOTE_BLOCK_PLING, lang.get(Translations.Guis_Gadgets_BoomBox_ElectricPiano)),
				new Note(32, Sound.BLOCK_NOTE_BLOCK_HARP, lang.get(Translations.Guis_Gadgets_BoomBox_Harp))
		};
	}

	public void show(Player p) {
		NoteSelector(p).show(p);
	}
		
	
	private GUIWindow NoteSelector(Player p) {
		GUIWindow gui = new GUIWindow(lang.get(Translations.Guis_Gadgets_BoomBox_Title), GUIRow.row5, true);
		for (Note note : Notes) {
			
			BaseItem item = items.ServerIcon(Material.NOTE_BLOCK, note.getName());
			
			if (selectedSlot.containsKey(p.getUniqueId())) {
				Integer slot = selectedSlot.get(p.getUniqueId());
			
				if (note.getSlot() == slot) {
					utilities.glint(item, true);
				}
			}
			
			
			gui.setItem(new GUIItem(item, (e)-> {
				Player bp = (Player)e.getWhoClicked();
				selectedSound.put(bp.getUniqueId(), note.getSound());
				selectedSlot.put(bp.getUniqueId(), note.getSlot());
				bp.closeInventory();
				
			}), note.getSlot());
			
		}
		return gui;
	}

	
	
}
