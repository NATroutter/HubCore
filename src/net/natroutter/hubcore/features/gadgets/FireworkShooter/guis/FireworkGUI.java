package net.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.*;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIRow;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.handlers.langHandler.language.LangManager;
import org.bukkit.entity.Player;

public class FireworkGUI {

    private PlayerDataHandler pdh;
    private LangManager lang;
    private Items items;
    private FWSHandler fwsHandler;

    public FireworkGUI(Handler handler) {
        this.pdh = handler.getDataHandler();
        this.lang = handler.getLang();
        this.items = handler.getItems();
        this.fwsHandler = handler.getFwsHandler();
    }

    public void showSettingsGUI(Player p) {
        settingsGuiBuilder(p).show(p);
    }

    private GUIWindow settingsGuiBuilder(Player p) {
        GUIWindow gui = new GUIWindow(lang.get(Translations.Guis_Gadgets_Fireworks_Title), GUIRow.row6, true);

        gui.setItem(new GUIItem(items.SmallBall(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.SMALLBALL.name());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row2, 2);

        gui.setItem(new GUIItem(items.LargeBall(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.LARGEBALL.name());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row2, 3);

        gui.setItem(new GUIItem(items.Star(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.STAR.name());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row2, 4);

        gui.setItem(new GUIItem(items.Creeper(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.CREEPER.name());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row2, 5);

        gui.setItem(new GUIItem(items.Burst(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.BURST.name());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row2, 6);

        //Additives
        gui.setItem(new GUIItem(items.Twinkle(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setTwinkle(!data.getTwinkle());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row4, 2);

        gui.setItem(new GUIItem(items.Trail(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setTrail(!data.getTrail());
            pdh.set(data);
            showSettingsGUI(t);
        }), GUIRow.row5, 2);

        PlayerData dd = pdh.get(p.getUniqueId());
        gui.setItem(new GUIItem(items.CurrentSettings(dd), (e)->{
        }), GUIRow.row5, 4);

        //colors
        FColor color = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        gui.setItem(new GUIItem(items.colorDisplay(color), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            t.closeInventory();
            fwsHandler.colortype.put(t.getUniqueId(), FColorType.MAIN);
            colorsGuiBuilder(t).show(t);
        }), GUIRow.row4, 6);

        FColor fadecolor = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        gui.setItem(new GUIItem(items.fadeColor(fadecolor), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            t.closeInventory();
            fwsHandler.colortype.put(t.getUniqueId(), FColorType.FADE);
            colorsGuiBuilder(t).show(t);
        }), GUIRow.row5, 6);
        return gui;
    }

    //COLORS GUI START HERE

    private PlayerData changeColor(PlayerData data, FColor color, FColorType type) {
        if (type.equals(FColorType.MAIN)) {
            data.setColor_r(color.getR());
            data.setColor_b(color.getB());
            data.setColor_g(color.getG());
        } else if (type.equals(FColorType.FADE)) {
            data.setFade_r(color.getR());
            data.setFade_g(color.getG());
            data.setFade_b(color.getB());
        }
        return data;
    }


    private void showColorsGUI(Player p) {
        colorsGuiBuilder(p).show(p);
    }

    private GUIWindow colorsGuiBuilder(Player p) {
        GUIWindow gui = new GUIWindow(lang.get(Translations.Guis_Gadgets_Fireworks_ColorsTitle), GUIRow.row6, true);

        //Row 1
        gui.setItem(new GUIItem(items.fireworkStar(FColor.DarkRed), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkRed, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 1);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Red), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Red, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 2);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Gold), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Gold, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 3);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Yellow), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Yellow, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 4);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.DarkGreen), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkGreen, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 5);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Green), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Green, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 6);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Aqua), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Aqua, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(items.fireworkStar(FColor.DarkAqua), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkAqua, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 1);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.DarkBlue), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkBlue, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 2);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Blue), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Blue, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 3);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.LightPurple), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.LightPurple, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 4);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.DarkPurple), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkPurple, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 5);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.White), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.White, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 6);

        gui.setItem(new GUIItem(items.fireworkStar(FColor.Gray), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Gray, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row3, 7);

        //Row 3
        gui.setItem(new GUIItem(items.fireworkStar(FColor.DarkGray), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkGray, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row4, 3);

        //color dispaly
        FColorType type = fwsHandler.colortype.getOrDefault(p.getUniqueId(), FColorType.MAIN);
        PlayerData dd = pdh.get(p.getUniqueId());
        FColor cc = FColor.Black;
        if (type.equals(FColorType.MAIN)) {
            cc = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            cc = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        }
        gui.setItem(new GUIItem(items.colorDisplay(cc), (e)->{
        }), GUIRow.row4, 4);


        gui.setItem(new GUIItem(items.fireworkStar(FColor.Black), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkGray, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showColorsGUI(t);
        }), GUIRow.row4, 5);

        //controls
        gui.setItem(new GUIItem(items.back(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}

            t.closeInventory();
            showSettingsGUI(t);
        }), GUIRow.row6, 3);

        gui.setItem(new GUIItem(items.customColor(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}

            if (t.hasPermission("hubcore.gadgets.fireworkshooter.customcolor")) {
                t.closeInventory();
                showCustomColorGUI(t);
            } else {
                lang.send(t, Translations.Prefix, Translations.NoPerm);
            }
        }), GUIRow.row6, 5);

        return gui;
    }

    //CUSTOM COLORS GUI START HERE

    private PlayerData colorUp(PlayerData data, RGB rgb, FColorType type) {
        if (type.equals(FColorType.MAIN)) {
            switch (rgb) {
                case RED:
                    int r = data.getColor_r() + 1;
                    if (r >= 255) {r=255;}
                    data.setColor_r(r);
                case GREEN:
                    int g = data.getColor_g() + 1;
                    if (g >= 255) {g=255;}
                    data.setColor_g(g);
                case BLUE:
                    int b = data.getColor_b() + 1;
                    if (b >= 255) {b=255;}
                    data.setColor_b(b);
            }
        } else if (type.equals(FColorType.FADE)) {
            switch (rgb) {
                case RED:
                    int r = data.getFade_r() + 1;
                    if (r >= 255) {r=255;}
                    data.setFade_r(r);
                case GREEN:
                    int g = data.getFade_g() + 1;
                    if (g >= 255) {g=255;}
                    data.setFade_g(g);
                case BLUE:
                    int b = data.getFade_b() + 1;
                    if (b >= 255) {b=255;}
                    data.setFade_b(b);
            }
        }
        return data;
    }

    private PlayerData colorDown(PlayerData data, RGB rgb, FColorType type) {
        if (type.equals(FColorType.MAIN)) {
            switch (rgb) {
                case RED:
                    int r = data.getColor_r() - 1;
                    if (r <= 0) {r=0;}
                    data.setColor_r(r);
                case GREEN:
                    int g = data.getColor_g() - 1;
                    if (g <= 0) {g=0;}
                    data.setColor_g(g);
                case BLUE:
                    int b = data.getColor_b() - 1;
                    if (b <= 0) {b=0;}
                    data.setColor_b(b);
            }
        } else if (type.equals(FColorType.FADE)) {
            switch (rgb) {
                case RED:
                    int r = data.getFade_r() - 1;
                    if (r <= 0) {r=0;}
                    data.setFade_r(r);
                case GREEN:
                    int g = data.getFade_g() - 1;
                    if (g <= 0) {g=0;}
                    data.setFade_g(g);
                case BLUE:
                    int b = data.getFade_b() - 1;
                    if (b <= 0) {b=0;}
                    data.setFade_b(b);
            }
        }
        return data;
    }

    private void showCustomColorGUI(Player p) {
        if (p.hasPermission("hubcore.gadgets.fireworkshooter.customcolor")) {
            customColorGuiBuilder(p).show(p);
        } else {
            lang.send(p, Translations.Prefix, Translations.NoPerm);
        }
    }

    private GUIWindow customColorGuiBuilder(Player p) {
        GUIWindow gui = new GUIWindow(lang.get(Translations.Guis_Gadgets_Fireworks_CustomColorTitle), GUIRow.row3, true);

        //back
        gui.setItem(new GUIItem(items.back(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            t.closeInventory();
            showColorsGUI(t);
        }), GUIRow.row2, 1);

        //red up
        gui.setItem(new GUIItem(items.arrowUP(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(colorUp(data, RGB.RED, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showCustomColorGUI(t);
        }), GUIRow.row1, 3);

        //green up
        gui.setItem(new GUIItem(items.arrowUP(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(colorUp(data, RGB.GREEN, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showCustomColorGUI(t);
        }), GUIRow.row1, 4);

        //blue up
        gui.setItem(new GUIItem(items.arrowUP(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(colorUp(data, RGB.BLUE, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showCustomColorGUI(t);
        }), GUIRow.row1, 5);


        FColorType type = fwsHandler.colortype.getOrDefault(p.getUniqueId(), FColorType.MAIN);
        PlayerData dd = pdh.get(p.getUniqueId());
        if (type.equals(FColorType.MAIN)) {
            //current red
            gui.setItem(new GUIItem(items.red(dd.getColor_r()), (e)->{
            }), GUIRow.row2, 3);

            //current green
            gui.setItem(new GUIItem(items.green(dd.getColor_g()), (e)->{
            }), GUIRow.row2, 4);

            //current blue
            gui.setItem(new GUIItem(items.blue(dd.getColor_b()), (e)->{
            }), GUIRow.row2, 5);
        } else if (type.equals(FColorType.FADE)) {
            //current red
            gui.setItem(new GUIItem(items.red(dd.getFade_r()), (e)->{
            }), GUIRow.row2, 3);

            //current green
            gui.setItem(new GUIItem(items.green(dd.getFade_g()), (e)->{
            }), GUIRow.row2, 4);

            //current blue
            gui.setItem(new GUIItem(items.blue(dd.getFade_b()), (e)->{
            }), GUIRow.row2, 5);
        }


        FColor cc = FColor.Black;
        if (type.equals(FColorType.MAIN)) {
            cc = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            cc = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        }
        gui.setItem(new GUIItem(items.colorDisplay(cc), (e)->{
        }), GUIRow.row2, 7);


        //red down
        gui.setItem(new GUIItem(items.arrowDOWN(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(colorDown(data, RGB.RED, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showCustomColorGUI(t);
        }), GUIRow.row3, 3);

        //green down
        gui.setItem(new GUIItem(items.arrowDOWN(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(colorDown(data, RGB.GREEN, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showCustomColorGUI(t);
        }), GUIRow.row3, 4);

        //blue down
        gui.setItem(new GUIItem(items.arrowDOWN(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(colorDown(data, RGB.BLUE, fwsHandler.colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            showCustomColorGUI(t);
        }), GUIRow.row3, 5);
        return gui;
    }

}
