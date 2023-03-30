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

	public static FriendSystem getInstance() {
		return instance;
	}

	public void onEnable() {
		instance = this;

		DBConfig dbConfig = new DBConfig("database.yml", "plugins/FriendSystem");
		mysql = new MySQL(dbConfig.getHost(), dbConfig.getPort(), dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase());

		friendManager = new FriendManager();

		settingsConfig = new SettingsConfig("settings.yml", "plugins/FriendSystem");
		//init all language configs
        new MessagesGermanConfig("messages_german.yml", "plugins/FriendSystem");
		new MessagesEnglishConfig("messages_english.yml", "plugins/FriendSystem");
		new MessagesFrenchConfig("messages_french.yml", "plugins/FriendSystem");

		messagesConfig = loadMessagesConfig();

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

	public MySQL getMySQL() {
		return mysql;
	}

	public FriendManager getFriendManager() {
		return friendManager;
	}

	public MessagesConfig getConfig() {
		return messagesConfig;
	}

	public SettingsConfig getSettingsConfig() {
		return settingsConfig;
	}

	public void reloadConfigs() {
		settingsConfig.reload();
		messagesConfig = loadMessagesConfig();
	}

	public MessagesConfig loadMessagesConfig() {
		switch (settingsConfig.getLanguage()) {
			case "german":
				return new MessagesGermanConfig("messages_german.yml", "plugins/FriendSystem");
			case "english":
				return new MessagesEnglishConfig("messages_english.yml", "plugins/FriendSystem");
			case "french":
				return new MessagesFrenchConfig("messages_french.yml", "plugins/FriendSystem");
			default:
				return new MessagesEnglishConfig("messages_" + settingsConfig.getLanguage() + ".yml", "plugins/FriendSystem");
		}
	}

}
