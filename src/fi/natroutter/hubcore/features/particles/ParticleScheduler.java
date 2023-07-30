package fi.natroutter.hubcore.features.particles;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.natlibs.handlers.Particles;
import fi.natroutter.natlibs.objects.ParticleSettings;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;


public class ParticleScheduler {

    private PlayerDataHandler pdh = HubCore.getDataHandler();

    private int red = 255;
    private int green = 0;
    private int blue = 0;

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

    private ParticleSettings cloud(Particle particle) {
        return new ParticleSettings(particle, 15, 0.3, 0.5, 0.3, 0);
    }

    private ParticleSettings tail(Particle particle) {
        return new ParticleSettings(particle, 20, 0, 0, 0, 0);
    }

    public ParticleScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HubCore.getInstance(), ()-> {

            int speed = Config.RainbowSpeed.asInteger();

            speed = Math.min(speed, 255);
            speed = Math.max(speed, 0);

            if (red == 255 && green < 255 && blue == 0) {
                green = Math.min(green + speed, 255);
            } else if (red > 0 && green == 255 && blue == 0) {
                red = Math.max(red - speed, 0);
            } else if (red == 0 && green == 255 && blue < 255) {
                blue = Math.min(blue + speed, 255);
            } else if (red == 0 && green > 0 && blue == 255) {
                green = Math.max(green - speed, 0);
            } else if (red < 255 && green == 0 && blue == 255) {
                red = Math.min(red + speed, 255);
            } else if (red == 255 && green == 0 && blue > 0) {
                blue = Math.max(blue - speed, 0);
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
                    set = cloud(type.getParticle());
                } else if (mode.equals(ParticleMode.TAIL)) {
                    set = tail(type.getParticle());
                }

                if (type.equals(ParticleTypes.LAVA)) {
                    set.setCount(1);
                }

                if (type.equals(ParticleTypes.RAINBOWDUST)) {
                    set.setDustOptions(new Particle.DustOptions(Color.fromRGB(red, green, blue), 1));
                }

                Particles.spawnWorld(partLoc, set);
            }
        }, 0, 2);

    }

}
