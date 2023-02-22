package net.natroutter.hubcore.features.particles;

import fi.natroutter.natlibs.handlers.gui.GUIItem;
import fi.natroutter.natlibs.handlers.gui.GUIRow;
import fi.natroutter.natlibs.handlers.gui.GUIWindow;
import fi.natroutter.natlibs.handlers.langHandler.language.LangManager;
import fi.natroutter.natlibs.objects.BaseItem;
import fi.natroutter.natlibs.utilities.StringHandler;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.natroutter.hubcore.Handler;
import net.natroutter.hubcore.files.Config;
import net.natroutter.hubcore.files.Translations;
import net.natroutter.hubcore.handlers.Database.PlayerData;
import net.natroutter.hubcore.handlers.Database.PlayerDataHandler;
import net.natroutter.hubcore.utilities.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ParticleGUI {

    public HashMap<UUID, GUIWindow> GUIS = new HashMap<>();
    public HashMap<UUID, ParticleMode> pmode = new HashMap<>();

    private Items items;
    private LangManager lang;
    private PlayerDataHandler pdh;
    private Config config;
    
    public ParticleGUI(Handler handler) {
        this.items = handler.getItems();
        this.lang = handler.getLang();
        this.pdh = handler.getDataHandler();
        this.config = handler.getConfig();
    }

    LegacyComponentSerializer lcs = LegacyComponentSerializer.legacySection();

    private GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lcs.deserialize(lang.get(Translations.Guis_Particles_Title)), GUIRow.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }


    public void show(Player p) {
        guiBuilder(p).show(p);
    }

    private BaseItem particleItem(Material mat, ParticleTypes type) {
        BaseItem item = new BaseItem(mat);
        item.setDisplayName(type.getLangName(lang));
        ArrayList<String> lore = new ArrayList<>();
        for (String line : lang.getList(Translations.Guis_Particles_Effect_Lore)) {
            StringHandler s = new StringHandler(line);
            s.replaceAll("{need}", type.getNeed(config));
            lore.add(s.build());
        }
        item.setLore(lore);
        return item;
    }

    private GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        //Row 1
        gui.setItem(new GUIItem(particleItem(Material.RED_MUSHROOM, ParticleTypes.WITCH), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.WITCH.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.WITCH.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 1);

        gui.setItem(new GUIItem(particleItem(Material.TOTEM_OF_UNDYING, ParticleTypes.TOTEM), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!p.hasPermission(ParticleTypes.TOTEM.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.TOTEM.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 2);

        gui.setItem(new GUIItem(particleItem(Material.INK_SAC, ParticleTypes.INK), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.INK.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.INK.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 3);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_TORCH, ParticleTypes.SOULFLAME), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SOULFLAME.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SOULFLAME.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 4);

        gui.setItem(new GUIItem(particleItem(Material.SNOWBALL, ParticleTypes.CLOUD), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.CLOUD.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.CLOUD.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 5);

        gui.setItem(new GUIItem(particleItem(Material.SOUL_SOIL, ParticleTypes.SOUL), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SOUL.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SOUL.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 6);

        gui.setItem(new GUIItem(particleItem(Material.NOTE_BLOCK, ParticleTypes.NOTES), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.NOTES.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.NOTES.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row2, 7);


        //Row 2
        gui.setItem(new GUIItem(particleItem(Material.CONDUIT, ParticleTypes.CONDUIT), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.CONDUIT.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.CONDUIT.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 1);

        gui.setItem(new GUIItem(particleItem(Material.LAVA_BUCKET, ParticleTypes.LAVA), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.LAVA.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.LAVA.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 2);

        gui.setItem(new GUIItem(particleItem(Material.APPLE, ParticleTypes.HEARTS), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.HEARTS.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.HEARTS.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 3);

        gui.setItem(new GUIItem(particleItem(Material.POPPY, ParticleTypes.HAPPY), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.HAPPY.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.HAPPY.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 4);

        gui.setItem(new GUIItem(particleItem(Material.VILLAGER_SPAWN_EGG, ParticleTypes.ANGRY), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ANGRY.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ANGRY.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 5);

        gui.setItem(new GUIItem(particleItem(Material.ENCHANTED_BOOK, ParticleTypes.ENCHANT), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ENCHANT.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ENCHANT.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 6);

        gui.setItem(new GUIItem(particleItem(Material.END_ROD, ParticleTypes.ENDROD), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ENDROD.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ENDROD.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row3, 7);


        //row 3
        gui.setItem(new GUIItem(particleItem(Material.ENCHANTING_TABLE, ParticleTypes.ENCHANTING), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.ENCHANTING.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.ENCHANTING.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 1);

        gui.setItem(new GUIItem(particleItem(Material.REDSTONE, ParticleTypes.RAINBOWDUST), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.RAINBOWDUST.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.RAINBOWDUST.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 2);

        gui.setItem(new GUIItem(particleItem(Material.DRAGON_EGG, ParticleTypes.DRAGONSBREATH), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.DRAGONSBREATH.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.DRAGONSBREATH.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 3);

        gui.setItem(new GUIItem(particleItem(Material.STONE_SWORD, ParticleTypes.CRITICAL), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.CRITICAL.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.CRITICAL.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 4);

        gui.setItem(new GUIItem(particleItem(Material.PILLAGER_SPAWN_EGG, ParticleTypes.DAMAGE), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.DAMAGE.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.DAMAGE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 5);

        gui.setItem(new GUIItem(particleItem(Material.FLINT_AND_STEEL, ParticleTypes.SMOKE), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SMOKE.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SMOKE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 6);

        gui.setItem(new GUIItem(particleItem(Material.BAMBOO, ParticleTypes.SNEEZE), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            if (!t.hasPermission(ParticleTypes.SNEEZE.getPermission(config))) {
                lang.send(t, Translations.Prefix, Translations.NoPerm);return;
            }
            data.setParticle(ParticleTypes.SNEEZE.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row4, 7);


        //settings
        gui.setItem(new GUIItem(items.particle_Disable(), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());
            data.setParticle(ParticleTypes.DISABLED.name());
            pdh.set(data);
            t.closeInventory();
        }), GUIRow.row6, 2);


        gui.setItem(new GUIItem(items.particle_ChangeMode(pmode.getOrDefault(p.getUniqueId(), ParticleMode.CLOUD)), (e)->{
            if (!(e.getWhoClicked() instanceof Player t)) {return;}
            PlayerData data = pdh.get(t.getUniqueId());

            ParticleMode mode = ParticleMode.fromString(data.getParticlemode());

            if (mode != null && mode.next() != null) {
                data.setParticlemode(mode.next().name());
                pmode.put(t.getUniqueId(), mode.next());
                guiBuilder(t).show(t);
            }
            pdh.set(data);

        }), GUIRow.row6, 6);

        return gui;
    }








}
