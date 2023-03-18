package me.sk8ingduck.friendsystem.config;

public class SettingsConfig extends Config {

	private String language;

	public SettingsConfig(String name, String path) {
		super(name, path);

		this.language = (String) getPathOrSet("language", "german", false);
	}

	public String getLanguage() {
		return language;
	}

	public void reload() {
		super.reload();
		this.language = (String) getPathOrSet("language", "german", false);
	}
}

