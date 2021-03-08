package net.natroutter.hubcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.Database;
import org.jetbrains.annotations.NotNull;

public class Setspawn extends Command {

	Lang lang = HubCore.getLang();
	Database database = HubCore.getDatabase();
	
	public Setspawn() {
		super("");
		this.setPermission("hubcore.setspawn");
		this.setPermissionMessage(lang.Prefix + lang.NoPerm);
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		
		BasePlayer p = BasePlayer.from(sender);
		if (args.length == 0) {
			database.saveLoc("General", "Spawn", p.getLocation());
			p.getWorld().setSpawnLocation(p.getLocation());
			p.sendMessage(lang.Prefix + lang.SpawnSet);
		} else {
			Utils.CommandError(p, CET.TooMany, "/setspawn");
		}
		
		return false;
	}

	
}
