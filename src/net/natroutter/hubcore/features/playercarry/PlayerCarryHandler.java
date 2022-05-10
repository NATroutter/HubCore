package net.natroutter.hubcore.features.playercarry;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerCarryHandler {

    public ArrayList<UUID> bypassCarry = new ArrayList<>();
    public HashMap<UUID, Long> cooldown = new HashMap<>();
    protected int cooldownTime = 2;


    public void bypass(Player p, boolean status) {
        if (status) {
            if (!bypassCarry.contains(p.getUniqueId())) {
                bypassCarry.add(p.getUniqueId());
            }
        } else {
            bypassCarry.remove(p.getUniqueId());
        }
    }

}
