package fi.natroutter.hubcore.features.SelectorItems;

import fi.natroutter.natlibs.objects.BaseItem;

public record HubItem(String id, Integer slot,
                      BaseItem item) {
}
