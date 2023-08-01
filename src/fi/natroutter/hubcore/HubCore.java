package fi.natroutter.hubcore;

import fi.natroutter.hubcore.commands.*;
import fi.natroutter.hubcore.features.BetterParkourListener;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemListener;
import fi.natroutter.hubcore.features.ServerSelector;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSListener;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis.FwColorGUI;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis.FwCustomGUI;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis.FwSettingsGUI;
import fi.natroutter.hubcore.features.gadgets.GadgetGUI;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
import fi.natroutter.hubcore.features.gadgets.GadgetListener;
import fi.natroutter.hubcore.features.gadgets.boombox.MusicGUI;
import fi.natroutter.hubcore.features.gadgets.boombox.MusicPlayer;
import fi.natroutter.hubcore.features.gadgets.grapler.Grapler;
import fi.natroutter.hubcore.features.gadgets.jumpper.JumpperHandler;
import fi.natroutter.hubcore.features.gadgets.slapper.SlapperHandler;
import fi.natroutter.hubcore.features.gadgets.slapper.SlapperListener;
import fi.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import fi.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonListener;
import fi.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import fi.natroutter.hubcore.features.particles.ParticleGUI;
import fi.natroutter.hubcore.features.particles.ParticleScheduler;
import fi.natroutter.hubcore.features.playercarry.PlayerCarryHandler;
import fi.natroutter.hubcore.features.playercarry.PlayerCarryListener;
import fi.natroutter.hubcore.features.protections.ProtectionHandler;
import fi.natroutter.hubcore.features.protections.ProtectionListener;
import fi.natroutter.hubcore.handlers.AdminModeHandler;
import fi.natroutter.hubcore.handlers.CommonHandler;
import fi.natroutter.hubcore.handlers.CommonListener;
import fi.natroutter.hubcore.handlers.Database.Database;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.hubcore.utilities.ServerSwitcher;
import fi.natroutter.hubcore.utilities.Utils;
import fi.natroutter.natlibs.handlers.database.YamlDatabase;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;


public class HubCore extends JavaPlugin {


    @Getter private static String VERSION = "2.0.0";



    @Getter private static JavaPlugin instance;
    @Getter private static YamlDatabase yamlDatabase;
    @Getter private static Hooks hooks;
    @Getter private static PlayerDataHandler dataHandler;
    @Getter private static AdminModeHandler adminModeHandler;
    @Getter private static WingsHandler wingsHandler;
    @Getter private static GadgetHandler gadgetHandler;
    @Getter private static SelectorItemHandler selectorItemHandler;
    @Getter private static GadgetGUI gadgetGUI;
    @Getter private static ServerSelector serverSelector;
    @Getter private static ParticleGUI particleGUI;
    @Getter private static Utils utils;
    @Getter private static MusicGUI musicGUI;
    @Getter private static SnowCannonHandler snowCannonHandler;

    @Getter private static FwCustomGUI fwCustomGUI;
    @Getter private static FwColorGUI fwColorGUI;
    @Getter private static FwSettingsGUI fwSettingsGUI;

    @Getter private static FWSHandler fwsHandler;
    @Getter private static SlapperHandler slapperHandler;
    @Getter private static JumpperHandler jumpperHandler;
    @Getter private static ParticleScheduler particleScheduler;
    @Getter private static PlayerCarryHandler playerCarryHandler;
    @Getter private static ProtectionHandler protectionHandler;
    @Getter private static ServerSwitcher serverSwitcher;

    @Override
    public void onEnable() {
        instance = this;
        //Print sexy banner and hook other plugins!
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        PluginDescriptionFile pdf = instance.getDescription();


        console.sendMessage("§8─────────────────────────────────────────");
        console.sendMessage("§8┌[ §cHubCore §4v"+pdf.getVersion()+" §cEnabled §8]");
        console.sendMessage("§8├ §7Plugin by: §4NATroutter");
        console.sendMessage("§8├ §7Website: §4NATroutter.fi");
        console.sendMessage("§8└ §7Hooks:");
        hooks = new Hooks(instance);
        console.sendMessage("§8─────────────────────────────────────────");

        utils = new Utils();
        serverSwitcher = new ServerSwitcher();

        yamlDatabase = new YamlDatabase(instance);
        dataHandler = new PlayerDataHandler(instance, new Database(instance), 30 * 60);

        wingsHandler = new WingsHandler();
        serverSelector = new ServerSelector();
        particleGUI = new ParticleGUI();
        gadgetHandler = new GadgetHandler();
        selectorItemHandler = new SelectorItemHandler();
        gadgetGUI = new GadgetGUI();
        adminModeHandler = new AdminModeHandler();
        musicGUI = new MusicGUI();
        snowCannonHandler = new SnowCannonHandler();
        fwsHandler = new FWSHandler();

        fwCustomGUI = new FwCustomGUI();
        fwColorGUI = new FwColorGUI();
        fwSettingsGUI = new FwSettingsGUI();

        slapperHandler = new SlapperHandler();
        jumpperHandler = new JumpperHandler();
        playerCarryHandler = new PlayerCarryHandler();
        protectionHandler = new ProtectionHandler();

        for(Player p : Bukkit.getOnlinePlayers()) {
            if (adminModeHandler.isAdmin(p)) {continue;}
            selectorItemHandler.update(p);
        }

        particleScheduler = new ParticleScheduler();

        new CommonHandler(instance);
        new SnowCannonHandler();

        PluginManager pm = Bukkit.getPluginManager();
        CommandMap map = Bukkit.getCommandMap();

        //Register all listeners
        pm.registerEvents(new SelectorItemListener(), this);
        pm.registerEvents(new GadgetListener(), this);
        pm.registerEvents(new MusicPlayer(), this);
        pm.registerEvents(new SnowCannonListener(), this);
        pm.registerEvents(new SlapperListener(), this);
        pm.registerEvents(new FWSListener(), this);
        pm.registerEvents(new PlayerCarryListener(), this);
        pm.registerEvents(new ProtectionListener(), this);
        pm.registerEvents(new CommonListener(), this);
        pm.registerEvents(new Grapler(), this);

        if (hooks.getBetterParkour().isHooked()) {
            pm.registerEvents(new BetterParkourListener(), this);
        }

        //Register all commands
        map.registerAll("hubcore", Arrays.asList(
                new Adminmode(),
                new Hubitems(),
                new noeffect(),
                new nocarry(),
                new HubCoreCMD()
        ));

    }

    @Override
    public void onDisable() {
        if (dataHandler != null) {
            dataHandler.save();
        }
        Bukkit.getServer().shutdown();
    }

}
