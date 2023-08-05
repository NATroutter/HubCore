package fi.natroutter.hubcore.features.gadgets;

import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.Utilities;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gadget {

	@Getter
	private String Identifier;

	@Getter
	private Integer slot;

	private BaseItem GadgetItem;
	private BaseItem IconItem;

	@Getter
	private Config need;

	private Config permission;

	public Gadget(String Identifier, Integer slot, BaseItem GadgetItem, Config need, Config permission) {
		this.slot = slot;
		this.GadgetItem = GadgetItem;
		this.Identifier = Identifier;
		this.need = need;
		this.permission = permission;
	}
	
	public Gadget(String Identifier, Integer slot, BaseItem GadgetItem, BaseItem IconItem, Config need, Config permission) {
		this.slot = slot;
		this.GadgetItem = GadgetItem;
		this.IconItem = IconItem;
		this.Identifier = Identifier;
		this.need = need;
		this.permission = permission;
	}

	public String getPermission() {
		return permission.asString();
	}

	public BaseItem getIcon() {
		if (IconItem != null) {
			return IconItem;
		}
		return GadgetItem;
	}

	public BaseItem getIconWithNeed() {
		BaseItem item = BaseItem.from(GadgetItem.clone());

		List<Component> list = new ArrayList<>(item.lore());
		list.addAll(Lang.Items_Gadgets_Unlock_Lore.asComponentList(
				Placeholder.component("need", need.asComponent())
		));
		item.lore(list);

		return item;
	}
	
	public BaseItem getItem() {
		return GadgetItem;
	}

	public Component getName() {
		return Objects.requireNonNull(GadgetItem.displayName());
	}
}
