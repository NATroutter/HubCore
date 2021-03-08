package net.natroutter.hubcore.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import net.natroutter.natlibs.handlers.gui.GUIWindow.Rows;

public class Config {

	
	public String ChatFormat = "{Prefix} §7{DisplayName} {Suffix}§8§l§ §f{Message}";
	
	public ServerSelector serverSelector = new ServerSelector();
	public static class ServerSelector {
		public String Title = "§4§lServerSelector";
		public List<SelectorItem> SelectorItems = new ArrayList<SelectorItem>() {{
			add(new SelectorItem("Pit", Rows.row2, 1, Material.GRASS_BLOCK, "§c§lPit", null));
			add(new SelectorItem("kingdom", Rows.row2, 2, Material.GOLDEN_SWORD, "§c§lkingdom", null));
			add(new SelectorItem("luckywars", Rows.row2, 4, Material.SPONGE, "§c§lLuckywars", null));
			add(new SelectorItem("creative", Rows.row2, 6, Material.DIAMOND, "§c§lCreative", null));
			add(new SelectorItem("prison", Rows.row2, 7, Material.IRON_BARS, "§c§lPrison", null));
			add(new SelectorItem("hub1", Rows.row4, 3, Material.NETHER_STAR, "§c§lHub 1", null));
			add(new SelectorItem("hub2", Rows.row4, 5, Material.NETHER_STAR, "§c§lHub 2", null));
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
