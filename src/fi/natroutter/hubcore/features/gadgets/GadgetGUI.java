package fi.natroutter.hubcore.features.gadgets;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.natlibs.handlers.guibuilder.Button;
import fi.natroutter.natlibs.handlers.guibuilder.GUI;
import fi.natroutter.natlibs.handlers.guibuilder.GUIFrame;
import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import org.bukkit.entity.Player;

import java.util.List;

public class GadgetGUI extends GUIFrame {

	private GadgetHandler gadgetHandler = HubCore.getGadgetHandler();
	private SelectorItemHandler selectorItemHandler = HubCore.getSelectorItemHandler();
	public GadgetGUI() {
		super(Lang.Guis_Gadgets_Title, Rows.row3);
	}


	@Override
	protected boolean onShow(Player player, GUI gui, List<Object> args) {
		for (Gadget gad : gadgetHandler.gadgets) {
			gui.setButton(new Button(gad.getIconWithNeed(), (e, g)->{
				Player p = e.getPlayer();
				if (!p.hasPermission(gad.getPermission())) {
					p.sendMessage(Lang.NoPerm.prefixed());
					return;
				}
				gadgetHandler.setGadget(p, gad);
				selectorItemHandler.update(p);
				gui.close(p);
			}),gad.getSlot());
		}
		return true;
	}
}
