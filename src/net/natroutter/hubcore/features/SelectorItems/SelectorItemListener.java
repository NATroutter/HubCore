package net.natroutter.hubcore.features.SelectorItems;

import fi.natroutter.natlibs.handlers.gui.GUIWindow;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.objects.BaseItem;
import net.natroutter.betterparkour.BetterParkour;
import net.natroutter.betterparkour.ParkourAPI;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.particles.ParticleGUI;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.handlers.Hooks;
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
import java.util.HashMap;
import java.util.UUID;

public class SelectorItemListener implements Listener {

	private LangManager lang;
	private Hooks hooks;
	private SelectorItemHandler selectorItemHandler;
	private GadgetHandler gadgetHandler;
	private AdminModeHandler adminModeHandler;
	private Items items;
	private GadgetGUI gadgetGUI;
	private ServerSelector serverSelector;
	private ParticleGUI particleGUI;

	public SelectorItemListener(Handler handler) {
		this.lang = handler.getLang();
		this.hooks = handler.getHooks();
		this.selectorItemHandler = handler.getSelectorItemHandler();
		this.gadgetHandler = handler.getGadgetHandler();
		this.adminModeHandler = handler.getAdminModeHandler();
		this.items = handler.getItems();
		this.gadgetGUI = handler.getGadgetGUI();
		this.serverSelector = handler.getServerSelector();
		this.particleGUI = handler.getParticleGUI();
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		cooldown.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.getInventory().clear();

		selectorItemHandler.InitializeItems(p);
		selectorItemHandler.update(p);

		for (int i = 0; i < 10; i++) {
			p.sendMessage(" ");
		}
		lang.sendList(p, Translations.WelcomeMotd);
	}

	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (adminModeHandler.isAdmin(p)) {return;}
		
		Item item = e.getItemDrop();
		BaseItem item2 = BaseItem.from(item.getItemStack());
		
		for(Gadget gad : gadgetHandler.gadgets) {
			if (item2.isSimilar(gad.getItem())) {
				item.remove();
				gadgetHandler.setGadget(p, null);
				selectorItemHandler.update(p);
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
			
			if (adminModeHandler.isAdmin(p)) {return;}
			if (selectorItemHandler.bypassHubItems.contains(p.getUniqueId())) {return;}

			e.setCancelled(true);
			
			if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
				selectorItemHandler.update(p);
			}
		}
	}

	public HashMap<UUID, Long> cooldown = new HashMap<>();
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
		if (selectorItemHandler.bypassHubItems.contains(p.getUniqueId())) {return;}

		if (e.hasItem() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			BaseItem item = BaseItem.from(e.getItem());

			if (hooks.getBetterParkour().isHooked()) {
				ParkourAPI api = BetterParkour.getAPI();
				if (api.getParkourHandler().inCourse(p)) {
					e.setCancelled(true);
					return;
				}
			}
			
			if (item.isSimilar(items.serverSelector(p))) {
				e.setCancelled(true);
				serverSelector.show(p);
				
			} else if (item.isSimilar(items.gadgetSelector())) {
				e.setCancelled(true);
				gadgetGUI.show(p);
				
			} else if (item.isSimilar(items.particleSelector())) {
				e.setCancelled(true);
				particleGUI.show(p);

			} else if (item.isSimilar(items.Info())) {
				if (onCooldown(p)){return;}
				lang.sendList(p, Translations.Info);

			}
				
		}
	}
	
	@EventHandler
	public void onHandSwap(PlayerSwapHandItemsEvent e) {
		Player p = e.getPlayer();
		if (adminModeHandler.isAdmin(p)) {return;}
		e.setCancelled(true);
	}
	
}
