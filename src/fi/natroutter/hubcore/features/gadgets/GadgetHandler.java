package fi.natroutter.hubcore.features.gadgets;

import java.util.*;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import org.bukkit.entity.Player;

public class GadgetHandler {

	public HashMap<UUID, Gadget> SelectedGadget = new HashMap<>();
	public LinkedList<Gadget> gadgets;

	private WingsHandler wingsHandler = HubCore.getWingsHandler();
	
	public GadgetHandler() {
		gadgets = new LinkedList<>(Arrays.asList(
				new Gadget("BoomBox", 10, Items.gadged_BoomBox(), Config.Gadgets_Boombox_Need, Config.Gadgets_Boombox_Perm),
				new Gadget("Wings", 11, Items.gadged_Booster(), Items.gadged_Wing(), Config.Gadgets_Wings_Need, Config.Gadgets_Wings_Perm),
				new Gadget("SnowCannon", 12, Items.gadged_SnowCannon(), Config.Gadgets_Snowcannon_Need, Config.Gadgets_Snowcannon_Perm),
				new Gadget("Slapper", 13, Items.gadged_Slapper(), Config.Gadgets_Slapper_Need, Config.Gadgets_Slapper_Perm),
				new Gadget("Jumpper", 14, Items.gadged_Jumpper(), Config.Gadgets_Jumpper_Need, Config.Gadgets_Jumpper_Perm),
				new Gadget("FWShooter", 15, Items.gadged_FireworkShooter(), Config.Gadgets_Fireworkshooter_Need, Config.Gadgets_Fireworkshooter_Perm)
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
