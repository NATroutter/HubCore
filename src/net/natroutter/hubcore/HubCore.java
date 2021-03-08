package net.natroutter.hubcore;

import net.natroutter.hubcore.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import net.natroutter.hubcore.features.ChatFormater;
import net.natroutter.hubcore.features.Protection;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemListener;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.features.gadgets.GadgetListener;
import net.natroutter.hubcore.features.gadgets.boombox.MusicPlayer;
import net.natroutter.hubcore.features.gadgets.slapper.SlapperListener;
import net.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonListener;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.handlers.CommonHandler;
import net.natroutter.hubcore.handlers.Hooks;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.handlers.ConfigManager;
import net.natroutter.natlibs.handlers.EventManager;
import net.natroutter.natlibs.handlers.LangManager;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.Database;
import net.natroutter.natlibs.utilities.Utilities;

public class HubCore extends JavaPlugin {

    private static JavaPlugin instance;
    private static Hooks hooks;
    private static Database database;
    private static Lang lang;
    private static Config config;

    public static JavaPlugin getPlugin() {return instance;}
    public static Hooks getHooks() { return hooks; }
    public static Database getDatabase() {return database;}
    public static Lang getLang() {return lang;}
    public static Config getCfg() {return config;}


    @Override
    public void onEnable() {
        instance = this;
        EventManager eventManager = NATLibs.getEventManager();
        Utilities utils = NATLibs.getUtilities();

        database = new Database(this);


        //Initialize and load Configuration!
        ConfigManager confManager = new ConfigManager(this);
        config = (Config)confManager.load(Config.class).get();

        //Initialize and load language!
        LangManager langManager = new LangManager(this);
        lang = (Lang)langManager.load(Lang.class).get();


        //Initialize Gadget handlers!
        GadgetHandler.Initialize();


        //Initialize Common handler!
        new CommonHandler(this);


        //Print sexy banner and hook other plugins!
        utils.consoleMessage("§8─────────────────────────────────────────");
        utils.consoleMessage("§8┌[ §cHubCore §4v1.0 §cEnabled §8]");
        utils.consoleMessage("§8├ §7Plugin by: §4NATroutter");
        utils.consoleMessage("§8├ §7Website: §4NATroutter.net");
        utils.consoleMessage("§8└ §7Hooks:");
        hooks = new Hooks(this);
        utils.consoleMessage("§8─────────────────────────────────────────");


        //Register all listeners
        eventManager.RegisterListeners(this,
                SelectorItemListener.class, GadgetListener.class, MusicPlayer.class,
                SnowCannonListener.class, SlapperListener.class, Protection.class,
                ChatFormater.class
        );


        //Register all commands
        eventManager.RegisterCommands(this,
                Setspawn.class, Spawn.class, Adminmode.class,
                Hubitems.class, Fly.class, Speed.class,
                Gamemode.class, Rename.class, Setlore.class,
                Enderchest.class, Invsee.class
        );


        //Update all online players inventories!
        for(BasePlayer p : BasePlayer.getOnlinePlayers()) {
            if (AdminModeHandler.isAdmin(p)) {continue;}
            SelectorItemHandler.update(p);
        }

    }

    @Override
    public void onDisable() {
        instance = null;
    }


}
