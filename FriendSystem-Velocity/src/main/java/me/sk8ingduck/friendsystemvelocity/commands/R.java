package me.sk8ingduck.friendsystemvelocity.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import me.sk8ingduck.friendsystemvelocity.config.MessagesConfig;
import me.sk8ingduck.friendsystemvelocity.util.FriendManager;
import me.sk8ingduck.friendsystemvelocity.util.FriendPlayer;
import me.sk8ingduck.friendsystemvelocity.util.Util;

import java.util.UUID;

public class R implements SimpleCommand {

	private final FriendManager fm;

	public R() {
		fm = FriendSystem.getInstance().getFriendManager();
	}

	@Override
	public void execute(Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();
		MessagesConfig c = FriendSystem.getInstance().getMessagesConfig();

		if (source instanceof Player) {
			Player player = (Player) source;
			if (args.length < 1) {
				player.sendMessage(c.get("r.error.syntax"));
				return;
			}
			Player player2 = fm.getMsgPartner(player);
			if (player2 == null || !player2.isActive()) {
				player.sendMessage(c.get("r.error.noreceiver"));
				return;
			}

			boolean onlineMode = FriendSystem.server.getConfiguration().isOnlineMode();
			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? player.getUniqueId().toString() : player.getUsername());
			FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode ? player2.getUniqueId().toString() : player2.getUsername());

			String ownPrefix = Util.getPrefix(player.getUniqueId());
			String prefix = Util.getPrefix(player2.getUniqueId());

			if (!friendPlayer.isFriendsWith(onlineMode ? player2.getUniqueId().toString() : player2.getUsername()) &&
					!player.hasPermission("friend.msgbypass")) {
				player.sendMessage(c.get("msg.error.notfriends",
						"%PLAYER%", player2.getUsername(),
						"%PREFIX%", prefix));
				return;
			}

			if (!friendPlayer2.isMsgsAllowed() && !player.hasPermission("friend.msgbypass")) {
				player.sendMessage(c.get("msg.error.msgstoggled", "%PLAYER%", player2.getUsername(), "%PREFIX%", prefix));
				return;
			}

			StringBuilder msg = new StringBuilder();

			for (String arg : args) msg.append(arg).append(" ");

			fm.setMsgPartner(player, player2);
			fm.setMsgPartner(player2, player);

			player.sendMessage(c.get("msg.r.sender", false,
					"%PLAYER%", player.getUsername(),
					"%PREFIX%", ownPrefix,
					"%PLAYER2%", player2.getUsername(),
					"%PREFIX2%", prefix,
					"%MSG%", msg.toString()));

			player2.sendMessage(c.get("msg.r.receiver", false,
					"%PLAYER%", player.getUsername(),
					"%PREFIX%", ownPrefix,
					"%PLAYER2%", player2.getUsername(),
					"%PREFIX2%", prefix,
					"%MSG%", msg.toString()));
		}
	}
}
