package fi.natroutter.hubcore.handlers;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.events.AdminModeToggleEvent;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.natlibs.handlers.database.YamlDatabase;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AdminModeHandler {

	private YamlDatabase database = HubCore.getYamlDatabase();
	private SelectorItemHandler selectorItemHandler = HubCore.getSelectorItemHandler();
	
	public boolean isAdmin(Player p) {
		return database.getBoolean(p, "Adminmode");
	}

	public void setAdminMode(Player p, boolean state, boolean silent) {
		AdminModeToggleEvent event = new AdminModeToggleEvent(p, state);
		Bukkit.getPluginManager().callEvent(event);

		if (!event.isCancelled()) {
			if (state) {
				database.save(p, "Adminmode", true);
				if (!silent) {
					p.sendMessage(Lang.AdminModeToggle.prefixed(
							Placeholder.component("state", Lang.ToggleStates_on.asComponent())
					));
				}
			} else {
				database.save(p, "Adminmode", false);
				selectorItemHandler.update(p);
				if (!silent) {
					p.sendMessage(Lang.AdminModeToggle.prefixed(
							Placeholder.component("state", Lang.ToggleStates_off.asComponent())
					));
				}
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
