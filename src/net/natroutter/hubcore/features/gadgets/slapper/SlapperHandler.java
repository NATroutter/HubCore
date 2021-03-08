package net.natroutter.hubcore.features.gadgets.slapper;

import org.bukkit.Sound;
import org.bukkit.util.Vector;

import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.natlibs.objects.BasePlayer;

public class SlapperHandler {

	
	
	
	
	public static void slap(BasePlayer p, BasePlayer target) {
		if (!AdminModeHandler.isAdmin(p) || !AdminModeHandler.isVip(p)) {
			target.setVelocity(p.getLocation().getDirection().multiply(1.2D).add(new Vector(0.0, 0.6, 0.0)));
	        target.playSound(target.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1.0F, 1.0F);
	        p.playSound(target.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1.0F, 1.0F);
		}

	}
	
	
	
}
