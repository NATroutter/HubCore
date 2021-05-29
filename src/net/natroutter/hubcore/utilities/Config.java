package net.natroutter.hubcore.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import net.natroutter.natlibs.handlers.gui.GUIWindow.Rows;

public class Config {


	public boolean DisableRedstone = true;

	public gadgets gadgets = new gadgets();
	public static class gadgets {

		public boombox boombox = new boombox();
		public static class boombox {
			public String permission = "hubcore.gadgets.boombox";
			public String need = "Premium -VIP";
		}

		public wings wings = new wings();
		public static class wings {
			public String permission = "hubcore.gadgets.wings";
			public String need = "Premium -VIP";
		}

		public snowcannon snowcannon = new snowcannon();
		public static class snowcannon {
			public String permission = "hubcore.gadgets.snowcannon";
			public String need = "Premium -VIP";
		}

		public slapper slapper = new slapper();
		public static class slapper {
			public String permission = "hubcore.gadgets.slapper";
			public String need = "Premium -VIP";
		}

		public jumpper jumpper = new jumpper();
		public static class jumpper {
			public String permission = "hubcore.gadgets.jumpper";
			public String need = "Premium -VIP";
		}

		public fireworkshooter fireworkshooter = new fireworkshooter();
		public static class fireworkshooter {
			public String permission = "hubcore.gadgets.fireworkshooter";
			public String need = "Premium -VIP";
		}

	}

	public particles particles = new particles();
	public static class particles {

		public witch witch = new witch();
		public static class witch {
			public String permission = "hubcore.particle.witch";
			public String need = "Premium -VIP";
		}

		public totem totem = new totem();
		public static class totem {
			public String permission = "hubcore.particle.totem";
			public String need = "Premium -VIP";
		}

		public ink ink = new ink();
		public static class ink {
			public String permission = "hubcore.particle.ink";
			public String need = "Premium -VIP";
		}

		public soulflame soulflame = new soulflame();
		public static class soulflame {
			public String permission = "hubcore.particle.soulflame";
			public String need = "Premium -VIP";
		}

		public cloud cloud = new cloud();
		public static class cloud {
			public String permission = "hubcore.particle.cloud";
			public String need = "Premium -VIP";
		}

		public soul soul = new soul();
		public static class soul {
			public String permission = "hubcore.particle.soul";
			public String need = "Premium -VIP";
		}

		public notes notes = new notes();
		public static class notes {
			public String permission = "hubcore.particle.notes";
			public String need = "Premium -VIP";
		}

		public conduit conduit = new conduit();
		public static class conduit {
			public String permission = "hubcore.particle.conduit";
			public String need = "Premium -VIP";
		}

		public lava lava = new lava();
		public static class lava {
			public String permission = "hubcore.particle.lava";
			public String need = "Premium -VIP";
		}

		public hearts hearts = new hearts();
		public static class hearts {
			public String permission = "hubcore.particle.hearts";
			public String need = "Premium -VIP";
		}

		public happy happy = new happy();
		public static class happy {
			public String permission = "hubcore.particle.happy";
			public String need = "Premium -VIP";
		}

		public angry angry = new angry();
		public static class angry {
			public String permission = "hubcore.particle.angry";
			public String need = "Premium -VIP";
		}

		public enchanthit enchanthit = new enchanthit();
		public static class enchanthit {
			public String permission = "hubcore.particle.enchanthit";
			public String need = "Premium -VIP";
		}

		public endrod endrod = new endrod();
		public static class endrod {
			public String permission = "hubcore.particle.endrod";
			public String need = "Premium -VIP";
		}

		public enchanting enchanting = new enchanting();
		public static class enchanting {
			public String permission = "hubcore.particle.enchanting";
			public String need = "Premium -VIP";
		}

		public rainbowdust rainbowdust = new rainbowdust();
		public static class rainbowdust {
			public String permission = "hubcore.particle.rainbowdust";
			public String need = "Premium -VIP";
		}

		public dragonbreath dragonbreath = new dragonbreath();
		public static class dragonbreath {
			public String permission = "hubcore.particle.dragonbreath";
			public String need = "Premium -VIP";
		}

		public critical critical = new critical();
		public static class critical {
			public String permission = "hubcore.particle.critical";
			public String need = "Premium -VIP";
		}

		public damage damage = new damage();
		public static class damage {
			public String permission = "hubcore.particle.damage";
			public String need = "Premium -VIP";
		}

		public smoke smoke = new smoke();
		public static class smoke {
			public String permission = "hubcore.particle.smoke";
			public String need = "Premium -VIP";
		}

		public sneeze sneeze = new sneeze();
		public static class sneeze {
			public String permission = "hubcore.particle.sneeze";
			public String need = "Premium -VIP";
		}
	}

	public ServerSelector serverSelector = new ServerSelector();
	public static class ServerSelector {
		public String Title = "§4§lServerSelector";
		public List<SelectorItem> SelectorItems = new ArrayList<>() {{
			add(new SelectorItem("Purgatory", Rows.row2, 1, Material.NETHERRACK, "§c§lPurgatory", null));
			add(new SelectorItem("Survival", Rows.row2, 2, Material.COBBLESTONE, "§c§lSurvival", null));
			add(new SelectorItem("SkyBlcok", Rows.row2, 3, Material.GRASS_BLOCK, "§c§lSkyBlock", null));

			add(new SelectorItem("DTM", Rows.row2, 5, Material.WOODEN_SWORD, "§c§lDTM", null));
			add(new SelectorItem("KitPvP", Rows.row2, 6, Material.STONE_SWORD, "§c§lKitPvP", null));
			add(new SelectorItem("WaterBattle", Rows.row2, 7, Material.DIAMOND_SWORD, "§c§lWaterBattle", null));

			add(new SelectorItem("creative", Rows.row3, 2, Material.DIAMOND_BLOCK, "§c§lCreative", null));
			add(new SelectorItem("zombiesurvival", Rows.row3, 4, Material.ZOMBIE_HEAD, "§c§lZombieSurvival", null));
			add(new SelectorItem("kingdoms", Rows.row3, 6, Material.GOLDEN_HELMET, "§c§lKingdoms", null));

			add(new SelectorItem("luckywars", Rows.row4, 1, Material.SPONGE, "§c§lLuckywars", null));
			add(new SelectorItem("prison", Rows.row4, 7, Material.IRON_BARS, "§c§lPrison", null));

			add(new SelectorItem("Hub", Rows.row4, 3, Material.NETHER_STAR, "§c§lHub 1", null));
			add(new SelectorItem("Hub2", Rows.row4, 5, Material.NETHER_STAR, "§c§lHub 2", null));
		}};
	}
	
	
	public static class SelectorItem {
		
		public String Server;
		public Rows Row;
		public Integer Slot;
		public Material Material;
		public String Name;
		public List<String> Lore;
		
		public SelectorItem(String Server, Rows Row, Integer Slot, Material Material, String Name, List<String> Lore) {
			this.Server = Server;
			this.Row = Row;
			this.Slot = Slot;
			this.Material = Material;
			this.Name = Name;
			this.Lore = Lore;
		}
	}
	
	
	
	
	
	
	
}
