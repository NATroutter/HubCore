package net.natroutter.hubcore.handlers;

import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.events.AdminModeToggleEvent;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.LangHandler.language.LangManager;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AdminModeHandler {

	private YamlDatabase database;
	private LangManager lang;
	private SelectorItemHandler selectorItemHandler;

	public AdminModeHandler(Handler handler) {
		this.database = handler.getYamlDatabase();
		this.lang = handler.getLang();
		this.selectorItemHandler = handler.getSelectorItemHandler();
	}
	
	public boolean isAdmin(Player p) {
		return database.getBoolean(p, "Adminmode");
	}

	public void setAdminMode(Player p, boolean state, boolean silent) {
		AdminModeToggleEvent event = new AdminModeToggleEvent(p, state);
		Bukkit.getPluginManager().callEvent(event);

		if (!event.isCancelled()) {
			StringHandler message = new StringHandler(lang.get(Translations.AdminModeToggle));
			message.setPrefix(lang.get(Translations.Prefix));

			if (state) {
				database.save(p, "Adminmode", true);
				message.replaceAll("{state}", lang.get(Translations.ToggleStates_on));
			} else {
				database.save(p, "Adminmode", false);
				message.replaceAll("{state}", lang.get(Translations.ToggleStates_off));
				selectorItemHandler.update(p);
			}

			if (!silent) {
				message.send(p);
			}

		}
	}

	public void ToggleAdmin(Player p) {
		ToggleAdmin(p, false);
	}
	
	public void ToggleAdmin(Player p, boolean silent) {
		boolean state = database.getBoolean(p, "Adminmode");
		setAdminMode(p, !state, silent);
	}
	
}
