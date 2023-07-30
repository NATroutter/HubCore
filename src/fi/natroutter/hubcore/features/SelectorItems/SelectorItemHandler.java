package fi.natroutter.hubcore.features.SelectorItems;

import java.util.*;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fi.natroutter.hubcore.features.gadgets.Gadget;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
import org.bukkit.inventory.ItemStack;

public class SelectorItemHandler {

	public HashMap<UUID, LinkedList<HubItem>> hubItemMap = new HashMap<>();

	private GadgetHandler gadgetHandler = HubCore.getGadgetHandler();

	public LinkedList<HubItem> defaultItems(Player p) {
		LinkedList<HubItem> list = new LinkedList<>();
		list.add(new HubItem("ParticleSelector", 0, Items.particleSelector()));
		list.add(new HubItem("GadgetSelector", 1, Items.gadgetSelector()));
		list.add(new HubItem("ServerSelector", 4, Items.serverSelector(p)));
		list.add(new HubItem("InfoBook", 8, Items.Info()));
		return list;
	}

	public void InitializeItems(Player p) {
		if (!hubItemMap.containsKey(p.getUniqueId())) {
			hubItemMap.put(p.getUniqueId(), new LinkedList<>(defaultItems(p)));
		}
	}

	public void reloadItems() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Bukkit.broadcastMessage("test: " + p.getUniqueId());
			hubItemMap.put(p.getUniqueId(), defaultItems(p));
			update(p);
		}
	}

	public void addHubItem(Player p, HubItem item) {
		if (!hubItemMap.containsKey(p.getUniqueId())) {InitializeItems(p);}

		LinkedList<HubItem> list = hubItemMap.get(p.getUniqueId());
		list.add(item);

		hubItemMap.put(p.getUniqueId(), list);
	}

	public void setHubItems(Player p, LinkedList<HubItem> items) {
		if (!hubItemMap.containsKey(p.getUniqueId())) {InitializeItems(p);}
		hubItemMap.put(p.getUniqueId(), items);
	}

	public void addHubItems(Player p, HubItem... items) {
		if (!hubItemMap.containsKey(p.getUniqueId())) {InitializeItems(p);}

		LinkedList<HubItem> list = hubItemMap.get(p.getUniqueId());
		list.addAll(new LinkedList<>(Arrays.asList(items)));

		hubItemMap.put(p.getUniqueId(), list);
	}

	public void replaceHubItems(HashMap<UUID, LinkedList<HubItem>> map) {
		hubItemMap = map;
	}

	public List<HubItem> getHubItems(Player p) {
		if (!hubItemMap.containsKey(p.getUniqueId())) {InitializeItems(p);}

		return hubItemMap.get(p.getUniqueId());
	}

	protected ArrayList<UUID> bypassHubItems = new ArrayList<>();
	public void useHubItems(Player p, boolean status) {
		if (!status) {
			if (!bypassHubItems.contains(p.getUniqueId())) {
				bypassHubItems.add(p.getUniqueId());
			}
		} else {
			bypassHubItems.remove(p.getUniqueId());
		}
	}

	public Boolean isHubItem(Player p, BaseItem item) {
		for(HubItem hubitem : getHubItems(p)) {
			if (item.isSimilar(hubitem.item())) {
				return true;
			}
		}
		return false;
	}

	public void update(Player p) {
		if (bypassHubItems.contains(p.getUniqueId())) {return;}

		Inventory inv = p.getInventory();
		ClearInvalidItems(p);
		
		Gadget gad = gadgetHandler.getGadget(p);
		
		for (HubItem hubitem : getHubItems(p)) {

			if (hubitem.id().equals("GadgetSelector")) {
				if (gad != null) {
					inv.setItem(hubitem.slot(), gad.getItem());
					continue;
				}
			}
			inv.setItem(hubitem.slot(), hubitem.item());
		}
		p.updateInventory();
	}
	
	private void ClearInvalidItems(Player p) {
		if (bypassHubItems.contains(p.getUniqueId())) {return;}

		Inventory inv = p.getInventory();
		Gadget selectedGad = gadgetHandler.getGadget(p);
		
		for (int slot = 0; slot < inv.getContents().length; slot++) {
			ItemStack stack = inv.getItem(slot);
			if (stack != null) {
				BaseItem item = BaseItem.from(stack);

				if (!item.getType().equals(Material.AIR)) {

					if (selectedGad != null) {
						if (item.isSimilar(selectedGad.getItem())) {
							continue;
						} else if (item.isSimilar(selectedGad.getIcon())) {
							continue;
						}
					}

					if (isHubItem(p, item)) {continue;}
					item.destroy();
				}
			}
		}
		p.updateInventory();
	}
	
}














