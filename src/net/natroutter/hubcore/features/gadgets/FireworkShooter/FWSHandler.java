package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import jdk.jshell.execution.Util;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.guis.SettingsGUI;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Utils;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class FWSHandler {

    public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
    private static int cooldownTime = 2;

    public static void shoot(Player p) {

        if(cooldown.containsKey(p.getUniqueId())) {
            long cooldownLeft = ((cooldown.get(p.getUniqueId())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(cooldownLeft>0) {
                return;
            }
        }
        cooldown.put(p.getUniqueId(), System.currentTimeMillis());

        Utils.soundInRadius(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 30);

        Location loc = p.getEyeLocation();
        Firework firework = p.getWorld().spawn(loc, Firework.class);
        firework.setShooter(p);

        firework.setCustomName("Fireworkshooter-projectile");
        firework.setCustomNameVisible(false);
        firework.setSilent(false);

        Vector vector = p.getEyeLocation().getDirection().normalize();
        firework.setVelocity(vector.multiply(0.8));

        firework.setShotAtAngle(true);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.setPower(1);

        FireworkEffect.Builder effect = getEffect(p);
        if (effect != null) {
            meta.addEffect(effect.build());
        }

        firework.setFireworkMeta(meta);

    }

    private static FireworkEffect.Builder getEffect(Player p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data != null) {

            FireworkEffect.Builder e = FireworkEffect.builder();

            e.trail(data.getTrail());
            e.flicker(data.getTwinkle());
            e.withColor(Color.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b()));
            e.withFade(Color.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b()));

            FShape shape = FShape.fromString(data.getShape());
            if (shape != null) {
                switch (shape) {
                    case SMALLBALL:return e.with(FireworkEffect.Type.BALL);
                    case LARGEBALL:return e.with(FireworkEffect.Type.BALL_LARGE);
                    case STAR:return e.with(FireworkEffect.Type.STAR);
                    case CREEPER:return e.with(FireworkEffect.Type.CREEPER);
                    case BURST:return e.with(FireworkEffect.Type.BURST);
                }
            }
        }
        return null;
    }

    public static void showGUI(Player p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        SettingsGUI.show(p, data);
    }


}
