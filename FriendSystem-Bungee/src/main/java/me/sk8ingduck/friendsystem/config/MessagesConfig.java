package me.sk8ingduck.friendsystem.config;

import java.util.HashMap;

public abstract class MessagesConfig extends Config {

	protected final HashMap<String, String> messages;

	public MessagesConfig(String name, String path) {
		super(name, path);

		this.messages = new HashMap<>();
		loadMessages();
		messages.forEach((messagePath, message) -> messages.put(messagePath, (String) getPathOrSet(messagePath, message)));
	}

	public abstract void loadMessages();

	public String get(String path) {
		return get(path, true);
	}

	public String get(String path, boolean prefix) {
		return ((prefix ? messages.get("prefix") : "") + messages.get(path));
	}

}
