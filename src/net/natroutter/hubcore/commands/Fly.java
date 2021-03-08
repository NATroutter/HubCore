package net.natroutter.hubcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.natlibs.utilities.Utilities;
import org.jetbrains.annotations.NotNull;

public class Fly extends Command {

	Lang lang = HubCore.getLang();
	Utilities utils = NATLibs.getUtilities();
	
	public Fly() {
		super("");
	}
	
	private void ToggleFly(BasePlayer p, boolean state) {
		StringHandler message = new StringHandler(lang.ToggleFly);
		message.setPrefix(lang.Prefix);
		message.replaceAll("{State}", getState(state));
				
		p.setAllowFlight(state);
		p.setFlying(state);
		message.send(p);
	}
	
	private String getState(boolean state) {
		return state ? lang.ToggleStates.on : lang.ToggleStates.off;
	}

	
	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		
		BasePlayer p = BasePlayer.from(sender);
		
		if (args.length == 0) {
			if (p.hasPermission("hubcore.fly")) {
				ToggleFly(p, utils.FlipBool(p.getAllowFlight()));
			} else {
				Utils.CommandError(p, CET.NoPerm);
			}
		} else if (args.length == 1) {
			if (p.hasPermission("hubcore.fly.other")) {
				
				BasePlayer target = BasePlayer.from(Bukkit.getPlayer(args[0]));
				if (target == null || !target.isOnline()) {
					Utils.CommandError(p, CET.InvalidPlayer);
					return false;
				}
			
				boolean newState = utils.FlipBool(target.getAllowFlight());
				
				StringHandler message = new StringHandler(lang.ToggleFlyOther);
				message.setPrefix(lang.Prefix);
				message.replaceAll("{Player}", target.getName());
				message.replaceAll("{State}", getState(newState));
				
				if (!target.getUniqueId().equals(p.getUniqueId())) {
					message.send(p);
				}
				ToggleFly(target, newState);
				
			} else {
				Utils.CommandError(p, CET.NoPerm);
			}
		} else {
			Utils.CommandError(p, CET.TooMany, "/Fly");
		}
		
		return false;
	}
	
}
