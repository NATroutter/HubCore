package fi.natroutter.hubcore.features.gadgets.boombox;

import fi.natroutter.natlibs.objects.BaseItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemFlag;

public class Note {

	private int slot;
    private Sound sound;
    private String name;

    public Note(Integer slot, Sound sound, String name) {
        this.slot = slot;
        this.sound = sound;
        this.name = name;
    }

    public String getNameStripped() {
        return ChatColor.stripColor(name);
    }
    
    public String getName() {
		return name;
	}

    public int getSlot() {
        return slot;
    }

    public Sound getSound() {
        return sound;
    }

    public BaseItem getItem() {
    	BaseItem item = new BaseItem(Material.NOTE_BLOCK);
        item.setDisplayName(this.name);
        item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

	
}
