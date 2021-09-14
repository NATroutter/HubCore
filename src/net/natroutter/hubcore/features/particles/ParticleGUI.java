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

    private static GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        //Row 1
        gui.setItem(new GUIItem(particleItem(Material.RED_MUSHROOM, particleTypes.WITCH), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.WITCH.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.WITCH.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(particleItem(Material.TOTEM_OF_UNDYING, particleTypes.TOTEM), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!p.hasPermission(particleTypes.TOTEM.getPermission())) {
                p.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.TOTEM.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(particleItem(Material.INK_SAC, particleTypes.INK), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.INK.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.INK.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_TORCH, particleTypes.SOULFLAME), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.SOULFLAME.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SOULFLAME.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(particleItem(Material.SNOWBALL, particleTypes.CLOUD), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.CLOUD.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.CLOUD.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_SOIL, particleTypes.SOUL), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.SOUL.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SOUL.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(particleItem(Material.NOTE_BLOCK, particleTypes.NOTES), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.NOTES.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.NOTES.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(particleItem(Material.CONDUIT, particleTypes.CONDUIT), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.CONDUIT.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.CONDUIT.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 1);

        gui.setItem(new GUIItem(particleItem(Material.LAVA_BUCKET, particleTypes.LAVA), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.LAVA.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.LAVA.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 2);

        gui.setItem(new GUIItem(particleItem(Material.APPLE, particleTypes.HEARTS), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.HEARTS.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.HEARTS.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 3);

        gui.setItem(new GUIItem(particleItem(Material.POPPY, particleTypes.HAPPY), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.HAPPY.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.HAPPY.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 4);

        gui.setItem(new GUIItem(particleItem(Material.VILLAGER_SPAWN_EGG, particleTypes.ANGRY), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.ANGRY.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ANGRY.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 5);

        gui.setItem(new GUIItem(particleItem(Material.ENCHANTED_BOOK, particleTypes.ENCHANT), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.ENCHANT.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ENCHANT.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 6);

        gui.setItem(new GUIItem(particleItem(Material.END_ROD, particleTypes.ENDROD), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.ENDROD.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ENDROD.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row3, 7);


        //row 3
        gui.setItem(new GUIItem(particleItem(Material.ENCHANTING_TABLE, particleTypes.ENCHANTING), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.ENCHANTING.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.ENCHANTING.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 1);

        gui.setItem(new GUIItem(particleItem(Material.REDSTONE, particleTypes.RAINBOWDUST), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.RAINBOWDUST.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.RAINBOWDUST.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 2);

        gui.setItem(new GUIItem(particleItem(Material.DRAGON_EGG, particleTypes.DRAGONSBREATH), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.DRAGONSBREATH.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.DRAGONSBREATH.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 3);

        gui.setItem(new GUIItem(particleItem(Material.STONE_SWORD, particleTypes.CRITICAL), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.CRITICAL.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.CRITICAL.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 4);

        gui.setItem(new GUIItem(particleItem(Material.PILLAGER_SPAWN_EGG, particleTypes.DAMAGE), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.DAMAGE.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.DAMAGE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 5);

        gui.setItem(new GUIItem(particleItem(Material.FLINT_AND_STEEL, particleTypes.SMOKE), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.SMOKE.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SMOKE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 6);

        gui.setItem(new GUIItem(particleItem(Material.BAMBOO, particleTypes.SNEEZE), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(particleTypes.SNEEZE.getPermission())) {
                t.sendMessage(lang.Prefix + lang.NoPerm);return;
            }
            data.setParticle(particleTypes.SNEEZE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row4, 7);


        //settings
        gui.setItem(new GUIItem(Items.Particle.Disable(), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
            PlayerData data = pdh.get(t.getUniqueId());
            data.setParticle(particleTypes.DISABLED.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIWindow.Rows.row6, 2);


        gui.setItem(new GUIItem(Items.Particle.changemode(pmode.getOrDefault(p.getUniqueId(), ParticleMode.CLOUD)), (e)->{
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player t = (Player)e.getWhoClicked();
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
