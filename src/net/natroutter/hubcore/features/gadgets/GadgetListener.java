package net.natroutter.hubcore.features.gadgets;

import net.natroutter.betterparkour.BetterParkour;
import net.natroutter.betterparkour.ParkourAPI;
import net.natroutter.betterparkour.handlers.ParkourHandler;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.FireworkGUI;
import net.natroutter.hubcore.handlers.Hooks;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
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
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class GadgetListener implements Listener {

	public HashMap<UUID, Long> cooldown = new HashMap<>();

	private Hooks hooks;
	private GadgetHandler gadgetHandler;
	private Items items;
	private SlapperHandler slapperHandler;
	private MusicGUI musicGUI;
	private WingsHandler wingsHandler;
	private SnowCannonHandler snowCannonHandler;
	private JumpperHandler jumpperHandler;
	private FWSHandler fwsHandler;
	private FireworkGUI fireworkGUI;

	public GadgetListener(Handler handler) {
		this.hooks = handler.getHooks();
		this.gadgetHandler = handler.getGadgetHandler();
		this.items = handler.getItems();
		this.slapperHandler = handler.getSlapperHandler();
		this.musicGUI = handler.getMusicGUI();
		this.wingsHandler = handler.getWingsHandler();
		this.snowCannonHandler = handler.getSnowCannonHandler();
		this.jumpperHandler = handler.getJumpperHandler();
		this.fwsHandler = handler.getFwsHandler();
		this.fireworkGUI = handler.getFireworkGUI();
	}

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
	public void onLeave(PlayerQuitEvent e) {
		cooldown.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (!e.hasItem()) {return;}

		Player p = e.getPlayer();
		BaseItem item = BaseItem.from(e.getItem());
		Action act = e.getAction();

		if (gadgetHandler.disableGadgets.contains(p.getUniqueId())) {return;}

		if (hooks.getBetterParkour().isHooked()) {
			ParkourAPI api = BetterParkour.getAPI();
			ParkourHandler parkourHandler = api.getParkourHandler();
			if (parkourHandler.inCourse(p)) {
				return;
			}
		}

		if (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK)) {

			if (item.isSimilar(items.gadged_BoomBox()) && p.isSneaking()) {
				if (onCooldown(p, 1)) {return;}
				e.setCancelled(true);
				musicGUI.show(p);
				
			} else if (item.isSimilar(items.gadged_Booster())) {
				e.setCancelled(true);
				wingsHandler.boost(p);
				
			} else if (item.isSimilar(items.gadged_SnowCannon())) {
				e.setCancelled(true);
				snowCannonHandler.shoot(p);

			} else if (item.isSimilar(items.gadged_Jumpper())) {
				e.setCancelled(true);
				jumpperHandler.jump(p);
				
			} else if (item.isSimilar(items.gadged_FireworkShooter())) {
				if (onCooldown(p, 1)) {return;}
				e.setCancelled(true);
				fwsHandler.shoot(p);

			}
		} else if (act.equals(Action.LEFT_CLICK_AIR) || act.equals(Action.LEFT_CLICK_BLOCK)) {
			if (item.isSimilar(items.gadged_FireworkShooter())) {
				e.setCancelled(true);
				fireworkGUI.showSettingsGUI(p);

			}
		}
	}
	
	@EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();

		if (gadgetHandler.disableGadgets.contains(p.getUniqueId())) {return;}

		if (ent.hasMetadata("NPC")) {return;}

        if (e.getRightClicked() instanceof Player target) {

			BaseItem item = BaseItem.from(p.getInventory().getItemInMainHand());
        	
        	if (!item.getType().equals(Material.AIR)) {

				if (hooks.getBetterParkour().isHooked()) {
					ParkourAPI api = BetterParkour.getAPI();
					ParkourHandler parkourHandler = api.getParkourHandler();
					if (parkourHandler.inCourse(p) || parkourHandler.inCourse(target)) {
						return;
					}
				}

        		if (item.isSimilar(items.gadged_Slapper())) {
        			e.setCancelled(true);
					if (onCooldown(p, 5)) {return;}
					slapperHandler.slap(p, target);
        		}
        	}
        	
        }

    }
	
}
