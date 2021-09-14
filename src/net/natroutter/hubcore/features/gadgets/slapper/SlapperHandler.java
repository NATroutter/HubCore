package net.natroutter.hubcore.features.gadgets.slapper;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.natroutter.hubcore.handlers.AdminModeHandler;

public class SlapperHandler {

	private static PlayerDataHandler pdh = HubCore.getDataHandler();
	
	
	
	public static void slap(Player p, Player target) {
		PlayerData data = pdh.get(target.getUniqueId());
		if (data.getNoEffect()) {return;}

		if (!AdminModeHandler.isAdmin(p)) {

			target.setVelocity(p.getLocation().getDirection().multiply(1.2D).add(new Vector(0.0, 0.6, 0.0)));
	        target.playSound(target.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1.0F, 1.0F);
	        p.playSound(target.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1.0F, 1.0F);
		}

	}
	
	
	
}
