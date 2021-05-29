package net.natroutter.hubcore.features;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.natroutter.hubcore.handlers.AdminModeHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Protection implements Listener {

private final Config config = HubCore.getCfg();

	@EventHandler(priority = EventPriority.MONITOR)
	public void onRedstoneActive(BlockRedstoneEvent e) {
		if (config.DisableRedstone) {
			e.setNewCurrent(0);
		}
	}

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPistonRetract(BlockPistonRetractEvent e) {
		if (config.DisableRedstone) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPistonExtend(BlockPistonExtendEvent e) {
		if (config.DisableRedstone) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player)e.getPlayer();
		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if (!AdminModeHandler.isAdmin(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		//if (1 == 1) {return;}
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
