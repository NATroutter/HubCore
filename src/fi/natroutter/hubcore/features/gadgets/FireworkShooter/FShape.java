package fi.natroutter.hubcore.features.gadgets.FireworkShooter;

import fi.natroutter.hubcore.files.Lang;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.natlibs.objects.BaseItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Item;

@AllArgsConstructor
public enum FShape {

    SMALLBALL(
            Lang.Guis_Gadgets_Fireworks_Shapes_SmallBall,
            Items.SmallBall()
    ),
    LARGEBALL(
            Lang.Guis_Gadgets_Fireworks_Shapes_LargeBall,
            Items.LargeBall()
    ),
    STAR(
            Lang.Guis_Gadgets_Fireworks_Shapes_Star,
            Items.Star()
    ),
    CREEPER(
            Lang.Guis_Gadgets_Fireworks_Shapes_Creeper,
            Items.Creeper()
    ),
    BURST(
            Lang.Guis_Gadgets_Fireworks_Shapes_Burst,
            Items.Burst()
    );

    @Getter
    public Lang langName;

    @Getter
    private BaseItem item;


    public static FShape fromString(String value) {
        for (FShape shape : values()) {
            if (shape.name().equalsIgnoreCase(value)) {
                return shape;
            }
        }
        return null;
    }
}
