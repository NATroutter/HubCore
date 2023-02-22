package fi.natroutter.hubcore.features.gadgets;

import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.files.Translations;
import fi.natroutter.natlibs.handlers.gui.GUIItem;
import fi.natroutter.natlibs.handlers.gui.GUIRow;
import fi.natroutter.natlibs.handlers.gui.GUIWindow;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

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

	LegacyComponentSerializer lcs = LegacyComponentSerializer.legacySection();

	private GUIWindow GUI(Player p) {
		GUIWindow gui = new GUIWindow(lcs.deserialize(lang.get(Translations.Guis_Gadgets_Title)), GUIRow.row3, true);

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
