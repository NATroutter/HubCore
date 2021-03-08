package net.natroutter.hubcore.features;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Config.SelectorItem;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.handlers.gui.GUIWindow.Rows;
import net.natroutter.natlibs.objects.BasePlayer;

public class ServerSelector {

	private static final Config cfg = HubCore.getCfg();
	
	private static final GUIWindow gui = new GUIWindow(cfg.serverSelector.Title, Rows.row5, true);

	public static void show(BasePlayer p) {
		SelectorGUI(p).show(p);
	}
		
	
	private static GUIWindow SelectorGUI(BasePlayer p) {
		for(SelectorItem item : cfg.serverSelector.SelectorItems) {
			
			gui.setItem(new GUIItem(Items.ServerIcon(item.Material, item.Name), (e)-> Connect(p, item.Server)), item.Row, item.Slot);
			
		}
		return gui;
	}
	
	private static void Connect(BasePlayer p, String serverName) {
		p.sendMessage("Connection to " + serverName);
	}
	

}
