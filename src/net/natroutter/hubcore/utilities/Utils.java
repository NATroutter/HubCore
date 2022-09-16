package net.natroutter.hubcore.utilities;

import net.natroutter.hubcore.Handler;
import net.natroutter.natlibs.handlers.langHandler.language.LangManager;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

	private LangManager lang;

	public Utils(Handler handler) {
		this.lang = handler.getLang();
	}

	public boolean nameMatch(ItemStack item1, ItemStack item2) {
		if (item1 == null || item2 == null) {
			return false;
		}
		if (item1.getItemMeta() == null || item2.getItemMeta() == null) {
			return false;
		}
		if (item1.getItemMeta().getDisplayName() == null || item2.getItemMeta().getDisplayName() == null) {
			return false;
		}
		if (!item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) {
			return false;
		}
		return true;
	}

	public void soundInRadius(Location loc, Sound sound, int radius) {
		if (loc.getWorld() == null) {return;}
		for (Entity ent : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)) {
			if (ent instanceof Player) {
				((Player) ent).playSound(loc, sound, SoundCategory.MASTER, 100, 1);
			}
		}
	}
	
}
