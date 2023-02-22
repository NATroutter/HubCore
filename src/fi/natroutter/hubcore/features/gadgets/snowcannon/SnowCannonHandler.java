package fi.natroutter.hubcore.features.gadgets.snowcannon;

import java.util.ArrayList;

import fi.natroutter.natlibs.objects.ParticleSettings;
import fi.natroutter.hubcore.Handler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;


public class SnowCannonHandler {

	public ArrayList<Projectile> sonwCannonBullets = new ArrayList<>();
	public String ProjectileName = "SnowCannon-Projectile";

	private ParticleSettings getPartSet(Location loc) {
		return new ParticleSettings(Particle.END_ROD, loc, 1, 0.1, 0.1, 0.1, 0.02);
	}

	public SnowCannonHandler() {

	}

	public SnowCannonHandler(Handler handler) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(handler.getInstance(), () -> {
			if (sonwCannonBullets.size() > 0){
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
					handler.getSpawner().spawnParticleWorld(getPartSet(bullet.getLocation()));
				}
			}
		}, 0, 2);
	}
	
	public void shoot(Player p) {
		Projectile proj = p.launchProjectile(Snowball.class);
		p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1f, 1f);
		
		proj.setCustomName(ProjectileName);
		proj.setGlowing(true);

		sonwCannonBullets.add(proj);
	}
	
	public Integer newDuration(Integer dura) {
		Integer max = 20 * 15; //seconds
		if (dura > max) {
			return dura;
		} else {
			return dura + 20;
		}
	}
	
}














