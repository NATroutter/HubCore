package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import net.citizensnpcs.api.CitizensAPI;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.handlers.Hooks;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class FWSListener implements Listener {

    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private Hooks hooks = HubCore.getHooks();

    @EventHandler
    public void noHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Firework)) {return;}
        if (!(e.getHitEntity() instanceof Player)) {return;}
        if (!(e.getEntity().getShooter() instanceof Player)) {return;}

        if (e.getHitEntity().hasMetadata("NPC")) {return;}

        Player victim = (Player)e.getHitEntity();
        Player shooter = (Player)e.getEntity().getShooter();
        Firework firework = (Firework)e.getEntity();

        PlayerData data = pdh.get(victim.getUniqueId());
        if (data.getNoEffect()) {return;}

        if (firework.getCustomName() == null) {return;}

        if (firework.getCustomName().equalsIgnoreCase("Fireworkshooter-projectile")) {
            shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 100, 1);
            victim.playSound(shooter.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 100, 2);
            victim.setVelocity(new Vector(0, 2, 0));
        }
    }





}
