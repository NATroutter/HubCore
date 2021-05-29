package net.natroutter.hubcore.utilities;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Utils {

	private static Lang lang = HubCore.getLang();

	public static void soundInRadius(Location loc, Sound sound, int radius) {
		if (loc.getWorld() == null) {return;}
		for (Entity ent : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)) {
			if (ent instanceof Player) {
				((Player) ent).playSound(loc, sound, SoundCategory.MASTER, 100, 1);
			}
		}
	}
	
}
