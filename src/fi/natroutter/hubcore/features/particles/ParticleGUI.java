package fi.natroutter.hubcore.features.particles;

import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.handlers.Database.PlayerData;
import fi.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.handlers.guibuilder.Button;
import fi.natroutter.natlibs.handlers.guibuilder.GUI;
import fi.natroutter.natlibs.handlers.guibuilder.GUIFrame;
import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import fi.natroutter.natlibs.objects.BaseItem;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ParticleGUI extends GUIFrame {

    public HashMap<UUID, ParticleMode> pmode = new HashMap<>();

    private PlayerDataHandler pdh = HubCore.getDataHandler();

    public ParticleGUI() {
        super(Lang.Guis_Particles_Title, Rows.row6);
    }

    private BaseItem particleItem(Player p, Material mat, ParticleTypes type) {
        PlayerData data = pdh.get(p.getUniqueId());
        ParticleTypes pType = ParticleTypes.fromString(data.getParticle());

        BaseItem item = new BaseItem(mat);
        item.name(type.getLangName());
        item.lore(Lang.Guis_Particles_Effect_Lore.asComponentList(
                Placeholder.component("need", type.getNeed().asComponent())
        ));
        if (type.equals(pType)) {
            item.setGlow(true);
        }
        return item;
    }

    private Button ParticleButton(Player player, ParticleTypes particle) {
        return new Button(particleItem(player, particle.getMaterial(), particle), (e,g)->{
            if (!e.getClickType().equals(ClickType.LEFT))return;
            Player p = e.getPlayer();
            PlayerData data = pdh.get(p.getUniqueId());
            if (!p.hasPermission(particle.getPermission())) {
                p.sendMessage(Lang.NoPerm.prefixed());
                return;
            }
            data.setParticle(particle.name());
            pdh.set(data);
            g.close(p);
        });
    }

    @Override
    protected boolean onShow(Player player, GUI gui, List<Object> args) {

        gui.setButton(ParticleButton(player, ParticleTypes.WITCH),Rows.row2,1);
        gui.setButton(ParticleButton(player, ParticleTypes.TOTEM),Rows.row2,2);
        gui.setButton(ParticleButton(player, ParticleTypes.INK),Rows.row2,3);
        gui.setButton(ParticleButton(player, ParticleTypes.SOULFLAME),Rows.row2,4);
        gui.setButton(ParticleButton(player, ParticleTypes.CLOUD),Rows.row2,5);
        gui.setButton(ParticleButton(player, ParticleTypes.SOUL),Rows.row2,6);
        gui.setButton(ParticleButton(player, ParticleTypes.NOTES),Rows.row2,7);

        gui.setButton(ParticleButton(player, ParticleTypes.CONDUIT),Rows.row3,1);
        gui.setButton(ParticleButton(player, ParticleTypes.LAVA),Rows.row3,2);
        gui.setButton(ParticleButton(player, ParticleTypes.HEARTS),Rows.row3,3);
        gui.setButton(ParticleButton(player, ParticleTypes.HAPPY),Rows.row3,4);
        gui.setButton(ParticleButton(player, ParticleTypes.ANGRY),Rows.row3,5);
        gui.setButton(ParticleButton(player, ParticleTypes.ENCHANT),Rows.row3,6);
        gui.setButton(ParticleButton(player, ParticleTypes.ENDROD),Rows.row3,7);

        gui.setButton(ParticleButton(player, ParticleTypes.ENCHANTING),Rows.row4,1);
        gui.setButton(ParticleButton(player, ParticleTypes.RAINBOWDUST),Rows.row4,2);
        gui.setButton(ParticleButton(player, ParticleTypes.DRAGONSBREATH),Rows.row4,3);
        gui.setButton(ParticleButton(player, ParticleTypes.CRITICAL),Rows.row4,4);
        gui.setButton(ParticleButton(player, ParticleTypes.DAMAGE),Rows.row4,5);
        gui.setButton(ParticleButton(player, ParticleTypes.SMOKE),Rows.row4,6);
        gui.setButton(ParticleButton(player, ParticleTypes.SNEEZE),Rows.row4,7);

        gui.setButton(new Button(Items.particle_Disable(), (e,g)->{
            if (!e.getClickType().equals(ClickType.LEFT))return;
            Player p = e.getPlayer();
            PlayerData data = pdh.get(p.getUniqueId());
            data.setParticle(ParticleTypes.DISABLED.name());
            pdh.set(data);
            g.close(p);
        }), Rows.row6, 2);

        gui.setButton(new Button(Items.particle_ChangeMode(pmode.getOrDefault(player.getUniqueId(), ParticleMode.CLOUD)), (e,g)->{
            if (!e.getClickType().equals(ClickType.LEFT))return;
            Player p = e.getPlayer();
            PlayerData data = pdh.get(p.getUniqueId());

            ParticleMode mode = ParticleMode.fromString(data.getParticlemode());

            if (mode != null && mode.next() != null) {
                data.setParticlemode(mode.next().name());
                pmode.put(p.getUniqueId(), mode.next());
            }
            pdh.set(data);
        }), Rows.row6, 6);

        return true;
    }
}
