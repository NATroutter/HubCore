package net.natroutter.hubcore.features.protections;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
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

import net.natroutter.hubcore.handlers.AdminModeHandler;

import java.util.ArrayList;
import java.util.UUID;

public class Protection implements Listener {

private final Config config = HubCore.getCfg();

	@EventHandler(priority = EventPriority.MONITOR)
	public void onRedstoneActive(BlockRedstoneEvent e) {
		if (config.DisableRedstone) {
			e.setNewCurrent(0);
		}
	}

	//Disable block physics

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(BlockPhysicsEvent e) {
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(BlockFormEvent e) {
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(BlockFromToEvent e) {
		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPhysic(EntityChangeBlockEvent e) {
		if (e.getEntity() instanceof Player p) {
			if (bypassProtection.contains(p.getUniqueId())) {return;}
		}

		if (config.DisablePhysics) {
			e.setCancelled(true);
		}
	}


	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (bypassProtection.contains(p.getUniqueId())) {return;}

		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (bypassProtection.contains(p.getUniqueId())) {return;}

		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}

	protected static ArrayList<UUID> bypassProtection = new ArrayList<>();
	public static void bypass(Player p, boolean status) {
		if (status) {
			if (!bypassProtection.contains(p.getUniqueId())) {
				bypassProtection.add(p.getUniqueId());
			}
		} else {
			bypassProtection.remove(p.getUniqueId());
		}
	}

	@EventHandler
	public void onPvP(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player attacker)) {return;}
		if (!(e.getEntity() instanceof Player victim)) {return;}
		if (bypassProtection.contains(victim.getUniqueId()) && bypassProtection.contains(attacker.getUniqueId())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onDamageEntity(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player p) {
			if (bypassProtection.contains(p.getUniqueId())) {return;}

			if (!AdminModeHandler.isAdmin(p)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (bypassProtection.contains(e.getPlayer().getUniqueId())) {return;}
		if (e.hasBlock()) {
			Player p = e.getPlayer();
			Block block = e.getClickedBlock();
			String type = block.getType().name();


			if (!(type.endsWith("BUTTON") || type.endsWith("PRESSURE_PLATE") || type.endsWith("LEVER"))) {
				if (!AdminModeHandler.isAdmin(p)) {
					e.setCancelled(true);
				}
			}

		}
	}
	
	
	
	
	
	
}
