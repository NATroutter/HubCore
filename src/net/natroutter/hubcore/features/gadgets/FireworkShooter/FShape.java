package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.natlibs.handlers.LangHandler.language.LangManager;

public enum FShape {

    SMALLBALL,
    LARGEBALL,
    STAR,
    CREEPER,
    BURST;

    public String langName(LangManager lang) {
        return switch (this) {
            case SMALLBALL -> lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_SmallBall);
            case LARGEBALL -> lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_LargeBall);
            case STAR -> lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_Star);
            case CREEPER -> lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_Creeper);
            case BURST -> lang.get(Translations.Guis_Gadgets_Fireworks_Shapes_Burst);
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
