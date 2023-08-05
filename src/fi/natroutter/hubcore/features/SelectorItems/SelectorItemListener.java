package fi.natroutter.hubcore.features.SelectorItems;

import fi.natroutter.betterparkour.BetterParkour;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.ServerSelector;
import fi.natroutter.hubcore.features.gadgets.Gadget;
import fi.natroutter.hubcore.features.gadgets.GadgetGUI;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
import fi.natroutter.hubcore.features.particles.ParticleGUI;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.AdminModeHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.handlers.guibuilder.GuiHelper;
import fi.natroutter.natlibs.objects.BaseItem;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class SelectorItemListener implements Listener {

	private Hooks hooks = HubCore.getHooks();
	private SelectorItemHandler selectorItemHandler = HubCore.getSelectorItemHandler();
	private GadgetHandler gadgetHandler = HubCore.getGadgetHandler();
	private AdminModeHandler adminModeHandler = HubCore.getAdminModeHandler();
	private GadgetGUI gadgetGUI = HubCore.getGadgetGUI();
	private ServerSelector serverSelector = HubCore.getServerSelector();
	private ParticleGUI particleGUI = HubCore.getParticleGUI();

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		cooldown.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.joinMessage(null);
		Player p = e.getPlayer();
		p.getInventory().clear();

		selectorItemHandler.InitializeItems(p);
		selectorItemHandler.update(p);

		for (int i = 0; i < 10; i++) {
			p.sendMessage(" ");
		}
		p.sendMessage(Lang.WelcomeMotd.asSingleComponent());
	}

	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		e.quitMessage(null);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (adminModeHandler.isAdmin(p)) {return;}
		
		Item item = e.getItemDrop();
		BaseItem item2 = BaseItem.from(item.getItemStack());
		
		for(Gadget gad : gadgetHandler.gadgets) {
			if (item2.isSimilar(gad.getItem())) {
				if (gad.getIdentifier().equals("Wings")) {
					EntityEquipment equip = e.getPlayer().getEquipment();
					if (equip.getChestplate().displayName().equals(gad.getIcon().displayName())) {
						equip.setChestplate(new ItemStack(Material.AIR));
					}
				}
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
		if(e.getWhoClicked() instanceof Player p) {
			if (!GuiHelper.hasOpen(p)) {

				if (adminModeHandler.isAdmin(p)) {return;}
				if (selectorItemHandler.bypassHubItems.contains(p.getUniqueId())) {return;}

				e.setCancelled(true);

				if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
					selectorItemHandler.update(p);
				}
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
				if (BetterParkour.getParkourHandler().inCourse(p)) {
					e.setCancelled(true);
					return;
				}
			}
			
			if (item.isSimilar(Items.serverSelector(p))) {
				e.setCancelled(true);
				serverSelector.show(p);
				
			} else if (item.isSimilar(Items.gadgetSelector())) {
				e.setCancelled(true);
				gadgetGUI.show(p);
				
			} else if (item.isSimilar(Items.particleSelector())) {
				e.setCancelled(true);
				particleGUI.show(p);

			} else if (item.isSimilar(Items.Info())) {
				if (onCooldown(p)){return;}
				p.sendMessage(Lang.Info.asSingleComponent());

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
