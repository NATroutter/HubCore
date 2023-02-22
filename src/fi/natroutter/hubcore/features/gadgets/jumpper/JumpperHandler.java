package fi.natroutter.hubcore.features.gadgets.jumpper;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class JumpperHandler {

	public JumpperHandler(){}

	public void jump(Player p) {
		
		if (p.isOnGround()) {
			Vector ogVec = p.getLocation().getDirection();
			Vector vecF = new Vector(ogVec.getX(), ogVec.getY() + 0.3, ogVec.getZ()).normalize().multiply(2);
			p.setVelocity(vecF);
		}
		
	}
	
}
