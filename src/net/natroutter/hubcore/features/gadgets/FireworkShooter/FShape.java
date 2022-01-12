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
        return switch (this) {
            case SMALLBALL -> lang.Guis.Gadgets.fireworks.shapes.SmallBall;
            case LARGEBALL -> lang.Guis.Gadgets.fireworks.shapes.LargeBall;
            case STAR -> lang.Guis.Gadgets.fireworks.shapes.Star;
            case CREEPER -> lang.Guis.Gadgets.fireworks.shapes.Creeper;
            case BURST -> lang.Guis.Gadgets.fireworks.shapes.Burst;
        };
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
