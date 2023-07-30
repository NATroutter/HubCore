package fi.natroutter.hubcore.commands;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class nocarry extends Command {

    private PlayerDataHandler pdh = HubCore.getDataHandler();

    public nocarry() {
        super("nocarry");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Lang.OnlyIngame.prefixed());
            return false;
        }

        if (args.length == 0) {
            if (!p.hasPermission("hubcore.nocarry")) {
                sender.sendMessage(Lang.NoPerm.prefixed());
                return false;
            }

            PlayerData data = pdh.get(p.getUniqueId());
            boolean state = !data.getNocarry();
            data.setNocarry(state);

            sender.sendMessage(Lang.NoCarryToggle.prefixed(
                    Placeholder.parsed("state", (state ? Lang.ToggleStates_off : Lang.ToggleStates_on).asLegacy())
            ));

            pdh.set(data);

        } else {
            sender.sendMessage(Lang.TooManyArguments.prefixed());
        }

        return false;
    }
}
