package me.sk8ingduck.friendsystem;

import me.sk8ingduck.friendsystem.commands.Freload;
import me.sk8ingduck.friendsystem.commands.Friend;
import me.sk8ingduck.friendsystem.commands.Msg;
import me.sk8ingduck.friendsystem.commands.R;
import me.sk8ingduck.friendsystem.config.*;
import me.sk8ingduck.friendsystem.config.languages.*;
import me.sk8ingduck.friendsystem.events.Disconnect;
import me.sk8ingduck.friendsystem.events.Join;
import me.sk8ingduck.friendsystem.events.PluginMessage;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.UUID;
import java.util.regex.Pattern;

public class FriendSystem extends Plugin {


	private static FriendSystem instance;
	private MySQL mysql;
	private FriendManager friendManager;
	private SettingsConfig settingsConfig;
	private MessagesConfig messagesConfig;

	private Friend friendCommand;

	public static FriendSystem getInstance() {
		return instance;
	}

	public void onEnable() {
		instance = this;

		String pluginFolder = "plugins/FriendSystem";

		DBConfig dbConfig = new DBConfig("database.yml", pluginFolder);
		mysql = new MySQL(dbConfig.getHost(), dbConfig.getPort(), dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase());

		friendManager = new FriendManager();

		settingsConfig = new SettingsConfig("settings.yml", pluginFolder);
		//init all language configs
        new MessagesGermanConfig("messages_german.yml", pluginFolder);
		new MessagesEnglishConfig("messages_english.yml", pluginFolder);
		new MessagesFrenchConfig("messages_french.yml", pluginFolder);
		new MessagesChineseConfig("messages_chinese.yml", pluginFolder);
		new MessagesItalianConfig("messages_italian.yml", pluginFolder);

		messagesConfig = loadMessagesConfig();

		PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
		pluginManager.registerListener(this, new Join());
		pluginManager.registerListener(this, new Disconnect());
		pluginManager.registerListener(this, new PluginMessage());

		pluginManager.registerCommand(this, new Freload());
		friendCommand = new Friend();
		pluginManager.registerCommand(this, friendCommand);
		if (settingsConfig.isMsgCommandEnabled())
			pluginManager.registerCommand(this, new Msg());
		if (settingsConfig.isrCommandEnabled())
			pluginManager.registerCommand(this, new R());

		getProxy().registerChannel("me:friendsystem");

		getLogger().info("§aFriendSystem enabled!");
	}

	public void onDisable() {
		getLogger().info("§cFriendSystem disabled!");
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
		String pluginFolder = "plugins/FriendSystem";

		switch (settingsConfig.getLanguage()) {
			case "german":
				return new MessagesGermanConfig("messages_german.yml", pluginFolder);
			case "english":
				return new MessagesEnglishConfig("messages_english.yml", pluginFolder);
			case "french":
				return new MessagesFrenchConfig("messages_french.yml", pluginFolder);
			case "chinese":
				return new MessagesChineseConfig("messages_chinese.yml", pluginFolder);
			case "italian":
				return new MessagesItalianConfig("messages_italian.yml", pluginFolder);
			case "russian":
				return new MessagesRussianConfig("messages_russian.yml", pluginFolder);
			case "spanish":
				return new MessagesSpanishConfig("messages_spanish.yml", pluginFolder);
			default:
				return new MessagesEnglishConfig("messages_" + settingsConfig.getLanguage() + ".yml", pluginFolder);
		}
	}


	public static ProxiedPlayer getPlayer(String nameOrUuid) {
		if (getInstance().checkUUID(nameOrUuid))
			return ProxyServer.getInstance().getPlayer(UUID.fromString(nameOrUuid));

		return ProxyServer.getInstance().getPlayer(nameOrUuid);
	}
	private boolean checkUUID(String uuid) {
		return Pattern.compile("^(\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12})$")
				.matcher(uuid).matches();
	}

	public Friend getFriendCommand() {
		return friendCommand;
	}
}
