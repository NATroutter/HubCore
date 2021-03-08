package net.natroutter.hubcore.features.SelectorItems;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import net.natroutter.hubcore.features.ServerSelector;
import net.natroutter.hubcore.features.gadgets.Gadget;
import net.natroutter.hubcore.features.gadgets.GadgetGUI;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;

public class SelectorItemListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		SelectorItemHandler.update(p);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		if (AdminModeHandler.isAdmin(p)) {return;}
		
		Item item = e.getItemDrop();
		BaseItem item2 = BaseItem.from(item.getItemStack());
		
		for(Gadget gad : GadgetHandler.Gadgets) {
			if (item2.matches(gad.getItem())) {
				item.remove();
				GadgetHandler.setGadget(p, null);
				return;
			}
		}
		e.setCancelled(true);
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryclick(InventoryClickEvent e) {
		GUIWindow window = GUIWindow.getWindow(e.getView());
		if (window == null) {
			BasePlayer p = BasePlayer.from(e.getWhoClicked());
			
			if (AdminModeHandler.isAdmin(p)) {return;}
			e.setCancelled(true);
			
			if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
				SelectorItemHandler.update(p);
			}
		}
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		//if (1 == 1) {return;}
		BasePlayer p = BasePlayer.from(e.getPlayer());
		if (e.hasItem() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			BaseItem item = BaseItem.from(e.getItem());
			
			if (item.matches(Items.JoinItems.serverSelector(p))) {
				e.setCancelled(true);
				ServerSelector.show(p);
				
			} else if (item.matches(Items.JoinItems.gadgetSelector())) {
				e.setCancelled(true);
				GadgetGUI.show(p);
				
			}
				
		}
	}
	
	@EventHandler
	public void onHandSwap(PlayerSwapHandItemsEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		if (AdminModeHandler.isAdmin(p)) {return;}
		e.setCancelled(true);
	}
	
}
