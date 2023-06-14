package me.sk8ingduck.friendsystemgui.pluginmessage;

public class Request {

	private final String uuid;
	private final String name;
	private String requestDate;
	private final String expiracy;
	public Request(String uuid, String name, String requestDate, String expiracy) {
		this.uuid = uuid;
		this.name = name;
		this.requestDate = requestDate;
		this.expiracy = expiracy;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public String getExpiracy() {
		return expiracy;
	}
}
