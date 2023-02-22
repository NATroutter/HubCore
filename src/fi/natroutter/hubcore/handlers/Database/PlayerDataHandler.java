package fi.natroutter.hubcore.handlers.Database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.DatabaseConnection;
import fi.natroutter.hubcore.features.particles.ParticleMode;
import fi.natroutter.hubcore.features.particles.ParticleTypes;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.*;

public class PlayerDataHandler {

    private final static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    private final JavaPlugin plugin;
    private final Database database;

    private HashMap<UUID, PlayerData> cache = new HashMap<>();

    ArrayList<UUID> cacheRemove = new ArrayList<>();

    public PlayerDataHandler(JavaPlugin plugin, Database database, int saveInterval) {
        this.database = database;
        this.plugin = plugin;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::save, 0, saveInterval * 20L);
    }

    public void save() {
        int online = Bukkit.getOnlinePlayers().size();

        ArrayList<UUID> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {list.add(p.getUniqueId());}

        if (cacheRemove.size() > 0) {
            for (int i = 0; i < cacheRemove.size(); i++) {
                cache.remove(cacheRemove.get(i));
                cacheRemove.remove(i);
            }
        }
        if (online > 0) {
            console.sendMessage("§4["+plugin.getName()+"] §cSaving PlayerData... (Size:" + cache.size() + ", PlayerCount:"+online+")");
            for (Map.Entry<UUID, PlayerData> entry : cache.entrySet()) {
                if (!list.contains(entry.getKey())) {
                    cacheRemove.add(entry.getKey());
                }
            }
            try {saveBulkData(cache.values());}catch (Exception e) {}
        }
    }

    public void save(UUID uuid) {
        if (cache.containsKey(uuid)) {
            updateForID(cache.get(uuid));
        }
    }

    public void remove(UUID uuid) {
        cache.remove(uuid);
    }

    public PlayerData get(UUID uuid) {
        if (cache.containsKey(uuid)) {
            return cache.get(uuid);
        } else {
            PlayerData data = queryForID(uuid);
            cache.put(uuid, data);
            return data;
        }
    }

    public void set(PlayerData data) {
        cache.put(data.getUuid(), data);
    }

    private PlayerData queryForID(UUID uuid) {
        if (uuid == null) {return null;}
        try {
            PlayerData data = database.getPlayerData().queryForId(uuid);
            if (data == null) {
                data = DefaultPlayerData(uuid);
                if (database.getPlayerData().create(data) == 0) {
                    console.sendMessage("§4["+plugin.getName()+"][PlayerDataHandler](query) §cFailed to create new player data to database replying default!");
                }
            }
            return data;
        } catch (SQLException e) {
            console.sendMessage("§4["+plugin.getName()+"][PlayerDataHandler](query) §cDatabase failure!");
            e.printStackTrace();
        }
        return null;
    }

    private void updateForID(PlayerData data) {
        try {
            database.getPlayerData().createOrUpdate(data);
        } catch (SQLException e) {
            console.sendMessage("§4["+plugin.getName()+"][PlayerDataHandler](update) §cDatabase failure!");
            e.printStackTrace();
        }
    }

    public void saveBulkData(Collection<PlayerData> objectTypes) throws SQLException {
        long t1 = System.nanoTime();
        DatabaseConnection conn = database.getPlayerData().startThreadConnection();
        Savepoint savepoint = null;
        try {
            savepoint = conn.setSavePoint(null);
            doInsert(objectTypes, database.getPlayerData());
        } finally {
            conn.commit(savepoint);
            database.getPlayerData().endThreadConnection(conn);
        }
        long t2 = System.nanoTime();
    }

    private void doInsert(Collection<PlayerData> objectTypes, Dao<PlayerData, UUID> simpleDao) {
        for (PlayerData a : objectTypes) {
            try {
                if (a != null) {
                    simpleDao.createOrUpdate(a);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static PlayerData DefaultPlayerData(UUID uuid) {
        return new PlayerData(uuid, "SMALLBALL", false, false, 0, 0, 0, 0, 0, 0, ParticleTypes.DISABLED.name() , ParticleMode.CLOUD.name(), false, false);
    }

}
