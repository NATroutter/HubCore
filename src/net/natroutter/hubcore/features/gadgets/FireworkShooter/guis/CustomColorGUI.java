package net.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.RGB;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CustomColorGUI {

    public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();
    private static final Lang lang = HubCore.getLang();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.Gadgets.fireworks.CustomColorTitle, GUIWindow.Rows.row3, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    private static PlayerData colorUp(PlayerData data, RGB rgb, FColorType type) {
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

    private static PlayerData colorDown(PlayerData data, RGB rgb, FColorType type) {
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

    protected static void show(Player p, PlayerData data, FColorType type) {
        if (p.hasPermission("hubcore.gadgets.fireworkshooter.customcolor")) {
            guiBuilder(p, data, type).show(p, true);
        } else {
            p.sendMessage(lang.Prefix + lang.NoPerm);
        }
    }

    private static GUIWindow guiBuilder(Player p, PlayerData data, FColorType type) {
        GUIWindow gui = getGUI(p);

        //back
        gui.setItem(new GUIItem(Items.back(), (e)->{
            p.closeInventory();
            ColorGUI.show(p, data, type);
            guiBuilder(p, data, type);
        }), GUIWindow.Rows.row2, 1);

        //red up
        gui.setItem(new GUIItem(Items.arrowUP(), (e)->{
            guiBuilder(p, colorUp(data, RGB.RED, type), type);
        }), GUIWindow.Rows.row1, 3);

        //green up
        gui.setItem(new GUIItem(Items.arrowUP(), (e)->{
            guiBuilder(p, colorUp(data, RGB.GREEN, type), type);
        }), GUIWindow.Rows.row1, 4);

        //blue up
        gui.setItem(new GUIItem(Items.arrowUP(), (e)->{
            guiBuilder(p, colorUp(data, RGB.BLUE, type), type);
        }), GUIWindow.Rows.row1, 5);

        if (type.equals(FColorType.MAIN)) {
            //current red
            gui.setItem(new GUIItem(Items.Gadgets.Firework.red(data.getColor_r()), (e)->{
            }), GUIWindow.Rows.row2, 3);

            //current green
            gui.setItem(new GUIItem(Items.Gadgets.Firework.green(data.getColor_g()), (e)->{
            }), GUIWindow.Rows.row2, 4);

            //current blue
            gui.setItem(new GUIItem(Items.Gadgets.Firework.blue(data.getColor_b()), (e)->{
            }), GUIWindow.Rows.row2, 5);
        } else if (type.equals(FColorType.FADE)) {
            //current red
            gui.setItem(new GUIItem(Items.Gadgets.Firework.red(data.getFade_r()), (e)->{
            }), GUIWindow.Rows.row2, 3);

            //current green
            gui.setItem(new GUIItem(Items.Gadgets.Firework.green(data.getFade_g()), (e)->{
            }), GUIWindow.Rows.row2, 4);

            //current blue
            gui.setItem(new GUIItem(Items.Gadgets.Firework.blue(data.getFade_b()), (e)->{
            }), GUIWindow.Rows.row2, 5);
        }

        //red down
        gui.setItem(new GUIItem(Items.arrowDOWN(), (e)->{
            guiBuilder(p, colorDown(data, RGB.RED, type), type);
        }), GUIWindow.Rows.row3, 3);

        //green down
        gui.setItem(new GUIItem(Items.arrowDOWN(), (e)->{
            guiBuilder(p, colorDown(data, RGB.GREEN, type), type);
        }), GUIWindow.Rows.row3, 4);

        //blue down
        gui.setItem(new GUIItem(Items.arrowDOWN(), (e)->{
            guiBuilder(p, colorDown(data, RGB.BLUE, type), type);
        }), GUIWindow.Rows.row3, 5);

        //color dispaly
        FColor color = null;
        if (type.equals(FColorType.MAIN)) {
            color = FColor.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            color = FColor.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b());
        }
        if (color != null) {
            gui.setItem(new GUIItem(Items.Gadgets.Firework.colorDisplay(color), (e)->{
            }), GUIWindow.Rows.row2, 7);
        }

        return gui;
    }

}
