package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.mysql.MySQL;

import java.util.HashMap;
import java.util.UUID;

public class FriendManager {

	private final HashMap<UUID, FriendPlayer> friendPlayers;
	private final MySQL mySQL;
	public FriendManager() {
		this.friendPlayers = new HashMap<>();
		this.mySQL = FriendSystem.getInstance().getMySQL();
	}

	public FriendPlayer getFriendPlayer(UUID uuid) {
		if (!friendPlayers.containsKey(uuid)) {
			friendPlayers.put(uuid, mySQL.getFriendPlayer(uuid));
		}
		return friendPlayers.get(uuid);
	}

	public void addFriendPlayer(UUID uuid) {
		mySQL.addPlayerAsync(uuid);
	}

	public void updateLastSeen(UUID uuid) {
		mySQL.updateLastSeen(uuid);
	}
}
