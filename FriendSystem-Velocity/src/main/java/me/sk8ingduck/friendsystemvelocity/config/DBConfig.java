package me.sk8ingduck.friendsystemvelocity.config;

public class DBConfig extends Config {

	private final String host;
	private final int port;
	private final String username;
	private final String password;
	private final String database;

	public DBConfig(String filePath) {
		super(filePath, false);

		this.host = rootNode.node("mysql", "host").getString("localhost");
		this.port = rootNode.node("mysql", "port").getInt(3306);
		this.username = rootNode.node("mysql", "username").getString("root");
		this.password = rootNode.node("mysql", "password").getString("pw");
		this.database = rootNode.node("mysql", "database").getString("db");

		save();
	}

	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getDatabase() {
		return database;
	}
}