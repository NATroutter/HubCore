package net.natroutter.hubcore.features.gadgets;

import java.util.HashMap;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Items;
import org.bukkit.entity.Player;

public class GadgetHandler {

	public static final HashMap<Player, Gadget> SelectedGadget = new HashMap<>();

	private static final Config config = HubCore.getCfg();
	
	public static Gadget[] Gadgets = new Gadget[] {
		new Gadget("BoomBox", 10, Items.Gadgets.BoomBox(), config.gadgets.boombox.need, config.gadgets.boombox.permission),
		new Gadget("Wings", 11, Items.Gadgets.Wings.Booster(), Items.Gadgets.Wings.Wing(), config.gadgets.wings.need, config.gadgets.wings.permission),
		new Gadget("SnowCannon", 12, Items.Gadgets.SnowCannon(), config.gadgets.snowcannon.need, config.gadgets.snowcannon.permission),
		new Gadget("Slapper", 13, Items.Gadgets.Slapper(), config.gadgets.slapper.need, config.gadgets.slapper.permission),
		new Gadget("Jumpper", 14, Items.Gadgets.Jumpper(), config.gadgets.jumpper.need, config.gadgets.jumpper.permission),
		new Gadget("FWShooter", 15, Items.Gadgets.FireworkShooter(), config.gadgets.fireworkshooter.need, config.gadgets.fireworkshooter.permission)
	};

	public static void setGadget(Player p, Gadget gad) {
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
	
	public static Gadget getGadget(Player p) {
		return SelectedGadget.getOrDefault(p, null);
	}
	
	
	
	
}
