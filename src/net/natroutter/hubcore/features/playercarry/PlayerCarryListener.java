package net.natroutter.hubcore.features.playercarry;

import net.natroutter.betterparkour.BetterParkour;
import net.natroutter.betterparkour.ParkourAPI;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.handlers.Hooks;
import net.natroutter.natlibs.handlers.LangHandler.language.LangManager;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class PlayerCarryListener implements Listener {

    private Handler handler;
    private Hooks hooks;
    private LangManager lang;
    private PlayerDataHandler pdh;
    private PlayerCarryHandler playerCarryHandler;

    public PlayerCarryListener(Handler handler) {
        this.handler = handler;
        this.hooks = handler.getHooks();
        this.lang = handler.getLang();
        this.pdh = handler.getDataHandler();
        this.playerCarryHandler = handler.getPlayerCarryHandler();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        playerCarryHandler.cooldown.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Player ride) {

            Player p = e.getPlayer();

            if (playerCarryHandler.bypassCarry.contains(p.getUniqueId())) {
                return;
            }

            if (!Bukkit.getOnlinePlayers().contains(ride)) {
                return;
            }
            if (e.getRightClicked().hasMetadata("NPC")) {return;}

            if (hooks.getBetterParkour().isHooked()) {
                ParkourAPI api = BetterParkour.getAPI();
                if (api.getParkourHandler().inCourse(p)) {
                    e.setCancelled(true);
                    return;
                }
            }

            for (Entity pas : p.getPassengers()) {
                if (pas.getUniqueId().equals(ride.getUniqueId())) {
                    return;
                }
            }
            if (!p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                return;
            }

            if(playerCarryHandler.cooldown.containsKey(p.getUniqueId())) {
                long cooldownLeft = ((playerCarryHandler.cooldown.get(p.getUniqueId())/1000)+playerCarryHandler.cooldownTime) - (System.currentTimeMillis()/1000);
                if(cooldownLeft>0) {
                    return;
                }
            }
            playerCarryHandler.cooldown.put(p.getUniqueId(), System.currentTimeMillis());

            if (pdh.get(ride.getUniqueId()).getNocarry()) {
                StringHandler msg = new StringHandler(lang.get(Translations.CantBackpack)).setPrefix(lang.get(Translations.Prefix));
                msg.replaceAll("{name}", ride.getName());
                msg.send(p);
                return;
            }
            if (pdh.get(p.getUniqueId()).getNocarry()) {
                StringHandler msg = new StringHandler(lang.get(Translations.CantBackpack)).setPrefix(lang.get(Translations.Prefix));
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

            StringHandler msg1 = new StringHandler(lang.get(Translations.inYourBackpack)).setPrefix(lang.get(Translations.Prefix));
            msg1.replaceAll("{name}", p.getName());
            msg1.send(ride);


            StringHandler msg2 = new StringHandler(lang.get(Translations.OnbackPack)).setPrefix(lang.get(Translations.Prefix));
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
            Bukkit.getScheduler().runTaskLater(handler.getInstance(), () -> passengers.forEach(player -> player.setVelocity(vec)), 1L);
        }
    }


}
