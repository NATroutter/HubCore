package net.natroutter.hubcore.commands;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.hubcore.utilities.Utils.CET;
import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Database;
import net.natroutter.natlibs.utilities.Utilities;
import org.jetbrains.annotations.NotNull;

public class Spawn extends Command {

	public Spawn() {
		super("");
	}

	Lang lang = HubCore.getLang();
	Database database = HubCore.getDatabase();
	Utilities utils = NATLibs.getUtilities();
	
	private ParticleSettings PartSet(BasePlayer p) {
		return new ParticleSettings(Particle.DRAGON_BREATH, p.getLocation(),
				100, 0.5, 1, 0.5, 0
		);
	}
	 
	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(lang.OnlyIngame);
			return false;
		}
		
		BasePlayer p = BasePlayer.from(sender);
		if (args.length == 0) {
			
			Location loc = database.getLocation("General", "Spawn");
			if (loc != null) {
				utils.spawnParticleInRadius(PartSet(p), 10);
				
				p.teleport(loc);
				p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 50);
				p.sendMessage(lang.Prefix + lang.TeleportedToSpawn);
			} else {
				p.sendMessage(lang.Prefix + lang.SpawnNotset);
			}
			
		} else {
			Utils.CommandError(p, CET.TooMany, "/spawn");
		}
		
		
		return false;
	}

	
	
}
