package me.sk8ingduck.friendsystemgui.pluginmessage;

import java.util.UUID;

public class Request {

	private final UUID uuid;
	private final String name;

	public Request(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

}
