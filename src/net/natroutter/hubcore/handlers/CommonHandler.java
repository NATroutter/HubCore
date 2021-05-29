package net.natroutter.hubcore.handlers;

import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class CommonHandler {

	private final JavaPlugin pl;
	
	public CommonHandler(JavaPlugin pl) {
		this.pl = pl;
		Initialize();
	}
	
	private void Initialize() {
		setWorldSettings();
	}
	
	
	public void setWorldSettings() {
		
		for (World world : pl.getServer().getWorlds()) {
			world.setDifficulty(Difficulty.PEACEFUL);
			world.setGameRuleValue("announceAdvancements", "false");
			world.setGameRuleValue("doFireTick", "false");
			world.setGameRuleValue("doDaylightCycle", "false");
			world.setGameRuleValue("doWeatherCycle", "false");
			world.setGameRuleValue("disableRaids", "true");
			world.setGameRuleValue("doImmediateRespawn", "true");
			world.setGameRuleValue("mobGriefing", "false");
			world.setGameRuleValue("doMobSpawning", "false");
			world.setGameRuleValue("doTraderSpawning", "false");
			world.setGameRuleValue("drowningDamage", "false");
			world.setGameRuleValue("fireDamage", "false");
			world.setGameRuleValue("fallDamage", "false");
			world.setGameRuleValue("spawnRadius", "0");
			world.setGameRuleValue("showDeathMessages", "false");
			world.setGameRuleValue("keepInventory", "true");
			world.setGameRuleValue("doInsomnia", "false");
			world.setGameRuleValue("doMobLoot", "false");
			world.setGameRuleValue("doPatrolSpawning", "false");

		}
		
	}
	
	
}
