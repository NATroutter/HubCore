package net.natroutter.hubcore.utilities;

import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FShape;
import net.natroutter.hubcore.features.particles.ParticleMode;
import net.natroutter.hubcore.files.Config;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.natlibs.handlers.LangHandler.language.LangManager;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.SkullCreator;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class Items {

	private LangManager lang;
	private Config config;

	public Items(Handler handler) {
		this.lang = handler.getLang();
		this.config = handler.getConfig();
	}
	
	public BaseItem ServerIcon(Material mat, String name) {
		BaseItem item = new BaseItem(mat);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(name);
		return item;
	}

	public BaseItem back() {
		BaseItem item = new SkullCreator().Create(lang.get(Translations.Guis_Gadgets_Fireworks_Back), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQyZmRlOGI4MmU4YzFiOGMyMmIyMjY3OTk4M2ZlMzVjYjc2YTc5Nzc4NDI5YmRhZGFiYzM5N2ZkMTUwNjEifX19");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem arrowUP() {
		BaseItem item = new SkullCreator().Create(lang.get(Translations.Guis_Gadgets_Fireworks_ColorUp), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhNTY2N2VmNzI4NWM5MjI1ZmMyNjdkNDUxMTdlYWI1NDc4Yzc4NmJkNWFmMGExOTljMjlhMmMxNGMxZiJ9fX0=");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem arrowDOWN() {
		BaseItem item = new SkullCreator().Create(lang.get(Translations.Guis_Gadgets_Fireworks_ColorDown), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDFiNjJkYjVjMGEzZmExZWY0NDFiZjcwNDRmNTExYmU1OGJlZGY5YjY3MzE4NTNlNTBjZTkwY2Q0NGZiNjkifX19");
		item.addItemFlags(ItemFlag.values());
		return item;
	}


	public BaseItem particle_ChangeMode(ParticleMode mode) {
		BaseItem item = new SkullCreator().Create(lang.get(Translations.Guis_Particles_ParticleMode), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZDhmZThkNGQ1YzA1ODg2ZGI5ZjU5MTI3OTYxNTQ5ZTYwMmYzMGM1MTc1OTk5ODEzMWIyYWQyNWQyNjQifX19");
		item.addItemFlags(ItemFlag.values());
		ArrayList<String> lore = new ArrayList<>();
		for (String line : lang.getList(Translations.Guis_Particles_ParticleMode_lore)) {
			StringHandler nl = new StringHandler(line);
			nl.replaceAll("{status}", mode.langName(lang));
			lore.add(nl.build());
		}
		item.setLore(lore);
		return item;
	}

	public BaseItem particle_Disable() {
		BaseItem item = new BaseItem(Material.BARRIER);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(lang.get(Translations.Guis_Particles_Disabled));
		item.setLore(lang.getList(Translations.Guis_Particles_Disabled_Lore));
		return item;
	}




	public BaseItem CurrentSettings(PlayerData data) {
		BaseItem item = new BaseItem(Material.PAPER);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_CurrentSetting));

		FShape shape = FShape.fromString(data.getShape());
		FColor color = FColor.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b());
		FColor fade = FColor.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b());

		ArrayList<String> lore = new ArrayList<>();
		for (String line : lang.getList(Translations.Guis_Gadgets_Fireworks_CurrentSetting_Lore)) {

			StringHandler nl = new StringHandler(line);
			nl.replaceAll("{shape}", shape != null ? shape.langName(lang) : lang.get(Translations.Unknown));
			nl.replaceAll("{color}", color.getLangName(lang));
			nl.replaceAll("{fadecolor}", fade.getLangName(lang));
			nl.replaceAll("{twinkle}", data.getTwinkle() ? lang.get(Translations.ToggleStates_on) : lang.get(Translations.ToggleStates_off));
			nl.replaceAll("{trail}", data.getTrail() ? lang.get(Translations.ToggleStates_on) : lang.get(Translations.ToggleStates_off));
			lore.add(nl.build());
		}
		item.setLore(lore);
		return item;
	}



	public BaseItem red(int value) {
		BaseItem item = new BaseItem(Material.RED_CONCRETE);
		StringHandler name = new StringHandler(lang.get(Translations.Guis_Gadgets_Fireworks_Red));
		name.replaceAll("{value}", value);
		item.setDisplayName(name.build());
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem green(int value) {
		BaseItem item = new BaseItem(Material.GREEN_CONCRETE);
		StringHandler name = new StringHandler(lang.get(Translations.Guis_Gadgets_Fireworks_Green));
		name.replaceAll("{value}", value);
		item.setDisplayName(name.build());
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem blue(int value) {
		BaseItem item = new BaseItem(Material.BLUE_CONCRETE);
		StringHandler name = new StringHandler(lang.get(Translations.Guis_Gadgets_Fireworks_Blue));
		name.replaceAll("{value}", value);
		item.setDisplayName(name.build());
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem customColor() {
		BaseItem item = new BaseItem(Material.SLIME_BALL);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_CustomColor));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem colorDisplay(FColor color) {
		BaseItem item = new BaseItem(Material.LEATHER_CHESTPLATE);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Color));
		item.addItemFlags(ItemFlag.values());
		LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
		meta.setColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
		item.setItemMeta(meta);
		return item;
	}

	public BaseItem fireworkStar(FColor color) {
		BaseItem item = new BaseItem(Material.FIREWORK_STAR);
		item.setDisplayName(color.getLangName(lang));
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

	public BaseItem fadeColor(FColor color) {
		BaseItem item = new BaseItem(Material.LEATHER_CHESTPLATE);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_FadeColor));
		item.addItemFlags(ItemFlag.values());
		LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
		meta.setColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
		item.setItemMeta(meta);
		return item;
	}

	public BaseItem save() {
		BaseItem item = new BaseItem(Material.WRITABLE_BOOK);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_SaveSettings));
		item.addItemFlags(ItemFlag.values());
		return item;
	}


	public BaseItem SmallBall() {
		BaseItem item = new BaseItem(Material.MAGMA_CREAM);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_SmallBall));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem LargeBall() {
		BaseItem item = new BaseItem(Material.FIRE_CHARGE);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_LargeBall));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem Star() {
		BaseItem item = new BaseItem(Material.GOLD_NUGGET);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_Star));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem Creeper() {
		BaseItem item = new BaseItem(Material.CREEPER_HEAD);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_Creeper));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem Burst() {
		BaseItem item = new BaseItem(Material.FEATHER);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_Burst));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem Trail() {
		BaseItem item = new BaseItem(Material.DIAMOND);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Additives_Trail));
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public BaseItem Twinkle() {
		BaseItem item = new BaseItem(Material.GLOWSTONE_DUST);
		item.setDisplayName(lang.get(Translations.Guis_Gadgets_Fireworks_Additives_Twinkle));
		item.addItemFlags(ItemFlag.values());
		return item;
	}


	public BaseItem gadged_FireworkShooter() {
		BaseItem item = new BaseItem(Material.DIAMOND_HOE);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_FireWorkShooter));
		item.addItemFlags(ItemFlag.values());
		item.setLore(lang.getList(Translations.Items_Gadgets_FireWorkShooter_Lore));
		return item;
	}

	public BaseItem gadged_Jumpper() {
		BaseItem item = new BaseItem(Material.TRIPWIRE_HOOK);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_Jumpper));
		item.addItemFlags(ItemFlag.values());
		item.setLore(lang.getList(Translations.Items_Gadgets_Jumpper_Lore));
		return item;
	}

	public BaseItem gadged_Slapper() {
		BaseItem item = new BaseItem(Material.PORKCHOP);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_Slapper));
		item.addItemFlags(ItemFlag.values());
		item.setLore(lang.getList(Translations.Items_Gadgets_Slapper_Lore));
		return item;
	}

	public BaseItem gadged_BoomBox() {
		BaseItem item = new BaseItem(Material.MUSIC_DISC_PIGSTEP);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_BoomBox));
		item.addItemFlags(ItemFlag.values());
		item.setLore(lang.getList(Translations.Items_Gadgets_BoomBox_Lore));
		return item;
	}

	public BaseItem gadged_SnowCannon() {
		BaseItem item = new BaseItem(Material.NETHERITE_HOE);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_SnowCannon));
		item.addItemFlags(ItemFlag.values());
		item.setLore(lang.getList(Translations.Items_Gadgets_SnowCannon_Lore));
		return item;
	}

	public BaseItem gadged_Wing() {
		BaseItem item = new BaseItem(Material.ELYTRA);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_Wings_Name));
		item.addItemFlags(ItemFlag.values());
		item.setUnbreakable(true);
		item.setLore(lang.getList(Translations.Items_Gadgets_Wings_Lore));
		return item;
	}

	public BaseItem gadged_Booster() {
		BaseItem item = new BaseItem(Material.FIREWORK_ROCKET);
		item.setDisplayName(lang.get(Translations.Items_Gadgets_Wings_Booster));
		item.addItemFlags(ItemFlag.values());
		item.setLore(lang.getList(Translations.Items_Gadgets_Wings_Booster_Lore));
		return item;
	}

	public BaseItem particleSelector() {
		BaseItem item = new BaseItem(config.ParticleSelector_item);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(lang.get(Translations.Items_JoinItems_ParticleSelector));
		item.setLore(lang.getList(Translations.Items_JoinItems_ParticleSelector_Lore));
		return item;
	}

	public BaseItem gadgetSelector() {
		BaseItem item = new BaseItem(config.GadgetSelector_item);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(lang.get(Translations.Items_JoinItems_GadgetSelector));
		item.setLore(lang.getList(Translations.Items_JoinItems_GadgetSelector_Lore));
		return item;
	}

	public BaseItem serverSelector(Player p) {
		p.setCompassTarget(p.getWorld().getSpawnLocation());

		BaseItem item = new BaseItem(config.ServerSelector_item);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(lang.get(Translations.Items_JoinItems_ServerSelector));
		item.setLore(lang.getList(Translations.Items_JoinItems_ServerSelector_Lore));
		return item;
	}

	public BaseItem Info() {
		BaseItem item = new BaseItem(config.InfoBook_item);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(lang.get(Translations.Items_JoinItems_InfoBook));
		item.setLore(lang.getList(Translations.Items_JoinItems_InfoBook_Lore));
		return item;
	}

}
