package fi.natroutter.hubcore.features.playercarry;

import fi.natroutter.betterparkour.BetterParkour;
import fi.natroutter.betterparkour.ParkourAPI;
import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.files.Translations;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.utilities.StringHandler;
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

import java.util.ArrayList;
import java.util.List;


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

            if (hooks.getBetterParkour().isHooked()) {
                ParkourAPI api = BetterParkour.getAPI();
                if (api.getParkourHandler().inCourse(p) || api.getParkourHandler().inCourse(ride)) {
                    e.setCancelled(true);
                    return;
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
