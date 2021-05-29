package net.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ColorGUI {


    public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();
    private static final Lang lang = HubCore.getLang();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.Gadgets.fireworks.colorsTitle, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    protected static void show(Player p, PlayerData data, FColorType type) {
        guiBuilder(p, data, type).show(p, true);
    }

    protected static PlayerData changeColor(PlayerData data, FColor color, FColorType type) {
        if (type.equals(FColorType.MAIN)) {
            data.setColor_r(color.getR());
            data.setColor_g(color.getG());
            data.setColor_b(color.getB());
        } else if (type.equals(FColorType.FADE)) {
            data.setFade_r(color.getR());
            data.setFade_g(color.getG());
            data.setFade_b(color.getB());
        }
        return data;
    }

    private static GUIWindow guiBuilder(Player p, PlayerData data, FColorType type) {
        GUIWindow gui = getGUI(p);

        //Row 1
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkRed), (e)->{
            guiBuilder(p, changeColor(data, FColor.DarkRed, type), type);
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Red), (e)->{
            guiBuilder(p, changeColor(data, FColor.Red, type), type);
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Gold), (e)->{
            guiBuilder(p, changeColor(data, FColor.Gold, type), type);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Yellow), (e)->{
            guiBuilder(p, changeColor(data, FColor.Yellow, type), type);
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkGreen), (e)->{
            guiBuilder(p, changeColor(data, FColor.DarkGreen, type), type);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Green), (e)->{
            guiBuilder(p, changeColor(data, FColor.Green, type), type);
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Aqua), (e)->{
            guiBuilder(p, changeColor(data, FColor.Aqua, type), type);
        }), GUIWindow.Rows.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkAqua), (e)->{
            guiBuilder(p, changeColor(data, FColor.DarkAqua, type), type);
        }), GUIWindow.Rows.row3, 1);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkBlue), (e)->{
            guiBuilder(p, changeColor(data, FColor.DarkBlue, type), type);
        }), GUIWindow.Rows.row3, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Blue), (e)->{
            guiBuilder(p, changeColor(data, FColor.Blue, type), type);
        }), GUIWindow.Rows.row3, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.LightPurple), (e)->{
            guiBuilder(p, changeColor(data, FColor.LightPurple, type), type);
        }), GUIWindow.Rows.row3, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkPurple), (e)->{
            guiBuilder(p, changeColor(data, FColor.DarkPurple, type), type);
        }), GUIWindow.Rows.row3, 5);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.White), (e)->{
            guiBuilder(p, changeColor(data, FColor.White, type), type);
        }), GUIWindow.Rows.row3, 6);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Gray), (e)->{
            guiBuilder(p, changeColor(data, FColor.Gray, type), type);
        }), GUIWindow.Rows.row3, 7);

        //Row 3
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkGray), (e)->{
            guiBuilder(p, changeColor(data, FColor.DarkGray, type), type);
        }), GUIWindow.Rows.row4, 3);

        //color dispaly
        FColor color = null;
        if (type.equals(FColorType.MAIN)) {
            color = FColor.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            color = FColor.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b());
        }
        if (color != null) {
            gui.setItem(new GUIItem(Items.Gadgets.Firework.colorDisplay(color), (e)->{
            }), GUIWindow.Rows.row4, 4);
        }


        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Black), (e)->{
            guiBuilder(p, changeColor(data, FColor.Black, type), type);
        }), GUIWindow.Rows.row4, 5);

        //controls
        gui.setItem(new GUIItem(Items.back(), (e)->{
            p.closeInventory();
            SettingsGUI.show(p, data);
        }), GUIWindow.Rows.row6, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.customColor(), (e)->{
            if (p.hasPermission("hubcore.gadgets.fireworkshooter.customcolor")) {
                p.closeInventory();
                CustomColorGUI.show(p, data, type);
            } else {
                p.sendMessage(lang.Prefix + lang.NoPerm);
            }
        }), GUIWindow.Rows.row6, 5);

        return gui;
    }





}
