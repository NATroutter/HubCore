package fi.natroutter.hubcore.features.gadgets.slapper;

import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.objects.BaseItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class SlapperListener implements Listener {


	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		BaseItem item = BaseItem.from(e.getItem());
		if (item.isSimilar(Items.gadged_Slapper())) {
			e.setCancelled(true);
		}
	}
	
}
