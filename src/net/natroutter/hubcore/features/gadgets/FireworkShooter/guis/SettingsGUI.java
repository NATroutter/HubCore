package net.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import net.natroutter.hubcore.features.gadgets.FireworkShooter.FShape;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SettingsGUI {

    public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();
    private static final Lang lang = HubCore.getLang();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.Gadgets.fireworks.title, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    public static void show(Player p, PlayerData data) {
        guiBuilder(p, data).show(p, true);
    }

    private static GUIWindow guiBuilder(Player p, PlayerData data) {
        GUIWindow gui = getGUI(p);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.SmallBall(), (e)->{
            data.setShape(FShape.SMALLBALL.name());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.LargeBall(), (e)->{
            data.setShape(FShape.LARGEBALL.name());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.Star(), (e)->{
            data.setShape(FShape.STAR.name());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.Creeper(), (e)->{
            data.setShape(FShape.CREEPER.name());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.Burst(), (e)->{
            data.setShape(FShape.BURST.name());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row2, 6);

        //Additives
        gui.setItem(new GUIItem(Items.Gadgets.Firework.additives.Twinkle(), (e)->{
            data.setTwinkle(!data.getTwinkle());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row4, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.additives.Trail(), (e)->{
            data.setTrail(!data.getTrail());
            guiBuilder(p, data);
        }), GUIWindow.Rows.row5, 2);

        //settings
        gui.setItem(new GUIItem(Items.Gadgets.Firework.save(), (e)->{
            PlayerDataHandler.updateForID(data);
            p.closeInventory();
        }), GUIWindow.Rows.row4, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.CurrentSettings(data), (e)->{
        }), GUIWindow.Rows.row5, 4);

        //colors
        FColor color = FColor.fromRGB(data.getColor_r(), data.getColor_g(), data.getColor_b());
        gui.setItem(new GUIItem(Items.Gadgets.Firework.colorDisplay(color), (e)->{
            p.closeInventory();
            ColorGUI.show(p, data, FColorType.MAIN);
        }), GUIWindow.Rows.row4, 6);

        FColor fadecolor = FColor.fromRGB(data.getFade_r(), data.getFade_g(), data.getFade_b());
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fadeColor(fadecolor), (e)->{
            p.closeInventory();
            ColorGUI.show(p, data, FColorType.FADE);
        }), GUIWindow.Rows.row5, 6);


        return gui;
    }

}
