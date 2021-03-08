package net.natroutter.hubcore.features.gadgets;

import net.natroutter.natlibs.objects.BaseItem;

import java.util.Objects;

public class Gadget {

	String Identifier;
	Integer slot;
	BaseItem GadgetItem;
	BaseItem IconItem;
	
	public Gadget(String Identifier, Integer slot, BaseItem GadgetItem) {
		this.slot = slot;
		this.GadgetItem = GadgetItem;
		this.Identifier = Identifier;
	}
	
	public Gadget(String Identifier, Integer slot, BaseItem GadgetItem, BaseItem IconItem) {
		this.slot = slot;
		this.GadgetItem = GadgetItem;
		this.IconItem = IconItem;
		this.Identifier = Identifier;
	}
	
	public String getIdentifier() {
		return Identifier;
	}
	
	public BaseItem getIcon() {
		if (IconItem != null) {
			return IconItem;
		}
		return GadgetItem;
	}
	
	public BaseItem getItem() {
		return GadgetItem;
	}

	public String getName() {
		return Objects.requireNonNull(GadgetItem.getItemMeta()).getDisplayName();
	}
}
