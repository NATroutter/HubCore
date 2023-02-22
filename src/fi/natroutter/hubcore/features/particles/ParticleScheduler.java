package fi.natroutter.hubcore.features.particles;

import fi.natroutter.hubcore.Handler;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.natlibs.handlers.ParticleSpawner;
import fi.natroutter.natlibs.objects.ParticleSettings;
import fi.natroutter.natlibs.utilities.Utilities;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;


public class ParticleScheduler {

    private Handler handler;
    private Utilities utils;
    private PlayerDataHandler pdh;
    private int task;
    private ParticleSpawner spawner;

    private int red = 255;
    private int green = 0;
    private int blue = 0;

    public ParticleScheduler(Handler handler) {
        this.handler = handler;
        this.utils = handler.getUtilities();
        this.pdh = handler.getDataHandler();
        this.spawner = handler.getSpawner();
        init();
    }

    protected ArrayList<UUID> disableParticle = new ArrayList<>();
    public void disableParticle(Player p, boolean status) {
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
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(handler.getInstance(), ()-> {
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
                    spawner.spawnParticleWorld(set);
                }
            }
        }, 0, 2);

    }

    public void spawnDust(Player p, ParticleSettings set) {
        Particle.DustOptions opt = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1);
        World world = set.getLoc().getWorld();
        if (world == null) {return;}
        world.spawnParticle(set.getParticle(), set.getLoc(), set.getCount(), set.getOffsetX(), set.getOffsetY(), set.getOffsetY(), set.getSpeed(), opt);
    }

}
