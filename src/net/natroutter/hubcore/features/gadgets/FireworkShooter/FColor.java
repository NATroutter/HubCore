package net.natroutter.hubcore.features.gadgets.FireworkShooter;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.utilities.StringHandler;

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

    private static final Lang lang = HubCore.getLang();

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

    public String getLangName() {
        switch (this) {
            case DarkRed: return lang.Guis.Gadgets.fireworks.colors.DarkRed;
            case Red: return lang.Guis.Gadgets.fireworks.colors.Red;
            case Gold: return lang.Guis.Gadgets.fireworks.colors.Gold;
            case Yellow: return lang.Guis.Gadgets.fireworks.colors.Yellow;
            case DarkGreen: return lang.Guis.Gadgets.fireworks.colors.DarkGreen;
            case Green: return lang.Guis.Gadgets.fireworks.colors.Green;
            case Aqua: return lang.Guis.Gadgets.fireworks.colors.Aqua;
            case DarkAqua: return lang.Guis.Gadgets.fireworks.colors.DarkAqua;
            case DarkBlue: return lang.Guis.Gadgets.fireworks.colors.DarkBlue;
            case Blue: return lang.Guis.Gadgets.fireworks.colors.Blue;
            case LightPurple: return lang.Guis.Gadgets.fireworks.colors.LightPurple;
            case DarkPurple: return lang.Guis.Gadgets.fireworks.colors.DarkPurple;
            case White: return lang.Guis.Gadgets.fireworks.colors.White;
            case Gray: return lang.Guis.Gadgets.fireworks.colors.Gray;
            case DarkGray: return lang.Guis.Gadgets.fireworks.colors.DarkGray;
            case Black: return lang.Guis.Gadgets.fireworks.colors.Black;
            case Custom:
                StringHandler h = new StringHandler(lang.Guis.Gadgets.fireworks.colors.Custom);
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
