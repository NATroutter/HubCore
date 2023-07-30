package fi.natroutter.hubcore.features.gadgets.FireworkShooter;

import fi.natroutter.betterparkour.BetterParkour;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class FWSListener implements Listener {

    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private Hooks hooks = HubCore.getHooks();

    @EventHandler
    public void noHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Firework firework)) {return;}
        if (!(e.getHitEntity() instanceof Player victim)) {return;}
        if (!(e.getEntity().getShooter() instanceof Player shooter)) {return;}

        if (e.getHitEntity().hasMetadata("NPC")) {return;}

        if (hooks.getBetterParkour().isHooked()) {
            if (BetterParkour.getParkourHandler().inCourse(victim)) {
                e.setCancelled(true);
                return;
            }
        }

        PlayerData data = pdh.get(victim.getUniqueId());
        if (data.getNoEffect()) {return;}

        if (firework.getCustomName() == null) {return;}

        if (firework.getPersistentDataContainer().has(FWSHandler.namespacedKey, PersistentDataType.INTEGER)) {
            shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 100, 1);
            victim.playSound(shooter.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 100, 2);
            victim.setVelocity(new Vector(0, 2, 0));
        }
    }





}
