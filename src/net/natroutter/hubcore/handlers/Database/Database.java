package net.natroutter.hubcore.handlers.Database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.table.TableUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class Database {

    private Dao<PlayerData, UUID> PlayerDao;

    private boolean valid = false;

    public boolean getValid() {
        return valid;
    }

    public Database(JavaPlugin pl) {
        ConsoleCommandSender console = pl.getServer().getConsoleSender();

        Logger.setGlobalLogLevel(Level.INFO);

        try {
            String ConString = "jdbc:sqlite:" + pl.getDataFolder() + "/database.db";
            JdbcPooledConnectionSource source = new JdbcPooledConnectionSource(ConString);
            TableUtils.createTableIfNotExists(source, PlayerData.class);
            PlayerDao = DaoManager.createDao(source, PlayerData.class);

            valid = true;
        } catch (Exception e) {
            console.sendMessage("§4["+pl.getName()+"][Database] §cDatabase failure");
            e.printStackTrace();
        }
    }

    public Dao<PlayerData, UUID> getPlayerData() {
        if (!valid) {return null;}
        return PlayerDao;
    }

}
