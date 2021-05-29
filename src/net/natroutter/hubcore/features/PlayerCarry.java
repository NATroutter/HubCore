package net.natroutter.hubcore.features;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;


public class PlayerCarry implements Listener {

    private Lang lang = HubCore.getLang();

    public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
    private static int cooldownTime = 2;

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            Player p = e.getPlayer();
            Player target = (Player)e.getRightClicked();

            if(cooldown.containsKey(p.getUniqueId())) {
                long cooldownLeft = ((cooldown.get(p.getUniqueId())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(cooldownLeft>0) {
                    return;
                }
            }
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());

            if (p.getPassengers().contains(target)) {return;}

            if (target.getPassengers().size() > 0) {
                Entity pe = target.getPassengers().get(target.getPassengers().size() -1);
                if (!p.getUniqueId().equals(pe.getUniqueId())) {
                    pe.addPassenger(p);

                    if (pe instanceof Player) {
                        StringHandler msg1 = new StringHandler(lang.inYourBackpack).setPrefix(lang.Prefix);
                        msg1.replaceAll("{name}", p.getName());
                        msg1.send((Player)pe);
                    }

                    StringHandler msg2 = new StringHandler(lang.OnbackPack).setPrefix(lang.Prefix);
                    msg2.replaceAll("{name}", pe.getName());
                    msg2.send(p);

                }
            } else {
                target.addPassenger(p);

                StringHandler msg1 = new StringHandler(lang.inYourBackpack).setPrefix(lang.Prefix);
                msg1.replaceAll("{name}", p.getName());
                msg1.send(target);

                StringHandler msg2 = new StringHandler(lang.OnbackPack).setPrefix(lang.Prefix);
                msg2.replaceAll("{name}", target.getName());
                msg2.send(p);

            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().name().startsWith("LEFT_CLICK")) {
            if (p.getPassengers().size() > 0) {
                throwPassangers(p);
            }
        }
    }

    public void throwPassangers(Entity thrower) {
        thrower.eject();
        for (Entity e : thrower.getPassengers()) {
            e.eject();
            if (e.getPassengers().size() > 0) {
                throwPassangers(e);
            }
        }
    }

}
