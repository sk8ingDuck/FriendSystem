package me.sk8ingduck.friendsystem.events;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Disconnect implements Listener {

	private final FriendManager fm;

	public Disconnect() {
		fm = FriendSystem.getInstance().getFriendManager();
	}

	@EventHandler
	public void onPlayerDisconnect(PlayerDisconnectEvent event) {
		MessagesConfig c = FriendSystem.getInstance().getConfig();

		ProxiedPlayer player = event.getPlayer();

		boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();
		String playerUUIDorName = onlineMode ? player.getUniqueId().toString() : player.getName();
		fm.updateLastSeen(playerUUIDorName); //mysql update

		FriendPlayer friendPlayer = fm.getFriendPlayer(playerUUIDorName);
		friendPlayer.updateLastSeen(); //cache update

		friendPlayer.getOnlineFriends().keySet().forEach(friend -> {
			FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode ? friend.getUniqueId().toString() : friend.getName());
			if (friendPlayer2.isNotifiesAllowed()) {
				friend.sendMessage(new TextComponent(c.get("notifies.leave")
						.replaceAll("%PLAYER%", player.getName())));
			}
		});
	}

}
