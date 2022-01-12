package net.natroutter.hubcore.commands;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class nocarry extends Command {

    private Lang lang = HubCore.getLang();
    private PlayerDataHandler pdh = HubCore.getDataHandler();

    public nocarry() {
        super("");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(lang.Prefix + lang.OnlyIngame);
            return false;
        }

        if (args.length == 0) {
            if (p.hasPermission("hubcore.nocarry")) {
                PlayerData data = pdh.get(p.getUniqueId());
                boolean state = !data.getNocarry();
                data.setNocarry(state);
                StringHandler msg = new StringHandler(lang.NoCarryToggle).setPrefix(lang.Prefix);
                msg.replaceAll("{state}", state ? lang.ToggleStates.off : lang.ToggleStates.on);
                msg.send(p);
                pdh.set(data);

            } else {
                p.sendMessage(lang.Prefix + lang.NoPerm);
            }
        } else {
            p.sendMessage(lang.Prefix + lang.TooManyArguments);
        }

        return false;
    }
}
