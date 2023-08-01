package fi.natroutter.hubcore.files;


import fi.natroutter.hubcore.HubCore;
import fi.natroutter.natlibs.config.IConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.Prefix;

@AllArgsConstructor
public enum Config implements IConfig {

    Language("Language"),
    DisableRedstone("DisableRedstone"),
    DisablePhysics("DisablePhysics"),
    RainbowSpeed("RainbowSpeed"),

    Items_ParticleSelector("Item.ParticleSelector"),
    Items_GadgetSelector("Item.GadgetSelector"),
    Items_ServerSelector("Item.ServerSelector"),
    Items_InfoBook("Item.InfoBook"),

    Gadgets_Boombox_Perm("Gadgets.Boombox.Permission"),
    Gadgets_Boombox_Need("Gadgets.Boombox.Need"),

    Gadgets_Wings_Perm("Gadgets.Wings.Permission"),
    Gadgets_Wings_Need("Gadgets.Wings.Need"),

    Gadgets_Snowcannon_Perm("Gadgets.Snowcannon.Permission"),
    Gadgets_Snowcannon_Need("Gadgets.Snowcannon.Need"),

    Gadgets_Slapper_Perm("Gadgets.Slapper.Permission"),
    Gadgets_Slapper_Need("Gadgets.Slapper.Need"),

    Gadgets_Jumpper_Perm("Gadgets.Jumpper.Permission"),
    Gadgets_Jumpper_Need("Gadgets.Jumpper.Need"),

    Gadgets_Fireworkshooter_Perm("Gadgets.Fireworkshooter.Permission"),
    Gadgets_Fireworkshooter_Need("Gadgets.Fireworkshooter.Need"),

    Gadgets_Grapler_Perm("Gadgets.Grapler.Permission"),
    Gadgets_Grapler_Need("Gadgets.Grapler.Need"),



    Particles_Witch_Perm("Particles.Witch.Permission"),
    Particles_Witch_Need("Particles.Witch.Need"),

    Particles_Totem_Perm("Particles.Totem.Permission"),
    Particles_Totem_Need("Particles.Totem.Need"),

    Particles_Ink_Perm("Particles.Ink.Permission"),
    Particles_Ink_Need("Particles.Ink.Need"),

    Particles_Soulflame_Perm("Particles.Soulflame.Permission"),
    Particles_Soulflame_Need("Particles.Soulflame.Need"),

    Particles_Cloud_Perm("Particles.Cloud.Permission"),
    Particles_Cloud_Need("Particles.Cloud.Need"),

    Particles_Soul_Perm("Particles.Soul.Permission"),
    Particles_Soul_Need("Particles.Soul.Need"),

    Particles_Notes_Perm("Particles.Notes.Permission"),
    Particles_Notes_Need("Particles.Notes.Need"),

    Particles_Conduit_Perm("Particles.Conduit.Permission"),
    Particles_Conduit_Need("Particles.Conduit.Need"),

    Particles_Lava_Perm("Particles.Lava.Permission"),
    Particles_Lava_Need("Particles.Lava.Need"),

    Particles_Hearts_Perm("Particles.Hearts.Permission"),
    Particles_Hearts_Need("Particles.Hearts.Need"),

    Particles_Happy_Perm("Particles.Happy.Permission"),
    Particles_Happy_Need("Particles.Happy.Need"),

    Particles_Angry_Perm("Particles.Angry.Permission"),
    Particles_Angry_Need("Particles.Angry.Need"),

    Particles_Enchanthit_Perm("Particles.Enchanthit.Permission"),
    Particles_Enchanthit_Need("Particles.Enchanthit.Need"),

    Particles_Endrod_Perm("Particles.Endrod.Permission"),
    Particles_Endrod_Need("Particles.Endrod.Need"),

    Particles_Enchanting_Perm("Particles.Enchanting.Permission"),
    Particles_Enchanting_Need("Particles.Enchanting.Need"),

    Particles_Rainbowdust_Perm("Particles.Rainbowdust.Permission"),
    Particles_Rainbowdust_Need("Particles.Rainbowdust.Need"),

    Particles_Dragonbreath_Perm("Particles.Dragonbreath.Permission"),
    Particles_Dragonbreath_Need("Particles.Dragonbreath.Need"),

    Particles_Critical_Perm("Particles.Critical.Permission"),
    Particles_Critical_Need("Particles.Critical.Need"),

    Particles_Damage_Perm("Particles.Damage.Permission"),
    Particles_Damage_Need("Particles.Damage.Need"),

    Particles_Smoke_Perm("Particles.Smoke.Permission"),
    Particles_Smoke_Need("Particles.Smoke.Need"),

    Particles_Sneeze_Perm("Particles.Sneeze.Permission"),
    Particles_Sneeze_Need("Particles.Sneeze.Need"),

    ServerSelector_UseOverride("ServerSelector.UseOverride"),
    ServerSelector_OverrideCommand("ServerSelector.OverrideCommand"),

    ServerSelector_Title("ServerSelector.Title"),
    ServerSelector_GuiSize("ServerSelector.GuiSize"),

    ServerSelector_Items("ServerSelector.Items"),

    ;

    public static void reload() {
        Language.reloadFile();
    }

    @Getter
    public String path;

    @Override
    public JavaPlugin getPlugin() {
        return HubCore.getInstance();
    }
}
