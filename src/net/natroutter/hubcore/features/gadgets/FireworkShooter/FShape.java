package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;

public enum FShape {

    SMALLBALL,
    LARGEBALL,
    STAR,
    CREEPER,
    BURST;

    private static final Lang lang = HubCore.getLang();

    public String langName() {
        switch (this) {
            case SMALLBALL: return lang.Guis.Gadgets.fireworks.shapes.SmallBall;
            case LARGEBALL: return lang.Guis.Gadgets.fireworks.shapes.LargeBall;
            case STAR: return lang.Guis.Gadgets.fireworks.shapes.Star;
            case CREEPER: return lang.Guis.Gadgets.fireworks.shapes.Creeper;
            case BURST: return lang.Guis.Gadgets.fireworks.shapes.Burst;
        }
        return null;
    }

    public static FShape fromString(String value) {
        for (FShape shape : values()) {
            if (shape.name().equalsIgnoreCase(value)) {
                return shape;
            }
        }
        return null;
    }
}
