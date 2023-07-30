package fi.natroutter.hubcore.features.gadgets;

import fi.natroutter.betterparkour.BetterParkour;
import fi.natroutter.betterparkour.handlers.ParkourHandler;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis.FwSettingsGUI;
import fi.natroutter.hubcore.features.gadgets.boombox.MusicGUI;
import fi.natroutter.hubcore.features.gadgets.slapper.SlapperHandler;
import fi.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.objects.BaseItem;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fi.natroutter.hubcore.features.gadgets.jumpper.JumpperHandler;
import fi.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GadgetListener implements Listener {

	public HashMap<UUID, Long> cooldown = new HashMap<>();

	private Hooks hooks = HubCore.getHooks();
	private GadgetHandler gadgetHandler = HubCore.getGadgetHandler();
	private SlapperHandler slapperHandler = HubCore.getSlapperHandler();
	private MusicGUI musicGUI = HubCore.getMusicGUI();
	private WingsHandler wingsHandler = HubCore.getWingsHandler();
	private SnowCannonHandler snowCannonHandler = HubCore.getSnowCannonHandler();
	private JumpperHandler jumpperHandler = HubCore.getJumpperHandler();
	private FWSHandler fwsHandler = HubCore.getFwsHandler();
	private FwSettingsGUI fwSettingsGUI = HubCore.getFwSettingsGUI();

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
		if(!Objects.equals(e.getHand(), EquipmentSlot.HAND)) return;
		if (!e.hasItem()) {return;}

		Player p = e.getPlayer();
		BaseItem item = BaseItem.from(e.getItem());
		Action act = e.getAction();

		if (gadgetHandler.disableGadgets.contains(p.getUniqueId())) {return;}

		if (hooks.getBetterParkour().isHooked()) {
			ParkourHandler parkourHandler = BetterParkour.getParkourHandler();
			if (parkourHandler.inCourse(p)) {
				return;
			}
		}

		if (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK)) {

			if (item.isSimilar(Items.gadged_BoomBox()) && p.isSneaking()) {
				if (onCooldown(p, 1)) {return;}
				e.setCancelled(true);
				musicGUI.show(p);
				
			} else if (item.isSimilar(Items.gadged_Booster())) {
				e.setCancelled(true);
				wingsHandler.boost(p);
				
			} else if (item.isSimilar(Items.gadged_SnowCannon())) {
				e.setCancelled(true);
				snowCannonHandler.shoot(p);

			} else if (item.isSimilar(Items.gadged_Jumpper())) {
				e.setCancelled(true);
				jumpperHandler.jump(p);
				
			} else if (item.isSimilar(Items.gadged_FireworkShooter())) {
				if (onCooldown(p, 1)) {return;}
				e.setCancelled(true);
				fwsHandler.shoot(p);

			}
		} else if (act.equals(Action.LEFT_CLICK_AIR) || act.equals(Action.LEFT_CLICK_BLOCK)) {
			if (item.isSimilar(Items.gadged_FireworkShooter())) {
				e.setCancelled(true);
				fwSettingsGUI.show(p);

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
					ParkourHandler parkourHandler = BetterParkour.getParkourHandler();
					if (parkourHandler.inCourse(p) || parkourHandler.inCourse(target)) {
						return;
					}
				}

        		if (item.isSimilar(Items.gadged_Slapper())) {
        			e.setCancelled(true);
					if (onCooldown(p, 5)) {return;}
					slapperHandler.slap(p, target);
        		}
        	}
        	
        }

    }
	
}
