package me.sk8ingduck.friendsystem;

import me.sk8ingduck.friendsystem.commands.Freload;
import me.sk8ingduck.friendsystem.commands.Friend;
import me.sk8ingduck.friendsystem.commands.Msg;
import me.sk8ingduck.friendsystem.commands.R;
import me.sk8ingduck.friendsystem.config.*;
import me.sk8ingduck.friendsystem.events.Disconnect;
import me.sk8ingduck.friendsystem.events.Join;
import me.sk8ingduck.friendsystem.events.PluginMessage;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class FriendSystem extends Plugin {
    private static FriendSystem instance;
    private MySQL mysql;
    private FriendManager friendManager;
    private SettingsConfig settingsConfig;
    private MessagesConfig messagesConfig;

    public void onEnable() {
        instance = this;

        DBConfig dbConfig = new DBConfig("database.yml", "plugins/FriendSystem");
        mysql = new MySQL(dbConfig.getHost(), dbConfig.getPort(), dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase());

        friendManager = new FriendManager();

        settingsConfig = new SettingsConfig("settings.yml", "plugins/FriendSystem");
        MessagesConfig german = new MessagesGermanConfig("messages_german.yml", "plugins/FriendSystem");
        MessagesConfig english = new MessagesEnglishConfig("messages_english.yml", "plugins/FriendSystem");

        messagesConfig = settingsConfig.getLanguage().equalsIgnoreCase("german") ? german : english;

        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerListener(this, new Join());
        pluginManager.registerListener(this, new Disconnect());
        pluginManager.registerListener(this, new PluginMessage());

        pluginManager.registerCommand(this, new Freload());
        pluginManager.registerCommand(this, new Friend());
        pluginManager.registerCommand(this, new Msg());
        pluginManager.registerCommand(this, new R());

        getProxy().registerChannel("me:friendsystem");

        System.out.println("§a[FriendSystem] FriendSystem enabled!");
    }

    public void onDisable() {
        System.out.println("§c[FriendSystem] FriendSystem disabled!");
        mysql.close();
    }

    public static FriendSystem getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mysql;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public MessagesConfig getConfig() {
        return messagesConfig;
    }

    public void reloadConfigs() {
        settingsConfig.reload();
        if (settingsConfig.getLanguage().equalsIgnoreCase("german")) {
            messagesConfig = new MessagesGermanConfig("messages_german.yml", "plugins/FriendSystem");
        } else {
            messagesConfig = new MessagesEnglishConfig("messages_english.yml", "plugins/FriendSystem");
        }
    }
}
