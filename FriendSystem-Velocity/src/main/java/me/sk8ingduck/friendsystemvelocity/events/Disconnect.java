package me.sk8ingduck.friendsystemvelocity.events;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import me.sk8ingduck.friendsystemvelocity.config.MessagesConfig;
import me.sk8ingduck.friendsystemvelocity.util.FriendManager;
import me.sk8ingduck.friendsystemvelocity.util.FriendPlayer;
import me.sk8ingduck.friendsystemvelocity.util.Util;

public class Disconnect {

	private final FriendManager fm;

	public Disconnect() {
		fm = FriendSystem.getInstance().getFriendManager();
	}


	@Subscribe
	public void onPostLogin(DisconnectEvent event) {
		MessagesConfig c = FriendSystem.getInstance().getMessagesConfig();

		Player player = event.getPlayer();

		boolean onlineMode = FriendSystem.server.getConfiguration().isOnlineMode();
		String playerUUIDorName = onlineMode ? player.getUniqueId().toString() : player.getUsername();
		fm.updateLastSeen(playerUUIDorName); //mysql update

		FriendPlayer friendPlayer = fm.getFriendPlayer(playerUUIDorName);
		friendPlayer.updateLastSeen(); //cache update

		friendPlayer.getOnlineFriends().keySet().forEach(friend -> {
			FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode ? friend.getUniqueId().toString() : friend.getUsername());
			if (friendPlayer2.isNotifiesAllowed()) {
				friend.sendMessage(c.get("notifies.leave", "%PLAYER%", player.getUsername(),
						"%PREFIX%", Util.getPrefix(player.getUniqueId())));
			}
		});
	}
}
