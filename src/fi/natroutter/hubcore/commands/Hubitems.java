package fi.natroutter.hubcore.commands;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.files.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Hubitems extends Command {


	private SelectorItemHandler selectorItemHandler = HubCore.getSelectorItemHandler();

	public Hubitems() {
		super("Hubitems");
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!sender.hasPermission("hubcore.hubitem")) {
			sender.sendMessage(Lang.NoPerm.prefixed());
			return false;
		}

		if (!(sender instanceof Player p)) {
			sender.sendMessage(Lang.OnlyIngame.prefixed());
			return false;
		}

        if (args.length == 0) {
			selectorItemHandler.update(p);
		} else {
			sender.sendMessage(Lang.TooManyArguments.prefixed());
		}
		
		return false;
	}

	
	
	
}
