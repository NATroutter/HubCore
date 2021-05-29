package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Config;
import net.natroutter.hubcore.utilities.Lang;
import org.bukkit.Particle;

import java.util.ArrayList;

public enum particleTypes {

    DISABLED(null),
    WITCH(Particle.SPELL_WITCH),
    TOTEM(Particle.TOTEM),
    INK(Particle.SQUID_INK),
    SOULFLAME(Particle.SOUL_FIRE_FLAME),
    CLOUD(Particle.CLOUD),
    SOUL(Particle.SOUL),
    NOTES(Particle.NOTE),
    CONDUIT(Particle.NAUTILUS),
    LAVA(Particle.LAVA),
    HEARTS(Particle.HEART),
    HAPPY(Particle.VILLAGER_HAPPY),
    ANGRY(Particle.VILLAGER_ANGRY),
    ENCHANT(Particle.CRIT_MAGIC),
    ENDROD(Particle.END_ROD),
    ENCHANTING(Particle.ENCHANTMENT_TABLE),
    RAINBOWDUST(Particle.REDSTONE),
    DRAGONSBREATH(Particle.DRAGON_BREATH),
    CRITICAL(Particle.CRIT),
    DAMAGE(Particle.DAMAGE_INDICATOR),
    SMOKE(Particle.SMOKE_NORMAL),
    SNEEZE(Particle.SNEEZE);

    private static final Lang lang = HubCore.getLang();
    private static final Config config = HubCore.getCfg();

    private Particle particle;

    particleTypes(Particle particle) {
        this.particle = particle;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getLangName() {
        switch (this) {
            case WITCH: return lang.Guis.particles.witch;
            case TOTEM: return lang.Guis.particles.totem;
            case INK: return lang.Guis.particles.ink;
            case SOULFLAME: return lang.Guis.particles.soulflame;
            case CLOUD: return lang.Guis.particles.cloud;
            case SOUL: return lang.Guis.particles.soul;
            case NOTES: return lang.Guis.particles.notes;
            case CONDUIT: return lang.Guis.particles.conduit;
            case LAVA: return lang.Guis.particles.lava;
            case HEARTS: return lang.Guis.particles.hearts;
            case HAPPY: return lang.Guis.particles.happy;
            case ANGRY: return lang.Guis.particles.angry;
            case ENCHANT: return lang.Guis.particles.enchanthit;
            case ENDROD: return lang.Guis.particles.endrod;
            case ENCHANTING: return lang.Guis.particles.enchanting;
            case RAINBOWDUST: return lang.Guis.particles.rainbowdust;
            case DRAGONSBREATH: return lang.Guis.particles.dragonbreath;
            case CRITICAL: return lang.Guis.particles.critical;
            case DAMAGE: return lang.Guis.particles.damage;
            case SMOKE: return lang.Guis.particles.smoke;
            case SNEEZE: return lang.Guis.particles.sneeze;
        }
        return null;
    }

    public String getPermission() {
        switch (this) {
            case WITCH: return config.particles.witch.permission;
            case TOTEM: return config.particles.totem.permission;
            case INK: return config.particles.ink.permission;
            case SOULFLAME: return config.particles.soulflame.permission;
            case CLOUD: return config.particles.cloud.permission;
            case SOUL: return config.particles.soul.permission;
            case NOTES: return config.particles.notes.permission;
            case CONDUIT: return config.particles.conduit.permission;
            case LAVA: return config.particles.lava.permission;
            case HEARTS: return config.particles.hearts.permission;
            case HAPPY: return config.particles.happy.permission;
            case ANGRY: return config.particles.angry.permission;
            case ENCHANT: return config.particles.enchanthit.permission;
            case ENDROD: return config.particles.endrod.permission;
            case ENCHANTING: return config.particles.enchanting.permission;
            case RAINBOWDUST: return config.particles.rainbowdust.permission;
            case DRAGONSBREATH: return config.particles.dragonbreath.permission;
            case CRITICAL: return config.particles.critical.permission;
            case DAMAGE: return config.particles.damage.permission;
            case SMOKE: return config.particles.smoke.permission;
            case SNEEZE: return config.particles.sneeze.permission;

            default:return config.particles.witch.permission;
        }
    }

    public String getNeed() {
        switch (this) {
            case WITCH: return config.particles.witch.need;
            case TOTEM: return config.particles.totem.need;
            case INK: return config.particles.ink.need;
            case SOULFLAME: return config.particles.soulflame.need;
            case CLOUD: return config.particles.cloud.need;
            case SOUL: return config.particles.soul.need;
            case NOTES: return config.particles.notes.need;
            case CONDUIT: return config.particles.conduit.need;
            case LAVA: return config.particles.lava.need;
            case HEARTS: return config.particles.hearts.need;
            case HAPPY: return config.particles.happy.need;
            case ANGRY: return config.particles.angry.need;
            case ENCHANT: return config.particles.enchanthit.need;
            case ENDROD: return config.particles.endrod.need;
            case ENCHANTING: return config.particles.enchanting.need;
            case RAINBOWDUST: return config.particles.rainbowdust.need;
            case DRAGONSBREATH: return config.particles.dragonbreath.need;
            case CRITICAL: return config.particles.critical.need;
            case DAMAGE: return config.particles.damage.need;
            case SMOKE: return config.particles.smoke.need;
            case SNEEZE: return config.particles.sneeze.need;

            default:return config.particles.witch.need;
        }
    }

    public static particleTypes fromString(String value) {
        for (particleTypes type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

}
