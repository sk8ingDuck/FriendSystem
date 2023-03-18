package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class FriendManager {

	private final MySQL mySQL;
	private final HashMap<UUID, FriendPlayer> friendPlayers;
	private final HashMap<ProxiedPlayer, ProxiedPlayer> msgs;
	public FriendManager() {
		this.mySQL = FriendSystem.getInstance().getMySQL();
		this.friendPlayers = new HashMap<>();
		this.msgs = new HashMap<>();
	}

	public FriendPlayer getFriendPlayer(UUID uuid) {
		if (!friendPlayers.containsKey(uuid)  || friendPlayers.get(uuid) == null) {
			friendPlayers.put(uuid, mySQL.getFriendPlayer(uuid));
		}

		return friendPlayers.get(uuid);
	}

	public void addFriendPlayer(UUID uuid) {
		mySQL.addPlayerAsync(uuid);
	}

	public void updateLastSeen(UUID uuid) {
		mySQL.updateLastSeenAsync(uuid);
	}

	public void setMsgPartner(ProxiedPlayer player, ProxiedPlayer partner) {
		msgs.put(player, partner);
	}
	public ProxiedPlayer getMsgPartner(ProxiedPlayer player) {
		return msgs.get(player);
	}
}
