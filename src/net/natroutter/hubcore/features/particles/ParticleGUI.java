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
    public static HashMap<UUID, ParticleMode> pmode = new HashMap<>();

    private static final Lang lang = HubCore.getLang();
    private static final PlayerDataHandler pdh = HubCore.getDataHandler();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.Guis.particles.title, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }


    public static void show(Player p) {
        guiBuilder(p).show(p, true);
    }

    private static BaseItem particleItem(Material mat, ParticleTypes type) {
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

    private static GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        //Row 1
        gui.setItem(new GUIItem(particleItem(Material.RED_MUSHROOM, ParticleTypes.WITCH), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.WITCH.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.WITCH.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(particleItem(Material.TOTEM_OF_UNDYING, ParticleTypes.TOTEM), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!p.hasPermission(ParticleTypes.TOTEM.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.TOTEM.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(particleItem(Material.INK_SAC, ParticleTypes.INK), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.INK.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.INK.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_TORCH, ParticleTypes.SOULFLAME), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SOULFLAME.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SOULFLAME.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(particleItem(Material.SNOWBALL, ParticleTypes.CLOUD), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.CLOUD.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.CLOUD.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_SOIL, ParticleTypes.SOUL), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SOUL.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SOUL.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(particleItem(Material.NOTE_BLOCK, ParticleTypes.NOTES), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.NOTES.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.NOTES.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(particleItem(Material.CONDUIT, ParticleTypes.CONDUIT), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.CONDUIT.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.CONDUIT.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 1);

        gui.setItem(new GUIItem(particleItem(Material.LAVA_BUCKET, ParticleTypes.LAVA), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.LAVA.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.LAVA.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 2);

        gui.setItem(new GUIItem(particleItem(Material.APPLE, ParticleTypes.HEARTS), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.HEARTS.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.HEARTS.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 3);

        gui.setItem(new GUIItem(particleItem(Material.POPPY, ParticleTypes.HAPPY), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.HAPPY.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.HAPPY.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 4);

        gui.setItem(new GUIItem(particleItem(Material.VILLAGER_SPAWN_EGG, ParticleTypes.ANGRY), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ANGRY.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ANGRY.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 5);

        gui.setItem(new GUIItem(particleItem(Material.ENCHANTED_BOOK, ParticleTypes.ENCHANT), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ENCHANT.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ENCHANT.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 6);

        gui.setItem(new GUIItem(particleItem(Material.END_ROD, ParticleTypes.ENDROD), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ENDROD.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ENDROD.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 7);


        //row 3
        gui.setItem(new GUIItem(particleItem(Material.ENCHANTING_TABLE, ParticleTypes.ENCHANTING), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ENCHANTING.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ENCHANTING.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 1);

        gui.setItem(new GUIItem(particleItem(Material.REDSTONE, ParticleTypes.RAINBOWDUST), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.RAINBOWDUST.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.RAINBOWDUST.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 2);

        gui.setItem(new GUIItem(particleItem(Material.DRAGON_EGG, ParticleTypes.DRAGONSBREATH), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.DRAGONSBREATH.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.DRAGONSBREATH.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 3);

        gui.setItem(new GUIItem(particleItem(Material.STONE_SWORD, ParticleTypes.CRITICAL), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.CRITICAL.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.CRITICAL.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 4);

        gui.setItem(new GUIItem(particleItem(Material.PILLAGER_SPAWN_EGG, ParticleTypes.DAMAGE), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.DAMAGE.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.DAMAGE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 5);

        gui.setItem(new GUIItem(particleItem(Material.FLINT_AND_STEEL, ParticleTypes.SMOKE), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SMOKE.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SMOKE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 6);

        gui.setItem(new GUIItem(particleItem(Material.BAMBOO, ParticleTypes.SNEEZE), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SNEEZE.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SNEEZE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 7);


        //settings
        gui.setItem(new GUIItem(Items.Particle.Disable(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setParticle(ParticleTypes.DISABLED.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row6, 2);


        gui.setItem(new GUIItem(Items.Particle.changemode(pmode.getOrDefault(p.getUniqueId(), ParticleMode.CLOUD)), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            ParticleMode mode = ParticleMode.fromString(data.getParticlemode());

            if (mode != null && mode.next() != null) {
                data.setParticlemode(mode.next().name());
                pmode.put(t.getUniqueId(), mode.next());
                guiBuilder(t);
            }
            pdh.set(data);

        }), GUIWindow.Rows.row6, 6);

        return gui;
    }








}
