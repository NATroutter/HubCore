package fi.natroutter.hubcore.features.gadgets.snowcannon;

import java.util.ArrayList;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.natlibs.NATLibs;
import fi.natroutter.natlibs.handlers.Particles;
import fi.natroutter.natlibs.objects.ParticleSettings;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.persistence.PersistentDataType;


public class SnowCannonHandler {

	public ArrayList<Projectile> sonwCannonBullets = new ArrayList<>();
	protected NamespacedKey namespacedKey = new NamespacedKey(HubCore.getInstance(), "snowcannon-projectile");

	private ParticleSettings getPartSet() {
		return new ParticleSettings(Particle.END_ROD, 1, 0.1, 0.1, 0.1, 0.02);
	}

	public SnowCannonHandler() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HubCore.getInstance(), () -> {
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
					Particles.spawnWorld(bullet.getLocation(), getPartSet());
				}
			}
		}, 0, 2);
	}
	
	public void shoot(Player p) {
		Projectile proj = p.launchProjectile(Snowball.class);
		p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1f, 1f);
		proj.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, 1);
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














