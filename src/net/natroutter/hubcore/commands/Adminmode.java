package net.natroutter.hubcore.commands;

import java.util.Collections;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.utilities.Lang;

public class Adminmode extends Command {

	Lang lang = HubCore.getLang();
	YamlDatabase database = HubCore.getYamlDatabase();
	
	public Adminmode() {
		super("");
		this.setAliases(Collections.singletonList("am"));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("hubcore.admin")) {
			sender.sendMessage(lang.Prefix + lang.NoPerm);
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		Player p = (Player)sender;
		
		if (args.length == 0) {
			AdminModeHandler.ToggleAdmin(p);
		} else if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(lang.Prefix + lang.TargetNotFound);
				return false;
			}

			boolean state = database.getBoolean(p, "Adminmode");

			StringHandler str = new StringHandler(lang.AdminModeToggleOther);
			str.setPrefix(lang.Prefix);
			str.replaceAll("{name}", p.getName());

			if (state) {
				str.replaceAll("{state}", lang.ToggleStates.off);
			} else {
				str.replaceAll("{state}", lang.ToggleStates.on);
			}
			str.send(p);

			AdminModeHandler.ToggleAdmin(p);

		} else {
			p.sendMessage(lang.Prefix + lang.TooManyArguments);
		}
		return false;
	}


	
}
