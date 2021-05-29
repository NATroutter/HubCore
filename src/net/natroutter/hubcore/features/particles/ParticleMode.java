package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;

public enum ParticleMode {
    TAIL,
    CLOUD;

    public ParticleMode next() {
        switch (this) {
            case TAIL: return CLOUD;
            case CLOUD: return TAIL;
        }
        return null;
    }

    private static final Lang lang = HubCore.getLang();

    public String langName() {
        switch (this) {
            case TAIL: return lang.Guis.particles.particles.Tail;
            case CLOUD: return lang.Guis.particles.particles.Cloud;
        }
        return null;
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
