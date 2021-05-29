package net.natroutter.hubcore;

import net.natroutter.hubcore.commands.*;
import net.natroutter.hubcore.features.PlayerCarry;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FWSListener;
import net.natroutter.hubcore.features.particles.ParticleScheduler;
import net.natroutter.hubcore.handlers.Database.Database;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.FileManager;
import net.natroutter.natlibs.objects.ConfType;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Bungeecord.BungeeHandler;
import net.natroutter.natlibs.utilities.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.natroutter.hubcore.features.Protection;
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

import java.util.ArrayList;

public class HubCore extends JavaPlugin {

    //TODO
    //Estö redstone kokonaan
    //kanto reppu gadget (pystyy ottaa pelaajan kyytiin ja heittää sen ilmaan)
    //ESTÖ PELAAJAN LYÖNTI ÄÄNI SAATANA!

    private static JavaPlugin instance;
    private static Hooks hooks;
    private static Lang lang;
    private static Config config;
    private static YamlDatabase yamlDatabase;
    private static Utilities utilities;
    private static SkullCreator skullCreator;
    private static BungeeHandler bungee;
    private static Database database;

    public static JavaPlugin getPlugin() {return instance;}
    public static Hooks getHooks() { return hooks; }
    public static Lang getLang() {return lang;}
    public static Config getCfg() {return config;}
    public static YamlDatabase getYamlDatabase() {return yamlDatabase;}
    public static Utilities getUtilities() {return utilities;}
    public static SkullCreator getSkullCreator() {return skullCreator;}
    public static BungeeHandler getBungee() {return bungee;}
    public static Database getDatabase() {return database;}

    @Override
    public void onEnable() {
        instance = this;

        NATLibs libs = new NATLibs(this);
        bungee = libs.createBungeecordHandler();

        yamlDatabase = new YamlDatabase(this);
        utilities = new Utilities(this);
        skullCreator = new SkullCreator();
        config = new FileManager(this, ConfType.Config).load(Config.class);
        lang = new FileManager(this, ConfType.Lang).load(Lang.class);
        database = new Database(this);

        //Initialize Common handler!
        new CommonHandler(this);


        //Print sexy banner and hook other plugins!
        utilities.consoleMessage("§8─────────────────────────────────────────");
        utilities.consoleMessage("§8┌[ §cHubCore §4v1.0 §cEnabled §8]");
        utilities.consoleMessage("§8├ §7Plugin by: §4NATroutter");
        utilities.consoleMessage("§8├ §7Website: §4NATroutter.net");
        utilities.consoleMessage("§8└ §7Hooks:");
        hooks = new Hooks(this);
        utilities.consoleMessage("§8─────────────────────────────────────────");


        EventManager evm = new EventManager(this);

        //Register all listeners
        evm.RegisterListeners(
                SelectorItemListener.class, GadgetListener.class, MusicPlayer.class,
                SnowCannonListener.class, SlapperListener.class, Protection.class,
                FWSListener.class, PlayerCarry.class
        );

        //Register all commands
        evm.RegisterCommands(
                Adminmode.class, Hubitems.class
        );


        //Update all online players inventories!
        for(Player op : Bukkit.getOnlinePlayers()) {
            if (AdminModeHandler.isAdmin(op)) {continue;}
            SelectorItemHandler.update(op);
        }

        ParticleScheduler particleScheduler = new ParticleScheduler(this, utilities);

    }

    @Override
    public void onDisable() {
        instance = null;
    }

}