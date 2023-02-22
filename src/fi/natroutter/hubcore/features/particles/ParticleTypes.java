package fi.natroutter.hubcore.features.particles;

import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.files.Translations;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import org.bukkit.Particle;

public enum ParticleTypes {

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

    private Particle particle;

    ParticleTypes(Particle particle) {
        this.particle = particle;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getLangName(LangManager lang) {
        switch (this) {
            case DISABLED -> {return lang.get(Translations.Guis_Particles_Disabled);}
            case WITCH -> {return lang.get(Translations.Guis_Particles_Witch);}
            case TOTEM -> {return lang.get(Translations.Guis_Particles_Totem);}
            case INK -> {return lang.get(Translations.Guis_Particles_Ink);}
            case SOULFLAME -> {return lang.get(Translations.Guis_Particles_Soulflame);}
            case CLOUD -> {return lang.get(Translations.Guis_Particles_Cloud);}
            case SOUL -> {return lang.get(Translations.Guis_Particles_Soul);}
            case NOTES -> {return lang.get(Translations.Guis_Particles_Notes);}
            case CONDUIT -> {return lang.get(Translations.Guis_Particles_Conduit);}
            case LAVA -> {return lang.get(Translations.Guis_Particles_Lava);}
            case HEARTS -> {return lang.get(Translations.Guis_Particles_Hearts);}
            case HAPPY -> {return lang.get(Translations.Guis_Particles_Happy);}
            case ANGRY -> {return lang.get(Translations.Guis_Particles_Angry);}
            case ENCHANT -> {return lang.get(Translations.Guis_Particles_Enchanthit);}
            case ENDROD -> {return lang.get(Translations.Guis_Particles_Endrod);}
            case ENCHANTING -> {return lang.get(Translations.Guis_Particles_Enchanting);}
            case RAINBOWDUST -> {return lang.get(Translations.Guis_Particles_RainbowDust);}
            case DRAGONSBREATH -> {return lang.get(Translations.Guis_Particles_DragonBreath);}
            case CRITICAL -> {return lang.get(Translations.Guis_Particles_Critical);}
            case DAMAGE -> {return lang.get(Translations.Guis_Particles_Damage);}
            case SMOKE -> {return lang.get(Translations.Guis_Particles_Smoke);}
            case SNEEZE -> {return lang.get(Translations.Guis_Particles_Sneeze);}
        }
        return null;
    }

    public String getPermission(Config config) {
        switch (this) {
            case DISABLED -> {return "";}
            case WITCH -> {return config.particles.witch.permission;}
            case TOTEM -> {return config.particles.totem.permission;}
            case INK -> {return config.particles.ink.permission;}
            case SOULFLAME -> {return config.particles.soulflame.permission;}
            case CLOUD -> {return config.particles.cloud.permission;}
            case SOUL -> {return config.particles.soul.permission;}
            case NOTES -> {return config.particles.notes.permission;}
            case CONDUIT -> {return config.particles.conduit.permission;}
            case LAVA -> {return config.particles.lava.permission;}
            case HEARTS -> {return config.particles.hearts.permission;}
            case HAPPY -> {return config.particles.happy.permission;}
            case ANGRY -> {return config.particles.angry.permission;}
            case ENCHANT -> {return config.particles.enchanthit.permission;}
            case ENDROD -> {return config.particles.endrod.permission;}
            case ENCHANTING -> {return config.particles.enchanting.permission;}
            case RAINBOWDUST -> {return config.particles.rainbowdust.permission;}
            case DRAGONSBREATH -> {return config.particles.dragonbreath.permission;}
            case CRITICAL -> {return config.particles.critical.permission;}
            case DAMAGE -> {return config.particles.damage.permission;}
            case SMOKE -> {return config.particles.smoke.permission;}
            case SNEEZE -> {return config.particles.sneeze.permission;}
        }
        return "";
    }

    public String getNeed(Config config) {
        switch (this) {
            case DISABLED -> {return "";}
            case WITCH -> {return config.particles.witch.need;}
            case TOTEM -> {return config.particles.totem.need;}
            case INK -> {return config.particles.ink.need;}
            case SOULFLAME -> {return config.particles.soulflame.need;}
            case CLOUD -> {return config.particles.cloud.need;}
            case SOUL -> {return config.particles.soul.need;}
            case NOTES -> {return config.particles.notes.need;}
            case CONDUIT -> {return config.particles.conduit.need;}
            case LAVA -> {return config.particles.lava.need;}
            case HEARTS -> {return config.particles.hearts.need;}
            case HAPPY -> {return config.particles.happy.need;}
            case ANGRY -> {return config.particles.angry.need;}
            case ENCHANT -> {return config.particles.enchanthit.need;}
            case ENDROD -> {return config.particles.endrod.need;}
            case ENCHANTING -> {return config.particles.enchanting.need;}
            case RAINBOWDUST -> {return config.particles.rainbowdust.need;}
            case DRAGONSBREATH -> {return config.particles.dragonbreath.need;}
            case CRITICAL -> {return config.particles.critical.need;}
            case DAMAGE -> {return config.particles.damage.need;}
            case SMOKE -> {return config.particles.smoke.need;}
            case SNEEZE -> {return config.particles.sneeze.need;}
        }
        return "";
    }

    public static ParticleTypes fromString(String value) {
        for (ParticleTypes type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

}
