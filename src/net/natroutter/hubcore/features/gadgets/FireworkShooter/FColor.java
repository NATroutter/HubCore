package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.files.Translations;

public enum FColor {

    DarkRed(170, 0, 0),
    Red(255,85,85),
    Gold(255,170,0),
    Yellow(255,255,85),
    DarkGreen(0,170,0),
    Green(85,255,85),
    Aqua(85,255,255),
    DarkAqua(0,170,170),
    DarkBlue(0,0,170),
    Blue(85,85,255),
    LightPurple(255,85,255),
    DarkPurple(170,0,170),
    White(255,255,255),
    Gray(170,170,170),
    DarkGray(85,85,85),
    Black(0,0,0),
    Custom(0,0,0);

    private int r;
    private int g;
    private int b;

    FColor(int red, int green, int blue) {
        this.r = red;
        this.g = green;
        this.b = blue;
    }

    public static FColor fromString(String value) {
        for (FColor color : values()) {
            if (color.name().equalsIgnoreCase(value)) {
                return color;
            }
        }
        return null;
    }

    public static FColor fromRGB(int red, int green, int blue) {
        for (FColor c : values()) {
            if (c.getR() == red && c.getG() == green && c.getB() == blue) {
                return  c;
            }
        }
        FColor c = FColor.Custom;
        c.setR(red);
        c.setG(green);
        c.setB(blue);
        return c;
    }

    public String getLangName(LangManager lang) {
        switch (this) {
            case DarkRed: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_DarkRed);
            case Red: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Red);
            case Gold: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Gold);
            case Yellow: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Yellow);
            case DarkGreen: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_DarkGreen);
            case Green: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Green);
            case Aqua: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Aqua);
            case DarkAqua: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_DarkAqua);
            case DarkBlue: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_DarkBlue);
            case Blue: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Blue);
            case LightPurple: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_LightPurple);
            case DarkPurple: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_DarkPurple);
            case White: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_White);
            case Gray: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Gray);
            case DarkGray: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_DarkGray);
            case Black: return lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Black);
            case Custom:
                StringHandler h = new StringHandler(lang.get(Translations.Guis_Gadgets_Fireworks_Colors_Custom));
                h.replaceAll("{r}", r);
                h.replaceAll("{g}", g);
                h.replaceAll("{b}", b);
                return h.build();
        }
        return null;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }
}
