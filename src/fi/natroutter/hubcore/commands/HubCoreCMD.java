package fi.natroutter.hubcore.commands;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.natlibs.objects.Complete;
import fi.natroutter.natlibs.utilities.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HubCoreCMD extends Command {

    public HubCoreCMD() {
        super("hubcore");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {

        if (args.length == 0) {
            PluginDescriptionFile pdf = HubCore.getInstance().getDescription();
            sender.sendMessage(" ");
            sender.sendMessage(Utilities.translateColors("&8&l&m━━━━━━━━━━━━&8&l|&4&l HubCore &8&l|&m━━━━━━━━━━━━&r"));
            sender.sendMessage(Utilities.translateColors("&8&l» &7Hubcore &c"+ pdf.getVersion()));
            sender.sendMessage(Utilities.translateColors("&8&l» &7Developed by: &c" + String.join("&7, &c",pdf.getAuthors())));
            sender.sendMessage(Utilities.translateColors("&8&l» &7Website: &c" + pdf.getWebsite()));
            sender.sendMessage(Utilities.translateColors("&8&l&m━━━━━━━━━━━━&8&l|&4&l HubCore &8&l|&m━━━━━━━━━━━━&r"));
            sender.sendMessage(" ");
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("hubcore.reload")) {
                    sender.sendMessage(Lang.NoPerm.prefixed());
                    return false;
                }

                Config.reload();
                Lang.reload();
                HubCore.getSelectorItemHandler().reloadItems();
                sender.sendMessage(Lang.ConfigReloaded.prefixed());
                return true;
            } else {
                sender.sendMessage(Lang.InvalidArguments.prefixed());
            }
        } else {
            sender.sendMessage(Lang.TooManyArguments.prefixed());
        }

        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return Utilities.getCompletesWithPerms(sender, args[0], List.of(
                    new Complete("reload", "hubcore.reload")
            ));
        }
        return null;
    }
}
