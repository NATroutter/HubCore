package net.natroutter.hubcore.features;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Config.SelectorItem;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.handlers.gui.GUIWindow.Rows;
import net.natroutter.natlibs.utilities.Bungeecord.BungeeHandler;
import org.bukkit.entity.Player;

public class ServerSelector {

	private static final Config cfg = HubCore.getCfg();
	private static final BungeeHandler bungee = HubCore.getBungee();
	
	private static final GUIWindow gui = new GUIWindow(cfg.serverSelector.Title, Rows.row6, true);

	public static void show(Player p) {
		SelectorGUI(p).show(p);
	}
		
	
	private static GUIWindow SelectorGUI(Player p) {
		for(SelectorItem item : cfg.serverSelector.SelectorItems) {
			
			gui.setItem(new GUIItem(Items.ServerIcon(item.Material, item.Name), (e)-> Connect(p, item.Server)), item.Row, item.Slot);
			
		}
		return gui;
	}
	
	private static void Connect(Player p, String serverName) {
		bungee.switchServer(p, serverName);
	}
	

}
