package net.natroutter.hubcore;

import net.natroutter.hubcore.commands.*;
import net.natroutter.hubcore.features.PlayerCarry;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSListener;
import net.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import net.natroutter.hubcore.features.particles.ParticleScheduler;
import net.natroutter.hubcore.handlers.CommonListener;
import net.natroutter.hubcore.handlers.Database.Database;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.FileManager;
import net.natroutter.natlibs.objects.ConfType;
import net.natroutter.natlibs.utilities.Bungeecord.BungeeHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.natroutter.hubcore.features.protections.Protection;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemListener;
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
import net.natroutter.natlibs.handlers.EventManager;
import net.natroutter.natlibs.utilities.Utilities;

public class HubCore extends JavaPlugin implements NATLibs{

    private static NATLibs natlibs;
    private static JavaPlugin instance;
    private static Lang lang;
    private static Config config;
    private static YamlDatabase yamlDatabase;
    private static Utilities utilities;
    private static BungeeHandler bungee;
    private static Hooks hooks;
    private static PlayerDataHandler dataHandler;

    public static NATLibs getNATLibs() {return natlibs;}
    public static JavaPlugin getPlugin() {return instance;}
    public static Lang getLang() {return lang;}
    public static Config getCfg() {return config;}
    public static YamlDatabase getYamlDatabase() {return yamlDatabase;}
    public static Utilities getUtilities() {return utilities;}
    public static BungeeHandler getBungee() {return bungee;}
    public static Hooks getHooks() {return hooks;}
    public static PlayerDataHandler getDataHandler() {return dataHandler;}

    private static String ServerSwitchCommand = "";

    public static String getServerSwitchCommand() {return ServerSwitchCommand;}
    public static void setServerSwitchCommand(String cmd) {ServerSwitchCommand = cmd;}

    @Override
    public void onEnable() {
        //Print sexy banner and hook other plugins!
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        PluginDescriptionFile pdf = getDescription();
        console.sendMessage("§8─────────────────────────────────────────");
        console.sendMessage("§8┌[ §cHubCore §4v"+pdf.getVersion()+" §cEnabled §8]");
        console.sendMessage("§8├ §7Plugin by: §4NATroutter");
        console.sendMessage("§8├ §7Website: §4NATroutter.net");
        console.sendMessage("§8└ §7Hooks:");
        hooks = new Hooks(this);
        console.sendMessage("§8─────────────────────────────────────────");

        instance = this;

        natlibs = registerLibrary(this);
        bungee = createBungeecordHandler(this);

        utilities = new Utilities(this);
        yamlDatabase = new YamlDatabase(this);
        config = new FileManager(this, ConfType.Config).load(Config.class);
        lang = new FileManager(this, ConfType.Lang).load(Lang.class);
        dataHandler = new PlayerDataHandler(this, new Database(this), 30 * 60);

        //Initialize Common handler!
        new CommonHandler(this);

        SnowCannonHandler.Initialize();

        EventManager evm = new EventManager(this);

        //Register all listeners
        evm.RegisterListeners(
                SelectorItemListener.class, GadgetListener.class, MusicPlayer.class,
                SnowCannonListener.class, SlapperListener.class,
                FWSListener.class, PlayerCarry.class, Protection.class,
                CommonListener.class
        );

        //Register all commands
        evm.RegisterCommands(
                Adminmode.class, Hubitems.class, noeffect.class, nocarry.class
        );


        //Update all online players inventories!
        for(Player op : Bukkit.getOnlinePlayers()) {
            if (AdminModeHandler.isAdmin(op)) {continue;}
            SelectorItemHandler.update(op);
        }

        new ParticleScheduler(this, utilities);

    }

    @Override
    public void onDisable() {
        dataHandler.save();
        instance = null;
    }

}
