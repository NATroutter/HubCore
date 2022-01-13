package net.natroutter.hubcore.features.gadgets;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.StringHandler;

import java.util.Objects;

public class Gadget {

	String Identifier;
	Integer slot;
	BaseItem GadgetItem;
	BaseItem IconItem;
	String need;
	String permission;

	private final Lang lang = HubCore.getLang();

	public Gadget(String Identifier, Integer slot, BaseItem GadgetItem, String need, String permission) {
		this.slot = slot;
		this.GadgetItem = GadgetItem;
		this.Identifier = Identifier;
		this.need = need;
		this.permission = permission;
	}
	
	public Gadget(String Identifier, Integer slot, BaseItem GadgetItem, BaseItem IconItem, String need, String permission) {
		this.slot = slot;
		this.GadgetItem = GadgetItem;
		this.IconItem = IconItem;
		this.Identifier = Identifier;
		this.need = need;
		this.permission = permission;
	}
	
	public String getIdentifier() {
		return Identifier;
	}

	public String getPermission() {
		return permission;
	}

	public String getNeed() {
		return need;
	}

	public BaseItem getIcon() {
		if (IconItem != null) {
			return IconItem;
		}
		return GadgetItem;
	}

	public BaseItem getIconWithNeed() {
		BaseItem item = null;

		if (IconItem != null) {
			item = new BaseItem(IconItem);
		} else {
			item = new BaseItem(GadgetItem);
		}
		for (String line : lang.Items.gadgets.unlock_lore) {
			StringHandler s = new StringHandler(line);
			s.replaceAll("{need}", need);
			item.addLore(s.build());
		}
		return item;
	}
	
	public BaseItem getItem() {
		return GadgetItem;
	}

	public String getName() {
		return Objects.requireNonNull(GadgetItem.getItemMeta()).getDisplayName();
	}
}
