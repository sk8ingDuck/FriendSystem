package me.sk8ingduck.friendsystem;

import java.util.HashMap;

import me.sk8ingduck.friendsystem.commands.Friend;
import me.sk8ingduck.friendsystem.commands.Msg;
import me.sk8ingduck.friendsystem.commands.R;
import me.sk8ingduck.friendsystem.events.Disconnect;
import me.sk8ingduck.friendsystem.events.Join;
import me.sk8ingduck.friendsystem.events.PluginMessage;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.config.DBConfig;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class FriendSystem extends Plugin {
    private static FriendSystem fs;

    private MySQL mysql;

    private MessagesConfig messagesConfig;
    public HashMap<ProxiedPlayer, ProxiedPlayer> msgs;

    public void onEnable() {
        fs = this;
        msgs = new HashMap<>();

        DBConfig dbConfig = new DBConfig("database.yml", "plugins/FriendSystem");
        messagesConfig = new MessagesConfig("messages.yml", "plugins/FriendSystem");

        mysql = new MySQL(dbConfig.getHost(), dbConfig.getPort(), dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase());
        getProxy().registerChannel("me:friendsystem");

        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerListener(this, new Join());
        pluginManager.registerListener(this, new Disconnect());
        pluginManager.registerListener(this, new PluginMessage());

        pluginManager.registerCommand(this, new Friend());
        pluginManager.registerCommand(this, new Msg());
        pluginManager.registerCommand(this, new R());

        System.out.println("§a[FriendSystem] FriendSystem enabled!");
    }

    public void onDisable() {
        System.out.println("§c[FriendSystem] FriendSystem disabled!");
        mysql.close();
    }

    public static FriendSystem getFriendSystem() {
        return fs;
    }

    public MySQL getMySQL() {
        return mysql;
    }

    public MessagesConfig getConfig() {
        return messagesConfig;
    }

}
