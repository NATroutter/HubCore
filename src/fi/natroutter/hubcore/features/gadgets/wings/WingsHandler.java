package fi.natroutter.hubcore.features.gadgets.wings;

import fi.natroutter.hubcore.utilities.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class WingsHandler {

	
	public void Active(Player p) {
		PlayerInventory inv = p.getInventory();
		inv.setChestplate(Items.gadged_Wing());
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
