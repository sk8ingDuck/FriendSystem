package me.sk8ingduck.friendsystemgui;

import me.sk8ingduck.friendsystemgui.command.FCommand;
import me.sk8ingduck.friendsystemgui.config.*;
import me.sk8ingduck.friendsystemgui.gui.GuiManager;
import me.sk8ingduck.friendsystemgui.listener.PlayerInteractEntityListener;
import me.sk8ingduck.friendsystemgui.listener.PlayerInteractListener;
import me.sk8ingduck.friendsystemgui.listener.PlayerJoinListener;
import me.sk8ingduck.friendsystemgui.pluginmessage.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

public final class FriendSystemGUI extends JavaPlugin {

    private static FriendSystemGUI instance;

    private SettingsConfig settingsConfig;
    private GuiConfig guiConfig;
    private PluginMessage pm;

    @Override
    public void onEnable() {
        instance = this;

        settingsConfig = new SettingsConfig("settings.yml", getDataFolder());

        GuiConfig german = new GuiGermanConfig("guis_german.yml", getDataFolder());
        GuiConfig english = new GuiEnglishConfig("guis_english.yml", getDataFolder());

        guiConfig = settingsConfig.getLanguage().equalsIgnoreCase("german") ? german : english;

        GuiManager.init();

        pm = new PluginMessage();
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
    }

    public static FriendSystemGUI getInstance() {
        return instance;
    }
    public SettingsConfig getSettingsConfig() {
        return settingsConfig;
    }
    public GuiConfig getGuiConfig() {
        return guiConfig;
    }
    public PluginMessage getPluginMessaging() {
        return pm;
    }

}
