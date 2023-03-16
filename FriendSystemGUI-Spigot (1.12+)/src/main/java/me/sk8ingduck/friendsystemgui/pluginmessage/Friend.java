package me.sk8ingduck.friendsystemgui.pluginmessage;

import java.util.UUID;

public class Friend {

	private final UUID uuid;
	private final String name;
	private final boolean online;
	private final String server;
	private final String lastSeen;

	public Friend(UUID uuid, String name, boolean online, String server, String lastSeen) {
		this.uuid = uuid;
		this.name = name;
		this.online = online;
		this.server = server;
		this.lastSeen = lastSeen;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public boolean isOnline() {
		return online;
	}

	public String getServer() {
		return server;
	}

	public String getLastSeen() {
		return lastSeen;
	}
}
