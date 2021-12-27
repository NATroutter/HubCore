package net.natroutter.hubcore.features.gadgets.snowcannon;

import net.citizensnpcs.api.CitizensAPI;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.handlers.Hooks;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.natlibs.events.PlayerJumpEvent;
import net.natroutter.natlibs.utilities.Utilities;

public class SnowCannonListener implements Listener {
	
	private static final Utilities utils = HubCore.getUtilities();
	private PlayerDataHandler pdh = HubCore.getDataHandler();
	private Hooks hooks = HubCore.getHooks();

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Projectile proj = e.getEntity();

		if (e.getHitEntity() != null) {
			if (e.getHitEntity().hasMetadata("NPC")) {return;}
		}

		if (proj.getCustomName() == null) {return;}

		if (proj.getCustomName().equals(SnowCannonHandler.ProjectileName)) {
			Entity ent = e.getHitEntity();
			
			if (ent instanceof Player) {
				Player p = (Player)ent;
				p.playSound(p.getLocation(), Sound.BLOCK_SNOW_HIT, 1f, 0.5f);
				
				if (AdminModeHandler.isAdmin(p)) {return;}

				if (proj.getShooter() instanceof Player) {
					Player shooter = (Player)proj.getShooter();
					if (shooter.getUniqueId().equals(p.getUniqueId())) {
						return;
					}
				}

				PlayerData data = pdh.get(p.getUniqueId());
				if (data.getNoEffect()) {return;}

				if (p.getActivePotionEffects().size() > 0) {
					for (PotionEffect pot : p.getActivePotionEffects()) {
						Integer dura = SnowCannonHandler.newDuration(pot.getDuration());
						
						if (pot.getType().equals(PotionEffectType.SLOW)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, dura, 2), false);
							
						} else if (pot.getType().equals(PotionEffectType.SLOW_FALLING)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, dura, 2), false);
							
						} else if (pot.getType().equals(PotionEffectType.SLOW_DIGGING)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, dura, 2), false);
							
						} else if (pot.getType().equals(PotionEffectType.GLOWING)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, dura, 2), false);
							
						}
					}
				} else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2), false);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 2), false);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2), false);
					p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2), false);
				}

				if (p.getFreezeTicks() < p.getMaxFreezeTicks()) {
					p.setFreezeTicks(p.getFreezeTicks() + 1);
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Snowball) {
			Snowball ball = (Snowball)e.getDamager();
			if (ball.getCustomName() == null) { return; }
			if (ball.getCustomName().equals(SnowCannonHandler.ProjectileName)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJumpEvent e) {
		Player p = e.getPlayer();
		if (p.hasPotionEffect(PotionEffectType.SLOW) && p.hasPotionEffect(PotionEffectType.SLOW_FALLING) && p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
			
			Integer toGround = utils.distanceToGround(p.getLocation());
			
			p.setVelocity(p.getVelocity().add(new Vector(0, -Math.abs((toGround*2) + 5), 0)));
		}
		
	}
	

}













