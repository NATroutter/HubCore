package net.natroutter.hubcore.features.gadgets;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.handlers.gui.GUIWindow.Rows;
import net.natroutter.natlibs.objects.BasePlayer;

public class GadgetGUI {

	private static final Lang lang = HubCore.getLang();
	
	private static final GUIWindow gui = new GUIWindow(lang.Guis.Gadgets.Title, Rows.row3, true);
	
	public static void show(BasePlayer p) {
		GUI().show(p);
	}
		
	
	private static GUIWindow GUI() {
		
		for (Gadget gad : GadgetHandler.Gadgets) {
			gui.setItem(new GUIItem(gad.getIcon(), (e)-> {
				
				BasePlayer p = BasePlayer.from(e.getWhoClicked());
				p.closeInventory();
				
				GadgetHandler.setGadget(p, gad);
				
			}), gad.slot);
		}
		
		return gui;
	}
	

}
