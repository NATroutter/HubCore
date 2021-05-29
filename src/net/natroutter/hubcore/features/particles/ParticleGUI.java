package net.natroutter.hubcore.features.particles;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.Lang;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ParticleGUI {

    public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();
    private static final Lang lang = HubCore.getLang();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.particles.title, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }


    public static void show(Player p, PlayerData data) {
        guiBuilder(p, data).show(p, true);
    }

    private static BaseItem particleItem(Material mat, particleTypes type) {
        BaseItem item = new BaseItem(mat);
        item.setDisplayName(type.getLangName());
        ArrayList<String> lore = new ArrayList<>();
        for (String line : lang.Guis.particles.effect_lore) {
            StringHandler s = new StringHandler(line);
            s.replaceAll("{need}", type.getNeed());
            lore.add(s.build());
        }
        item.setLore(lore);
        return item;
    }

    private static GUIWindow guiBuilder(Player p, PlayerData data) {
        GUIWindow gui = getGUI(p);

        gui.setCloseEvent((e)->{
            PlayerDataHandler.updateForID(data);
        });

        //Row 1
        gui.setItem(new GUIItem(particleItem(Material.RED_MUSHROOM, particleTypes.WITCH), (e)->{
            if (!p.hasPermission(particleTypes.WITCH.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.WITCH.name());
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(particleItem(Material.TOTEM_OF_UNDYING, particleTypes.TOTEM), (e)->{
            if (!p.hasPermission(particleTypes.TOTEM.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.TOTEM.name());
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(particleItem(Material.INK_SAC, particleTypes.INK), (e)->{
            if (!p.hasPermission(particleTypes.INK.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.INK.name());
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_TORCH, particleTypes.SOULFLAME), (e)->{
            if (!p.hasPermission(particleTypes.SOULFLAME.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SOULFLAME.name());
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(particleItem(Material.SNOWBALL, particleTypes.CLOUD), (e)->{
            if (!p.hasPermission(particleTypes.CLOUD.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.CLOUD.name());
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_SOIL, particleTypes.SOUL), (e)->{
            if (!p.hasPermission(particleTypes.SOUL.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SOUL.name());
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(particleItem(Material.NOTE_BLOCK, particleTypes.NOTES), (e)->{
            if (!p.hasPermission(particleTypes.NOTES.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.NOTES.name());
        }), GUIWindow.Rows.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(particleItem(Material.CONDUIT, particleTypes.CONDUIT), (e)->{
            if (!p.hasPermission(particleTypes.CONDUIT.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.CONDUIT.name());
        }), GUIWindow.Rows.row3, 1);

        gui.setItem(new GUIItem(particleItem(Material.LAVA_BUCKET, particleTypes.LAVA), (e)->{
            if (!p.hasPermission(particleTypes.LAVA.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.LAVA.name());
        }), GUIWindow.Rows.row3, 2);

        gui.setItem(new GUIItem(particleItem(Material.APPLE, particleTypes.HEARTS), (e)->{
            if (!p.hasPermission(particleTypes.HEARTS.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.HEARTS.name());
        }), GUIWindow.Rows.row3, 3);

        gui.setItem(new GUIItem(particleItem(Material.POPPY, particleTypes.HAPPY), (e)->{
            if (!p.hasPermission(particleTypes.HAPPY.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.HAPPY.name());
        }), GUIWindow.Rows.row3, 4);

        gui.setItem(new GUIItem(particleItem(Material.VILLAGER_SPAWN_EGG, particleTypes.ANGRY), (e)->{
            if (!p.hasPermission(particleTypes.ANGRY.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ANGRY.name());
        }), GUIWindow.Rows.row3, 5);

        gui.setItem(new GUIItem(particleItem(Material.ENCHANTED_BOOK, particleTypes.ENCHANT), (e)->{
            if (!p.hasPermission(particleTypes.ENCHANT.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ENCHANT.name());
        }), GUIWindow.Rows.row3, 6);

        gui.setItem(new GUIItem(particleItem(Material.END_ROD, particleTypes.ENDROD), (e)->{
            if (!p.hasPermission(particleTypes.ENDROD.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ENDROD.name());
        }), GUIWindow.Rows.row3, 7);


        //row 3
        gui.setItem(new GUIItem(particleItem(Material.ENCHANTING_TABLE, particleTypes.ENCHANTING), (e)->{
            if (!p.hasPermission(particleTypes.ENCHANTING.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ENCHANTING.name());
        }), GUIWindow.Rows.row4, 1);

        gui.setItem(new GUIItem(particleItem(Material.REDSTONE, particleTypes.RAINBOWDUST), (e)->{
            if (!p.hasPermission(particleTypes.RAINBOWDUST.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.RAINBOWDUST.name());
        }), GUIWindow.Rows.row4, 2);

        gui.setItem(new GUIItem(particleItem(Material.DRAGON_EGG, particleTypes.DRAGONSBREATH), (e)->{
            if (!p.hasPermission(particleTypes.DRAGONSBREATH.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.DRAGONSBREATH.name());
        }), GUIWindow.Rows.row4, 3);

        gui.setItem(new GUIItem(particleItem(Material.STONE_SWORD, particleTypes.CRITICAL), (e)->{
            if (!p.hasPermission(particleTypes.CRITICAL.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.CRITICAL.name());
        }), GUIWindow.Rows.row4, 4);

        gui.setItem(new GUIItem(particleItem(Material.PILLAGER_SPAWN_EGG, particleTypes.DAMAGE), (e)->{
            if (!p.hasPermission(particleTypes.DAMAGE.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.DAMAGE.name());
        }), GUIWindow.Rows.row4, 5);

        gui.setItem(new GUIItem(particleItem(Material.FLINT_AND_STEEL, particleTypes.SMOKE), (e)->{
            if (!p.hasPermission(particleTypes.SMOKE.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SMOKE.name());
        }), GUIWindow.Rows.row4, 6);

        gui.setItem(new GUIItem(particleItem(Material.BAMBOO, particleTypes.SNEEZE), (e)->{
            if (!p.hasPermission(particleTypes.SNEEZE.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SNEEZE.name());
        }), GUIWindow.Rows.row4, 7);


        //settings
        gui.setItem(new GUIItem(Items.Particle.Disable(), (e)->{
            data.setParticle(particleTypes.DISABLED.name());
        }), GUIWindow.Rows.row6, 2);

        ParticleMode mode = ParticleMode.fromString(data.getParticlemode());
        gui.setItem(new GUIItem(Items.Particle.changemode(mode), (e)->{
            if (mode != null && mode.next() != null) {
                data.setParticlemode(mode.next().name());
                guiBuilder(p, data);
            }
        }), GUIWindow.Rows.row6, 6);

        return gui;
    }








}
