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
    private static final PlayerDataHandler pdh = HubCore.getDataHandler();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.Gadgets.fireworks.title, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    public static void show(Player p) {
        guiBuilder(p).show(p, true);
    }

    private static GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.SmallBall(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.SMALLBALL.name());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.LargeBall(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.LARGEBALL.name());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.Star(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.STAR.name());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.Creeper(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.CREEPER.name());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.Shapes.Burst(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setShape(FShape.BURST.name());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row2, 6);

        //Additives
        gui.setItem(new GUIItem(Items.Gadgets.Firework.additives.Twinkle(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setTwinkle(!data.getTwinkle());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row4, 2);

        gui.setItem(new GUIItem(Items.Gadgets.Firework.additives.Trail(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setTrail(!data.getTrail());
            pdh.set(data);
            guiBuilder(t);
        }), GUIWindow.Rows.row5, 2);

        PlayerData dd = pdh.get(p.getUniqueId());
        gui.setItem(new GUIItem(Items.Gadgets.Firework.CurrentSettings(dd), (e)->{
        }), GUIWindow.Rows.row5, 4);

        //colors
        FColor color = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        gui.setItem(new GUIItem(Items.Gadgets.Firework.colorDisplay(color), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            t.closeInventory();
            ColorGUI.colortype.put(t.getUniqueId(), FColorType.MAIN);
            ColorGUI.show(t);
        }), GUIWindow.Rows.row4, 6);

        FColor fadecolor = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        gui.setItem(new GUIItem(Items.Gadgets.Firework.fadeColor(fadecolor), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            t.closeInventory();
            ColorGUI.colortype.put(t.getUniqueId(), FColorType.FADE);
            ColorGUI.show(t);
        }), GUIWindow.Rows.row5, 6);


        return gui;
    }

}
