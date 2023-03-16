package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Msg extends Command {
	private final FriendSystem fs;
	private final MessagesConfig c;

	public Msg() {
		super("msg");

		fs = FriendSystem.getInstance();
		c = fs.getConfig();
	}

	public void execute(CommandSender cs, String[] args) {
		if (cs instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) cs;
			if (args.length < 2) {
				player.sendMessage(new TextComponent(c.get("msg.error.syntax")));
				return;
			}

			String playerName = args[0];

			if (playerName.equalsIgnoreCase(player.getName())) {
				player.sendMessage(new TextComponent(c.get("msg.error.interact")));
				return;
			}

			UUIDFetcher.getUUID(playerName, uuid -> {
				if (uuid == null) {
					player.sendMessage(new TextComponent(c.get("msg.error.playernotfound")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}
				FriendPlayer friendPlayer = FriendSystem.getInstance().getFriendManager().getFriendPlayer(player.getUniqueId());
				FriendPlayer friendPlayer2 = FriendSystem.getInstance().getFriendManager().getFriendPlayer(uuid);
				if (friendPlayer2 == null) {
					player.sendMessage(new TextComponent(c.get("friend.error.notonnetwork")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}
				if (!friendPlayer.isFriendsWith(uuid)) {
					player.sendMessage(new TextComponent(c.get("msg.error.notfriends")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}
				if (!friendPlayer2.isMsgsAllowed()) {
					player.sendMessage(new TextComponent(c.get("msg.error.msgstoggled")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}
				ProxiedPlayer player2 = ProxyServer.getInstance().getPlayer(playerName);
				if (player2 == null) {
					player.sendMessage(new TextComponent(c.get("msg.error.notonline")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}

				StringBuilder msg = new StringBuilder();
				for (int i = 1; i < args.length; i++)
					msg.append(args[i]).append(" ");

				fs.msgs.put(player, player2);
				fs.msgs.put(player2, player);

				player.sendMessage(new TextComponent(c.get("msg.format.sender")
						.replaceAll("%PLAYER%", player.getName())
						.replaceAll("%PLAYER2%", player2.getName())
						.replaceAll("%MSG%", msg.toString())));

				player2.sendMessage(new TextComponent(c.get("msg.format.receiver")
						.replaceAll("%PLAYER%", player.getName())
						.replaceAll("%PLAYER2%", player2.getName())
						.replaceAll("%MSG%", msg.toString())));
			});
		}
	}

}
