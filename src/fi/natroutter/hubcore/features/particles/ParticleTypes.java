package fi.natroutter.hubcore.features.particles;

import fi.natroutter.hubcore.files.Config;
import fi.natroutter.hubcore.files.Lang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Particle;

@AllArgsConstructor
public enum ParticleTypes {

    DISABLED(null,null,Lang.Guis_Particles_Disabled,null,null),
    WITCH(
            Particle.SPELL_WITCH,
            Material.RED_MUSHROOM,
            Lang.Guis_Particles_Witch,
            Config.Particles_Witch_Perm.asString(),
            Config.Particles_Witch_Need
    ),
    TOTEM(
            Particle.TOTEM,
            Material.TOTEM_OF_UNDYING,
            Lang.Guis_Particles_Totem,
            Config.Particles_Totem_Perm.asString(),
            Config.Particles_Totem_Need
    ),
    INK(
            Particle.SQUID_INK,
            Material.INK_SAC,
            Lang.Guis_Particles_Ink,
            Config.Particles_Ink_Perm.asString(),
            Config.Particles_Ink_Need
    ),
    SOULFLAME(
            Particle.SOUL_FIRE_FLAME,
            Material.SOUL_TORCH,
            Lang.Guis_Particles_Soulflame,
            Config.Particles_Soulflame_Perm.asString(),
            Config.Particles_Soulflame_Need
    ),
    CLOUD(
            Particle.CLOUD,
            Material.SNOWBALL,
            Lang.Guis_Particles_Cloud,
            Config.Particles_Cloud_Perm.asString(),
            Config.Particles_Cloud_Need
    ),
    SOUL(
            Particle.SOUL,
            Material.SOUL_SOIL,
            Lang.Guis_Particles_Soul,
            Config.Particles_Soul_Perm.asString(),
            Config.Particles_Soul_Need
    ),
    NOTES(
            Particle.NOTE,
            Material.NOTE_BLOCK,
            Lang.Guis_Particles_Notes,
            Config.Particles_Notes_Perm.asString(),
            Config.Particles_Notes_Need
    ),
    CONDUIT(
            Particle.NAUTILUS,
            Material.CONDUIT,
            Lang.Guis_Particles_Conduit,
            Config.Particles_Conduit_Perm.asString(),
            Config.Particles_Conduit_Need
    ),
    LAVA(
            Particle.LAVA,
            Material.LAVA_BUCKET,
            Lang.Guis_Particles_Lava,
            Config.Particles_Lava_Perm.asString(),
            Config.Particles_Lava_Need
    ),
    HEARTS(
            Particle.HEART,
            Material.APPLE,
            Lang.Guis_Particles_Hearts,
            Config.Particles_Hearts_Perm.asString(),
            Config.Particles_Hearts_Need
    ),
    HAPPY(
            Particle.VILLAGER_HAPPY,
            Material.POPPY,
            Lang.Guis_Particles_Happy,
            Config.Particles_Happy_Perm.asString(),
            Config.Particles_Happy_Need
    ),
    ANGRY(
            Particle.VILLAGER_ANGRY,
            Material.VILLAGER_SPAWN_EGG,
            Lang.Guis_Particles_Angry,
            Config.Particles_Angry_Perm.asString(),
            Config.Particles_Angry_Need
    ),
    ENCHANT(
            Particle.CRIT_MAGIC,
            Material.BOOK,
            Lang.Guis_Particles_Enchanthit,
            Config.Particles_Enchanthit_Perm.asString(),
            Config.Particles_Enchanthit_Need
    ),
    ENDROD(
            Particle.END_ROD,
            Material.END_ROD,
            Lang.Guis_Particles_Endrod,
            Config.Particles_Endrod_Perm.asString(),
            Config.Particles_Endrod_Need
    ),
    ENCHANTING(
            Particle.ENCHANTMENT_TABLE,
            Material.ENCHANTING_TABLE,
            Lang.Guis_Particles_Enchanting,
            Config.Particles_Enchanting_Perm.asString(),
            Config.Particles_Enchanting_Need
    ),
    RAINBOWDUST(
            Particle.REDSTONE,
            Material.REDSTONE,
            Lang.Guis_Particles_RainbowDust,
            Config.Particles_Rainbowdust_Perm.asString(),
            Config.Particles_Rainbowdust_Need
    ),
    DRAGONSBREATH(
            Particle.DRAGON_BREATH,
            Material.DRAGON_EGG,
            Lang.Guis_Particles_DragonBreath,
            Config.Particles_Dragonbreath_Perm.asString(),
            Config.Particles_Dragonbreath_Need
    ),
    CRITICAL(
            Particle.CRIT,
            Material.STONE_SWORD,
            Lang.Guis_Particles_Critical,
            Config.Particles_Critical_Perm.asString(),
            Config.Particles_Critical_Need
    ),
    DAMAGE(
            Particle.DAMAGE_INDICATOR,
            Material.PILLAGER_SPAWN_EGG,
            Lang.Guis_Particles_Damage,
            Config.Particles_Damage_Perm.asString(),
            Config.Particles_Damage_Need
    ),
    SMOKE(
            Particle.SMOKE_NORMAL,
            Material.FLINT_AND_STEEL,
            Lang.Guis_Particles_Smoke,
            Config.Particles_Smoke_Perm.asString(),
            Config.Particles_Smoke_Need
    ),
    SNEEZE(
            Particle.SNEEZE,
            Material.BAMBOO,
            Lang.Guis_Particles_Sneeze,
            Config.Particles_Sneeze_Perm.asString(),
            Config.Particles_Sneeze_Need
    );

    @Getter
    private Particle particle;

    @Getter
    private Material material;

    @Getter
    private Lang langName;

    @Getter
    private String permission;

    @Getter
    private Config need;

    public static ParticleTypes fromString(String value) {
        for (ParticleTypes type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

}
