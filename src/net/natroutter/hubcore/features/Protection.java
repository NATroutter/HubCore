package net.natroutter.hubcore.features;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.natlibs.objects.BasePlayer;

public class Protection implements Listener {

	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		if (!AdminModeHandler.isAdmin(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			BasePlayer p = BasePlayer.from(e.getEntity());
			if (!AdminModeHandler.isAdmin(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		//if (1 == 1) {return;}
		if (e.hasBlock()) {
			BasePlayer p = BasePlayer.from(e.getPlayer());
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
