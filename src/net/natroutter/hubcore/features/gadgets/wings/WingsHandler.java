package net.natroutter.hubcore.features.gadgets.wings;

import net.natroutter.hubcore.Handler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import net.natroutter.hubcore.utilities.Items;

public class WingsHandler {

	private Items items;

	public WingsHandler(Handler handler) {
		this.items = handler.getItems();
	}
	
	public void Active(Player p) {
		PlayerInventory inv = p.getInventory();
		inv.setChestplate(items.gadged_Wing());
	}
	
	public void boost(Player p) {
		
		if (p.isGliding()) {
			 Vector vector = p.getEyeLocation().getDirection().normalize();
	         
	         double maxSpeed = 2.5;
	         double multiply = 1.4;
	         double currentSpeed = p.getVelocity().length();
	         double newSpeed = Math.min(multiply * currentSpeed, maxSpeed);
	         
	         p.setVelocity(vector.normalize().multiply(newSpeed));
		}
		
	}
	
	
	
}
