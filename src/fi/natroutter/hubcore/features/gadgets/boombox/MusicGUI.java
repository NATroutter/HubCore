package fi.natroutter.hubcore.features.gadgets.boombox;

import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.handlers.guibuilder.Button;
import fi.natroutter.natlibs.handlers.guibuilder.GUI;
import fi.natroutter.natlibs.handlers.guibuilder.GUIFrame;
import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.Utilities;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MusicGUI extends GUIFrame {

	public HashMap<UUID, Sound> selectedSound = new HashMap<>();
	public HashMap<UUID, Integer> selectedSlot = new HashMap<>();
	private Note[] Notes;
	
	public MusicGUI() {
		super(Lang.Guis_Gadgets_BoomBox_Title, Rows.row5);
		Notes = new Note[] {
				new Note(10, Sound.BLOCK_NOTE_BLOCK_BASS, Lang.Guis_Gadgets_BoomBox_Bass.asLegacy()),
				new Note(11, Sound.BLOCK_NOTE_BLOCK_SNARE, Lang.Guis_Gadgets_BoomBox_SnareDrum.asLegacy()),
				new Note(12, Sound.BLOCK_NOTE_BLOCK_HAT, Lang.Guis_Gadgets_BoomBox_Hat.asLegacy()),
				new Note(13, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, Lang.Guis_Gadgets_BoomBox_BassDrum.asLegacy()),
				new Note(14, Sound.BLOCK_NOTE_BLOCK_BELL, Lang.Guis_Gadgets_BoomBox_Bells.asLegacy()),
				new Note(15, Sound.BLOCK_NOTE_BLOCK_FLUTE, Lang.Guis_Gadgets_BoomBox_Flute.asLegacy()),
				new Note(16, Sound.BLOCK_NOTE_BLOCK_CHIME, Lang.Guis_Gadgets_BoomBox_Chimes.asLegacy()),
				new Note(19, Sound.BLOCK_NOTE_BLOCK_GUITAR, Lang.Guis_Gadgets_BoomBox_Guitar.asLegacy()),
				new Note(20, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, Lang.Guis_Gadgets_BoomBox_Xylophone.asLegacy()),
				new Note(21, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, Lang.Guis_Gadgets_BoomBox_Vibraphone.asLegacy()),
				new Note(22, Sound.BLOCK_NOTE_BLOCK_COW_BELL, Lang.Guis_Gadgets_BoomBox_CowBell.asLegacy()),
				new Note(23, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, Lang.Guis_Gadgets_BoomBox_Didgeridoo.asLegacy()),
				new Note(24, Sound.BLOCK_NOTE_BLOCK_BIT, Lang.Guis_Gadgets_BoomBox_SquareWave.asLegacy()),
				new Note(25, Sound.BLOCK_NOTE_BLOCK_BANJO, Lang.Guis_Gadgets_BoomBox_Banjo.asLegacy()),
				new Note(30, Sound.BLOCK_NOTE_BLOCK_PLING, Lang.Guis_Gadgets_BoomBox_ElectricPiano.asLegacy()),
				new Note(32, Sound.BLOCK_NOTE_BLOCK_HARP, Lang.Guis_Gadgets_BoomBox_Harp.asLegacy())
		};
	}


	@Override
	protected boolean onShow(Player player, GUI gui, List<Object> args) {

		for (Note note : Notes) {
			BaseItem item = Items.ServerIcon(Material.NOTE_BLOCK, note.getName());

			if (selectedSlot.containsKey(player.getUniqueId())) {
				Integer slot = selectedSlot.get(player.getUniqueId());
				if (note.getSlot() == slot) {
					Utilities.glint(item, true);
				}
			}

			Button btn = new Button(item, (e,g)-> {
				Player p = e.getPlayer();
				selectedSound.put(p.getUniqueId(), note.getSound());
				selectedSlot.put(p.getUniqueId(), note.getSlot());
				gui.close(p);
			});

			gui.setButton(btn, note.getSlot());

		}
		return true;
	}
}
