package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;

public enum ParticleMode {
    TAIL,
    CLOUD;

    public ParticleMode next() {
        return switch (this) {
            case TAIL -> CLOUD;
            case CLOUD -> TAIL;
        };
    }

    private static final Lang lang = HubCore.getLang();

    public String langName() {
        return switch (this) {
            case TAIL -> lang.Guis.particles.particles.Tail;
            case CLOUD -> lang.Guis.particles.particles.Cloud;
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
