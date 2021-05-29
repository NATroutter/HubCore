package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class FWSListener implements Listener {


    @EventHandler
    public void noHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Firework)) {return;}
        if (!(e.getHitEntity() instanceof Player)) {return;}
        if (!(e.getEntity().getShooter() instanceof Player)) {return;}

        Player victim = (Player)e.getHitEntity();
        Player shooter = (Player)e.getEntity().getShooter();
        Firework firework = (Firework)e.getEntity();

        if (firework.getCustomName() == null) {return;}

        if (firework.getCustomName().equalsIgnoreCase("Fireworkshooter-projectile")) {
            shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 100, 1);
            victim.playSound(shooter.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 100, 2);
            victim.setVelocity(new Vector(0, 2, 0));
        }
    }





}
