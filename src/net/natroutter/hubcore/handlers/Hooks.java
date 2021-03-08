package net.natroutter.hubcore.handlers;

import net.milkbowl.vault.chat.Chat;
import net.natroutter.natlibs.NATLibs;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused"})
public class Hooks {

	public natcore_Hook natcore;
	public vault_Hook vault;
	public GeneralHook PlaceHolderApi;
	
	public Hooks(JavaPlugin pl) {
		natcore = new natcore_Hook(new Hook("NATLibs"));
		vault = new vault_Hook(pl, new Hook("Vault"));
		PlaceHolderApi = new GeneralHook(new Hook("PlaceholderAPI"));
		
	}
	
	public static class GeneralHook {
		public GeneralHook(Hook hook) {this.hook = hook;}
		private final Hook hook;
		
		public boolean isHooked() { return hook.Hooked; }
		public Plugin getPlugin() { return hook.plugin; }
	}
	
	
	public static class natcore_Hook {
		public natcore_Hook(Hook hook) {this.hook = hook;}
		private final Hook hook;
		
		public boolean isHooked() { return hook.Hooked; }
		public NATLibs getPlugin() { return (NATLibs)hook.plugin; }
	}
	
	public static class vault_Hook {
		private final Hook hook;
		private Chat chat;
		public vault_Hook(JavaPlugin pl, Hook hook) {
			this.hook = hook;
			RegisteredServiceProvider<Chat> chatProvider = pl.getServer().getServicesManager().getRegistration(Chat.class);
			if (chatProvider != null) {
				this.chat = chatProvider.getProvider();
				this.hook.Hooked = true;
			} else {
				this.hook.Hooked = false;
			}
			
		}
		
		public boolean isHooked() { return hook.Hooked; }
		public Chat getChat() { return chat; }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class Hook {

		private Plugin plugin = null;
		private boolean Hooked = false;
		
		public Hook(String name) {
			
			Plugin HookPL = Bukkit.getServer().getPluginManager().getPlugin(name);
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			if (HookPL != null && HookPL.isEnabled()) {
				this.plugin = HookPL;
				this.Hooked = true;
				console.sendMessage("  §a+ §7" + plugin.getName() + " hooked succesfully!");
			} else {
				console.sendMessage("  §4- §7" + name + " hooking failed!");
			}
		}
	}









	
}

















