package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import net.natroutter.betterparkour.BetterParkour;
import net.natroutter.betterparkour.ParkourAPI;
import net.natroutter.hubcore.Handler;
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

    private PlayerDataHandler pdh ;
    private Hooks hooks;

    public FWSListener(Handler handler) {
        this.pdh = handler.getDataHandler();
        this.hooks = handler.getHooks();
    }

    @EventHandler
    public void noHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Firework firework)) {return;}
        if (!(e.getHitEntity() instanceof Player victim)) {return;}
        if (!(e.getEntity().getShooter() instanceof Player shooter)) {return;}

        if (e.getHitEntity().hasMetadata("NPC")) {return;}

        if (hooks.getBetterParkour().isHooked()) {
            ParkourAPI api = BetterParkour.getAPI();
            if (api.getParkourHandler().inCourse(victim)) {
                e.setCancelled(true);
                return;
            }
        }

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
