package fi.natroutter.hubcore.utilities;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

	public void soundInRadius(Location loc, Sound sound, int radius) {
		if (loc.getWorld() == null) {return;}
		for (Entity ent : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)) {
			if (ent instanceof Player) {
				((Player) ent).playSound(loc, sound, SoundCategory.MASTER, 100, 1);
			}
		}
	}
	
}
