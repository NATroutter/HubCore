package fi.natroutter.hubcore.commands;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.AdminModeHandler;
import fi.natroutter.natlibs.handlers.database.YamlDatabase;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class Adminmode extends Command {

	private YamlDatabase database = HubCore.getYamlDatabase();
	private AdminModeHandler adminModeHandler = HubCore.getAdminModeHandler();

	public Adminmode() {
		super("adminmode");
		this.setAliases(Collections.singletonList("am"));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("hubcore.admin")) {
			sender.sendMessage(Lang.NoPerm.prefixed());
			return false;
		}

		if (!(sender instanceof Player p)) {
			sender.sendMessage(Lang.OnlyIngame.prefixed());
			return false;
		}
		
		if (args.length == 0) {
			adminModeHandler.ToggleAdmin(p);
		} else if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(Lang.TargetNotFound.prefixed());
				return false;
			}

			boolean state = database.getBoolean(target, "Adminmode");

			p.sendMessage(Lang.AdminModeToggleOther.asComponent(
					Placeholder.parsed("name", target.getName()),
					Placeholder.component("state", (state ? Lang.ToggleStates_off : Lang.ToggleStates_on).asComponent())
			));

			adminModeHandler.ToggleAdmin(target,true);

		} else {
			sender.sendMessage(Lang.TooManyArguments.prefixed());
		}
		return false;
	}


	
}
