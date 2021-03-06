package net.natroutter.hubcore.features.gadgets;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.natlibs.handlers.LangHandler.language.LangManager;
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

	public BaseItem getIconWithNeed(LangManager lang) {
		BaseItem item = null;

		if (IconItem != null) {
			item = new BaseItem(IconItem);
		} else {
			item = new BaseItem(GadgetItem);
		}
		for (String line : lang.getList(Translations.Items_Gadgets_Unlock_Lore)) {
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
