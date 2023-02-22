package fi.natroutter.hubcore.features.gadgets.slapper;

import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fi.natroutter.hubcore.handlers.AdminModeHandler;

public class SlapperHandler {

	private PlayerDataHandler pdh;
	private AdminModeHandler adminModeHandler;
	
	public SlapperHandler(Handler handler) {
		this.pdh = handler.getDataHandler();
		this.adminModeHandler = handler.getAdminModeHandler();
	}
	
	public void slap(Player p, Player target) {
		PlayerData data = pdh.get(target.getUniqueId());
		if (data.getNoEffect()) {return;}

		if (!adminModeHandler.isAdmin(p)) {

			target.setVelocity(p.getLocation().getDirection().multiply(1.2D).add(new Vector(0.0, 0.6, 0.0)));
	        target.playSound(target.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1.0F, 1.0F);
	        p.playSound(target.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1.0F, 1.0F);
		}

	}
	
	
	
}
