package net.natroutter.hubcore.features;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.chat.Chat;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Hooks;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;

public class ChatFormater implements Listener {

	
	
	Hooks hooks = HubCore.getHooks();
	Config cfg = HubCore.getCfg();
			
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		BasePlayer p = BasePlayer.from(e.getPlayer());
		
		StringHandler message = new StringHandler(e.getMessage());
		message.replaceAll("%", "%%");
		
		if (p.hasPermission("hubcore.chatcolor")) {
			message.replaceAll("&", "§");
		}

		String prefix = "";
		String suffix = "";
		if (hooks.vault.isHooked()) {
			Chat chat = hooks.vault.getChat();
			prefix = chat.getPlayerPrefix(p).replaceAll("&", "§");
			suffix = chat.getPlayerSuffix(p).replaceAll("&", "§");
		}
		
		StringHandler format = new StringHandler(cfg.ChatFormat);
		format.replaceAll("{Prefix}", prefix);
		format.replaceAll("{DisplayName}", p.getDisplayName());
		format.replaceAll("{Message}", message);
		format.replaceAll("{Suffix}", suffix);
		
		if (hooks.PlaceHolderApi.isHooked()) {
			format = StringHandler.from(format, PlaceholderAPI.setPlaceholders(p, format.build()));
		}
		
		e.setFormat(format.build());
	}
	
	
	
	
}
