package fi.natroutter.hubcore.utilities;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.SkullCreator;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FShape;
import fi.natroutter.hubcore.features.particles.ParticleMode;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class Items {

	public static BaseItem ServerIcon(Material mat, String name) {
		BaseItem item = new BaseItem(mat);
		item.addItemFlags(ItemFlag.values());
		item.name(name);
		return item;
	}

	public static BaseItem back() {
		BaseItem item = new SkullCreator().Create(Lang.Guis_Gadgets_Fireworks_Back, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQyZmRlOGI4MmU4YzFiOGMyMmIyMjY3OTk4M2ZlMzVjYjc2YTc5Nzc4NDI5YmRhZGFiYzM5N2ZkMTUwNjEifX19");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem arrowUP() {
		BaseItem item = new SkullCreator().Create(Lang.Guis_Gadgets_Fireworks_ColorUp, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhNTY2N2VmNzI4NWM5MjI1ZmMyNjdkNDUxMTdlYWI1NDc4Yzc4NmJkNWFmMGExOTljMjlhMmMxNGMxZiJ9fX0=");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem arrowDOWN() {
		BaseItem item = new SkullCreator().Create(Lang.Guis_Gadgets_Fireworks_ColorDown, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDFiNjJkYjVjMGEzZmExZWY0NDFiZjcwNDRmNTExYmU1OGJlZGY5YjY3MzE4NTNlNTBjZTkwY2Q0NGZiNjkifX19");
		item.addItemFlags(ItemFlag.values());
		return item;
	}


	public static BaseItem particle_ChangeMode(ParticleMode mode) {
		BaseItem item = new SkullCreator().Create(Lang.Guis_Particles_ParticleMode, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZDhmZThkNGQ1YzA1ODg2ZGI5ZjU5MTI3OTYxNTQ5ZTYwMmYzMGM1MTc1OTk5ODEzMWIyYWQyNWQyNjQifX19");
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Guis_Particles_ParticleMode_lore.asComponentList(
				Placeholder.parsed("status", mode.langName())
		));
		return item;
	}

	public static BaseItem particle_Disable() {
		BaseItem item = new BaseItem(Material.BARRIER);
		item.name(Lang.Guis_Particles_Disabled);
		item.lore(Lang.Guis_Particles_Disabled_Lore);
		return item;
	}




	public static BaseItem CurrentSettings(PlayerData data) {
		BaseItem item = new BaseItem(Material.PAPER);
		item.name(Lang.Guis_Gadgets_Fireworks_CurrentSetting);

		FShape shape = FShape.fromString(data.getShape());
		FColor color = FColor.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b());
		FColor fade = FColor.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b());

		item.lore(Lang.Guis_Gadgets_Fireworks_CurrentSetting_Lore.asComponentList(
				Placeholder.parsed("shape", shape.getLangName().asLegacy()),
				Placeholder.component("color", color.getLangName()),
				Placeholder.component("fadecolor", fade.getLangName()),
				Placeholder.parsed("twinkle", (data.getTwinkle() ? Lang.ToggleStates_on : Lang.ToggleStates_off).asLegacy()),
				Placeholder.parsed("trail",(data.getTrail() ? Lang.ToggleStates_on : Lang.ToggleStates_off).asLegacy())

		));
		return item;
	}



	public static BaseItem red(int value) {
		BaseItem item = new BaseItem(Material.RED_CONCRETE);
		item.name(Lang.Guis_Gadgets_Fireworks_Red.asComponent(
				Placeholder.parsed("value", String.valueOf(value))
		));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem green(int value) {
		BaseItem item = new BaseItem(Material.GREEN_CONCRETE);
		item.name(Lang.Guis_Gadgets_Fireworks_Green.asComponent(
				Placeholder.parsed("value", String.valueOf(value))
		));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem blue(int value) {
		BaseItem item = new BaseItem(Material.BLUE_CONCRETE);
		item.name(Lang.Guis_Gadgets_Fireworks_Blue.asComponent(
				Placeholder.parsed("value", String.valueOf(value))
		));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem customColor() {
		BaseItem item = new BaseItem(Material.SLIME_BALL);
		item.name(Lang.Guis_Gadgets_Fireworks_CustomColor);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem colorDisplay(FColor color) {
		BaseItem item = new BaseItem(Material.LEATHER_CHESTPLATE);
		item.name(Lang.Guis_Gadgets_Fireworks_Color);
		item.addItemFlags(ItemFlag.values());
		LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
		meta.setColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
		item.setItemMeta(meta);
		return item;
	}

	public static BaseItem fireworkStar(FColor color) {
		BaseItem item = new BaseItem(Material.FIREWORK_STAR);
		item.name(color.getLangName());
		item.addItemFlags(ItemFlag.values());
		FireworkEffectMeta meta = (FireworkEffectMeta)item.getItemMeta();

		FireworkEffect.Builder eff = FireworkEffect.builder().with(FireworkEffect.Type.BALL);
		for (int i = 0; i < 5; i++) {
			eff.withColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
		}

		meta.setEffect(eff.build());
		item.setItemMeta(meta);
		return item;
	}

	public static BaseItem fadeColor(FColor color) {
		BaseItem item = new BaseItem(Material.LEATHER_CHESTPLATE);
		item.name(Lang.Guis_Gadgets_Fireworks_FadeColor);
		item.addItemFlags(ItemFlag.values());
		LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
		meta.setColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
		item.setItemMeta(meta);
		return item;
	}

	public static BaseItem save() {
		BaseItem item = new BaseItem(Material.WRITABLE_BOOK);
		item.name(Lang.Guis_Gadgets_Fireworks_SaveSettings);
		item.addItemFlags(ItemFlag.values());
		return item;
	}


	public static BaseItem SmallBall() {
		BaseItem item = new BaseItem(Material.MAGMA_CREAM);
		item.name(Lang.Guis_Gadgets_Fireworks_Shapes_SmallBall);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem LargeBall() {
		BaseItem item = new BaseItem(Material.FIRE_CHARGE);
		item.name(Lang.Guis_Gadgets_Fireworks_Shapes_LargeBall);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem Star() {
		BaseItem item = new BaseItem(Material.GOLD_NUGGET);
		item.name(Lang.Guis_Gadgets_Fireworks_Shapes_Star);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem Creeper() {
		BaseItem item = new BaseItem(Material.CREEPER_HEAD);
		item.name(Lang.Guis_Gadgets_Fireworks_Shapes_Creeper);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem Burst() {
		BaseItem item = new BaseItem(Material.FEATHER);
		item.name(Lang.Guis_Gadgets_Fireworks_Shapes_Burst);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem Trail() {
		BaseItem item = new BaseItem(Material.DIAMOND);
		item.name(Lang.Guis_Gadgets_Fireworks_Additives_Trail);
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem Twinkle() {
		BaseItem item = new BaseItem(Material.GLOWSTONE_DUST);
		item.name(Lang.Guis_Gadgets_Fireworks_Additives_Twinkle);
		item.addItemFlags(ItemFlag.values());
		return item;
	}


	public static BaseItem gadged_FireworkShooter() {
		BaseItem item = new BaseItem(Material.DIAMOND_HOE);
		item.name(Lang.Items_Gadgets_FireWorkShooter);
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Items_Gadgets_FireWorkShooter_Lore);
		return item;
	}

	public static BaseItem gadged_Jumpper() {
		BaseItem item = new BaseItem(Material.TRIPWIRE_HOOK);
		item.name(Lang.Items_Gadgets_Jumpper);
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Items_Gadgets_Jumpper_Lore);
		return item;
	}

	public static BaseItem gadged_Slapper() {
		BaseItem item = new BaseItem(Material.PORKCHOP);
		item.name(Lang.Items_Gadgets_Slapper);
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Items_Gadgets_Slapper_Lore);
		return item;
	}

	public static BaseItem gadged_BoomBox() {
		BaseItem item = new BaseItem(Material.MUSIC_DISC_PIGSTEP);
		item.name(Lang.Items_Gadgets_BoomBox);
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Items_Gadgets_BoomBox_Lore);
		return item;
	}

	public static BaseItem gadged_SnowCannon() {
		BaseItem item = new BaseItem(Material.NETHERITE_HOE);
		item.name(Lang.Items_Gadgets_SnowCannon);
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Items_Gadgets_SnowCannon_Lore);
		return item;
	}

	public static BaseItem gadged_Wing() {
		BaseItem item = new BaseItem(Material.ELYTRA);
		item.name(Lang.Items_Gadgets_Wings_Name);
		item.addItemFlags(ItemFlag.values());
		item.setUnbreakable(true);
		item.lore(Lang.Items_Gadgets_Wings_Lore);
		return item;
	}

	public static BaseItem gadged_Booster() {
		BaseItem item = new BaseItem(Material.FIREWORK_ROCKET);
		item.name(Lang.Items_Gadgets_Wings_Booster);
		item.addItemFlags(ItemFlag.values());
		item.lore(Lang.Items_Gadgets_Wings_Booster_Lore);
		return item;
	}

	public static BaseItem gadget_graper() {
		BaseItem item = new BaseItem(Material.FISHING_ROD);
		item.setUnbreakable(true);
		item.name(Lang.Items_Gadgets_Grapler);
		item.lore(Lang.Items_Gadgets_Grapler_Lore);
		return item;
	}

	public static BaseItem particleSelector() {
		BaseItem item = new BaseItem(Config.Items_ParticleSelector.asMaterial());
		item.addItemFlags(ItemFlag.values());
		item.name(Lang.Items_JoinItems_ParticleSelector);
		item.lore(Lang.Items_JoinItems_ParticleSelector_Lore);
		return item;
	}

	public static BaseItem gadgetSelector() {
		BaseItem item = new BaseItem(Config.Items_GadgetSelector.asMaterial());
		item.addItemFlags(ItemFlag.values());
		item.name(Lang.Items_JoinItems_GadgetSelector);
		item.lore(Lang.Items_JoinItems_GadgetSelector_Lore);
		return item;
	}

	public static BaseItem serverSelector(Player p) {
		p.setCompassTarget(p.getWorld().getSpawnLocation());

		BaseItem item = new BaseItem(Config.Items_ServerSelector.asMaterial());
		item.addItemFlags(ItemFlag.values());
		item.name(Lang.Items_JoinItems_ServerSelector);
		item.lore(Lang.Items_JoinItems_ServerSelector_Lore);
		return item;
	}

	public static BaseItem Info() {
		BaseItem item = new BaseItem(Config.Items_InfoBook.asMaterial());
		item.addItemFlags(ItemFlag.values());
		item.name(Lang.Items_JoinItems_InfoBook);
		item.lore(Lang.Items_JoinItems_InfoBook_Lore);
		return item;
	}

}
