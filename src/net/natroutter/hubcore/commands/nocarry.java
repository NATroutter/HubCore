package net.natroutter.hubcore.commands;

import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class nocarry extends Command {

    private LangManager lang;
    private PlayerDataHandler pdh ;

    public nocarry(Handler handler) {
        super("nocarry");
        this.lang = handler.getLang();
        this.pdh = handler.getDataHandler();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            lang.send(sender, Translations.Prefix, Translations.OnlyIngame);
            return false;
        }

        if (args.length == 0) {
            if (p.hasPermission("hubcore.nocarry")) {
                PlayerData data = pdh.get(p.getUniqueId());
                boolean state = !data.getNocarry();
                data.setNocarry(state);
                StringHandler msg = new StringHandler(lang.get(Translations.NoCarryToggle)).setPrefix(lang.get(Translations.Prefix));
                msg.replaceAll("{state}", state ? lang.get(Translations.ToggleStates_off) : lang.get(Translations.ToggleStates_on));
                msg.send(p);
                pdh.set(data);

            } else {
                lang.send(sender, Translations.Prefix, Translations.NoPerm);
            }
        } else {
            lang.send(sender, Translations.Prefix, Translations.TooManyArguments);
        }

        return false;
    }
}
