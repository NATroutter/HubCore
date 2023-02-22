package net.natroutter.hubcore.features.particles;

import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.files.Translations;

public enum ParticleMode {
    TAIL,
    CLOUD;

    public ParticleMode next() {
        return switch (this) {
            case TAIL -> CLOUD;
            case CLOUD -> TAIL;
        };
    }

    public String langName(LangManager lang) {
        return switch (this) {
            case TAIL -> lang.get(Translations.Guis_Particles_Particles_Tail);
            case CLOUD -> lang.get(Translations.Guis_Particles_Particles_Cloud);
        };
    }

    public static ParticleMode fromString(String value) {
        for (ParticleMode mode : values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        return null;
    }

}
