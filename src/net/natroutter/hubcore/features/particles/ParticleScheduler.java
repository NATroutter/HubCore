package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;


public class ParticleScheduler {

    private static final PlayerDataHandler pdh = HubCore.getDataHandler();

    private JavaPlugin plugin;
    private Utilities utils;
    private int task;

    private int red = 255;
    private int green = 0;
    private int blue = 0;

    public ParticleScheduler(JavaPlugin plugin, Utilities utils) {
        this.plugin = plugin;
        this.utils = utils;
        init();
    }

    protected static ArrayList<UUID> disableParticle = new ArrayList<>();
    public static void disableParticle(Player p, boolean status) {
        if (status) {
            if (!disableParticle.contains(p.getUniqueId())) {
                disableParticle.add(p.getUniqueId());
            }
        } else {
            disableParticle.remove(p.getUniqueId());
        }
    }

    private ParticleSettings cloud(Location loc, Particle particle) {
        return new ParticleSettings(particle, loc, 20, 0.3, 0.5, 0.3, 0);
    }

    private ParticleSettings tail(Location loc, Particle particle) {
        return new ParticleSettings(particle, loc, 20, 0, 0, 0, 0);
    }

    private void init() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()-> {
            if(red > 0 && blue == 0){
                red--;
                green++;
            }
            if(green > 0 && red == 0){
                green--;
                blue++;
            }
            if(blue > 0 && green == 0){
                red++;
                blue--;
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (disableParticle.contains(p.getUniqueId())){
                    continue;
                }

                PlayerData data = pdh.get(p.getUniqueId());
                if (data == null) {return;}
                ParticleTypes type = ParticleTypes.fromString(data.getParticle());
                ParticleMode mode = ParticleMode.fromString(data.getParticlemode());
                if (type == null || mode == null) {continue;}
                if (type == ParticleTypes.DISABLED) {continue;}

                ParticleSettings set = null;
                Location partLoc = p.getLocation().add(0, 0.6, 0);
                if (mode.equals(ParticleMode.CLOUD)) {
                    set = cloud(partLoc, type.getParticle());
                } else if (mode.equals(ParticleMode.TAIL)) {
                    set = tail(partLoc, type.getParticle());
                }

                if (type.equals(ParticleTypes.LAVA)) {
                    set.setCount(1);
                }

                if (type.equals(ParticleTypes.RAINBOWDUST)) {
                    spawnDust(p, set);
                } else {
                    utils.spawnParticleInRadius(set, 50);
                }
            }
        }, 0, 2);

    }

    public void spawnDust(Player p, ParticleSettings set) {
        Particle.DustOptions opt = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1);
        for (Entity nearEnt : p.getNearbyEntities(50,50,50)) {
            if (!(nearEnt instanceof Player nearp)) {continue;}
            nearp.spawnParticle(set.getParticle(), set.getLoc(), set.getCount(), set.getOffsetX(), set.getOffsetY(), set.getOffsetY(), set.getSpeed(), opt);
        }
        p.spawnParticle(set.getParticle(), set.getLoc(), set.getCount(), set.getOffsetX(), set.getOffsetY(), set.getOffsetY(), set.getSpeed(), opt);
    }

}
