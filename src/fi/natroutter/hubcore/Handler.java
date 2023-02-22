package fi.natroutter.hubcore;

import fi.natroutter.hubcore.features.ServerSelector;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.features.gadgets.boombox.MusicGUI;
import fi.natroutter.hubcore.features.gadgets.slapper.SlapperHandler;
import fi.natroutter.hubcore.features.gadgets.snowcannon.SnowCannonHandler;
import fi.natroutter.hubcore.handlers.AdminModeHandler;
import fi.natroutter.hubcore.handlers.Database.Database;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.hubcore.utilities.Utils;
import fi.natroutter.natlibs.handlers.ParticleSpawner;
import fi.natroutter.natlibs.handlers.configuration.ConfigManager;
import fi.natroutter.natlibs.handlers.database.YamlDatabase;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.handlers.langHandler.language.Language;
import fi.natroutter.natlibs.handlers.langHandler.language.key.LanguageKey;
import fi.natroutter.natlibs.utilities.Utilities;
import lombok.Getter;
import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis.FireworkGUI;
import fi.natroutter.hubcore.features.gadgets.GadgetGUI;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
import fi.natroutter.hubcore.features.gadgets.jumpper.JumpperHandler;
import fi.natroutter.hubcore.features.gadgets.wings.WingsHandler;
import fi.natroutter.hubcore.features.particles.ParticleGUI;
import fi.natroutter.hubcore.features.particles.ParticleScheduler;
import fi.natroutter.hubcore.features.playercarry.PlayerCarryHandler;
import fi.natroutter.hubcore.features.protections.ProtectionHandler;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.handlers.CommonHandler;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.utilities.ServerSwitcher;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

@Getter
public class Handler {

    private JavaPlugin instance;
    private LangManager lang;
    private Config config;
    private YamlDatabase yamlDatabase;
    private Utilities utilities;
    private Hooks hooks;
    private PlayerDataHandler dataHandler;
    private ParticleSpawner spawner;
    private AdminModeHandler adminModeHandler;
    private Items items;
    private WingsHandler wingsHandler;
    private GadgetHandler gadgetHandler;
    private SelectorItemHandler selectorItemHandler;
    private GadgetGUI gadgetGUI;
    private ServerSelector serverSelector;
    private ParticleGUI particleGUI;
    private Utils utils;
    private MusicGUI musicGUI;
    private SnowCannonHandler snowCannonHandler;
    private FireworkGUI fireworkGUI;
    private FWSHandler fwsHandler;
    private SlapperHandler slapperHandler;
    private JumpperHandler jumpperHandler;
    private ParticleScheduler particleScheduler;
    private PlayerCarryHandler playerCarryHandler;
    private ProtectionHandler protectionHandler;
    private ServerSwitcher serverSwitcher;

    public Handler(JavaPlugin instance) {
        this.instance = instance;

        //Print sexy banner and hook other plugins!
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        PluginDescriptionFile pdf = instance.getDescription();

        this.config = new ConfigManager(instance).load(Config.class);

        Optional<Language> language = Language.getFromKey(LanguageKey.of("fi_fi"));
        this.lang = language.map(value -> new LangManager(instance, value)).orElseGet(() -> {
            console.sendMessage("§4["+instance.getName()+"][Lang] §cLanguage file defined in config not found, Using default!");
            return new LangManager(instance, Language.ENGLISH);
        });

        console.sendMessage("§8─────────────────────────────────────────");
        console.sendMessage("§8┌[ §cHubCore §4v"+pdf.getVersion()+" §cEnabled §8]");
        console.sendMessage("§8├ §7Plugin by: §4NATroutter");
        console.sendMessage("§8├ §7Website: §4NATroutter.net");
        console.sendMessage("§8└ §7Hooks:");
        this.hooks = new Hooks(instance);
        console.sendMessage("§8─────────────────────────────────────────");

        this.utils = new Utils(this);
        this.serverSwitcher = new ServerSwitcher(this);

        this.spawner = new ParticleSpawner();
        this.items = new Items(this);
        this.utilities = new Utilities(instance);
        this.yamlDatabase = new YamlDatabase(instance);
        this.dataHandler = new PlayerDataHandler(instance, new Database(instance), 30 * 60);

        this.wingsHandler = new WingsHandler(this);
        this.serverSelector = new ServerSelector(this);
        this.particleGUI = new ParticleGUI(this);
        this.gadgetHandler = new GadgetHandler(this);
        this.selectorItemHandler = new SelectorItemHandler(this);
        this.gadgetGUI = new GadgetGUI(this);
        this.adminModeHandler = new AdminModeHandler(this);
        this.musicGUI = new MusicGUI(this);
        this.snowCannonHandler = new SnowCannonHandler();
        this.fwsHandler = new FWSHandler(this);
        this.fireworkGUI = new FireworkGUI(this);
        this.slapperHandler = new SlapperHandler(this);
        this.jumpperHandler = new JumpperHandler();
        this.playerCarryHandler = new PlayerCarryHandler();
        this.protectionHandler = new ProtectionHandler();

        for(Player p : Bukkit.getOnlinePlayers()) {
            if (adminModeHandler.isAdmin(p)) {continue;}
            selectorItemHandler.update(p);
        }

        this.particleScheduler = new ParticleScheduler(this);

        new CommonHandler(instance);
        new SnowCannonHandler(this);
    }

}