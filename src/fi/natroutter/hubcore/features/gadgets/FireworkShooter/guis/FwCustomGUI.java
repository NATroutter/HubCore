package fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.RGB;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.handlers.guibuilder.Button;
import fi.natroutter.natlibs.handlers.guibuilder.GUI;
import fi.natroutter.natlibs.handlers.guibuilder.GUIFrame;
import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class FwCustomGUI extends GUIFrame {

    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private FwSettingsGUI fwSettingsGUI = HubCore.getFwSettingsGUI();
    private FWSHandler fwsHandler = HubCore.getFwsHandler();

    public FwCustomGUI() {
        super(Lang.Guis_Gadgets_Fireworks_CustomColorTitle, Rows.row3);
    }

    private PlayerData colorUp(PlayerData data, RGB rgb, FColorType type, int increment) {
        if (type.equals(FColorType.MAIN)) {
            switch (rgb) {
                case RED:
                    int r = data.getColor_r() + increment;
                    if (r >= 255) {r=255;}
                    data.setColor_r(r);
                case GREEN:
                    int g = data.getColor_g() + increment;
                    if (g >= 255) {g=255;}
                    data.setColor_g(g);
                case BLUE:
                    int b = data.getColor_b() + increment;
                    if (b >= 255) {b=255;}
                    data.setColor_b(b);
            }
        } else if (type.equals(FColorType.FADE)) {
            switch (rgb) {
                case RED:
                    int r = data.getFade_r() + increment;
                    if (r >= 255) {r=255;}
                    data.setFade_r(r);
                case GREEN:
                    int g = data.getFade_g() + increment;
                    if (g >= 255) {g=255;}
                    data.setFade_g(g);
                case BLUE:
                    int b = data.getFade_b() + increment;
                    if (b >= 255) {b=255;}
                    data.setFade_b(b);
            }
        }
        return data;
    }

    private PlayerData colorDown(PlayerData data, RGB rgb, FColorType type, int increment) {
        if (type.equals(FColorType.MAIN)) {
            switch (rgb) {
                case RED:
                    int r = data.getColor_r() - increment;
                    if (r <= 0) {r=0;}
                    data.setColor_r(r);
                case GREEN:
                    int g = data.getColor_g() - increment;
                    if (g <= 0) {g=0;}
                    data.setColor_g(g);
                case BLUE:
                    int b = data.getColor_b() - increment;
                    if (b <= 0) {b=0;}
                    data.setColor_b(b);
            }
        } else if (type.equals(FColorType.FADE)) {
            switch (rgb) {
                case RED:
                    int r = data.getFade_r() - increment;
                    if (r <= 0) {r=0;}
                    data.setFade_r(r);
                case GREEN:
                    int g = data.getFade_g() - increment;
                    if (g <= 0) {g=0;}
                    data.setFade_g(g);
                case BLUE:
                    int b = data.getFade_b() - increment;
                    if (b <= 0) {b=0;}
                    data.setFade_b(b);
            }
        }
        return data;
    }

    private enum Dire {UP,DOWN}
    private Button changeColor(RGB rgb, Dire dire) {
        return new Button(Items.back(), (e,g)->{
            ClickType click = e.getClickType();
            if (click.equals(ClickType.LEFT) || click.equals(ClickType.SHIFT_LEFT)) {
                int increment = click == ClickType.LEFT ? 1 : 10;
                PlayerData data = pdh.get(e);
                FColorType type = fwsHandler.colortype.getOrDefault(e.getPlayer().getUniqueId(), FColorType.MAIN);
                switch (dire) {
                    case UP -> pdh.set(colorUp(data, rgb, type, increment));
                    case DOWN -> pdh.set(colorDown(data, rgb, type, increment));
                }
            }
        });
    }

    @Override
    protected boolean onShow(Player player, GUI gui, List<Object> args) {

        //back
        gui.setButton(new Button(Items.back(), (e,g)->{
            switchGUI(e.getPlayer(), HubCore.getFwSettingsGUI());
        }),Rows.row2,1);

        gui.setButton(changeColor(RGB.RED,Dire.UP), Rows.row1, 3);
        gui.setButton(changeColor(RGB.GREEN,Dire.UP), Rows.row1, 4);
        gui.setButton(changeColor(RGB.BLUE,Dire.UP), Rows.row1, 5);


        FColorType type = fwsHandler.colortype.getOrDefault(player.getUniqueId(), FColorType.MAIN);
        PlayerData dd = pdh.get(player.getUniqueId());
        if (type.equals(FColorType.MAIN)) {

            gui.setItem(Items.red(dd.getColor_r()), Rows.row2,3);
            gui.setItem(Items.green(dd.getColor_g()), Rows.row2,4);
            gui.setItem(Items.blue(dd.getColor_b()), Rows.row2,5);

        } else if (type.equals(FColorType.FADE)) {
            gui.setItem(Items.red(dd.getFade_r()), Rows.row2,3);
            gui.setItem(Items.green(dd.getFade_g()), Rows.row2,4);
            gui.setItem(Items.blue(dd.getFade_b()), Rows.row2,5);
        }


        FColor cc = FColor.Black;
        if (type.equals(FColorType.MAIN)) {
            cc = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            cc = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        }
        gui.setItem(Items.colorDisplay(cc),Rows.row2,7);

        gui.setButton(changeColor(RGB.RED,Dire.DOWN), Rows.row3, 3);
        gui.setButton(changeColor(RGB.GREEN,Dire.DOWN), Rows.row3, 4);
        gui.setButton(changeColor(RGB.BLUE,Dire.DOWN), Rows.row3, 5);

        return true;
    }
}
