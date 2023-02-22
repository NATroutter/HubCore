package fi.natroutter.hubcore.handlers;

import fi.natroutter.natlibs.handlers.hooking.Hook;
import fi.natroutter.natlibs.handlers.hooking.HookSettings;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused"})

public class Hooks {

	//hook pluin variables
	private Hook placeholderAPI;
	private Hook citizen;
	private Hook betterParkour;

	//hook getters
	public Hook getPlaceholderAPI(){return placeholderAPI;}
	public Hook getCitizens(){return citizen;}
	public Hook getBetterParkour() {return betterParkour;}

	//hooks
	public Hooks(JavaPlugin pl) {
		HookSettings set = new HookSettings();
		set.setHookedMessage("  §a+ §7{plugin} Hooked succesfully!");
		set.setHookingFailedMessage("  §4- §7{plugin} Failed to hook!");
		set.setDisableMessage("§7Disabling plugin because plugin hooking failed");
		set.disableWhenFailed();

		placeholderAPI = new Hook(pl,set, "PlaceholderAPI", true);
		citizen = new Hook(pl,set, "Citizens", true);
		betterParkour = new Hook(pl, set, "BetterParkour", true);

	}

}

















