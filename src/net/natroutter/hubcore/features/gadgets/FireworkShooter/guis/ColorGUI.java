package net.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ColorGUI {


    public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();
    public static HashMap<UUID, FColorType> colortype = new HashMap<>();

    public static final PlayerDataHandler pdh = HubCore.getDataHandler();
    private static final Lang lang = HubCore.getLang();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.Gadgets.fireworks.colorsTitle, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    protected static void show(Player p) {
        guiBuilder(p).show(p, true);
    }

    protected static PlayerData changeColor(PlayerData data, FColor color, FColorType type) {
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

    public static void updateDisplay(Player p) {
        GUIWindow gui = getGUI(p);
        FColorType type = colortype.getOrDefault(p.getUniqueId(), FColorType.MAIN);
        PlayerData dd = pdh.get(p.getUniqueId());
        FColor cc = FColor.Black;
        if (type.equals(FColorType.MAIN)) {
            cc = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            cc = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        }
        gui.setItem(new GUIItem(Items.Gadgets.Firework.colorDisplay(cc), (e)->{
        }), GUIWindow.Rows.row4, 4);
    }

    private static GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        //Row 1
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkRed), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkRed, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Red), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Red, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Gold), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Gold, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Yellow), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Yellow, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkGreen), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkGreen, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Green), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Green, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Aqua), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Aqua, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkAqua), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkAqua, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 1);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkBlue), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkBlue, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Blue), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Blue, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.LightPurple), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.LightPurple, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkPurple), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkPurple, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 5);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.White), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.White, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 6);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Gray), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.Gray, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row3, 7);

        //Row 3
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.DarkGray), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkGray, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row4, 3);

        //color dispaly
        FColorType type = colortype.getOrDefault(p.getUniqueId(), FColorType.MAIN);
        PlayerData dd = pdh.get(p.getUniqueId());
        FColor cc = FColor.Black;
        if (type.equals(FColorType.MAIN)) {
            cc = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            cc = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        }
        gui.setItem(new GUIItem(Items.Gadgets.Firework.colorDisplay(cc), (e)->{
        }), GUIWindow.Rows.row4, 4);


        gui.setItem(new GUIItem(Items.Gadgets.Firework.fireworkStar(FColor.Black), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            pdh.set(changeColor(data, FColor.DarkGray, colortype.getOrDefault(t.getUniqueId(), FColorType.MAIN)));
            updateDisplay(t);
        }), GUIWindow.Rows.row4, 5);

        //controls
        gui.setItem(new GUIItem(Items.back(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}

            t.closeInventory();
            SettingsGUI.show(t);
        }), GUIWindow.Rows.row6, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.customColor(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}

            if (t.hasPermission("hubcore.gadgets.fireworkshooter.customcolor")) {
                t.closeInventory();
                GUIS.remove(t.getUniqueId());
                CustomColorGUI.show(t);
            } else {
                t.sendMessage(lang.Prefix + lang.NoPerm);
            }
        }), GUIWindow.Rows.row6, 5);

        return gui;
    }





}
