package net.natroutter.hubcore.features.gadgets.slapper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.objects.BaseItem;

public class SlapperListener implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		BaseItem item = BaseItem.from(e.getItem());
		if (item.matches(Items.Gadgets.Slapper())) {
			e.setCancelled(true);
		}
	}
	
}
