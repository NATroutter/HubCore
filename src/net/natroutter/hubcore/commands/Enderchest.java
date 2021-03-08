package net.natroutter.hubcore.commands;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Enderchest extends Command {

	Lang lang = HubCore.getLang();

	public Enderchest() {
		super("");
		this.setAliases(Arrays.asList("ec", "echest"));
	}
	
	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		
		BasePlayer p = BasePlayer.from(sender);
		
		if (args.length == 0) {
			if (p.hasPermission("hubcore.enderchest")) {
			
				p.openInventory(p.getEnderChest());
				p.sendMessage(lang.Prefix + lang.EnderChestOpened);
				
			} else {
				Utils.CommandError(p, CET.NoPerm);
			}
			
		} else if (args.length == 1) {
			if (p.hasPermission("hubcore.enderchest.other")) {
				
				BasePlayer target = BasePlayer.from(Bukkit.getPlayer(args[0]));
				if (target == null || !target.isOnline()) {
					Utils.CommandError(p, CET.InvalidPlayer);
					return false;
				}
				
				p.openInventory(target.getEnderChest());
				if (!p.getUniqueId().equals(target.getUniqueId())) {
					StringHandler message = new StringHandler(lang.EnderChestOpenedOther);
					message.setPrefix(lang.Prefix);
					message.replaceAll("{Player}", target.getName());
					message.send(p);
				}
				
			} else {
				Utils.CommandError(p, CET.NoPerm);
			}
			
		} else {
			Utils.CommandError(p, CET.TooMany, "/rename");
		}
		
		return false;
	}
	
}
