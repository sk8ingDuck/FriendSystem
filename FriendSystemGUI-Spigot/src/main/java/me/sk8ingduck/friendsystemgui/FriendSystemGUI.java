package me.sk8ingduck.friendsystemgui;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import me.sk8ingduck.friendsystemgui.command.FCommand;
import me.sk8ingduck.friendsystemgui.config.*;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.languages.*;
import me.sk8ingduck.friendsystemgui.gui.GuiManager;
import me.sk8ingduck.friendsystemgui.listener.*;
import me.sk8ingduck.friendsystemgui.pluginmessage.Placeholder;
import me.sk8ingduck.friendsystemgui.pluginmessage.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FriendSystemGUI extends JavaPlugin {

    private static FriendSystemGUI instance;
    private SettingsConfig settingsConfig;
    private GuiConfig guiConfig;
    private PluginMessage pm;

    private InventoryManager manager = new InventoryManager(this);
    @Override
    public void onEnable() {
        instance = this;

        manager.invoke();

        settingsConfig = new SettingsConfig("settings.yml", getDataFolder());

        new GuiGermanConfig("guis_german.yml", getDataFolder());
        new GuiEnglishConfig("guis_english.yml", getDataFolder());
        new GuiEnglishConfig("guis_french.yml", getDataFolder());
        new GuiChineseConfig("guis_chinese.yml", getDataFolder());
        new GuiItalianConfig("guis_italian.yml", getDataFolder());

        guiConfig = loadGuiConfig();

        GuiManager.init();

        pm = new PluginMessage();

        this.getCommand("f").setExecutor(new FCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "me:friendsystem");
        getServer().getMessenger().registerIncomingPluginChannel(this, "me:friendsystem", pm);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder().register();
        }
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    public static FriendSystemGUI getInstance() {
        return instance;
    }

    public InventoryManager getManager() {
        return manager;
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

    public GuiConfig loadGuiConfig() {
        switch (settingsConfig.getLanguage()) {
            case "german":
                return new GuiGermanConfig("guis_german.yml", getDataFolder());
            case "english":
                return new GuiEnglishConfig("guis_english.yml",getDataFolder());
            case "french":
                return new GuiFrenchConfig("guis_french.yml", getDataFolder());
            case "chinese":
                return new GuiChineseConfig("guis_chinese.yml", getDataFolder());
            case "italian":
                return new GuiItalianConfig("guis_italian.yml", getDataFolder());
            case "russian":
                return new GuiRussianConfig("guis_russian.yml", getDataFolder());
            case "spanish":
                return new GuiSpanishConfig("guis_spanish.yml", getDataFolder());
            default:
                return new GuiEnglishConfig("guis_" + settingsConfig.getLanguage() + ".yml", getDataFolder());
        }
    }

    public void reloadConfigs() {
        settingsConfig = new SettingsConfig("settings.yml", getDataFolder());
        guiConfig = loadGuiConfig();
    }
}
