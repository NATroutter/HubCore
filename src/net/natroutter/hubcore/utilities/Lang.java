package net.natroutter.hubcore.utilities;

import java.util.ArrayList;
import java.util.List;

public class Lang {
	
	public String Prefix = "§4§lHubCore §8§l§ ";
	public String NoPerm = "§7You dont have permissions to do that!";
	public String TooManyArguments = "§7Too many command arguments!\nUsage: §c{Usage}";
	public String InvalidArguments = "§7Invalid command arguments!\nUsage: §c{Usage}";
	public String OnlyIngame = "§7This Command can only be executed ingame!";
	public String SpawnSet = "§7Spawn has been set to your location!";
	public String SpawnNotset = "§7Spawn has not been set jet!";
	public String TeleportedToSpawn = "§7You have been teleported to spawn!";
	public String AdminModeToggle = "§7AdminMode: §c{State}";
	public String ToggleFly = "§7Flying: §c{State}";
	public String ToggleFlyOther = "§c{Player}§7's Flying: §c{State}";
	public String SpeedNotDefined = "§7Speed not defined!";
	public String InvalidSpeed = "§7That is not valid speed!";
	public String InvalidSpeedType = "§7Invalid SpeedType Valids: Walking,Flying";
	public String SpeedOutOfRange = "§7Speed must be between §c1-10";
	public String SpeedChanged = "§7Your §c{Type} §7Speed is now §c{Speed}";
	public String SpeedChangedOther = "§7Changed §c{Player}§7's §c{Type} §7Speed to §c{Speed}";
	public String InvalidPlayer = "§7Invalid Player!";
	public String InvalidGamemode = "§7That is not a valid gamemode";
	public String GamemodeChanged = "§7Your gamemode has been changed to §c{Gamemode}";
	public String GamemodeChangedOther = "§c{Player}§7' Gamemode has been changed to §c{Gamemode}";
	public String ItemRenamed = "§7Item has been renamed!";
	public String InvalidItem = "§7You are not holding an item!";
	public String LoreChanged = "§7Items lore has been changed!";
	public String EnderChestOpened ="§7Enderchest opened";
	public String EnderChestOpenedOther ="§c{Player}§7's enderchest opened!";
	public String PlayerInvOpened = "§7Inventory opened!";
	
	public List<String> ShowFormat = new ArrayList<String>() {{
		add("§7Slap other players!");
	}};
	
	public GameModes GameModes = new GameModes();
	public static class GameModes {
		public String Survival = "Survival";
		public String Creative = "Creative";
		public String Adventure = "Adventure";
		public String Spectator = "Spectator";
	}
	
	public SpeedTypes SpeedTypes = new SpeedTypes();
	public static class SpeedTypes {
		public String Walking = "Walking";
		public String Flying = "Flying";
	}
	
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
			
			public Guis.Gadgets.BoomBox boomBox = new BoomBox();
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
			
		}
		
		
	}
	
	public Items Items = new Items();
	public static class Items {
		public Items.Gadgets gadgets = new Items.Gadgets();
		public class Gadgets {
			
			public String Slapper = "§c§lSlapper";
			public List<String> Slapper_Lore = new ArrayList<String>() {{
				add("§7Slap other players!");
			}};
			
			public String BoomBox = "§c§lBoomBox";
			public List<String> BoomBox_Lore = new ArrayList<String>() {{
				add("§7Throw a pary anywhere any time!");
			}};
			
			public String SnowCannon = "§c§lSnowCannon";
			public List<String> SnowCannon_Lore = new ArrayList<String>() {{
				add("§7Shoot snow like nobodies business!");
			}};
			
			public Wings wings = new Wings();
			public class Wings {
				
				public String Wings = "§c§lWings";
				public List<String> Wings_Lore = new ArrayList<String>() {{
					add("§7Keeps you in the air, DONT REMOVE!");
				}};
				
				public String Booster = "§c§lBooster";
				public List<String> Booster_Lore = new ArrayList<String>() {{
					add("§7Boost your flight!");
				}};
			}
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
			
			public String Profile = "§4§lProfile";
			public List<String> Profile_Lore = new ArrayList<String>() {{
				add("§7Your Profile!");
			}};
			
		}
	}
	
	
}
