package fi.natroutter.hubcore.features.gadgets.FireworkShooter.guis;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColor;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FColorType;
import fi.natroutter.hubcore.features.gadgets.FireworkShooter.FShape;
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

public class FwSettingsGUI extends GUIFrame {

    private PlayerDataHandler pdh = HubCore.getDataHandler();
    private FWSHandler fwsHandler = HubCore.getFwsHandler();

    public FwSettingsGUI() {
        super(Lang.Guis_Gadgets_Fireworks_Title, Rows.row6);
    }

    private Button fwButton(FShape shape) {
        Button btn = new Button(shape.getItem(), (e,g)-> {
            PlayerData data = pdh.get(e);
            data.setShape(shape.name());
            pdh.set(data);
        });
        return btn;
    }

    @Override
    protected boolean onShow(Player player, GUI gui, List<Object> args) {

        gui.setButton(fwButton(FShape.SMALLBALL), Rows.row2, 2);
        gui.setButton(fwButton(FShape.LARGEBALL), Rows.row2, 3);
        gui.setButton(fwButton(FShape.STAR), Rows.row2, 4);
        gui.setButton(fwButton(FShape.CREEPER), Rows.row2, 5);
        gui.setButton(fwButton(FShape.BURST), Rows.row2, 6);

        //Additives
        gui.setButton(new Button(Items.Twinkle(), (e,g)->{
            PlayerData data = pdh.get(e);
            data.setTwinkle(!data.getTwinkle());
            pdh.set(data);
        }), Rows.row4,2);
        gui.setButton(new Button(Items.Twinkle(), (e,g)->{
            PlayerData data = pdh.get(e);
            data.setTrail(!data.getTrail());
            pdh.set(data);
        }), Rows.row5,2);

        PlayerData dd = pdh.get(player);
        gui.setItem(Items.CurrentSettings(dd),Rows.row5,4);

        //colors
        FColor color = FColor.fromRGB(dd.getColor_r(), dd.getColor_g(), dd.getColor_b());
        gui.setButton(new Button(Items.colorDisplay(color),(e,g)->{
            Player p = e.getPlayer();
            fwsHandler.colortype.put(p.getUniqueId(), FColorType.MAIN);
            switchGUI(p, HubCore.getFwColorGUI());
        }),Rows.row4,6);

        FColor fadecolor = FColor.fromRGB(dd.getFade_r(), dd.getFade_g(), dd.getFade_b());
        gui.setButton(new Button(Items.fadeColor(fadecolor),(e,g)->{
            Player p = e.getPlayer();
            fwsHandler.colortype.put(p.getUniqueId(), FColorType.FADE);
            switchGUI(p, HubCore.getFwColorGUI());
        }),Rows.row5,6);

        return true;
    }
}
