package fi.natroutter.hubcore.features.gadgets.FireworkShooter;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.utilities.Utils;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.natlibs.NATLibs;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class FWSHandler {

    protected static NamespacedKey namespacedKey = new NamespacedKey(HubCore.getInstance(), "fireworkshooter-projectile");

    public HashMap<UUID, FColorType> colortype = new HashMap<>();
    public HashMap<UUID, Long> cooldown = new HashMap<>();
    private static int cooldownTime = 2;

    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private Utils utils = HubCore.getUtils();

    public void shoot(Player p) {

        if(cooldown.containsKey(p.getUniqueId())) {
            long cooldownLeft = ((cooldown.get(p.getUniqueId())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(cooldownLeft>0) {
                return;
            }
        }
        cooldown.put(p.getUniqueId(), System.currentTimeMillis());

        utils.soundInRadius(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 30);

        Location loc = p.getEyeLocation();
        Firework firework = p.getWorld().spawn(loc, Firework.class);
        firework.setShooter(p);

        firework.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, 1);
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

    private FireworkEffect.Builder getEffect(Player p) {
        PlayerData data = pdh.get(p.getUniqueId());
        if (data != null) {

            FireworkEffect.Builder e = FireworkEffect.builder();

            e.trail(data.getTrail());
            e.flicker(data.getTwinkle());
            e.withColor(Color.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b()));
            e.withFade(Color.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b()));

            FShape shape = FShape.fromString(data.getShape());
            if (shape != null) {
                return switch (shape) {
                    case SMALLBALL -> e.with(FireworkEffect.Type.BALL);
                    case LARGEBALL -> e.with(FireworkEffect.Type.BALL_LARGE);
                    case STAR -> e.with(FireworkEffect.Type.STAR);
                    case CREEPER -> e.with(FireworkEffect.Type.CREEPER);
                    case BURST -> e.with(FireworkEffect.Type.BURST);
                };
            }
        }
        return null;
    }

}
