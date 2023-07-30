package fi.natroutter.hubcore.features.gadgets.snowcannon;

import fi.natroutter.betterparkour.BetterParkour;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.handlers.AdminModeHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.natlibs.events.PlayerJumpEvent;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.natlibs.utilities.Utilities;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;

public class SnowCannonListener implements Listener {

	private PlayerDataHandler pdh = HubCore.getDataHandler();
	private Hooks hooks = HubCore.getHooks();
	private AdminModeHandler adminModeHandler = HubCore.getAdminModeHandler();
	private SnowCannonHandler snowCannonHandler = HubCore.getSnowCannonHandler();


	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Projectile proj = e.getEntity();

		if (e.getHitEntity() != null) {
			if (e.getHitEntity().hasMetadata("NPC")) {return;}
		}

		if (proj.getCustomName() == null) {return;}

		if (proj.getPersistentDataContainer().has(snowCannonHandler.namespacedKey, PersistentDataType.INTEGER)) {
			Entity ent = e.getHitEntity();
			
			if (ent instanceof Player p) {
				if (hooks.getBetterParkour().isHooked()) {
					if (BetterParkour.getParkourHandler().inCourse(p)) {
						e.setCancelled(true);
						return;
					}
				}

				p.playSound(p.getLocation(), Sound.BLOCK_SNOW_HIT, 1f, 0.5f);
				
				if (adminModeHandler.isAdmin(p)) {return;}

				if (proj.getShooter() instanceof Player shooter) {
					if (shooter.getUniqueId().equals(p.getUniqueId())) {
						return;
					}
				}

				PlayerData data = pdh.get(p.getUniqueId());
				if (data.getNoEffect()) {return;}

				if (p.getActivePotionEffects().size() > 0) {
					for (PotionEffect pot : p.getActivePotionEffects()) {
						Integer dura = snowCannonHandler.newDuration(pot.getDuration());
						
						if (pot.getType().equals(PotionEffectType.SLOW)) {
							p.addPotionEffects(List.of(
									new PotionEffect(PotionEffectType.SLOW, dura, 2)
							));
							
						} else if (pot.getType().equals(PotionEffectType.SLOW_FALLING)) {
							p.addPotionEffects(List.of(
									new PotionEffect(PotionEffectType.SLOW_FALLING, dura, 2)
							));
							
						} else if (pot.getType().equals(PotionEffectType.SLOW_DIGGING)) {
							p.addPotionEffects(List.of(
									new PotionEffect(PotionEffectType.SLOW_DIGGING, dura, 2)
							));
							
						} else if (pot.getType().equals(PotionEffectType.GLOWING)) {
							p.addPotionEffects(List.of(
									new PotionEffect(PotionEffectType.GLOWING, dura, 2)
							));
							
						}
					}
				} else {
					p.addPotionEffects(List.of(
							new PotionEffect(PotionEffectType.SLOW, 40, 2),
							new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 2),
							new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2),
							new PotionEffect(PotionEffectType.GLOWING, 40, 2)
					));
				}

				if (p.getFreezeTicks() < p.getMaxFreezeTicks()) {
					p.setFreezeTicks(p.getFreezeTicks() + 1);
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Snowball ball) {
			if (ball.getPersistentDataContainer().has(snowCannonHandler.namespacedKey, PersistentDataType.INTEGER)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJumpEvent e) {
		Player p = e.getPlayer();
		if (p.hasPotionEffect(PotionEffectType.SLOW) && p.hasPotionEffect(PotionEffectType.SLOW_FALLING) && p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
			
			float toGround = Utilities.distanceToGround(p.getLocation());
			
			p.setVelocity(p.getVelocity().add(new Vector(0, -Math.abs((toGround*2) + 5), 0)));
		}
		
	}
	

}













