package fi.natroutter.hubcore.commands;

import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.files.Translations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import org.jetbrains.annotations.NotNull;

public class Hubitems extends Command {


	private LangManager lang;
	private SelectorItemHandler selectorItemHandler;
	public Hubitems(Handler handler) {
		super("Hubitems");
		this.lang = handler.getLang();
		this.selectorItemHandler = handler.getSelectorItemHandler();
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!sender.hasPermission("hubcore.hubitem")) {
			lang.send(sender, Translations.Prefix, Translations.NoPerm);
			return false;
		}

		if (!(sender instanceof Player p)) {
			lang.send(sender, Translations.Prefix, Translations.OnlyIngame);
			return false;
		}

        if (args.length == 0) {
			selectorItemHandler.update(p);
		} else {
			lang.send(sender, Translations.Prefix, Translations.TooManyArguments);
		}
		
		return false;
	}

	
	
	
}
