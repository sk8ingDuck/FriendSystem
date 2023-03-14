package me.sk8ingduck.friendsystemgui.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
public class MySQL {
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final HikariDataSource dataSource;

    public MySQL(String host, int port, String username, String password, String database) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        dataSource = new HikariDataSource(config);

        setup();
    }

    public void close() {
        dataSource.close();
    }

    public void setup() {
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS player(UUID VARCHAR(40), invites BOOLEAN, notifies BOOLEAN, msgs BOOLEAN, jump BOOLEAN, PRIMARY KEY(UUID));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS friend(UUID VARCHAR(40),friendUUID VARCHAR(40),FOREIGN KEY (friendUUID) REFERENCES player(UUID));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS request(UUID VARCHAR(40),requestUUID VARCHAR(40),FOREIGN KEY (requestUUID) REFERENCES player(UUID));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getOption(UUID uuid, String option) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT " + option + " FROM player WHERE UUID=?")) {
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean value = rs.getBoolean(option);
                return value;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPlayerRequested(UUID uuid1, UUID uuid2) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT requestUUID FROM request WHERE UUID = ?")) {
            stmt.setString(1, uuid1.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (UUID.fromString(rs.getString("requestUUID")).equals(uuid2)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void isPlayerRequested(UUID uuid1, UUID uuid2, Consumer<Boolean> isRequested) {
        pool.execute(() -> isRequested.accept(isPlayerRequested(uuid1, uuid2)));
    }

    private boolean arePlayersFriends(UUID uuid1, UUID uuid2) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT friendUUID FROM friend WHERE UUID = ?")) {
            stmt.setString(1, uuid1.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (UUID.fromString(rs.getString("friendUUID")).equals(uuid2)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void arePlayersFriends(UUID uuid1, UUID uuid2, Consumer<Boolean> areFriends) {
        pool.execute(() -> areFriends.accept(arePlayersFriends(uuid1, uuid2)));
    }
}
