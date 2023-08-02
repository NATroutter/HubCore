package fi.natroutter.hubcore.features.gadgets.grapler;

import fi.natroutter.betterparkour.BetterParkour;
import fi.natroutter.betterparkour.handlers.ParkourHandler;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.utilities.Items;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Grapler implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        FishHook hook = e.getHook();
        PlayerFishEvent.State state = e.getState();


        if (Items.gadget_graper().isSimilar(item)) {
            hook.customName(Component.text("Grapler"));

            if (HubCore.getHooks().getBetterParkour().isHooked()) {
                ParkourHandler parkourHandler = BetterParkour.getParkourHandler();
                if (parkourHandler.inCourse(p)) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getHook().getVehicle() instanceof Player target) {
                    if (parkourHandler.inCourse(target)) {
                        for (Entity ent : e.getHook().getPassengers()) {target.removePassenger(ent);}
                        e.getHook().eject();
                        e.setCancelled(true);
                        return;
                    }
                }
            }

            hook.setVelocity(hook.getVelocity().multiply(1.04206911D));
            if (state.equals(PlayerFishEvent.State.IN_GROUND) || state.equals(PlayerFishEvent.State.CAUGHT_ENTITY)) {
                if (!p.isInsideVehicle()) {
                    p.setVelocity(hook.getLocation().subtract(p.getLocation()).toVector().multiply(0.21D));
                }
                p.playSound(hook.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 100.0F);
            }
        }

    }

}
