package net.natroutter.hubcore.features.gadgets;

import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
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
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.UUID;

public class GadgetListener implements Listener {

	public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();

	public boolean onCooldown(Player p , int time) {
		if(cooldown.containsKey(p.getUniqueId())) {
			long cooldownLeft = ((cooldown.get(p.getUniqueId())/1000)+time) - (System.currentTimeMillis()/1000);
			if(cooldownLeft>0) {
				return true;
			}
		}
		cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		return false;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (!e.hasItem()) {return;}

		Player p = e.getPlayer();
		BaseItem item = BaseItem.from(e.getItem());
		Action act = e.getAction();

		if (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK)) {

			if (item.matches(Items.Gadgets.BoomBox()) && p.isSneaking()) {
				if (onCooldown(p, 1)) {return;}
				e.setCancelled(true);
				MusicGUI.show(p);
				
			} else if (item.matches(Items.Gadgets.Wings.Booster())) {
				e.setCancelled(true);
				WingsHandler.boost(p);
				
			} else if (item.matches(Items.Gadgets.SnowCannon())) {
				e.setCancelled(true);
				SnowCannonHandler.shoot(p);

			} else if (item.matches(Items.Gadgets.Jumpper())) {
				e.setCancelled(true);
				JumpperHandler.jump(p);
				
			} else if (item.matches(Items.Gadgets.FireworkShooter())) {
				if (onCooldown(p, 1)) {return;}
				e.setCancelled(true);
				FWSHandler.shoot(p);

			}
		} else if (act.equals(Action.LEFT_CLICK_AIR) || act.equals(Action.LEFT_CLICK_BLOCK)) {
			if (item.matches(Items.Gadgets.FireworkShooter())) {
				e.setCancelled(true);
				FWSHandler.showGUI(p);

			}
		}
	}
	
	@EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		
        if (e.getRightClicked() instanceof Player) {
			Player target = (Player)e.getRightClicked();
        	BaseItem item = BaseItem.from(p.getInventory().getItemInMainHand());
        	
        	if (!item.getType().equals(Material.AIR)) {
        		if (item.matches(Items.Gadgets.Slapper())) {
        			e.setCancelled(true);
					if (onCooldown(p, 5)) {return;}
        			SlapperHandler.slap(p, target);
        		}
        	}
        	
        }

    }
	
}
