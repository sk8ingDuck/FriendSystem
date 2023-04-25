package me.sk8ingduck.friendsystem.config;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SettingsConfig extends Config {

	private String language;
	private final boolean msgCommandEnabled;
	private final boolean rCommandEnabled;
	private final boolean permissionsEnabled;
	private Map<String, Integer> permissions;


	public SettingsConfig(String name, String path) {
		super(name, path);

		language = (String) getPathOrSet("language", "german", false);
		msgCommandEnabled = (boolean) getPathOrSet("commands.msgEnabled", true, false);
		rCommandEnabled = (boolean) getPathOrSet("commands.rCommand", true, false);
		permissionsEnabled = (boolean) getPathOrSet("permissions.enabled", false, false);

		permissions = new HashMap<>();
		loadPermissions();
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

	public void reload() {
		super.reload();
		this.language = (String) getPathOrSet("language", "german", false);
		loadPermissions();
	}


	public boolean isPermissionsEnabled() {
		return permissionsEnabled;
	}

	public int getMaxFriends(ProxiedPlayer player) {
		return permissions.entrySet().stream().filter(entry -> player.hasPermission(entry.getKey()))
				.mapToInt(Map.Entry::getValue).max().orElse(permissions.get("friendsystem.maxfriends.default"));
	}

	public void loadPermissions() {
		if (fileConfiguration.get("permissions.maxfriends.default") == null) {
			fileConfiguration.set("permissions.maxfriends.default", 5);
			fileConfiguration.set("permissions.maxfriends.premium", 25);
			fileConfiguration.set("permissions.maxfriends.premium+", 50);
			fileConfiguration.set("permissions.maxfriends.youtuber", 100);
			fileConfiguration.set("permissions.maxfriends.moderator", 500);
			super.save();
		}
		permissions = fileConfiguration.getSection("permissions.maxfriends")
				.getKeys()
				.stream()
				.collect(Collectors.toMap(key -> "friendsystem.maxfriends." + key, key -> fileConfiguration.getSection("permissions.maxfriends").getInt(key)));
	}

}

