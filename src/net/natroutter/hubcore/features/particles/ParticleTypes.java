package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Lang;
import org.bukkit.Particle;

import static net.natroutter.hubcore.features.particles.ParticleTypes.Contants.*;

public enum ParticleTypes {

    DISABLED(null, null, config.particles.witch.permission, config.particles.witch.need),
    WITCH(Particle.SPELL_WITCH, lang.Guis.particles.witch, config.particles.witch.permission, config.particles.witch.need),
    TOTEM(Particle.TOTEM, lang.Guis.particles.totem, config.particles.totem.permission, config.particles.totem.need),
    INK(Particle.SQUID_INK, lang.Guis.particles.ink, config.particles.ink.permission, config.particles.ink.need),
    SOULFLAME(Particle.SOUL_FIRE_FLAME, lang.Guis.particles.soulflame, config.particles.soulflame.permission, config.particles.soulflame.need),
    CLOUD(Particle.CLOUD, lang.Guis.particles.cloud, config.particles.cloud.permission, config.particles.cloud.need),
    SOUL(Particle.SOUL, lang.Guis.particles.soul, config.particles.soul.permission, config.particles.soul.need),
    NOTES(Particle.NOTE, lang.Guis.particles.notes, config.particles.notes.permission, config.particles.notes.need),
    CONDUIT(Particle.NAUTILUS, lang.Guis.particles.conduit, config.particles.conduit.permission, config.particles.conduit.need),
    LAVA(Particle.LAVA, lang.Guis.particles.lava, config.particles.lava.permission, config.particles.lava.need),
    HEARTS(Particle.HEART, lang.Guis.particles.hearts, config.particles.hearts.permission, config.particles.hearts.need),
    HAPPY(Particle.VILLAGER_HAPPY, lang.Guis.particles.happy, config.particles.happy.permission, config.particles.happy.need),
    ANGRY(Particle.VILLAGER_ANGRY, lang.Guis.particles.angry, config.particles.angry.permission, config.particles.angry.need),
    ENCHANT(Particle.CRIT_MAGIC, lang.Guis.particles.enchanthit, config.particles.enchanthit.permission, config.particles.enchanthit.need),
    ENDROD(Particle.END_ROD, lang.Guis.particles.endrod, config.particles.endrod.permission, config.particles.endrod.need),
    ENCHANTING(Particle.ENCHANTMENT_TABLE, lang.Guis.particles.enchanting, config.particles.enchanting.permission, config.particles.enchanting.need),
    RAINBOWDUST(Particle.REDSTONE, lang.Guis.particles.rainbowdust, config.particles.rainbowdust.permission, config.particles.rainbowdust.need),
    DRAGONSBREATH(Particle.DRAGON_BREATH, lang.Guis.particles.dragonbreath, config.particles.dragonbreath.permission, config.particles.dragonbreath.need),
    CRITICAL(Particle.CRIT, lang.Guis.particles.critical, config.particles.critical.permission, config.particles.critical.need),
    DAMAGE(Particle.DAMAGE_INDICATOR, lang.Guis.particles.damage, config.particles.damage.permission, config.particles.damage.need),
    SMOKE(Particle.SMOKE_NORMAL, lang.Guis.particles.smoke, config.particles.smoke.permission, config.particles.smoke.need),
    SNEEZE(Particle.SNEEZE, lang.Guis.particles.sneeze, config.particles.sneeze.permission, config.particles.sneeze.need);

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
        protected static final Lang lang = HubCore.getLang();
        protected static final Config config = HubCore.getCfg();
    }
}
