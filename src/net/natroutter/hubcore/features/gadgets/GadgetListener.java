package net.natroutter.hubcore.features.gadgets;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.natroutter.hubcore.features.gadgets.boombox.MusicGUI;
import net.natroutter.hubcore.features.gadgets.jumpper.JumpperHandler;
import net.natroutter.hubcore.features.gadgets.slapper.SlapperHandler;
import net.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import net.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;

public class GadgetListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		//if (1 == 1) {return;}
		BasePlayer p = BasePlayer.from(e.getPlayer());
		if (e.hasItem() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			BaseItem item = BaseItem.from(e.getItem());
			
			if (item.matches(Items.Gadgets.BoomBox()) && p.isSneaking()) {
				e.setCancelled(true);
				MusicGUI.show(p);
				
			} else if (item.matches(Items.Gadgets.Wings.Booster())) {
				e.setCancelled(true);
				WingsHandler.Boost(p);
				
			} else if (item.matches(Items.Gadgets.SnowCannon())) {
				e.setCancelled(true);
				SnowCannonHandler.Shoot(p);
				
			} else if (item.matches(Items.Gadgets.Jumpper())) {
				e.setCancelled(true);
				JumpperHandler.Jump(p);
				
			}
		}
	}
	
	@EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		
        if (e.getRightClicked() instanceof Player) {
        	BasePlayer target = BasePlayer.from(e.getRightClicked());
        	BaseItem item = BaseItem.from(p.getInventory().getItemInMainHand());
        	
        	if (!item.getType().equals(Material.AIR)) {
        		if (item.matches(Items.Gadgets.Slapper())) {
        			e.setCancelled(true);
        			SlapperHandler.slap(p, target);
        		}
        	}
        	
        }

    }
	
}
