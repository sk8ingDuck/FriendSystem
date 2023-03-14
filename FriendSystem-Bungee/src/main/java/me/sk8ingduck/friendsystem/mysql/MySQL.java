package me.sk8ingduck.friendsystem.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.sk8ingduck.friendsystem.utils.Util;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class MySQL {
    private final HikariDataSource dataSource;

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
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS player(UUID VARCHAR(40), invites BOOLEAN, notifies BOOLEAN, msgs BOOLEAN, jump BOOLEAN, PRIMARY KEY(UUID));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS friend(UUID VARCHAR(40),friendUUID VARCHAR(40),FOREIGN KEY (friendUUID) REFERENCES player(UUID));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS request(UUID VARCHAR(40),requestUUID VARCHAR(40),FOREIGN KEY (requestUUID) REFERENCES player(UUID));");

            stmt.close();
        } catch (SQLException e) {
            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("§c[FriendSystem] MySQL Connection could not be established. Error:"));
            e.printStackTrace();
        }
    }

    public void close() {
        dataSource.close();
    }


    private boolean doesPlayerExist(UUID uuid) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM player WHERE UUID = ?")) {
            stmt.setString(1, uuid.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void doesPlayerExist(UUID uuid, Consumer<Boolean> exists) {
        Util.pool.execute(() -> exists.accept(doesPlayerExist(uuid)));
    }

    private boolean arePlayersFriends(UUID uuid1, UUID uuid2) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT friendUUID FROM friend WHERE UUID = ?")) {
            stmt.setString(1, uuid1.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (UUID.fromString(rs.getString("friendUUID")).equals(uuid2)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void arePlayersFriends(UUID uuid1, UUID uuid2, Consumer<Boolean> areFriends) {
        Util.pool.execute(() -> areFriends.accept(arePlayersFriends(uuid1, uuid2)));
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
        Util.pool.execute(() -> isRequested.accept(isPlayerRequested(uuid1, uuid2)));
    }

    private void addPlayer(UUID uuid) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO `player`(`UUID`, `invites`, `notifies`, `msgs`, `jump`) VALUES (?, '1', '1', '1', '1')")) {
            stmt.setString(1, uuid.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPlayerAsync(UUID uuid) {
        Util.pool.execute(() -> addPlayer(uuid));
    }

    private void addFriend(UUID uuid, UUID friendUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO `friend`(`UUID`, `friendUUID`) VALUES (?, ?)")) {
            stmt.setString(1, uuid.toString());
            stmt.setString(2, friendUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriendAsync(UUID uuid, UUID friendUUID) {
        Util.pool.execute(() -> addFriend(uuid, friendUUID));
    }

    private void removeFriend(UUID uuid, UUID friendUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM `friend` WHERE UUID=? AND friendUUID=?")) {
            stmt.setString(1, uuid.toString());
            stmt.setString(2, friendUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFriendAsync(UUID uuid, UUID friendUUID) {
        Util.pool.execute(() -> removeFriend(uuid, friendUUID));
    }

    private void addRequest(UUID uuid, UUID requestUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO `request`(`UUID`, `requestUUID`) VALUES (?, ?)")) {
            stmt.setString(1, uuid.toString());
            stmt.setString(2, requestUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRequestAsync(UUID uuid, UUID requestUUID) {
        Util.pool.execute(() -> addRequest(uuid, requestUUID));
    }

    private void removeRequest(UUID uuid, UUID requestUUID) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM `request` WHERE UUID=? AND requestUUID=?")) {
            stmt.setString(1, uuid.toString());
            stmt.setString(2, requestUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRequestAsync(UUID uuid, UUID requestUUID) {
        Util.pool.execute(() -> removeRequest(uuid, requestUUID));
    }

    private boolean getOption(UUID uuid, String option) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT " + option + " FROM player WHERE UUID=?")) {
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(option);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getOption(UUID uuid, String option, Consumer<Boolean> value) {
        Util.pool.execute(() -> value.accept(getOption(uuid, option)));
    }

    public void setOption(UUID uuid, String option, boolean value) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE `player` SET `" + option + "`=? WHERE UUID=?")) {
            stmt.setBoolean(1, value);
            stmt.setString(2, uuid.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setOptionAsync(UUID uuid, String option, boolean value) {
        Util.pool.execute(() -> setOption(uuid, option, value));

    }

    private List<UUID> getFriendUUIDs(UUID uuid) {
        List<UUID> friends = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT friendUUID FROM friend WHERE UUID=?")) {
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                friends.add(UUID.fromString(rs.getString("friendUUID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }
    public void getFriendUUIDs(UUID uuid, Consumer<List<UUID>> friendUUIDs) {
        Util.pool.execute(() -> friendUUIDs.accept(getFriendUUIDs(uuid)));
    }

    private List<UUID> getRequestUUIDs(UUID uuid) {
        List<UUID> requests = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT requestUUID FROM request WHERE UUID=?")) {
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                requests.add(UUID.fromString(rs.getString("requestUUID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    public void getRequestUUIDs(UUID uuid, Consumer<List<UUID>> requestUUIDs) {
        Util.pool.execute(() -> requestUUIDs.accept(getRequestUUIDs(uuid)));
    }

    private int getRequestsCount(UUID uuid) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM request WHERE UUID=?")) {
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void getRequestsCount(UUID uuid, Consumer<Integer> requestsCount) {
        Util.pool.execute(() -> requestsCount.accept(getRequestsCount(uuid)));
    }
}
