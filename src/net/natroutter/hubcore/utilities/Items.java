package net.natroutter.hubcore.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.SkullCreator;

public class Items {

	private static final Lang lang = HubCore.getLang();
	
	public static BaseItem ServerIcon(Material mat, String name) {
		BaseItem item = new BaseItem(mat);
		item.addItemFlags(ItemFlag.values());
		item.setDisplayName(name);
		return item;
	}
	
	public static class Gadgets {
		
		public static BaseItem Jumpper() {
			BaseItem item = new BaseItem(Material.TRIPWIRE_HOOK);
			item.setDisplayName("§c-- §l§4Jumpper §c--");
			item.addItemFlags(ItemFlag.values());
			item.setLore("§5-----------------------------------------");
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
			BaseItem item = new BaseItem(Material.BLAZE_POWDER);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.ParticleSelector);
			item.setLore(lang.Items.JoinItems.ParticleSelector_Lore);
			return item;
		}
		
		public static BaseItem gadgetSelector() {
			BaseItem item = new BaseItem(Material.CHEST);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.GadgetSelector);
			item.setLore(lang.Items.JoinItems.GadgetSelector_Lore);
			return item;
		}
		
		public static BaseItem serverSelector(BasePlayer p) {
			p.setCompassTarget(p.getWorld().getSpawnLocation());
			
			BaseItem item = new BaseItem(Material.COMPASS);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.ServerSelector);
			item.setLore(lang.Items.JoinItems.ServerSelector_Lore);
			return item;
		}
		
		public static BaseItem InfoBook() {
			BaseItem item = new BaseItem(Material.KNOWLEDGE_BOOK);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.InfoBook);
			item.setLore(lang.Items.JoinItems.InfoBook_Lore);
			return item;
		}
		
		public static BaseItem Profile(BasePlayer p) {
			SkullCreator skull = NATLibs.getSkullCreator();
			BaseItem item = skull.Create(p);
			item.addItemFlags(ItemFlag.values());
			item.setDisplayName(lang.Items.JoinItems.Profile);
			item.setLore(lang.Items.JoinItems.Profile_Lore);
			return item;
		}
	}

	
	
	
}
