package me.sk8ingduck.friendsystemgui.pluginmessage;

public class Request {

	private final String uuid;
	private final String name;

	public Request(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}


}
