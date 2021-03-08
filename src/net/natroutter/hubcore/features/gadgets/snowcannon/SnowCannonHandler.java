package net.natroutter.hubcore.features.gadgets.snowcannon;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Utilities;

public class SnowCannonHandler {

	private static final Utilities utils = NATLibs.getUtilities();
	
	private static final HashMap<UUID, Integer> particleTasks = new HashMap<>();
	
	public static String ProjectileName = "SnowCannon-Projectile";
	
	private static ParticleSettings getPartSet(Location loc) {
		return new ParticleSettings(Particle.END_ROD, loc, 1, 0.1, 0.1, 0.1, 0.02);
	}
	
	public static void Shoot(BasePlayer p) {
		Projectile proj = p.launchProjectile(Snowball.class);
		p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1f, 1f);
		
		proj.setCustomName(ProjectileName);
		proj.setGlowing(true);
		
		particleTasks.put(proj.getUniqueId(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HubCore.getPlugin(), () -> {
			if (proj.isDead()) {

				Bukkit.getScheduler().cancelTask(particleTasks.get(proj.getUniqueId()));
			}
			utils.spawnParticleInRadius(getPartSet(proj.getLocation()), 40);
		}, 0, 1));
	}
	
	public static Integer newDuration(Integer dura) {
		Integer max = 20 * 15; //seconds
		if (dura > max) {
			return dura;
		} else {
			return dura + 20;
		}
	}
	
}














