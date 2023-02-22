package net.natroutter.hubcore.features;

import fi.natroutter.natlibs.handlers.gui.GUIItem;
import fi.natroutter.natlibs.handlers.gui.GUIWindow;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.files.Config;
import net.natroutter.hubcore.files.Config.SelectorItem;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.ServerSwitcher;
import org.bukkit.entity.Player;

public class ServerSelector {

	private Config config;
	private Items items;
	private ServerSwitcher serverSwitcher;
	public ServerSelector(Handler handler) {
		this.config = handler.getConfig();
		this.items = handler.getItems();
		this.serverSwitcher = handler.getServerSwitcher();
	}

	public void show(Player p) {
		SelectorGUI(p).show(p);
	}

	LegacyComponentSerializer lcs = LegacyComponentSerializer.legacySection();

	private GUIWindow SelectorGUI(Player p) {
		GUIWindow gui = new GUIWindow(lcs.deserialize(config.serverSelector.Title), config.serverSelector.GuiSize, true);
		for(SelectorItem item : config.serverSelector.SelectorItems) {
			if (item.Server == null || item.Material == null || item.Name == null || item.Row == null || item.Slot == null || item.Lore == null) {
				continue;
			}
			gui.setItem(new GUIItem(items.ServerIcon(item.Material, item.Name), (e)-> {
				if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
				serverSwitcher.switchServer(clicker, item.Server);
			}), item.Row, item.Slot);
		}
		return gui;
	}



}
