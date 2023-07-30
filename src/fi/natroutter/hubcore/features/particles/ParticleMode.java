package fi.natroutter.hubcore.features.particles;

import fi.natroutter.hubcore.files.Lang;

public enum ParticleMode {
    TAIL,
    CLOUD;

    public ParticleMode next() {
        return switch (this) {
            case TAIL -> CLOUD;
            case CLOUD -> TAIL;
        };
    }

    public String langName() {
        return switch (this) {
            case TAIL -> Lang.Guis_Particles_Particles_Tail.asString();
            case CLOUD -> Lang.Guis_Particles_Particles_Cloud.asString();
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
