package net.natroutter.hubcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.utilities.Lang;
import org.jetbrains.annotations.NotNull;

public class Hubitems extends Command {

	Lang lang = HubCore.getLang();
	
	public Hubitems() {
		super("");
		this.setPermission("hubcore.hubitem");
		this.setPermissionMessage(lang.Prefix + lang.NoPerm);
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}

		Player p = (Player)sender;
		if (args.length == 0) {
			SelectorItemHandler.update(p);
		} else {
			p.sendMessage(lang.Prefix + lang.TooManyArguments);
		}
		
		return false;
	}

	
	
	
}
