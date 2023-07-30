package fi.natroutter.hubcore.features.gadgets.FireworkShooter;

import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.objects.Color;
import fi.natroutter.natlibs.config.ILang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

@AllArgsConstructor
public enum FColor {

    DarkRed(
            new Color(170, 0, 0),
            Lang.Guis_Gadgets_Fireworks_Colors_DarkRed
    ),
    Red(
            new Color(255,85,85),
            Lang.Guis_Gadgets_Fireworks_Colors_Red
    ),
    Gold(
            new Color(255,170,0),
            Lang.Guis_Gadgets_Fireworks_Colors_Gold
    ),
    Yellow(
            new Color(255,255,85),
            Lang.Guis_Gadgets_Fireworks_Colors_Yellow
    ),
    DarkGreen(
            new Color(0,170,0),
            Lang.Guis_Gadgets_Fireworks_Colors_DarkGreen
    ),
    Green(
            new Color(85,255,85),
            Lang.Guis_Gadgets_Fireworks_Colors_Green
    ),
    Aqua(
            new Color(85,255,255),
            Lang.Guis_Gadgets_Fireworks_Colors_Aqua
    ),
    DarkAqua(
            new Color(0,170,170),
            Lang.Guis_Gadgets_Fireworks_Colors_DarkAqua
    ),
    DarkBlue(
            new Color(0,0,170),
            Lang.Guis_Gadgets_Fireworks_Colors_DarkBlue
    ),
    Blue(
            new Color(85,85,255),
            Lang.Guis_Gadgets_Fireworks_Colors_Blue
    ),
    LightPurple(
            new Color(255,85,255),
            Lang.Guis_Gadgets_Fireworks_Colors_LightPurple
    ),
    DarkPurple(
            new Color(170,0,170),
            Lang.Guis_Gadgets_Fireworks_Colors_DarkPurple
    ),
    White(
            new Color(255,255,255),
            Lang.Guis_Gadgets_Fireworks_Colors_White
    ),
    Gray(
            new Color(170,170,170),
            Lang.Guis_Gadgets_Fireworks_Colors_Gray
    ),
    DarkGray(
            new Color(85,85,85),
            Lang.Guis_Gadgets_Fireworks_Colors_DarkGray
    ),
    Black(
            new Color(0,0,0),
            Lang.Guis_Gadgets_Fireworks_Colors_Black
    ),
    Custom(
            new Color(0,0,0),
            Lang.Guis_Gadgets_Fireworks_Colors_Custom
    );

    @Getter
    private Color color;

    private ILang langName;

    public Component getLangName() {
        if (this.equals(Custom)) {
            return this.langName.asComponent(
                    Placeholder.parsed("r", String.valueOf(color.getR())),
                    Placeholder.parsed("g", String.valueOf(color.getG())),
                    Placeholder.parsed("b", String.valueOf(color.getB()))
            );
        }
        return this.langName.asComponent();
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

    public int getR() {return this.color.getR();}
    public int getG() {return this.color.getG();}
    public int getB() { return this.color.getB(); }

    public void setR(int r) {
        this.color.setR(r);
    }
    public void setG(int g) {
        this.color.setG(g);
    }
    public void setB(int b) {
        this.color.setB(b);
    }
}
