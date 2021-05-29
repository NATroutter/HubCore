package net.natroutter.hubcore.commands;

import java.util.Collections;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.utilities.Lang;

public class Adminmode extends Command {

	Lang lang = HubCore.getLang();
	
	public Adminmode() {
		super("");
		this.setPermission("hubcore.admin");
		this.setPermissionMessage(lang.Prefix + lang.NoPerm);
		this.setAliases(Collections.singletonList("am"));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		Player p = (Player)sender;
		
		if (args.length == 0) {
			AdminModeHandler.ToggleAdmin(p);
		} else {
			p.sendMessage(lang.Prefix + lang.TooManyArguments);
		}
		return false;
	}


	
}
