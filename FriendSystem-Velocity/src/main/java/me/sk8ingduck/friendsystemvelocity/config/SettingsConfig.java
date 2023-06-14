package me.sk8ingduck.friendsystemvelocity.config;

import com.velocitypowered.api.proxy.Player;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsConfig extends Config {

	private String language;

	private boolean msgCommandEnabled;
	private boolean rCommandEnabled;
	private boolean tabComplete;
	private boolean tabCompleteGlobal;
	private List<String> tabCompleteSuggestions;
	private List<String> tabCompleteSuggestPlayerAfter;
	private String dateTimeFormat;
	private int onlineFriendsMessageMinimum;
	private int requestsMessageMinimum;
	private boolean requestDurationEnabled;
	private int requestDuration;
	private int friendsPerPage;
	private int requestsPerPage;

	private boolean permissionsEnabled;
	private Map<String, Integer> permissions;
	public SettingsConfig(String filePath) {
		super(filePath, true);

		loadValues();
	}

	private void loadValues() {
		language = rootNode.node("language").getString();

		msgCommandEnabled = rootNode.node("commands", "msgEnabled").getBoolean();
		rCommandEnabled = rootNode.node("commands", "rEnabled").getBoolean();

		tabComplete = rootNode.node("tabComplete", "enabled").getBoolean();
		tabCompleteGlobal = rootNode.node("tabComplete", "global").getBoolean();
		try {
			tabCompleteSuggestions = rootNode.node("tabComplete", "suggestions").getList(String.class);
			tabCompleteSuggestPlayerAfter = rootNode.node("tabComplete", "suggestPlayerAfter").getList(String.class);
		} catch (SerializationException e) {
			throw new RuntimeException(e);
		}

		dateTimeFormat = rootNode.node("dateTimeFormat").getString();

		onlineFriendsMessageMinimum = rootNode.node("joinMessages", "onlineFriendsCount").getInt();
		requestsMessageMinimum = rootNode.node("joinMessages", "requestsCount").getInt();

		requestDurationEnabled = rootNode.node("requestDuration", "enabled").getBoolean();
		requestDuration = rootNode.node("requestDuration", "duration").getInt();
		friendsPerPage = rootNode.node("pagination", "friendsPerPage").getInt();
		requestsPerPage = rootNode.node("pagination", "requestsPerPage").getInt();

		permissionsEnabled = rootNode.node("permissions", "enabled").getBoolean();
		permissions = new HashMap<>();
		loadPermissions();
	}


	public void reload() {
		super.reload();
		loadValues();
	}

	public String getLanguage() {
		return language;
	}

	public boolean isMsgCommandEnabled() {
		return msgCommandEnabled;
	}

	public boolean isrCommandEnabled() {
		return rCommandEnabled;
	}


	public boolean isTabComplete() {
		return tabComplete;
	}

	public boolean isTabCompleteGlobal() {
		return tabCompleteGlobal;
	}

	public List<String> getTabCompleteSuggestions() {
		return tabCompleteSuggestions;
	}

	public List<String> getTabCompleteSuggestPlayerAfter() {
		return tabCompleteSuggestPlayerAfter;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public int getOnlineFriendsMessageMinimum() {
		return onlineFriendsMessageMinimum;
	}

	public int getRequestsMessageMinimum() {
		return requestsMessageMinimum;
	}

	public boolean isRequestDurationEnabled() {
		return requestDurationEnabled;
	}

	public int getRequestDuration() {
		return requestDuration;
	}

	public int getRequestsPerPage() {
		return requestsPerPage;
	}

	public int getFriendsPerPage() {
		return friendsPerPage;
	}

	public boolean isPermissionsEnabled() {
		return permissionsEnabled;
	}

	public int getMaxFriends(Player player) {
		return permissions.entrySet().stream().filter(entry -> player.hasPermission(entry.getKey()))
				.mapToInt(Map.Entry::getValue).max().orElse(permissions.get("friendsystem.maxfriends.default"));
	}

	public void loadPermissions() {
		ConfigurationNode maxFriendsNode = rootNode.node("permissions", "maxfriends");

		// Load the permissions into the map
		permissions.clear();
		for (Map.Entry<Object, ? extends ConfigurationNode> entry : maxFriendsNode.childrenMap().entrySet()) {
			String key = "friendsystem.maxfriends." + entry.getKey().toString();
			int value = entry.getValue().getInt();
			permissions.put(key, value);
		}
	}

}