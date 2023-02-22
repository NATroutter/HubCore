package fi.natroutter.hubcore.files;

import java.util.ArrayList;
import java.util.List;

import fi.natroutter.natlibs.handlers.gui.GUIRow;
import org.bukkit.Material;

public class Config {


	public String language = "en_us";
	public boolean DisableRedstone = true;
	public boolean DisablePhysics = true;

	public Material ParticleSelector_item = Material.BLAZE_POWDER;
	public Material GadgetSelector_item = Material.CHEST;
	public Material ServerSelector_item = Material.COMPASS;
	public Material InfoBook_item = Material.BOOK;

	public gadgets gadgets = new gadgets();
	public class gadgets {

		public boombox boombox = new boombox();
		public class boombox {
			public String permission = "hubcore.gadgets.boombox";
			public String need = "Premium -VIP";
		}

		public wings wings = new wings();
		public class wings {
			public String permission = "hubcore.gadgets.wings";
			public String need = "Premium -VIP";
		}

		public snowcannon snowcannon = new snowcannon();
		public class snowcannon {
			public String permission = "hubcore.gadgets.snowcannon";
			public String need = "Premium -VIP";
		}

		public slapper slapper = new slapper();
		public class slapper {
			public String permission = "hubcore.gadgets.slapper";
			public String need = "Premium -VIP";
		}

		public jumpper jumpper = new jumpper();
		public class jumpper {
			public String permission = "hubcore.gadgets.jumpper";
			public String need = "Premium -VIP";
		}

		public fireworkshooter fireworkshooter = new fireworkshooter();
		public class fireworkshooter {
			public String permission = "hubcore.gadgets.fireworkshooter";
			public String need = "Premium -VIP";
		}

	}

	public particles particles = new particles();
	public class particles {

		public witch witch = new witch();
		public class witch {
			public String permission = "hubcore.particle.witch";
			public String need = "Premium -VIP";
		}

		public totem totem = new totem();
		public class totem {
			public String permission = "hubcore.particle.totem";
			public String need = "Premium -VIP";
		}

		public ink ink = new ink();
		public class ink {
			public String permission = "hubcore.particle.ink";
			public String need = "Premium -VIP";
		}

		public soulflame soulflame = new soulflame();
		public class soulflame {
			public String permission = "hubcore.particle.soulflame";
			public String need = "Premium -VIP";
		}

		public cloud cloud = new cloud();
		public class cloud {
			public String permission = "hubcore.particle.cloud";
			public String need = "Premium -VIP";
		}

		public soul soul = new soul();
		public class soul {
			public String permission = "hubcore.particle.soul";
			public String need = "Premium -VIP";
		}

		public notes notes = new notes();
		public class notes {
			public String permission = "hubcore.particle.notes";
			public String need = "Premium -VIP";
		}

		public conduit conduit = new conduit();
		public class conduit {
			public String permission = "hubcore.particle.conduit";
			public String need = "Premium -VIP";
		}

		public lava lava = new lava();
		public class lava {
			public String permission = "hubcore.particle.lava";
			public String need = "Premium -VIP";
		}

		public hearts hearts = new hearts();
		public class hearts {
			public String permission = "hubcore.particle.hearts";
			public String need = "Premium -VIP";
		}

		public happy happy = new happy();
		public class happy {
			public String permission = "hubcore.particle.happy";
			public String need = "Premium -VIP";
		}

		public angry angry = new angry();
		public class angry {
			public String permission = "hubcore.particle.angry";
			public String need = "Premium -VIP";
		}

		public enchanthit enchanthit = new enchanthit();
		public class enchanthit {
			public String permission = "hubcore.particle.enchanthit";
			public String need = "Premium -VIP";
		}

		public endrod endrod = new endrod();
		public class endrod {
			public String permission = "hubcore.particle.endrod";
			public String need = "Premium -VIP";
		}

		public enchanting enchanting = new enchanting();
		public class enchanting {
			public String permission = "hubcore.particle.enchanting";
			public String need = "Premium -VIP";
		}

		public rainbowdust rainbowdust = new rainbowdust();
		public class rainbowdust {
			public String permission = "hubcore.particle.rainbowdust";
			public String need = "Premium -VIP";
		}

		public dragonbreath dragonbreath = new dragonbreath();
		public class dragonbreath {
			public String permission = "hubcore.particle.dragonbreath";
			public String need = "Premium -VIP";
		}

		public critical critical = new critical();
		public class critical {
			public String permission = "hubcore.particle.critical";
			public String need = "Premium -VIP";
		}

		public damage damage = new damage();
		public class damage {
			public String permission = "hubcore.particle.damage";
			public String need = "Premium -VIP";
		}

		public smoke smoke = new smoke();
		public class smoke {
			public String permission = "hubcore.particle.smoke";
			public String need = "Premium -VIP";
		}

		public sneeze sneeze = new sneeze();
		public class sneeze {
			public String permission = "hubcore.particle.sneeze";
			public String need = "Premium -VIP";
		}
	}

	public ServerSelector serverSelector = new ServerSelector();
	public class ServerSelector {
		public String Title = "§4§lServerSelector";
		public GUIRow GuiSize = GUIRow.row6;
		public List<SelectorItem> SelectorItems = new ArrayList<>() {{
			add(new SelectorItem("Purgatory", GUIRow.row2, 1, Material.NETHERRACK, "§c§lPurgatory", null));
			add(new SelectorItem("Survival", GUIRow.row2, 2, Material.COBBLESTONE, "§c§lSurvival", null));
			add(new SelectorItem("SkyBlcok", GUIRow.row2, 3, Material.GRASS_BLOCK, "§c§lSkyBlock", null));

			add(new SelectorItem("DTM", GUIRow.row2, 5, Material.WOODEN_SWORD, "§c§lDTM", null));
			add(new SelectorItem("KitPvP", GUIRow.row2, 6, Material.STONE_SWORD, "§c§lKitPvP", null));
			add(new SelectorItem("WaterBattle", GUIRow.row2, 7, Material.DIAMOND_SWORD, "§c§lWaterBattle", null));

			add(new SelectorItem("creative", GUIRow.row3, 2, Material.DIAMOND_BLOCK, "§c§lCreative", null));
			add(new SelectorItem("zombiesurvival", GUIRow.row3, 4, Material.ZOMBIE_HEAD, "§c§lZombieSurvival", null));
			add(new SelectorItem("kingdoms", GUIRow.row3, 6, Material.GOLDEN_HELMET, "§c§lKingdoms", null));

			add(new SelectorItem("luckywars", GUIRow.row4, 1, Material.SPONGE, "§c§lLuckywars", null));
			add(new SelectorItem("prison", GUIRow.row4, 7, Material.IRON_BARS, "§c§lPrison", null));

			add(new SelectorItem("Hub", GUIRow.row4, 3, Material.NETHER_STAR, "§c§lHub 1", null));
			add(new SelectorItem("Hub2", GUIRow.row4, 5, Material.NETHER_STAR, "§c§lHub 2", null));
		}};
	}
	
	
	public class SelectorItem {
		
		public String Server;
		public GUIRow Row;
		public Integer Slot;
		public Material Material;
		public String Name;
		public List<String> Lore;
		
		public SelectorItem(String Server, GUIRow Row, Integer Slot, Material Material, String Name, List<String> Lore) {
			this.Server = Server;
			this.Row = Row;
			this.Slot = Slot;
			this.Material = Material;
			this.Name = Name;
			this.Lore = Lore;
		}
	}
	
	
	
	
	
	
	
}
