package net.natroutter.hubcore.handlers;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AdminModeHandler {

	private static final YamlDatabase database = HubCore.getYamlDatabase();
	private static final Lang lang = HubCore.getLang();
	
	public static boolean isAdmin(Player p) {
		return database.getBoolean(p, "Adminmode");
	}
	
	public static void ToggleAdmin(Player p) {
		ToggleAdmin(p, false);
	}
	
	public static void ToggleAdmin(Player p, boolean silent) {
		boolean state = database.getBoolean(p, "Adminmode");

		StringHandler message = new StringHandler(lang.AdminModeToggle);
		message.setPrefix(lang.Prefix);

		if (state) {
			database.save(p, "Adminmode", false);
			message.replaceAll("{state}", lang.ToggleStates.off);
			SelectorItemHandler.update(p);
		} else {
			database.save(p, "Adminmode", true);
			message.replaceAll("{state}", lang.ToggleStates.on);
		}
		if (!silent) {
			message.send(p);
		}
	}
	
}
