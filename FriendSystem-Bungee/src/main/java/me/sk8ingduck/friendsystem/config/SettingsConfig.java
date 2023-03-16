package me.sk8ingduck.friendsystem.config;

public class SettingsConfig extends Config {

	private final String language;

	public SettingsConfig(String name, String path) {
		super(name, path);

		this.language = (String) getPathOrSet("language", "german", false);
	}

	public String getLanguage() {
		return language;
	}
}

