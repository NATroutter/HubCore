package net.natroutter.hubcore.features.gadgets;

import java.util.HashMap;

import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.objects.BasePlayer;

public class GadgetHandler {

	private static final HashMap<BasePlayer, Gadget> SelectedGadget = new HashMap<>();
	
	
	public static Gadget[] Gadgets = new Gadget[] {
		new Gadget("BoomBox", 10, Items.Gadgets.BoomBox()),
		new Gadget("Wings", 11, Items.Gadgets.Wings.Booster(), Items.Gadgets.Wings.Wing()),
		new Gadget("SnowCannon", 12, Items.Gadgets.SnowCannon()),
		new Gadget("Slapper", 13, Items.Gadgets.Slapper()),
		new Gadget("Jumpper", 14, Items.Gadgets.Jumpper())
	};
	
	public static void Initialize() {
		//
	}
	
	public static void setGadget(BasePlayer p, Gadget gad) {
		if (gad == null) {
			SelectedGadget.remove(p);
		} else {
			SelectedGadget.put(p, gad);
			if (gad.getIdentifier().equals("Wings")) {
				WingsHandler.Active(p);
			}
		}
		SelectorItemHandler.update(p);
	}
	
	public static Gadget getGadget(BasePlayer p) {
		return SelectedGadget.getOrDefault(p, null);
	}
	
	
	
	
}
