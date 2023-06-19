package me.sk8ingduck.friendsystem.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.ProxyServer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySQL {
    private final HikariDataSource dataSource;
    private final ExecutorService pool = Executors.newCachedThreadPool();

    public MySQL(String host, int port, String username, String password, String database) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true");
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
            stmt.executeUpdate("USE " + database);
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS player(" +
                    "UUID VARCHAR(40), " +
                    "invites BOOLEAN, " +
                    "notifies BOOLEAN, " +
                    "msgs BOOLEAN, " +
                    "jump BOOLEAN, " +
                    "lastSeen DATETIME, " +
                    "status VARCHAR(64), " +
                    "PRIMARY KEY(UUID));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS friend(" +
                    "UUID VARCHAR(40), " +
                    "friendUUID VARCHAR(40), " +
                    "isFavourite BOOLEAN DEFAULT 0, " +
                    "FOREIGN KEY (friendUUID) REFERENCES " + database + ".player(UUID));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS request(" +
                    "UUID VARCHAR(40), " +
                    "requestUUID VARCHAR(40), " +
                    "requestDate DATETIME, " +
                    "FOREIGN KEY (requestUUID) REFERENCES player(UUID));");

            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getColumns(null, null, "request", "requestDate");
            if (!tables.next()) {
                stmt.executeUpdate("ALTER TABLE request ADD COLUMN requestDate DATETIME");
                stmt.executeUpdate("UPDATE request SET requestDate=NOW()");
            }

            stmt.close();
        } catch (SQLException e) {
            ProxyServer.getInstance().getLogger()
                    .info("§c[FriendSystem] MySQL Connection could not be established. Error:");
            e.printStackTrace();
        }
    }

    public void close() {
        dataSource.close();
    }


    public FriendPlayer getFriendPlayer(String uuid) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM player WHERE UUID = ?")) {
            stmt.setString(1, uuid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean invites = rs.getBoolean("invites");
                    boolean notifies = rs.getBoolean("notifies");
                    boolean msgs = rs.getBoolean("msgs");
                    boolean jump = rs.getBoolean("jump");
                    LocalDateTime lastSeen = rs.getTimestamp("lastSeen").toLocalDateTime();
                    String status = rs.getString("status");

                    HashMap<String, Boolean> friends = new HashMap<>();
                    PreparedStatement friendsStmt = con.prepareStatement("SELECT friendUUID, isFavourite FROM friend WHERE UUID=?");
                    friendsStmt.setString(1, uuid);
                    ResultSet friendsRs = friendsStmt.executeQuery();
                    while (friendsRs.next()) {
                        friends.put(friendsRs.getString("friendUUID"), friendsRs.getBoolean("isFavourite"));
                    }
                    friendsRs.close();
                    friendsStmt.close();

                    HashMap<String, LocalDateTime> requests = new HashMap<>();
                    PreparedStatement requestsStmt = con.prepareStatement("SELECT requestUUID, requestDate FROM request WHERE UUID=?");
                    requestsStmt.setString(1, uuid);
                    ResultSet requestsRs = requestsStmt.executeQuery();
                    while (requestsRs.next()) {
                        requests.put(requestsRs.getString("requestUUID"), requestsRs.getTimestamp("requestDate").toLocalDateTime());
                    }
                    requestsRs.close();
                    requestsStmt.close();

                    return new FriendPlayer(uuid, invites, notifies, msgs, jump, lastSeen, status, friends, requests);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addPlayer(String uuid) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO `player`(`UUID`, `invites`, `notifies`, `msgs`, `jump`, `lastSeen`) " +
                             "VALUES (?, '1', '1', '1', '1',NOW())")) {
            stmt.setString(1, uuid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerAsync(String uuid) {
        pool.execute(() -> addPlayer(uuid));
    }

    private void addFriend(String uuid, String friendUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO `friend`(`UUID`, `friendUUID`) VALUES (?, ?)")) {
            stmt.setString(1, uuid);
            stmt.setString(2, friendUUID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriendAsync(String uuid, String friendUUID) {
        pool.execute(() -> addFriend(uuid, friendUUID));
    }

    private void removeFriend(String uuid, String friendUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM `friend` WHERE UUID=? AND friendUUID=?")) {
            stmt.setString(1, uuid);
            stmt.setString(2, friendUUID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFriendAsync(String uuid, String friendUUID) {
        pool.execute(() -> removeFriend(uuid, friendUUID));
    }

    private void updateFavourite(String uuid, String friendUUID, boolean state) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE `friend` SET `isFavourite`=? WHERE UUID = ? AND friendUUID = ?")) {
            stmt.setBoolean(1, state);
            stmt.setString(2, uuid);
            stmt.setString(3, friendUUID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFavouriteAsync(String uuid, String friendUUID, boolean state) {
        pool.execute(() -> updateFavourite(uuid, friendUUID, state));
    }

    private void addRequest(String uuid, String requestUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO `request`(`UUID`, `requestUUID`, `requestDate`) VALUES (?, ?, NOW())")) {
            stmt.setString(1, uuid);
            stmt.setString(2, requestUUID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRequestAsync(String uuid, String requestUUID) {
        pool.execute(() -> addRequest(uuid, requestUUID));
    }

    private void removeRequest(String uuid, String requestUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM `request` WHERE UUID=? AND requestUUID=?")) {
            stmt.setString(1, uuid);
            stmt.setString(2, requestUUID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRequestAsync(String uuid, String requestUUID) {
        pool.execute(() -> removeRequest(uuid, requestUUID));
    }

    private void setOption(String uuid, String option, boolean value) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE `player` SET `" + option + "`=? WHERE UUID=?")) {
            stmt.setBoolean(1, value);
            stmt.setString(2, uuid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setOptionAsync(String uuid, String option, boolean value) {
        pool.execute(() -> setOption(uuid, option, value));

    }

    private void updateLastSeen(String uuid) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "UPDATE `player` SET `lastSeen` = NOW() WHERE UUID = ?")) {
            stmt.setString(1, uuid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLastSeenAsync(String uuid) {
        pool.execute(() -> updateLastSeen(uuid));
    }

    public void updateStatus(String uuid, String status) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE `player` SET `status`=? WHERE UUID=?")) {
            stmt.setString(1, status);
            stmt.setString(2, uuid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusAsync(String uuid, String status) {
        pool.execute(() -> updateStatus(uuid, status));
    }
}
