package net.natroutter.hubcore.features.SelectorItems;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.natroutter.hubcore.features.gadgets.Gadget;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.objects.BaseItem;

public class SelectorItemHandler {
	
	private static List<HubItem> getHubItems(Player p) {
		return Arrays.asList(
			new HubItem("ParticleSelector", 0, Items.JoinItems.particleSelector()),
			new HubItem("GadgetSelector", 1, Items.JoinItems.gadgetSelector()),
			new HubItem("ServerSelector", 4, Items.JoinItems.serverSelector(p)),
			new HubItem("InfoBook", 8, Items.JoinItems.Info())
		);
	}
	
	private static Boolean isHubItem(Player p, BaseItem item) {
		for(HubItem hubitem : getHubItems(p)) {
			if (item.matches(hubitem.getItem())) {
				return true;
			}
		}
		return false;
	}
	
	public static class HubItem {
		private final String Identifier;
		private final Integer Slot;
		private final BaseItem Item;
		public HubItem(String Identifier, Integer Slot, BaseItem Item) {
			this.Identifier = Identifier;
			this.Slot = Slot;
			this.Item = Item;
		}
		public String getId() { return Identifier; }
		public Integer getSlot() { return Slot; }
		public BaseItem getItem() { return Item; }
	}
	
	
	public static void update(Player p) {
		Inventory inv = p.getInventory();
		ClearInvalidItems(p);
		
		Gadget gad = GadgetHandler.getGadget(p);
		
		for (HubItem hubitem : getHubItems(p)) {
			if (hubitem.getId().equals("GadgetSelector")) {
				
				if (gad != null) {
					if (!BaseItem.from(inv.getItem(hubitem.getSlot())).matches(gad.getItem())) {
						inv.setItem(hubitem.getSlot(), gad.getItem());
					}
				} else {
					if (!BaseItem.from(inv.getItem(hubitem.getSlot())).matches(hubitem.getItem())) {
						inv.setItem(hubitem.getSlot(), hubitem.getItem());
					}
				}
				
			} else {
				if (!BaseItem.from(inv.getItem(hubitem.getSlot())).matches(hubitem.getItem())) {
					inv.setItem(hubitem.getSlot(), hubitem.getItem());
				}
			}
		}
		p.updateInventory();
	}
	
	private static void ClearInvalidItems(Player p) {
		Inventory inv = p.getInventory();
		Gadget selectedGad = GadgetHandler.getGadget(p);
		
		for (int slot = 0; slot < inv.getContents().length; slot++) {
			BaseItem item = BaseItem.from(inv.getItem(slot));
			
			if (!item.getType().equals(Material.AIR)) {
				
				if (selectedGad != null) {
					if (item.matches(selectedGad.getItem())) {
						continue;
					} else if (item.matches(selectedGad.getIcon())) {
						continue;
					}
				}
				
				if (isHubItem(p, item)) {continue;}
				item.Destroy();
			}
		}
		p.updateInventory();
	}
	
}














