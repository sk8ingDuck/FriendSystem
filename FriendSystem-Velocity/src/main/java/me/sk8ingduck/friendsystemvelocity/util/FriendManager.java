package me.sk8ingduck.friendsystemvelocity.util;

import com.velocitypowered.api.proxy.Player;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;

import java.util.HashMap;

public class FriendManager {

	private final HashMap<String, FriendPlayer> friendPlayers;
	private final HashMap<Player, Player> msgs;

	public FriendManager() {
		this.friendPlayers = new HashMap<>();
		this.msgs = new HashMap<>();
	}

	public FriendPlayer getFriendPlayer(String uuid) {
		if (!friendPlayers.containsKey(uuid)  || friendPlayers.get(uuid) == null) {
			friendPlayers.put(uuid, FriendSystem.getInstance().getMysql().getFriendPlayer(uuid));
		}

		return friendPlayers.get(uuid);
	}

	public void addFriendPlayer(String uuid) {
		FriendSystem.getInstance().getMysql().addPlayerAsync(uuid);
	}

	public void updateLastSeen(String uuid) {
		FriendSystem.getInstance().getMysql().updateLastSeenAsync(uuid);
	}

	public void setMsgPartner(Player player, Player partner) {
		msgs.put(player, partner);
	}
	public Player getMsgPartner(Player player) {
		return msgs.get(player);
	}
}
