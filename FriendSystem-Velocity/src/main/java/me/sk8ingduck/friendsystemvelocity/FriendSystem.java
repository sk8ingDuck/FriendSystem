package me.sk8ingduck.friendsystemvelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginDescription;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import me.sk8ingduck.friendsystemvelocity.commands.Freload;
import me.sk8ingduck.friendsystemvelocity.commands.Friend;
import me.sk8ingduck.friendsystemvelocity.commands.Msg;
import me.sk8ingduck.friendsystemvelocity.commands.R;
import me.sk8ingduck.friendsystemvelocity.config.DBConfig;
import me.sk8ingduck.friendsystemvelocity.config.MessagesConfig;
import me.sk8ingduck.friendsystemvelocity.config.SettingsConfig;
import me.sk8ingduck.friendsystemvelocity.config.languages.MessagesEnglishConfig;
import me.sk8ingduck.friendsystemvelocity.events.Disconnect;
import me.sk8ingduck.friendsystemvelocity.events.Join;
import me.sk8ingduck.friendsystemvelocity.events.PluginMessage;
import me.sk8ingduck.friendsystemvelocity.mysql.MySQL;
import me.sk8ingduck.friendsystemvelocity.mysql.MySQLDriver;
import me.sk8ingduck.friendsystemvelocity.util.FriendManager;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Plugin(
		id = "friendsystem",
		name = "FriendSystem",
		version = "2.1",
		authors = "sk8ingDuck",
		dependencies = {
				@Dependency(id = "LuckPerms", optional = true)
		}
)
public class FriendSystem {

	public static FriendSystem instance;
	public static ProxyServer server;
	private final PluginDescription description;
	private final Logger logger;
	private FriendManager friendManager;
	private MySQL mysql;
	private SettingsConfig settingsConfig;
	private MessagesConfig messagesConfig;

	@Inject
	public FriendSystem(ProxyServer server, Logger logger, PluginDescription description) {
		instance = this;
		FriendSystem.server = server;
		this.description = description;
		this.logger = logger;

		try {
			new MySQLDriver(Paths.get("plugins/FriendSystem/driver"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		logger.info("FriendSystem loaded.");
	}

	public static Optional<Player> getPlayer(String nameOrUuid) {
		if (Pattern.compile("^(\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12})$")
				.matcher(nameOrUuid).matches())
			return server.getPlayer(UUID.fromString(nameOrUuid));

		return server.getPlayer(nameOrUuid);
	}

	public static FriendSystem getInstance() {
		return instance;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {

		DBConfig dbConfig = new DBConfig("plugins/FriendSystem/database.yml");

		mysql = new MySQL(dbConfig.getHost(), dbConfig.getPort(),
				dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase());

		friendManager = new FriendManager();

		settingsConfig = new SettingsConfig("plugins/FriendSystem/settings.yml");

		messagesConfig = new MessagesEnglishConfig("plugins/FriendSystem/messages_english.yml");

		CommandManager commandManager = server.getCommandManager();
		commandManager.register(commandManager.metaBuilder("friend").aliases("friends", "f").build(), new Friend());

		if (settingsConfig.isMsgCommandEnabled())
			commandManager.register(commandManager.metaBuilder("msg").build(), new Msg());
		if (settingsConfig.isrCommandEnabled())
			commandManager.register(commandManager.metaBuilder("r").build(), new R());

		commandManager.register(commandManager.metaBuilder("freload").build(), new Freload());


		server.getChannelRegistrar().register(MinecraftChannelIdentifier.create("me", "friendsystem"));

		server.getEventManager().register(this, new Join());
		server.getEventManager().register(this, new Disconnect());
		server.getEventManager().register(this, new PluginMessage());


		getLatestVersion(108701, version -> {
			if (!description.getVersion().orElse("").equalsIgnoreCase(version)) {
				logger.info("§6There is a new version available on SpigotMC!");
			} else {
				logger.info("§aPlugin is up-to-date!");
			}
		});
	}

	public FriendManager getFriendManager() {
		return friendManager;
	}

	public MySQL getMysql() {
		return mysql;
	}

	public SettingsConfig getSettingsConfig() {
		return settingsConfig;
	}

	public MessagesConfig getMessagesConfig() {
		return messagesConfig;
	}

	public void reloadConfigs() {
		settingsConfig.reload();
		messagesConfig = new MessagesEnglishConfig("plugins/FriendSystem/messages_english.yml");
	}

	public void getLatestVersion(int resourceId, Consumer<String> version) {
		try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
		     Scanner scanner = new Scanner(inputStream)) {
			if (scanner.hasNext()) {
				version.accept(scanner.next());
			}
		} catch (IOException exception) {
			logger.info("Cannot check for updates: " + exception.getMessage());
		}
	}

}
