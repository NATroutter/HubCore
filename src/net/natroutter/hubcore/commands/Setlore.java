package net.natroutter.hubcore.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.natlibs.utilities.Utilities;
import org.jetbrains.annotations.NotNull;

public class Setlore extends Command {

	Lang lang = HubCore.getLang();
	Utilities utils = NATLibs.getUtilities();
	
	public Setlore() {
		super("");
		this.setPermission("hubcore.setlore");
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
			Utils.CommandError(p, CET.Invalid);
			
		} else {
			StringHandler lore = new StringHandler(args, ' ').replaceColors();

			BaseItem item = BaseItem.from(p.getInventory().getItemInMainHand());

			if (!item.getType().equals(Material.AIR)) {
				item.setLore(lore.split('|'));
				p.updateInventory();
				p.sendMessage(lang.Prefix + lang.LoreChanged);

			} else {
				p.sendMessage(lang.Prefix + lang.InvalidItem);
			}

		}
		
		return false;
	}
}
