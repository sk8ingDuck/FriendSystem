package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class R extends Command {
	private final FriendManager fm;

	public R() {
		super("r");

		fm = FriendSystem.getInstance().getFriendManager();
	}

	public void execute(CommandSender cs, String[] args) {
		MessagesConfig c = FriendSystem.getInstance().getConfig();

		if (cs instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) cs;
			if (args.length < 1) {
				player.sendMessage(c.get("r.error.syntax"));
				return;
			}
			ProxiedPlayer player2 = fm.getMsgPartner(player);
			if (player2 == null || !player2.isConnected()) {
				player.sendMessage(c.get("r.error.noreceiver"));
				return;
			}

			boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();
			FriendPlayer friendPlayer = fm.getFriendPlayer(onlineMode ? player.getUniqueId().toString() : player.getName());
			FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode ? player2.getUniqueId().toString() : player2.getName());

			if (!friendPlayer.isFriendsWith(onlineMode ? player2.getUniqueId().toString() : player2.getName()) &&
					!player.hasPermission("friend.msgbypass")) {
				player.sendMessage(c.get("msg.error.notfriends",
						"%PLAYER%", player2.getName()));
				return;
			}

			if (!friendPlayer2.isMsgsAllowed() && !player.hasPermission("friend.msgbypass")) {
				player.sendMessage(c.get("msg.error.msgstoggled", "%PLAYER%", player2.getName()));
				return;
			}

			StringBuilder msg = new StringBuilder();

			for (String arg : args) msg.append(arg).append(" ");

			fm.setMsgPartner(player, player2);
			fm.setMsgPartner(player2, player);

			player.sendMessage(c.get("msg.r.sender", false,
					"%PLAYER%", player.getName(),
					"%PLAYER2%", player2.getName(),
					"%MSG%", msg.toString()));

			player2.sendMessage(c.get("msg.r.receiver", false,
					"%PLAYER%", player.getName(),
					"%PLAYER2%", player2.getName(),
					"%MSG%", msg.toString()));
		}
	}
}
