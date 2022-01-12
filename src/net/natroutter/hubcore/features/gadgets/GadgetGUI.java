package net.natroutter.hubcore.features.gadgets;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GadgetGUI {

	private static final Lang lang = HubCore.getLang();
	private static final Config config = HubCore.getCfg();

	public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();

	private static GUIWindow getGUI(Player p) {
		if (!GUIS.containsKey(p.getUniqueId())) {
			GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.Gadgets.Title, GUIWindow.Rows.row3, true));
		}
		return GUIS.get(p.getUniqueId());
	}
	
	public static void show(Player p) {
		GUI(p).show(p);
	}
	
	private static GUIWindow GUI(Player p) {
		GUIWindow gui = getGUI(p);

		for (Gadget gad : GadgetHandler.gadgets) {
			gui.setItem(new GUIItem(gad.getIconWithNeed(), (e)-> {
				if (e.getWhoClicked() instanceof Player clicker) {

                    if (clicker.hasPermission(gad.getPermission())) {
						clicker.closeInventory();
						GadgetHandler.setGadget(clicker, gad);
					} else {
						clicker.sendMessage(lang.Prefix + lang.NoPerm);
					}

				}

			}), gad.slot);
		}
		
		return gui;
	}
	

}
