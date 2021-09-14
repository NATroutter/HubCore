package net.natroutter.hubcore.features.gadgets.snowcannon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Utilities;

public class SnowCannonHandler {

	private static final Utilities utils = HubCore.getUtilities();

	public static ArrayList<Projectile> sonwCannonBullets = new ArrayList<>();
	
	public static String ProjectileName = "SnowCannon-Projectile";
	
	private static ParticleSettings getPartSet(Location loc) {
		return new ParticleSettings(Particle.END_ROD, loc, 1, 0.1, 0.1, 0.1, 0.02);
	}

	public static void Initialize() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HubCore.getPlugin(), () -> {
			for (int i = 0; i < sonwCannonBullets.size(); i++) {
				Projectile bullet = sonwCannonBullets.get(i);
				if (bullet.isDead()) {
					sonwCannonBullets.remove(i);
					return;
				}
				int secs = bullet.getTicksLived() / 20;
				if (secs > 1) {
					sonwCannonBullets.remove(i);
					bullet.remove();
					return;
				}
				utils.spawnParticleInRadius(getPartSet(bullet.getLocation()), 50);
			}
		}, 0, 2);
	}
	
	public static void shoot(Player p) {
		Projectile proj = p.launchProjectile(Snowball.class);
		p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1f, 1f);
		
		proj.setCustomName(ProjectileName);
		proj.setGlowing(true);

		sonwCannonBullets.add(proj);
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














