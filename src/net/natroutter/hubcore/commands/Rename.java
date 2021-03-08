package net.natroutter.hubcore.commands;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Rename extends Command {

	Lang lang = HubCore.getLang();

	public Rename() {
		super("");
		this.setPermission("hubcore.rename");
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
			StringHandler name = new StringHandler(args, ' ');
			BaseItem item = BaseItem.from(p.getInventory().getItemInMainHand());

			if (!item.getType().equals(Material.AIR)) {
				item.setDisplayName(name.build());
				p.updateInventory();
				p.sendMessage(lang.Prefix + lang.ItemRenamed);

			} else {
				p.sendMessage(lang.Prefix + lang.InvalidItem);
			}

		}
		
		return false;
	}
	
}
