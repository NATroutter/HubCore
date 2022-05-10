package net.natroutter.hubcore.features.gadgets;

import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.files.Config;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.natlibs.handlers.LangHandler.language.LangManager;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIRow;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GadgetGUI {

	private Config config;
	private LangManager lang;
	private GadgetHandler gadgetHandler;
	private SelectorItemHandler selectorItemHandler;

	public GadgetGUI(Handler handler) {
		this.config = handler.getConfig();
		this.lang = handler.getLang();
		this.gadgetHandler = handler.getGadgetHandler();
		this.selectorItemHandler = handler.getSelectorItemHandler();
	}
	
	public void show(Player p) {
		GUI(p).show(p);
	}
	
	private GUIWindow GUI(Player p) {
		GUIWindow gui = new GUIWindow(lang.get(Translations.Guis_Gadgets_Title), GUIRow.row3, true);

		for (Gadget gad : gadgetHandler.gadgets) {
			gui.setItem(new GUIItem(gad.getIconWithNeed(lang), (e)-> {
				if (e.getWhoClicked() instanceof Player clicker) {

                    if (clicker.hasPermission(gad.getPermission())) {
						clicker.closeInventory();
						gadgetHandler.setGadget(clicker, gad);
						selectorItemHandler.update(p);
					} else {
						lang.send(clicker, Translations.Prefix, Translations.NoPerm);
					}

				}

			}), gad.slot);
		}
		
		return gui;
	}
	

}
