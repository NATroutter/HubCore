package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Lang;
import org.bukkit.Particle;

import static net.natroutter.hubcore.features.particles.ParticleTypes.Contants.*;

public enum ParticleTypes {

    DISABLED(null, null, config.witch.permission, config.witch.need),
    WITCH(Particle.SPELL_WITCH, particles.witch, config.witch.permission, config.witch.need),
    TOTEM(Particle.TOTEM, particles.totem, config.totem.permission, config.totem.need),
    INK(Particle.SQUID_INK, particles.ink, config.ink.permission, config.ink.need),
    SOULFLAME(Particle.SOUL_FIRE_FLAME, particles.soulflame, config.soulflame.permission, config.soulflame.need),
    CLOUD(Particle.CLOUD, particles.cloud, config.cloud.permission, config.cloud.need),
    SOUL(Particle.SOUL, particles.soul, config.soul.permission, config.soul.need),
    NOTES(Particle.NOTE, particles.notes, config.notes.permission, config.notes.need),
    CONDUIT(Particle.NAUTILUS, particles.conduit, config.conduit.permission, config.conduit.need),
    LAVA(Particle.LAVA, particles.lava, config.lava.permission, config.lava.need),
    HEARTS(Particle.HEART, particles.hearts, config.hearts.permission, config.hearts.need),
    HAPPY(Particle.VILLAGER_HAPPY, particles.happy, config.happy.permission, config.happy.need),
    ANGRY(Particle.VILLAGER_ANGRY, particles.angry, config.angry.permission, config.angry.need),
    ENCHANT(Particle.CRIT_MAGIC, particles.enchanthit, config.enchanthit.permission, config.enchanthit.need),
    ENDROD(Particle.END_ROD, particles.endrod, config.endrod.permission, config.endrod.need),
    ENCHANTING(Particle.ENCHANTMENT_TABLE, particles.enchanting, config.enchanting.permission, config.enchanting.need),
    RAINBOWDUST(Particle.REDSTONE, particles.rainbowdust, config.rainbowdust.permission, config.rainbowdust.need),
    DRAGONSBREATH(Particle.DRAGON_BREATH, particles.dragonbreath, config.dragonbreath.permission, config.dragonbreath.need),
    CRITICAL(Particle.CRIT, particles.critical, config.critical.permission, config.critical.need),
    DAMAGE(Particle.DAMAGE_INDICATOR, particles.damage, config.damage.permission, config.damage.need),
    SMOKE(Particle.SMOKE_NORMAL, particles.smoke, config.smoke.permission, config.smoke.need),
    SNEEZE(Particle.SNEEZE, particles.sneeze, config.sneeze.permission, config.sneeze.need);

    private Particle particle;
    private final String langName;
    private final String permission;
    private final String need;

    ParticleTypes(Particle particle, String langName, String permission, String need) {
        this.particle = particle;
        this.langName = langName;
        this.permission = permission;
        this.need = need;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getLangName() {
        return langName;
    }

    public String getPermission() {
        return permission;
    }

    public String getNeed() {
        return need;
    }

    public static ParticleTypes fromString(String value) {
        for (ParticleTypes type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    protected static class Contants {
        protected static final Lang.Guis.Particles particles = HubCore.getLang().Guis.particles;
        protected static final Config.particles config = HubCore.getCfg().particles;
    }
}
