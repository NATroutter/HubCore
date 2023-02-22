package fi.natroutter.hubcore.features.gadgets;

import java.util.*;

import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import fi.natroutter.hubcore.files.Config;
import org.bukkit.entity.Player;

public class GadgetHandler {

	public HashMap<UUID, Gadget> SelectedGadget = new HashMap<>();
	public LinkedList<Gadget> gadgets;

	private Items items;
	private Config config;
	private WingsHandler wingsHandler;
	public GadgetHandler(Handler handler) {
		this.items = handler.getItems();
		this.config = handler.getConfig();
		this.wingsHandler = handler.getWingsHandler();

		gadgets = new LinkedList<>(Arrays.asList(
				new Gadget("BoomBox", 10, items.gadged_BoomBox(), config.gadgets.boombox.need, config.gadgets.boombox.permission),
				new Gadget("Wings", 11, items.gadged_Booster(), items.gadged_Wing(), config.gadgets.wings.need, config.gadgets.wings.permission),
				new Gadget("SnowCannon", 12, items.gadged_SnowCannon(), config.gadgets.snowcannon.need, config.gadgets.snowcannon.permission),
				new Gadget("Slapper", 13, items.gadged_Slapper(), config.gadgets.slapper.need, config.gadgets.slapper.permission),
				new Gadget("Jumpper", 14, items.gadged_Jumpper(), config.gadgets.jumpper.need, config.gadgets.jumpper.permission),
				new Gadget("FWShooter", 15, items.gadged_FireworkShooter(), config.gadgets.fireworkshooter.need, config.gadgets.fireworkshooter.permission)
		));
	}


	public void addGadgets(Gadget gadget) {
		gadgets.add(gadget);
	}

	public void setGadgets(int index, Gadget gadget) {
		gadgets.set(index, gadget);
	}

	protected ArrayList<UUID> disableGadgets = new ArrayList<>();
	public void disableGadgets(Player p, boolean status) {
		if (status) {
			if (!disableGadgets.contains(p.getUniqueId())) {
				disableGadgets.add(p.getUniqueId());
			}
		} else {
			disableGadgets.remove(p.getUniqueId());
		}
	}

	public void setGadget(Player p, Gadget gad) {
		if (gad == null) {
			SelectedGadget.remove(p.getUniqueId());
		} else {
			SelectedGadget.put(p.getUniqueId(), gad);
			if (gad.getIdentifier().equals("Wings")) {
				wingsHandler.Active(p);
			}
		}
	}
	
	public Gadget getGadget(Player p) {
		return SelectedGadget.getOrDefault(p.getUniqueId(), null);
	}
	
	
	
	
}
