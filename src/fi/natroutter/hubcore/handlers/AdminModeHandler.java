package fi.natroutter.hubcore.handlers;

import fi.natroutter.natlibs.handlers.database.YamlDatabase;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.utilities.StringHandler;
import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.events.AdminModeToggleEvent;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.files.Translations;
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
