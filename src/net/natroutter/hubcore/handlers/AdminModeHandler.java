package net.natroutter.hubcore.handlers;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.Database;
import net.natroutter.natlibs.utilities.StringHandler;

public class AdminModeHandler {

	private static final Database database = HubCore.getDatabase();
	private static final Lang lang = HubCore.getLang();
	
	public static boolean isAdmin(BasePlayer p) {
		Database database = HubCore.getDatabase();
		return database.getBoolean(p, "AdminMode");
	}
	
	public static boolean isVip(BasePlayer p) {
		return p.hasPermission("hubcore.vip");
	}
	
	public static void ToggleAdmin(BasePlayer p) {
		ToggleAdmin(p, false);
	}
	
	public static void ToggleAdmin(BasePlayer p, boolean silent) {
		boolean state = database.getBoolean(p, "AdminMode");
		StringHandler message = new StringHandler(lang.AdminModeToggle);
		message.setPrefix(lang.Prefix);
		
		if (state) {
			database.save(p, "AdminMode", false);
			message.replaceAll("{State}", lang.ToggleStates.off);
			SelectorItemHandler.update(p);
		} else {
			database.save(p, "AdminMode", true);
			message.replaceAll("{State}", lang.ToggleStates.on);
		}
		if (!silent) {
			message.send(p);
		}
	}
	
}
