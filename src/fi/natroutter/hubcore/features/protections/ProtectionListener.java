package fi.natroutter.hubcore.features.protections;

import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.files.Config;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fi.natroutter.hubcore.handlers.AdminModeHandler;

public class ProtectionListener implements Listener {


	private Config config;
	private AdminModeHandler adminModeHandler;
	private ProtectionHandler protectionHandler;
	public ProtectionListener(Handler handler) {
		this.config = handler.getConfig();
		this.adminModeHandler = handler.getAdminModeHandler();
		this.protectionHandler = handler.getProtectionHandler();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onRedstoneActive(BlockRedstoneEvent e) {
		if (config.DisableRedstone) {
			e.setNewCurrent(0);
		}
	}

	//Disable block physics

	private boolean excluded(String name) {
		return name.endsWith("_STAIRS") || name.endsWith("_FENCE") || name.endsWith("_FENCE_GATE") || name.endsWith("_DOOR") || name.endsWith("GLASS_PANE");
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(BlockPhysicsEvent e) {
		String name =e.getBlock().getType().name();
		if (excluded(name)) {return;}
		if (protectionHandler.disabledMaterials.contains(e.getBlock().getType())) {return;}
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(BlockFormEvent e) {
		String name =e.getBlock().getType().name();
		if (excluded(name)) {return;}
		if (protectionHandler.disabledMaterials.contains(e.getBlock().getType())) {return;}
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(BlockFromToEvent e) {
		String name =e.getBlock().getType().name();
		if (excluded(name)) {return;}
		if (protectionHandler.disabledMaterials.contains(e.getBlock().getType())) {return;}
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(EntityChangeBlockEvent e) {
		String name =e.getBlock().getType().name();
		if (excluded(name)) {return;}
		if (protectionHandler.disabledMaterials.contains(e.getBlock().getType())) {return;}

		if (e.getEntity() instanceof Player p) {
			if (protectionHandler.bypassProtection.contains(p.getUniqueId())) {return;}
		}

		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockExplode(BlockExplodeEvent e) {
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (protectionHandler.bypassProtection.contains(p.getUniqueId())) {return;}

		if (!adminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (protectionHandler.bypassProtection.contains(p.getUniqueId())) {return;}

		if (!adminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPvP(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player attacker)) {return;}
		if (!(e.getEntity() instanceof Player victim)) {return;}
		if (protectionHandler.bypassProtection.contains(victim.getUniqueId()) && protectionHandler.bypassProtection.contains(attacker.getUniqueId())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onDamageEntity(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player p) {
			if (protectionHandler.bypassProtection.contains(p.getUniqueId())) {return;}

			if (!adminModeHandler.isAdmin(p)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (protectionHandler.bypassProtection.contains(e.getPlayer().getUniqueId())) {return;}
		if (e.hasBlock() && e.getClickedBlock() != null) {
			Player p = e.getPlayer();
			Block block = e.getClickedBlock();
			String type = block.getType().name();


			if (!(type.endsWith("BUTTON") || type.endsWith("PRESSURE_PLATE") || type.endsWith("LEVER"))) {
				if (!adminModeHandler.isAdmin(p)) {
					e.setCancelled(true);
				}
			}

		}
	}
	
	
	
	
	
	
}
