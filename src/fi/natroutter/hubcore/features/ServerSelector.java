package fi.natroutter.hubcore.features;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.objects.SelectorItem;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.handlers.guibuilder.Button;
import fi.natroutter.natlibs.handlers.guibuilder.GUI;
import fi.natroutter.natlibs.handlers.guibuilder.GUIFrame;
import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import fi.natroutter.hubcore.utilities.ServerSwitcher;
import org.bukkit.entity.Player;
import fi.natroutter.hubcore.files.Config;

import java.util.List;

public class ServerSelector extends GUIFrame {

	private ServerSwitcher serverSwitcher = HubCore.getServerSwitcher();

	public ServerSelector() {
		super(Config.ServerSelector_Title, Config.ServerSelector_GuiSize.asRows());
	}

	@Override
	protected boolean onShow(Player player, GUI gui, List<Object> args) {

		for (SelectorItem item : Config.ServerSelector_Items.asObjectList(SelectorItem.class)) {
			//if (!item.isValid()) {continue;}

			gui.setButton(new Button(Items.ServerIcon(item.getMaterial(), item.getName()), (e, g)->{
				serverSwitcher.switchServer(e.getPlayer(), item.getServer());
			}), item.getRow(), item.getSlot());

		}

		return true;
	}
}
