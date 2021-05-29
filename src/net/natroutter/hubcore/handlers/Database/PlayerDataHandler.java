package net.natroutter.hubcore.handlers.Database;

import net.natroutter.hubcore.HubCore;
import net.natroutter.hubcore.features.particles.ParticleMode;
import net.natroutter.hubcore.features.particles.particleTypes;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.SQLException;
import java.util.UUID;

public class PlayerDataHandler {

    private static final Database database = HubCore.getDatabase();
    private final static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    public static PlayerData queryForID(UUID uuid) {
        if (uuid == null) {return null;}
        try {
            PlayerData data = database.getPlayerData().queryForId(uuid);
            if (data == null) {
                data = DefaultPlayerData(uuid);
                if (database.getPlayerData().create(data) == 0) {
                    console.sendMessage("§4[HubCore][PlayerDataHandler](query) §cFailed to create new player data to database replying default!");
                }
            }
            return data;
        } catch (SQLException e) {
            console.sendMessage("§4[HubCore][PlayerDataHandler](query) §cDatabase failure!");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateForID(PlayerData data) {
        try {
            database.getPlayerData().createOrUpdate(data);
            return true;
        } catch (SQLException e) {
            console.sendMessage("§4[HubCore][PlayerDataHandler](update) §cDatabase failure!");
            e.printStackTrace();
        }
        return false;
    }

    private static PlayerData DefaultPlayerData(UUID uuid) {
        return new PlayerData(uuid, "SMALLBALL", false, false, 0, 0, 0, 0, 0, 0, particleTypes.DISABLED.name() , ParticleMode.CLOUD.name());
    }

}
