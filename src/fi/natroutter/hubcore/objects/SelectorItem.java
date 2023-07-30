package fi.natroutter.hubcore.objects;

import fi.natroutter.natlibs.handlers.guibuilder.Rows;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Material;

import java.util.List;

@Getter @NoArgsConstructor
public class SelectorItem {

    private String server;
    private String row;
    private int slot;
    private String material;
    private String name;
    private List<String> lore;

    public Material getMaterial() {
        return Material.getMaterial(material);
    }

    public boolean isValid(){
        return (server == null || row == null || material == null || name == null || lore == null);
    }

    public Rows getRow() {
        return Rows.fromString(row);
    }
}
