package net.natroutter.hubcore.features.gadgets.jumpper;

import org.bukkit.util.Vector;

import net.natroutter.natlibs.objects.BasePlayer;

public class JumpperHandler {

	public static void Jump(BasePlayer p) {
		
		if (p.isOnGround()) {
			Vector ogVec = p.getLocation().getDirection();
			Vector vecF = new Vector(ogVec.getX(), ogVec.getY() + 0.3, ogVec.getZ()).normalize().multiply(2);
			p.setVelocity(vecF);
		}
		
	}
	
}
