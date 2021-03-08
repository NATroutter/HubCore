package net.natroutter.hubcore.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.objects.BasePlayer;

public class Adminmode extends Command {

	Lang lang = HubCore.getLang();
	
	public Adminmode() {
		super("");
		this.setPermission("hubcore.admin");
		this.setPermissionMessage(lang.Prefix + lang.NoPerm);
		this.setAliases(Arrays.asList("am"));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		BasePlayer p = BasePlayer.from(sender);
		
		if (args.length == 0) {
			AdminModeHandler.ToggleAdmin(p);
		} else {
			Utils.CommandError(p, CET.TooMany, "/Adminmode");
		}
	
		return false;
	}


	
}
