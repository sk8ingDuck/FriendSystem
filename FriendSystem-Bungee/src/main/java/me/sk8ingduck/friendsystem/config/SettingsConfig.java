package me.sk8ingduck.friendsystem.config;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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



	public SettingsConfig(String name, String path) {
		super(name, path, true);

		loadValues();
	}

	private void loadValues() {
		language = (String) getPathOrSet("language", "english");

		msgCommandEnabled = (boolean) getPath("commands.msgEnabled");
		rCommandEnabled = (boolean) getPath("commands.rEnabled");

		tabComplete = (boolean) getPath("tabComplete.enabled");
		tabCompleteGlobal = (boolean) getPath("tabComplete.global");

		tabCompleteSuggestions = (ArrayList<String>) getPath("tabComplete.suggestions");
		tabCompleteSuggestPlayerAfter = (ArrayList<String>) getPath("tabComplete.suggestPlayerAfter");

		dateTimeFormat = (String) getPath("dateTimeFormat");

		onlineFriendsMessageMinimum = (int) getPath("joinMessages.onlineFriendsCount");
		requestsMessageMinimum = (int) getPath("joinMessages.requestsCount");

		requestDurationEnabled = (boolean) getPath("requestDuration.enabled");
		requestDuration = (int) getPath("requestDuration.duration");

		friendsPerPage = (int) getPath("pagination.friendsPerPage");
		requestsPerPage = (int) getPath("pagination.requestsPerPage");

		permissionsEnabled = (boolean) getPath("permissions.enabled");
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

	public int getFriendsPerPage() {
		return friendsPerPage;
	}

	public int getRequestsPerPage() {
		return requestsPerPage;
	}

	public void reload() {
		super.reload();
		loadValues();
	}


	public boolean isPermissionsEnabled() {
		return permissionsEnabled;
	}

	public int getMaxFriends(ProxiedPlayer player) {
		return permissions.entrySet().stream().filter(entry -> player.hasPermission(entry.getKey()))
				.mapToInt(Map.Entry::getValue).max().orElse(permissions.get("friendsystem.maxfriends.default"));
	}

	public void loadPermissions() {
		permissions = fileConfiguration.getSection("permissions.maxfriends")
				.getKeys()
				.stream()
				.collect(Collectors.toMap(key -> "friendsystem.maxfriends." + key,
						key -> fileConfiguration.getSection("permissions.maxfriends").getInt(key)));
	}

}

