package net.natroutter.hubcore.features;

import net.citizensnpcs.api.CitizensAPI;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.handlers.Hooks;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class PlayerCarry implements Listener {

    private Lang lang = HubCore.getLang();
    private PlayerDataHandler pdh = HubCore.getDataHandler();

    public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
    private static int cooldownTime = 2;
    private Hooks hooks = HubCore.getHooks();


    protected static ArrayList<UUID> bypassCarry = new ArrayList<>();
    public static void bypass(Player p, boolean status) {
        if (status) {
            if (!bypassCarry.contains(p.getUniqueId())) {
                bypassCarry.add(p.getUniqueId());
            }
        } else {
            bypassCarry.remove(p.getUniqueId());
        }
    }


    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Player ride) {

            Player p = e.getPlayer();

            if (bypassCarry.contains(p.getUniqueId())) {
                return;
            }

            if (!Bukkit.getOnlinePlayers().contains((Player)ride)) {
                return;
            }
            if (hooks != null && hooks.getCitizens().isHooked()) {
                if (CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked())) {return;}
            }

            for (Entity pas : p.getPassengers()) {
                if (pas.getUniqueId().equals(ride.getUniqueId())) {
                    return;
                }
            }
            if (!p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                return;
            }

            if(cooldown.containsKey(p.getUniqueId())) {
                long cooldownLeft = ((cooldown.get(p.getUniqueId())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(cooldownLeft>0) {
                    return;
                }
            }
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());

            if (pdh.get(ride.getUniqueId()).getNocarry()) {
                StringHandler msg = new StringHandler(lang.CantBackpack).setPrefix(lang.Prefix);
                msg.replaceAll("{name}", ride.getName());
                msg.send(p);
                return;
            }
            if (pdh.get(p.getUniqueId()).getNocarry()) {
                StringHandler msg = new StringHandler(lang.CantBackpack).setPrefix(lang.Prefix);
                msg.replaceAll("{name}", ride.getName());
                msg.send(p);
                return;
            }

            if (!ride.getPassengers().isEmpty()) {
                while (!ride.getPassengers().isEmpty() && ride.getPassengers().get(0) != p) {
                    ride = (Player) ride.getPassengers().get(0);
                }
            }
            ride.addPassenger(p);

            StringHandler msg1 = new StringHandler(lang.inYourBackpack).setPrefix(lang.Prefix);
            msg1.replaceAll("{name}", p.getName());
            msg1.send((Player)ride);


            StringHandler msg2 = new StringHandler(lang.OnbackPack).setPrefix(lang.Prefix);
            msg2.replaceAll("{name}", ride.getName());
            msg2.send(p);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player ride = e.getPlayer();
        if (e.getAction().name().startsWith("LEFT_CLICK") && !ride.isInsideVehicle() && !e.hasItem()) {
            List<Player> passengers = new ArrayList<>();
            Vector vec = ride.getLocation().clone().getDirection().normalize().multiply(1.8);
            while (!ride.getPassengers().isEmpty()) {
                ride = (Player) ride.getPassengers().get(0);
                passengers.add(ride);
                ride.leaveVehicle();
            }
            Bukkit.getScheduler().runTaskLater(HubCore.getPlugin(), () -> passengers.forEach(player -> player.setVelocity(vec)), 1L);
        }
    }


}
