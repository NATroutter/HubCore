package net.natroutter.hubcore.features.SelectorItems;

import net.natroutter.natlibs.objects.BaseItem;

public class HubItem {
    private final String Identifier;
    private final Integer Slot;
    private final BaseItem Item;
    public HubItem(String Identifier, Integer Slot, BaseItem Item) {
        this.Identifier = Identifier;
        this.Slot = Slot;
        this.Item = Item;
    }
    public String getId() { return Identifier; }
    public Integer getSlot() { return Slot; }
    public BaseItem getItem() { return Item; }
}
