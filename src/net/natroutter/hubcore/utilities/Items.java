package net.natroutter.hubcore.utilities;

import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FShape;
import net.natroutter.hubcore.features.particles.ParticleMode;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.SkullCreator;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class Items {

	private static final Lang lang = HubCore.getLang();
	private static final Config config = HubCore.getCfg();
	
	public static BaseItem ServerIcon(Material mat, String name) {
		BaseItem item = new BaseItem(mat);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(name);
		return item;
	}



	public static BaseItem back() {
		BaseItem item = new SkullCreator().Create(lang.Guis.Gadgets.fireworks.Back, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQyZmRlOGI4MmU4YzFiOGMyMmIyMjY3OTk4M2ZlMzVjYjc2YTc5Nzc4NDI5YmRhZGFiYzM5N2ZkMTUwNjEifX19");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem arrowUP() {
		BaseItem item = new SkullCreator().Create(lang.Guis.Gadgets.fireworks.ColorUp, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhNTY2N2VmNzI4NWM5MjI1ZmMyNjdkNDUxMTdlYWI1NDc4Yzc4NmJkNWFmMGExOTljMjlhMmMxNGMxZiJ9fX0=");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static BaseItem arrowDOWN() {
		BaseItem item = new SkullCreator().Create(lang.Guis.Gadgets.fireworks.ColorDown, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDFiNjJkYjVjMGEzZmExZWY0NDFiZjcwNDRmNTExYmU1OGJlZGY5YjY3MzE4NTNlNTBjZTkwY2Q0NGZiNjkifX19");
		item.addItemFlags(ItemFlag.values());
		return item;
	}

	public static class Particle {
		public static BaseItem changemode(ParticleMode mode) {
			BaseItem item = new SkullCreator().Create(lang.Guis.particles.ParticleMode, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZDhmZThkNGQ1YzA1ODg2ZGI5ZjU5MTI3OTYxNTQ5ZTYwMmYzMGM1MTc1OTk5ODEzMWIyYWQyNWQyNjQifX19");
			item.addItemFlags(ItemFlag.values());
			ArrayList<String> lore = new ArrayList<>();
			for (String line : lang.Guis.particles.ParticleMode_lore) {
				StringHandler nl = new StringHandler(line);
				nl.replaceAll("{status}", mode.langName());
				lore.add(nl.build());
			}
			item.setLore(lore);
			return item;
		}

		public static BaseItem Disable() {
			BaseItem item = new BaseItem(Material.BARRIER);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Guis.particles.disabled);
			item.setLore(lang.Guis.particles.disabled_lore);
			return item;
		}
	}

	public static class Gadgets {

		public static class Firework {


			public static BaseItem CurrentSettings(PlayerData data) {
				BaseItem item = new BaseItem(Material.PAPER);
				item.setDisplayName(lang.Guis.Gadgets.fireworks.CurrentSetting);

				FShape shape = FShape.fromString(data.getShape());
				FColor color = FColor.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b());
				FColor fade = FColor.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b());

				ArrayList<String> lore = new ArrayList<>();
				for (String line : lang.Guis.Gadgets.fireworks.CurrentSetting_lore) {

					StringHandler nl = new StringHandler(line);
					nl.replaceAll("{shape}", shape != null ? shape.langName() : lang.Unknown);
					nl.replaceAll("{color}", color.getLangName());
					nl.replaceAll("{fadecolor}", fade.getLangName());
					nl.replaceAll("{twinkle}", data.getTwinkle() ? lang.ToggleStates.on : lang.ToggleStates.off);
					nl.replaceAll("{trail}", data.getTrail() ? lang.ToggleStates.on : lang.ToggleStates.off);
					lore.add(nl.build());
				}
				item.setLore(lore);
				return item;
			}



			public static BaseItem red(int value) {
				BaseItem item = new BaseItem(Material.RED_CONCRETE);
				StringHandler name = new StringHandler(lang.Guis.Gadgets.fireworks.Red);
				name.replaceAll("{value}", value);
				item.setDisplayName(name.build());
				item.addItemFlags(ItemFlag.values());
				return item;
			}

			public static BaseItem green(int value) {
				BaseItem item = new BaseItem(Material.GREEN_CONCRETE);
				StringHandler name = new StringHandler(lang.Guis.Gadgets.fireworks.Green);
				name.replaceAll("{value}", value);
				item.setDisplayName(name.build());
				item.addItemFlags(ItemFlag.values());
				return item;
			}

			public static BaseItem blue(int value) {
				BaseItem item = new BaseItem(Material.BLUE_CONCRETE);
				StringHandler name = new StringHandler(lang.Guis.Gadgets.fireworks.Blue);
				name.replaceAll("{value}", value);
				item.setDisplayName(name.build());
				item.addItemFlags(ItemFlag.values());
				return item;
			}

			public static BaseItem customColor() {
				BaseItem item = new BaseItem(Material.SLIME_BALL);
				item.setDisplayName(lang.Guis.Gadgets.fireworks.CustomColor);
				item.addItemFlags(ItemFlag.values());
				return item;
			}

			public static BaseItem colorDisplay(FColor color) {
				BaseItem item = new BaseItem(Material.LEATHER_CHESTPLATE);
				item.setDisplayName(lang.Guis.Gadgets.fireworks.color);
				item.addItemFlags(ItemFlag.values());
				LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
				meta.setColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
				item.setItemMeta(meta);
				return item;
			}

			public static BaseItem fireworkStar(FColor color) {
				BaseItem item = new BaseItem(Material.FIREWORK_STAR);
				item.setDisplayName(color.getLangName());
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
				item.setDisplayName(lang.Guis.Gadgets.fireworks.FadeColor);
				item.addItemFlags(ItemFlag.values());
				LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
				meta.setColor(Color.fromRGB(color.getR(), color.getG(), color.getB()));
				item.setItemMeta(meta);
				return item;
			}

			public static BaseItem save() {
				BaseItem item = new BaseItem(Material.WRITABLE_BOOK);
				item.setDisplayName(lang.Guis.Gadgets.fireworks.SaveSettings);
				item.addItemFlags(ItemFlag.values());
				return item;
			}

			public static class Shapes {

				public static BaseItem SmallBall() {
					BaseItem item = new BaseItem(Material.MAGMA_CREAM);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.shapes.SmallBall);
					item.addItemFlags(ItemFlag.values());
					return item;
				}

				public static BaseItem LargeBall() {
					BaseItem item = new BaseItem(Material.FIRE_CHARGE);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.shapes.LargeBall);
					item.addItemFlags(ItemFlag.values());
					return item;
				}

				public static BaseItem Star() {
					BaseItem item = new BaseItem(Material.GOLD_NUGGET);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.shapes.Star);
					item.addItemFlags(ItemFlag.values());
					return item;
				}

				public static BaseItem Creeper() {
					BaseItem item = new BaseItem(Material.CREEPER_HEAD);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.shapes.Creeper);
					item.addItemFlags(ItemFlag.values());
					return item;
				}

				public static BaseItem Burst() {
					BaseItem item = new BaseItem(Material.FEATHER);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.shapes.Burst);
					item.addItemFlags(ItemFlag.values());
					return item;
				}
			}
			public static class additives {

				public static BaseItem Trail() {
					BaseItem item = new BaseItem(Material.DIAMOND);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.additives.Trail);
					item.addItemFlags(ItemFlag.values());
					return item;
				}

				public static BaseItem Twinkle() {
					BaseItem item = new BaseItem(Material.GLOWSTONE_DUST);
					item.setDisplayName(lang.Guis.Gadgets.fireworks.additives.Twinkle);
					item.addItemFlags(ItemFlag.values());
					return item;
				}

			}
		}

		public static BaseItem FireworkShooter() {
			BaseItem item = new BaseItem(Material.DIAMOND_HOE);
			item.setDisplayName(lang.Items.gadgets.FireWorkShooter);
			item.addItemFlags(ItemFlag.values());
			item.setLore(lang.Items.gadgets.FireWorkShooter_Lore);
			return item;
		}

		public static BaseItem Jumpper() {
			BaseItem item = new BaseItem(Material.TRIPWIRE_HOOK);
			item.setDisplayName(lang.Items.gadgets.Jumpper);
			item.addItemFlags(ItemFlag.values());
			item.setLore(lang.Items.gadgets.Jumpper_Lore);
	        return item;
	    }
		
		public static BaseItem Slapper() {
			BaseItem item = new BaseItem(Material.PORKCHOP);
			item.setDisplayName(lang.Items.gadgets.Slapper);
			item.addItemFlags(ItemFlag.values());
			item.setLore(lang.Items.gadgets.Slapper_Lore);
	        return item;
	    }
		
		public static BaseItem BoomBox() {
			BaseItem item = new BaseItem(Material.MUSIC_DISC_PIGSTEP);
			item.setDisplayName(lang.Items.gadgets.BoomBox);
			item.addItemFlags(ItemFlag.values());
			item.setLore(lang.Items.gadgets.BoomBox_Lore);
	        return item;
	    }
		
		public static BaseItem SnowCannon() {
	        BaseItem item = new BaseItem(Material.NETHERITE_HOE);
	        item.setDisplayName(lang.Items.gadgets.SnowCannon);
	        item.addItemFlags(ItemFlag.values());
	        item.setLore(lang.Items.gadgets.SnowCannon_Lore);
	        return item;
	    }
		
		public static class Wings {
			
			public static BaseItem Wing() {
		        BaseItem item = new BaseItem(Material.ELYTRA);
		        item.setDisplayName(lang.Items.gadgets.wings.Wings);
		        item.addItemFlags(ItemFlag.values());
		        item.setUnbreakable(true);
		        item.setLore(lang.Items.gadgets.wings.Wings_Lore);
		        return item;
		    }
			
			public static BaseItem Booster() {
		        BaseItem item = new BaseItem(Material.FIREWORK_ROCKET);
		        item.setDisplayName(lang.Items.gadgets.wings.Booster);
		        item.addItemFlags(ItemFlag.values());
		        item.setLore(lang.Items.gadgets.wings.Booster_Lore);
		        return item;
		    }
			
		}
		
	}
	
	public static class JoinItems {

		public static BaseItem particleSelector() {
			BaseItem item = new BaseItem(lang.Items.JoinItems.ParticleSelector_item);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.ParticleSelector);
			item.setLore(lang.Items.JoinItems.ParticleSelector_Lore);
			return item;
		}
		
		public static BaseItem gadgetSelector() {
			BaseItem item = new BaseItem(lang.Items.JoinItems.GadgetSelector_item);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.GadgetSelector);
			item.setLore(lang.Items.JoinItems.GadgetSelector_Lore);
			return item;
		}
		
		public static BaseItem serverSelector(Player p) {
			p.setCompassTarget(p.getWorld().getSpawnLocation());
			
			BaseItem item = new BaseItem(lang.Items.JoinItems.ServerSelector_item);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.ServerSelector);
			item.setLore(lang.Items.JoinItems.ServerSelector_Lore);
			return item;
		}
		
		public static BaseItem Info() {
			BaseItem item = new BaseItem(lang.Items.JoinItems.InfoBook_item);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.InfoBook);
			item.setLore(lang.Items.JoinItems.InfoBook_Lore);
			return item;
		}
		

	}

	
	
	
}
