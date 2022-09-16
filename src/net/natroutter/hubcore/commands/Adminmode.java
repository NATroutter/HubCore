package net.natroutter.hubcore.commands;

import java.util.Collections;

import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.natlibs.handlers.database.YamlDatabase;
import net.natroutter.natlibs.handlers.langHandler.language.LangManager;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.AdminModeHandler;

public class Adminmode extends Command {

	private LangManager lang;
	private YamlDatabase database;
	private AdminModeHandler adminModeHandler;
	public Adminmode(Handler handler) {
		super("adminmode");
		this.setAliases(Collections.singletonList("am"));
		this.lang = handler.getLang();
		this.database = handler.getYamlDatabase();
		this.adminModeHandler = handler.getAdminModeHandler();
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!sender.hasPermission("hubcore.admin")) {
			lang.send(sender, Translations.Prefix, Translations.NoPerm);
			return false;
		}

		if (!(sender instanceof Player p)) {
			lang.send(sender, Translations.Prefix, Translations.OnlyIngame);
			return false;
		}
		
		if (args.length == 0) {
			adminModeHandler.ToggleAdmin(p);
		} else if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				lang.send(sender, Translations.Prefix, Translations.TargetNotFound);
				return false;
			}

			boolean state = database.getBoolean(target, "Adminmode");

			StringHandler str = new StringHandler(lang.get(Translations.AdminModeToggleOther));
			str.setPrefix(lang.get(Translations.Prefix));
			str.replaceAll("{name}", target.getName());

			if (state) {
				str.replaceAll("{state}", lang.get(Translations.ToggleStates_off));
			} else {
				str.replaceAll("{state}", lang.get(Translations.ToggleStates_on));
			}
			str.send(p);

			adminModeHandler.ToggleAdmin(target,true);

		} else {
			lang.send(sender, Translations.Prefix, Translations.TooManyArguments);
		}
		return false;
	}


	
}
