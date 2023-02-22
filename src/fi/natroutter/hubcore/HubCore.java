package fi.natroutter.hubcore;

import fi.natroutter.hubcore.commands.Adminmode;
import fi.natroutter.hubcore.commands.Hubitems;
import fi.natroutter.hubcore.commands.nocarry;
import fi.natroutter.hubcore.commands.noeffect;
import fi.natroutter.hubcore.features.protections.ProtectionListener;
import fi.natroutter.hubcore.features.BetterParkourListener;
import fi.natroutter.hubcore.features.playercarry.PlayerCarryListener;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSListener;
import fi.natroutter.hubcore.handlers.CommonListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fi.natroutter.hubcore.features.SelectorItems.SelectorItemListener;
import fi.natroutter.hubcore.features.gadgets.GadgetListener;
import fi.natroutter.hubcore.features.gadgets.boombox.MusicPlayer;
import fi.natroutter.hubcore.features.gadgets.slapper.SlapperListener;
import fi.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonListener;

import java.util.Arrays;

public class HubCore extends JavaPlugin {

    private Handler handler;

    private static HubCoreAPI api;
    public static HubCoreAPI getAPI() {
        return api;
    }

    @Override
    public void onEnable() {
        handler = new Handler(this);

        PluginManager pm = Bukkit.getPluginManager();
        CommandMap map = Bukkit.getCommandMap();

        //Register all listeners
        pm.registerEvents(new SelectorItemListener(handler), this);
        pm.registerEvents(new GadgetListener(handler), this);
        pm.registerEvents(new MusicPlayer(handler), this);
        pm.registerEvents(new SnowCannonListener(handler), this);
        pm.registerEvents(new SlapperListener(handler), this);
        pm.registerEvents(new FWSListener(handler), this);
        pm.registerEvents(new PlayerCarryListener(handler), this);
        pm.registerEvents(new ProtectionListener(handler), this);
        pm.registerEvents(new CommonListener(handler), this);

        if (handler.getHooks().getBetterParkour().isHooked()) {
            pm.registerEvents(new BetterParkourListener(handler), this);
        }

        //Register all commands
        map.registerAll("hubcore", Arrays.asList(
                new Adminmode(handler),
                new Hubitems(handler),
                new noeffect(handler),
                new nocarry(handler)
        ));

        api = new HubCoreAPI(handler);

    }

    @Override
    public void onDisable() {
        if (handler != null && handler.getDataHandler() != null) {
            handler.getDataHandler().save();
        }
        Bukkit.getServer().shutdown();
    }

}
