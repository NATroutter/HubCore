package net.natroutter.hubcore.features.gadgets.wings;

import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.objects.BasePlayer;

public class WingsHandler {

	
	public static void Active(BasePlayer p) {
		PlayerInventory inv = p.getInventory();
		inv.setChestplate(Items.Gadgets.Wings.Wing());
	}
	
	public static void Boost(BasePlayer p) {
		
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
