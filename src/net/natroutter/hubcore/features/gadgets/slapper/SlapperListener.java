package net.natroutter.hubcore.features.gadgets.slapper;

import fi.natroutter.natlibs.objects.BaseItem;
import net.natroutter.hubcore.Handler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import net.natroutter.hubcore.utilities.Items;

public class SlapperListener implements Listener {

	private Items items;
	public SlapperListener(Handler handler) {
		this.items = handler.getItems();
	}

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		BaseItem item = BaseItem.from(e.getItem());
		if (item.isSimilar(items.gadged_Slapper())) {
			e.setCancelled(true);
		}
	}
	
}
