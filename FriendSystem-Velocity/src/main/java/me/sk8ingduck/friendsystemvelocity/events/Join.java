package me.sk8ingduck.friendsystemvelocity.events;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import me.sk8ingduck.friendsystemvelocity.config.MessagesConfig;
import me.sk8ingduck.friendsystemvelocity.config.SettingsConfig;
import me.sk8ingduck.friendsystemvelocity.util.FriendManager;
import me.sk8ingduck.friendsystemvelocity.util.FriendPlayer;
import me.sk8ingduck.friendsystemvelocity.util.Util;

public class Join {

	private final FriendManager fm;
	public Join() {
		fm = FriendSystem.getInstance().getFriendManager();
	}

	@Subscribe
	public void onPostLogin(PostLoginEvent event) {
		MessagesConfig c = FriendSystem.getInstance().getMessagesConfig();

		Player player = event.getPlayer();

		boolean onlineMode = FriendSystem.server.getConfiguration().isOnlineMode();
		String playerUUIDorName = onlineMode ? player.getUniqueId().toString() : player.getUsername();

		FriendPlayer friendPlayer = fm.getFriendPlayer(playerUUIDorName);
		if (friendPlayer == null) {
			fm.addFriendPlayer(playerUUIDorName);
			return;
		}
		fm.updateLastSeen(playerUUIDorName); //update mysql
		friendPlayer.updateLastSeen(); //update cache

		friendPlayer.getOnlineFriends().keySet().forEach(friend -> {
			FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode ? friend.getUniqueId().toString() : friend.getUsername());
			if (friendPlayer2.isNotifiesAllowed()) {
				friend.sendMessage(c.get("notifies.join", "%PLAYER%", player.getUsername(),
						"%PREFIX%", Util.getPrefix(player.getUniqueId())));
			}
		});

		SettingsConfig settings = FriendSystem.getInstance().getSettingsConfig();
		int onlineFriends = friendPlayer.getOnlineFriends().size();
		int request = friendPlayer.getRequests().size();

		if (onlineFriends >= settings.getOnlineFriendsMessageMinimum())
			player.sendMessage(c.get("join.friendcounter", "%COUNT%", String.valueOf(onlineFriends)));
		if (request >= settings.getRequestsMessageMinimum())
			player.sendMessage(c.get("join.requestcounter", "%COUNT%", String.valueOf(request)));

	}
}
