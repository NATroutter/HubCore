package net.natroutter.hubcore.handlers;

import net.natroutter.natlibs.handlers.hooking.Hook;
import net.natroutter.natlibs.handlers.hooking.Hooker;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused"})

public class Hooks {

	//hook pluin variables
	private Hook placeholderAPI;
	private Hook citizen;

	//hook getters
	public Hook getPlaceholderAPI(){return placeholderAPI;}
	public Hook getCitizens(){return citizen;}

	//hooks
	public Hooks(JavaPlugin pl) {
		Hooker hooker = new Hooker(pl);
		hooker.setHookedMessage("  §a+ §7{plugin} Hooked succesfully!");
		hooker.setHookingFailedMessage("  §4- §7{plugin} Failed to hook!");
		hooker.setDisableMessage("§7Disabling plugin because plugin hooking failed");
		hooker.disableWhenFailed();

		placeholderAPI = hooker.create("PlaceholderAPI", true);
		citizen = hooker.create("Citizens", true);

	}

}

















