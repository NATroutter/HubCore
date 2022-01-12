package net.natroutter.hubcore.features.SelectorItems;

import net.natroutter.betterparkour.BetterParkour;
import net.natroutter.betterparkour.ParkourAPI;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.particles.ParticleGUI;
import net.natroutter.hubcore.handlers.Hooks;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.hooking.Hook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;

import net.natroutter.hubcore.features.ServerSelector;
import net.natroutter.hubcore.features.gadgets.Gadget;
import net.natroutter.hubcore.features.gadgets.GadgetGUI;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;

import java.util.HashMap;
import java.util.UUID;

public class SelectorItemListener implements Listener {

	private static final Lang lang = HubCore.getLang();
	private static final Hooks hooks = HubCore.getHooks();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.getInventory().clear();

		SelectorItemHandler.InitializeItems(p);
		SelectorItemHandler.update(p);

		for (int i = 0; i<255; i++) {
			p.sendMessage(" ");
		}
		for (String line : lang.welcommotd) {
			p.sendMessage(line);
		}
	}

	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (AdminModeHandler.isAdmin(p)) {return;}
		
		Item item = e.getItemDrop();
		BaseItem item2 = BaseItem.from(item.getItemStack());
		
		for(Gadget gad : GadgetHandler.gadgets) {
			if (item2.isSimilar(gad.getItem())) {
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
			Player p = (Player)e.getWhoClicked();
			
			if (AdminModeHandler.isAdmin(p)) {return;}
			if (SelectorItemHandler.bypassHubItems.contains(p.getUniqueId())) {return;}

			e.setCancelled(true);
			
			if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
				SelectorItemHandler.update(p);
			}
		}
	}

	public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private static int cooldownTime = 1;
	public boolean onCooldown(Player p) {
		if(cooldown.containsKey(p.getUniqueId())) {
			long cooldownLeft = ((cooldown.get(p.getUniqueId())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			if(cooldownLeft>0) {
				return true;
			}
		}
		cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		return false;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		//if (1 == 1) {return;}
		Player p = e.getPlayer();
		if (SelectorItemHandler.bypassHubItems.contains(p.getUniqueId())) {return;}

		if (e.hasItem() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			BaseItem item = BaseItem.from(e.getItem());

			if (hooks.getBetterParkour().isHooked()) {
				ParkourAPI api = BetterParkour.getAPI();
				if (api.getParkourHandler().inCourse(p)) {
					e.setCancelled(true);
					return;
				}
			}
			
			if (item.isSimilar(Items.JoinItems.serverSelector(p))) {
				e.setCancelled(true);
				ServerSelector.show(p);
				
			} else if (item.isSimilar(Items.JoinItems.gadgetSelector())) {
				e.setCancelled(true);
				GadgetGUI.show(p);
				
			} else if (item.isSimilar(Items.JoinItems.particleSelector())) {
				e.setCancelled(true);
				ParticleGUI.show(p);

			} else if (item.isSimilar(Items.JoinItems.Info())) {
				if (onCooldown(p)){return;}
				for (String line : lang.info) {
					p.sendMessage(line);
				}

			}
				
		}
	}
	
	@EventHandler
	public void onHandSwap(PlayerSwapHandItemsEvent e) {
		Player p = e.getPlayer();
		if (AdminModeHandler.isAdmin(p)) {return;}
		e.setCancelled(true);
	}
	
}
