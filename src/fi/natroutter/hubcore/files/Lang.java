package fi.natroutter.hubcore.files;


import fi.natroutter.hubcore.HubCore;
import fi.natroutter.natlibs.config.ILang;
import fi.natroutter.natlibs.config.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@AllArgsConstructor
public enum Lang implements ILang {
    Prefix("Prefix"),
    NoPerm("NoPerm"),
    TooManyArguments("TooManyArguments"),
    InvalidArguments("InvalidArguments"),
    ConfigReloaded("ConfigReloaded"),
    OnlyIngame("OnlyIngame"),
    AdminModeToggle("AdminModeToggle"),
    AdminModeToggleOther("AdminModeToggleOther"),
    OnbackPack("OnbackPack"),
    inYourBackpack("inYourBackpack"),
    GadgetEffective("GadgetEffective"),
    CantBackpack("CantBackpack"),
    NoCarryToggle("NoCarryToggle"),
    Unknown("Unknown"),
    TargetNotFound("TargetNotFound"),
    WelcomeMotd("WelcomeMotd"),
    Info("Info"),

    Gadgets_BoomBox_NoteNotSelected("Gadgets.BoomBox.NoteNotSelected"),
    ToggleStates_on("ToggleStates.on"),
    ToggleStates_off("ToggleStates.off"),
    Guis_Gadgets_Title("Guis.Gadgets.Title"),

    Guis_Gadgets_BoomBox_Title("Guis.Gadgets.BoomBox.Title"),
    Guis_Gadgets_BoomBox_Bass("Guis.Gadgets.BoomBox.Bass"),
    Guis_Gadgets_BoomBox_SnareDrum("Guis.Gadgets.BoomBox.SnareDrum"),
    Guis_Gadgets_BoomBox_Hat("Guis.Gadgets.BoomBox.Hat"),
    Guis_Gadgets_BoomBox_BassDrum("Guis.Gadgets.BoomBox.BassDrum"),
    Guis_Gadgets_BoomBox_Bells("Guis.Gadgets.BoomBox.Bells"),
    Guis_Gadgets_BoomBox_Flute("Guis.Gadgets.BoomBox.Flute"),
    Guis_Gadgets_BoomBox_Chimes("Guis.Gadgets.BoomBox.Chimes"),
    Guis_Gadgets_BoomBox_Guitar("Guis.Gadgets.BoomBox.Guitar"),
    Guis_Gadgets_BoomBox_Xylophone("Guis.Gadgets.BoomBox.Xylophone"),
    Guis_Gadgets_BoomBox_Vibraphone("Guis.Gadgets.BoomBox.Vibraphone"),
    Guis_Gadgets_BoomBox_CowBell("Guis.Gadgets.BoomBox.CowBell"),
    Guis_Gadgets_BoomBox_Didgeridoo("Guis.Gadgets.BoomBox.Didgeridoo"),
    Guis_Gadgets_BoomBox_SquareWave("Guis.Gadgets.BoomBox.SquareWave"),
    Guis_Gadgets_BoomBox_Banjo("Guis.Gadgets.BoomBox.Banjo"),
    Guis_Gadgets_BoomBox_ElectricPiano("Guis.Gadgets.BoomBox.ElectricPiano"),
    Guis_Gadgets_BoomBox_Harp("Guis.Gadgets.BoomBox.Harp"),

    Guis_Gadgets_Fireworks_Title("Guis.Gadgets.Fireworks.Title"),
    Guis_Gadgets_Fireworks_ColorsTitle("Guis.Gadgets.Fireworks.ColorsTitle"),
    Guis_Gadgets_Fireworks_CustomColorTitle("Guis.Gadgets.Fireworks.CustomColorTitle"),
    Guis_Gadgets_Fireworks_Color("Guis.Gadgets.Fireworks.Color"),
    Guis_Gadgets_Fireworks_FadeColor("Guis.Gadgets.Fireworks.FadeColor"),
    Guis_Gadgets_Fireworks_CurrentColor("Guis.Gadgets.Fireworks.CurrentColor"),
    Guis_Gadgets_Fireworks_Back("Guis.Gadgets.Fireworks.Back"),
    Guis_Gadgets_Fireworks_CustomColor("Guis.Gadgets.Fireworks.CustomColor"),
    Guis_Gadgets_Fireworks_ColorUp("Guis.Gadgets.Fireworks.ColorUp"),
    Guis_Gadgets_Fireworks_ColorDown("Guis.Gadgets.Fireworks.ColorDown"),
    Guis_Gadgets_Fireworks_Red("Guis.Gadgets.Fireworks.Red"),
    Guis_Gadgets_Fireworks_Green("Guis.Gadgets.Fireworks.Green"),
    Guis_Gadgets_Fireworks_Blue("Guis.Gadgets.Fireworks.Blue"),
    Guis_Gadgets_Fireworks_CurrentSetting("Guis.Gadgets.Fireworks.CurrentSetting"),
    Guis_Gadgets_Fireworks_CurrentSetting_Lore("Guis.Gadgets.Fireworks.CurrentSetting_Lore"),
    Guis_Gadgets_Fireworks_SaveSettings("Guis.Gadgets.Fireworks.SaveSettings"),
    Guis_Gadgets_Fireworks_Colors_DarkRed("Guis.Gadgets.Fireworks.Colors.DarkRed"),
    Guis_Gadgets_Fireworks_Colors_Red("Guis.Gadgets.Fireworks.Colors.Red"),
    Guis_Gadgets_Fireworks_Colors_Gold("Guis.Gadgets.Fireworks.Colors.Gold"),
    Guis_Gadgets_Fireworks_Colors_Yellow("Guis.Gadgets.Fireworks.Colors.Yellow"),
    Guis_Gadgets_Fireworks_Colors_DarkGreen("Guis.Gadgets.Fireworks.Colors.DarkGreen"),
    Guis_Gadgets_Fireworks_Colors_Green("Guis.Gadgets.Fireworks.Colors.Green"),
    Guis_Gadgets_Fireworks_Colors_Aqua("Guis.Gadgets.Fireworks.Colors.Aqua"),
    Guis_Gadgets_Fireworks_Colors_DarkAqua("Guis.Gadgets.Fireworks.Colors.DarkAqua"),
    Guis_Gadgets_Fireworks_Colors_DarkBlue("Guis.Gadgets.Fireworks.Colors.DarkBlue"),
    Guis_Gadgets_Fireworks_Colors_Blue("Guis.Gadgets.Fireworks.Colors.Blue"),
    Guis_Gadgets_Fireworks_Colors_LightPurple("Guis.Gadgets.Fireworks.Colors.LightPurple"),
    Guis_Gadgets_Fireworks_Colors_DarkPurple("Guis.Gadgets.Fireworks.Colors.DarkPurple"),
    Guis_Gadgets_Fireworks_Colors_White("Guis.Gadgets.Fireworks.Colors.White"),
    Guis_Gadgets_Fireworks_Colors_Gray("Guis.Gadgets.Fireworks.Colors.Gray"),
    Guis_Gadgets_Fireworks_Colors_DarkGray("Guis.Gadgets.Fireworks.Colors.DarkGray"),
    Guis_Gadgets_Fireworks_Colors_Black("Guis.Gadgets.Fireworks.Colors.Black"),
    Guis_Gadgets_Fireworks_Colors_Custom("Guis.Gadgets.Fireworks.Colors.Custom"),
    Guis_Gadgets_Fireworks_Additives_Trail("Guis.Gadgets.Fireworks.Additives.Trail"),
    Guis_Gadgets_Fireworks_Additives_Twinkle("Guis.Gadgets.Fireworks.Additives.Twinkle"),
    Guis_Gadgets_Fireworks_Shapes_SmallBall("Guis.Gadgets.Fireworks.Shapes.SmallBall"),
    Guis_Gadgets_Fireworks_Shapes_LargeBall("Guis.Gadgets.Fireworks.Shapes.LargeBall"),
    Guis_Gadgets_Fireworks_Shapes_Star("Guis.Gadgets.Fireworks.Shapes.Star"),
    Guis_Gadgets_Fireworks_Shapes_Creeper("Guis.Gadgets.Fireworks.Shapes.Creeper"),
    Guis_Gadgets_Fireworks_Shapes_Burst("Guis.Gadgets.Fireworks.Shapes.Burst"),

    Guis_Particles_Title("Guis.Particles.Title"),
    Guis_Particles_Particles_Tail("Guis.Particles.Particles.Tail"),
    Guis_Particles_Particles_Cloud("Guis.Particles.Particles.Cloud"),
    Guis_Particles_ParticleMode("Guis.Particles.ParticleMode"),
    Guis_Particles_ParticleMode_lore("Guis.Particles.ParticleMode_Lore"),
    Guis_Particles_Disabled("Guis.Particles.Disabled"),
    Guis_Particles_Disabled_Lore("Guis.Particles.Disabled_Lore"),
    Guis_Particles_Effect_Lore("Guis.Particles.Effect_Lore"),
    Guis_Particles_Witch("Guis.Particles.Witch"),
    Guis_Particles_Totem("Guis.Particles.Totem"),
    Guis_Particles_Ink("Guis.Particles.Ink"),
    Guis_Particles_Soulflame("Guis.Particles.Soulflame"),
    Guis_Particles_Cloud("Guis.Particles.Cloud"),
    Guis_Particles_Soul("Guis.Particles.Soul"),
    Guis_Particles_Notes("Guis.Particles.Notes"),
    Guis_Particles_Conduit("Guis.Particles.Conduit"),
    Guis_Particles_Lava("Guis.Particles.Lava"),
    Guis_Particles_Hearts("Guis.Particles.Hearts"),
    Guis_Particles_Happy("Guis.Particles.Happy"),
    Guis_Particles_Angry("Guis.Particles.Angry"),
    Guis_Particles_Enchanthit("Guis.Particles.Enchanthit"),
    Guis_Particles_Endrod("Guis.Particles.Endrod"),
    Guis_Particles_Enchanting("Guis.Particles.Enchanting"),
    Guis_Particles_RainbowDust("Guis.Particles.RainbowDust"),
    Guis_Particles_DragonBreath("Guis.Particles.DragonBreath"),
    Guis_Particles_Critical("Guis.Particles.Critical"),
    Guis_Particles_Damage("Guis.Particles.Damage"),
    Guis_Particles_Smoke("Guis.Particles.Smoke"),
    Guis_Particles_Sneeze("Guis.Particles.Sneeze"),

    Items_Gadgets_Unlock_Lore("Items.Gadgets.Unlock_Lore"),
    Items_Gadgets_FireWorkShooter("Items.Gadgets.FireWorkShooter"),
    Items_Gadgets_FireWorkShooter_Lore("Items.Gadgets.FireWorkShooter_Lore"),
    Items_Gadgets_Slapper("Items.Gadgets.Slapper"),
    Items_Gadgets_Slapper_Lore("Items.Gadgets.Slapper_Lore"),
    Items_Gadgets_BoomBox("Items.Gadgets.BoomBox"),
    Items_Gadgets_BoomBox_Lore("Items.Gadgets.BoomBox_Lore"),
    Items_Gadgets_SnowCannon("Items.Gadgets.SnowCannon"),
    Items_Gadgets_SnowCannon_Lore("Items.Gadgets.SnowCannon_Lore"),
    Items_Gadgets_Wings_Name("Items.Gadgets.Wings.Name"),
    Items_Gadgets_Wings_Lore("Items.Gadgets.Wings.Lore"),
    Items_Gadgets_Wings_Booster("Items.Gadgets.Wings.Booster"),
    Items_Gadgets_Wings_Booster_Lore("Items.Gadgets.Wings.Booster_Lore"),
    Items_Gadgets_Jumpper("Items.Gadgets.Jumpper"),
    Items_Gadgets_Jumpper_Lore("Items.Gadgets.Jumpper_Lore"),

    Items_Gadgets_Grapler("Items.Gadgets.Grapler"),
    Items_Gadgets_Grapler_Lore("Items.Gadgets.Grapler_Lore"),

    Items_JoinItems_ParticleSelector("Items.JoinItems.ParticleSelector"),
    Items_JoinItems_ParticleSelector_Lore("Items.JoinItems.ParticleSelector_Lore"),
    Items_JoinItems_GadgetSelector("Items.JoinItems.GadgetSelector"),
    Items_JoinItems_GadgetSelector_Lore("Items.JoinItems.GadgetSelector_Lore"),
    Items_JoinItems_ServerSelector("Items.JoinItems.ServerSelector"),
    Items_JoinItems_ServerSelector_Lore("Items.JoinItems.ServerSelector_Lore"),
    Items_JoinItems_InfoBook("Items.JoinItems.InfoBook"),
    Items_JoinItems_InfoBook_Lore("Items.JoinItems.InfoBook_Lore");

    @Getter
    private String path;

    public static void reload() {
        Prefix.reloadFile();
    }

    @Override
    public Language lang() {
        return Language.getFromKey(Config.Language);
    }

    @Override
    public ILang prefix() {
        return Prefix;
    }

    @Override
    public JavaPlugin getPlugin() {
        return HubCore.getInstance();
    }
}
