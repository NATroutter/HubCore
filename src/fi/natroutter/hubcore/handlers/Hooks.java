package fi.natroutter.hubcore.handlers;

import fi.natroutter.natlibs.handlers.Hook;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused"})
@Getter
public class Hooks {

	//hook pluin variables
	private Hook placeholderAPI;
	private Hook citizen;
	private Hook betterParkour;

	//hooks
	public Hooks(JavaPlugin pl) {
		placeholderAPI = new Hook.Builder(pl, "PlaceholderAPI", true).build();
		citizen = new Hook.Builder(pl, "Citizens", true).build();
		betterParkour = new Hook.Builder(pl, "BetterParkour", true).build();
	}

}

















