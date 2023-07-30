package fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FWSHandler;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.handlers.guibuilder.Button;
import fi.natroutter.natlibs.handlers.guibuilder.GUI;
import fi.natroutter.natlibs.handlers.guibuilder.GUIFrame;
import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import org.bukkit.entity.Player;

import java.util.List;

public class FwColorGUI extends GUIFrame {

    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private FWSHandler fwsHandler = HubCore.getFwsHandler();

    public FwColorGUI() {
        super(Lang.Guis_Gadgets_Fireworks_ColorsTitle, Rows.row6);
    }

    private Button builderButton(FColor color) {
        return new Button(Items.fireworkStar(color), (e,g)->{
            Player p = e.getPlayer();
            PlayerData data = pdh.get(e);
            FColorType type = fwsHandler.colortype.getOrDefault(p.getUniqueId(), FColorType.MAIN);
            pdh.set(changeColor(data, color, type));
        });
    }

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

    @Override
    protected boolean onShow(Player player, GUI gui, List<Object> args) {

        gui.setButton(builderButton(FColor.DarkRed), Rows.row2, 1);
        gui.setButton(builderButton(FColor.Red), Rows.row2, 2);
        gui.setButton(builderButton(FColor.Gold), Rows.row2, 3);
        gui.setButton(builderButton(FColor.Yellow), Rows.row2, 4);
        gui.setButton(builderButton(FColor.DarkGreen), Rows.row2, 5);
        gui.setButton(builderButton(FColor.Green), Rows.row2, 6);
        gui.setButton(builderButton(FColor.Aqua), Rows.row2, 7);

        gui.setButton(builderButton(FColor.DarkAqua), Rows.row3, 1);
        gui.setButton(builderButton(FColor.DarkBlue), Rows.row3, 2);
        gui.setButton(builderButton(FColor.Blue), Rows.row3, 3);
        gui.setButton(builderButton(FColor.LightPurple), Rows.row3, 4);
        gui.setButton(builderButton(FColor.DarkPurple), Rows.row3, 5);
        gui.setButton(builderButton(FColor.White), Rows.row3, 6);
        gui.setButton(builderButton(FColor.Gray), Rows.row3, 7);

        gui.setButton(builderButton(FColor.DarkGray), Rows.row4, 3);

        //color dispaly
        FColorType type = fwsHandler.colortype.getOrDefault(player.getUniqueId(), FColorType.MAIN);
        PlayerData dd = pdh.get(player.getUniqueId());
        FColor cc = FColor.Black;
        if (type.equals(FColorType.MAIN)) {
            cc = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        } else if (type.equals(FColorType.FADE)) {
            cc = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        }
        gui.setItem(Items.colorDisplay(cc), Rows.row4, 4);


        gui.setButton(builderButton(FColor.Black), Rows.row4, 5);

        //controls

        gui.setButton(new Button(Items.back(), (e,g)->{
            switchGUI(e.getPlayer(), HubCore.getFwSettingsGUI());
        }),Rows.row6,3);

        gui.setButton(new Button(Items.customColor(), (e,g)->{
            Player p = e.getPlayer();
            if (!p.hasPermission("hubcore.gadgets.fireworkshooter.customcolor")) {
                p.sendMessage(Lang.NoPerm.prefixed());
                return;
            }
            switchGUI(p, HubCore.getFwCustomGUI());
        }), Rows.row6, 5);

        return true;
    }
}
