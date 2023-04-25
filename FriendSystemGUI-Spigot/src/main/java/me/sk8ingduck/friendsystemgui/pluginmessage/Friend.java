package me.sk8ingduck.friendsystemgui.pluginmessage;

public class Friend {
	private final String uuid;
	private final String name;
	private final boolean online;
	private final String server;
	private final String lastSeen;
	private final String status;
	private final boolean isFavourite;

	public Friend(String uuid, String name, boolean online, String server, String lastSeen, String status, boolean isFavourite) {
		this.uuid = uuid;
		this.name = name;
		this.online = online;
		this.server = server;
		this.lastSeen = lastSeen;
		this.status = status;
		this.isFavourite = isFavourite;
	}

	public String getUuid() {
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

	public String getStatus() {
		return status;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

}
