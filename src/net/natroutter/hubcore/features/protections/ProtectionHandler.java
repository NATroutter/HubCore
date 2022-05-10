package net.natroutter.hubcore.features.protections;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProtectionHandler {

    protected ArrayList<UUID> bypassProtection = new ArrayList<>();
    List<Material> disabledMaterials = List.of(
            Material.POINTED_DRIPSTONE,
            Material.CHEST,
            Material.TRAPPED_CHEST,
            Material.IRON_BARS
    );

    public void bypass(Player p, boolean status) {
        if (status) {
            if (!bypassProtection.contains(p.getUniqueId())) {
                bypassProtection.add(p.getUniqueId());
            }
        } else {
            bypassProtection.remove(p.getUniqueId());
        }
    }

}
