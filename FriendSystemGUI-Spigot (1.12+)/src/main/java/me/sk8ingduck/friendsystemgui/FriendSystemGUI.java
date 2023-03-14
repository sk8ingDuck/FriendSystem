package me.sk8ingduck.friendsystemgui;

import me.sk8ingduck.friendsystemgui.command.FCommand;
import me.sk8ingduck.friendsystemgui.config.DBConfig;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.gui.GuiManager;
import me.sk8ingduck.friendsystemgui.listener.PlayerInteractEntityListener;
import me.sk8ingduck.friendsystemgui.listener.PlayerInteractListener;
import me.sk8ingduck.friendsystemgui.listener.PlayerJoinListener;
import me.sk8ingduck.friendsystemgui.mysql.MySQL;
import me.sk8ingduck.friendsystemgui.util.PluginMessaging;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

public final class FriendSystemGUI extends JavaPlugin {

    private static FriendSystemGUI instance;
    private GuiConfig guiConfig;
    private MySQL mysql;

    private PluginMessaging pm;

    @Override
    public void onEnable() {
        instance = this;

        guiConfig = new GuiConfig("guis.yml", getDataFolder());

        DBConfig dbConfig = new DBConfig("mysql.yml", getDataFolder());
        mysql = new MySQL(dbConfig.getHost(), dbConfig.getPort(), dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase());

        GuiManager.init();

        pm = new PluginMessaging();
        this.getCommand("f").setExecutor(new FCommand());
        Bukkit.getPluginManager().registerEvents(new MenuFunctionListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "me:friendsystem");
        getServer().getMessenger().registerIncomingPluginChannel(this, "me:friendsystem", pm);
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        getServer().getMessenger().unregisterIncomingPluginChannel(this);
        mysql.close();
    }

    public static FriendSystemGUI getInstance() {
        return instance;
    }
    public GuiConfig getSettingsConfig() {
        return guiConfig;
    }
    public MySQL getMysql() {
        return mysql;
    }
    public PluginMessaging getPluginMessaging() {
        return pm;
    }

}
