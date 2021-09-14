package net.natroutter.hubcore.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lang {
	
	public String Prefix = "§4§lHubCore §8§l» ";
	public String NoPerm = "§7You dont have permissions to do that!";
	public String TooManyArguments = "§7Too many command arguments!";
	public String OnlyIngame = "§7This Command can only be executed ingame!";
	public String AdminModeToggle = "§7AdminMode: §c{state}";
	public String AdminModeToggleOther = "§c{name}§7's AdminMode: §c{state}";
	public String OnbackPack = "§7You are now in the backpack of player §c{name}";
	public String inYourBackpack = "§c{name} §7is now in your backpack";
	public String GadgetEffective = "§7Gadgets effect you: §c{state}";
	public String CantBackpack = "§7you cant go to §c{name}§7's backpack";
	public String NoCarryToggle = "§7Player carry: §c{state}";
	public String Unknown = "Unknown";
	public String TargetNotFound = "§7Target not found!";

	public List<String> welcommotd = new ArrayList<>() {{
		add(" ");
		add("§8§l§m━━━━━━━━━━━━§8§l|§4§l Welcome §8§l|§m━━━━━━━━━━━━");
		add(" ");
		add("§7Welcome to server!");
		add(" ");
		add("§8§l§m━━━━━━━━━━━━§8§l|§4§l Welcome §8§l|§m━━━━━━━━━━━━");
		add(" ");
	}};

	public List<String> info = new ArrayList<>() {{
		add(" ");
		add("§8§l§m━━━━━━━━━━━━§8§l|§4§l Info §8§l|§m━━━━━━━━━━━━");
		add(" ");
		add("§7info text here!");
		add(" ");
		add("§8§l§m━━━━━━━━━━━━§8§l|§4§l Info §8§l|§m━━━━━━━━━━━━");
		add(" ");
	}};

	public Gadgets Gadgets = new Gadgets();
	public static class Gadgets {
		
		public BoomBox BoomBox = new BoomBox();
		public static class BoomBox {
			public String NoteNotSelected = "§7You need to select note first, (Tip: ShiftClick)";
		}
		
	}
	
	public ToggleStates ToggleStates = new ToggleStates();
	public static class ToggleStates {
		public String on = "Enabled";
		public String off = "Disabled";
	}
	
	public Guis Guis = new Guis();
	public static class Guis {
		
		public Guis.Gadgets Gadgets = new Gadgets();
		public static class Gadgets {
			
			public String Title = "§4§lGadgets";
			
			public BoomBox boomBox = new BoomBox();
			public static class BoomBox {
				public String Title = "§4§lBoomBox";
				
				public String Bass = "§c§lBass";
				public String SnareDrum = "§c§lSnare Drum";
				public String Hat = "§c§lHat";
				public String BassDrum = "§c§lBass Drum";
				public String Bells = "§c§lBells";
				public String Flute = "§c§lFlute";
				public String Chimes = "§c§lChimes";
				public String Guitar = "§c§lGuitar";
				public String Xylophone = "§c§lXylophone";
				public String Vibraphone = "§c§lVibraphone";
				public String CowBell = "§c§lCow Bell";
				public String Didgeridoo = "§c§lDidgeridoo";
				public String Squarewave = "§c§lSquare wave";
				public String Banjo = "§c§lBanjo";
				public String Electricpiano = "§c§lElectric piano";
				public String Harp = "§c§lHarp";
			}

			public FireWorks fireworks = new FireWorks();
			public static class FireWorks {
				public String title = "§4§lFireworks settings";
				public String colorsTitle = "§4§lFirework color";
				public String CustomColorTitle = "§4§lCustom firework color";

				public String color = "§cColor";
				public String FadeColor = "§cFade Color";

				public String CurrentColor = "§cCurrent Color";
				public String Back = "§cBack";
				public String CustomColor = "§cCustom color";

				public String ColorUp = "§cColor up";
				public String ColorDown = "§cColor down";

				public String Red = "§c§l{value}";
				public String Green = "§a§l{value}";
				public String Blue = "§9§l{value}";

				public String CurrentSetting = "§cCurrent settings";
				public List<String> CurrentSetting_lore = new ArrayList<>() {{
					add("§7Shape: §c{shape}");
					add("§7Color: §c{color}");
					add("§7Fade Color: §c{fadecolor}");
					add("§7Twinkle: §c{twinkle}");
					add("§7Trail: §c{trail}");
				}};

				public String SaveSettings = "§cSave Settings";

				public colors colors = new colors();
				public static class colors {
					public String DarkRed = "§4Dark red";
					public String Red = "§cRed";
					public String Gold = "§6Gold";
					public String Yellow = "§2Yellow";
					public String DarkGreen = "§2Dark Green";
					public String Green = "§aGreen";
					public String Aqua = "§bAqua";
					public String DarkAqua = "§3Dark Aqua";
					public String DarkBlue = "§1Dark Blue";
					public String Blue = "§9Blue";
					public String LightPurple = "§dLight Purple";
					public String DarkPurple = "§5Dark Purple";
					public String White = "§fWhite";
					public String Gray = "§7Gray";
					public String DarkGray = "§8Dark Gray";
					public String Black = "§0Black";
					public String Custom = "§8(§c{r}§7, §a{g}§7, §9{b}§8)";
				}

				public additives additives = new additives();
				public static class additives {
					public String Trail = "§cToggle trail";
					public String Twinkle = "§cToggle twinkle";
				}

				public shapes shapes = new shapes();
				public static class shapes {
					public String SmallBall = "§cSmallBall";
					public String LargeBall = "§cLargeBall";
					public String Star = "§cStar";
					public String Creeper = "§cCreeper";
					public String Burst = "§cBurst";
				}
			}
		}

		public Particles particles = new Particles();
		public static class Particles {
			public String title = "§4§lParticle Selector";

			public modes particles = new modes();
			public static class modes {
				public String Tail = "Tail";
				public String Cloud = "Cloud";
			}

			public String ParticleMode = "§cParticleMode";
			ArrayList<String> ParticleMode_lore = new ArrayList<>(){{
				add("§7Click here to change particle mode");
				add(" ");
				add("§7Current mode: §c{status}");
			}};

			public String disabled = "§cDisabled";
			ArrayList<String> disabled_lore = new ArrayList<>(){{
				add("§7Click here to disable current particle effects");
			}};

			public ArrayList<String> effect_lore = new ArrayList<>(){{
				add("§7Need to unlock: §c{need}");
			}};

			public String witch = "§cWitch";
			public String totem = "§cTotem";
			public String ink = "§cInk";
			public String soulflame = "§cSoulflame";
			public String cloud = "§cCloud";
			public String soul = "§cSoul";
			public String notes = "§cNotes";
			public String conduit = "§cConduit";
			public String lava = "§cLava";
			public String hearts = "§cHearts";
			public String happy = "§cHappy";
			public String angry = "§cAngry";
			public String enchanthit = "§cEnchanted hit";
			public String endrod = "§cEndrod";
			public String enchanting = "§cEnchanting";
			public String rainbowdust = "§cRainbowdust";
			public String dragonbreath = "§cDragonsbreath";
			public String critical = "§cCritical";
			public String damage = "§cDamage";
			public String smoke = "§cSmoke";
			public String sneeze = "§cSneeze";


		}
	}

	public Items Items = new Items();
	public static class Items {
		public Items.Gadgets gadgets = new Items.Gadgets();
		public class Gadgets {

			public List<String> unlock_lore = new ArrayList<>() {{
				add(" ");
				add("§7Need to unlock: §c{need}");
			}};

			public String FireWorkShooter = "§c§lFireworkShooter";
			public List<String> FireWorkShooter_Lore = new ArrayList<>() {{
				add("§7Shoots fireworks");
			}};

			public String Slapper = "§c§lSlapper";
			public List<String> Slapper_Lore = new ArrayList<>() {{
				add("§7Slap other players!");
			}};
			
			public String BoomBox = "§c§lBoomBox";
			public List<String> BoomBox_Lore = new ArrayList<>() {{
				add("§7Throw a pary anywhere any time!");
			}};
			
			public String SnowCannon = "§c§lSnowCannon";
			public List<String> SnowCannon_Lore = new ArrayList<>() {{
				add("§7Shoot snow like nobodies business!");
			}};
			
			public Wings wings = new Wings();
			public class Wings {
				
				public String Wings = "§c§lWings";
				public List<String> Wings_Lore = new ArrayList<>() {{
					add("§7Keeps you in the air, DONT REMOVE!");
				}};
				
				public String Booster = "§c§lBooster";
				public List<String> Booster_Lore = new ArrayList<>() {{
					add("§7Boost your flight!");
				}};
			}

			public String Jumpper = "§c§lJumpper";
			public List<String> Jumpper_Lore = new ArrayList<>() {{
				add("§7Jump like a rabbit");
			}};
		}
		
		public JoinItems JoinItems = new JoinItems();
		public static class JoinItems {
			
			public String ParticleSelector = "§4§lParticleSelector";
			public List<String> ParticleSelector_Lore = new ArrayList<String>() {{
				add("§7Select your fansy particles!");
			}};
			
			public String GadgetSelector = "§4§lGadgetSelector";
			public List<String> GadgetSelector_Lore = new ArrayList<String>() {{
				add("§7Select your weird and wacky Gadget");
			}};
			
			public String ServerSelector = "§4§lServerSelector";
			public List<String> ServerSelector_Lore = new ArrayList<String>() {{
				add("§7Select what you want to play!");
			}};
			
			public String InfoBook = "§4§lInfoBook";
			public List<String> InfoBook_Lore = new ArrayList<String>() {{
				add("§7Server information!");
			}};
			
		}
	}
	
	
}
