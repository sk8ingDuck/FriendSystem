package me.sk8ingduck.friendsystem.commands;

import com.google.common.collect.ImmutableList;
import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.config.SettingsConfig;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Msg extends Command implements TabExecutor {
	private final FriendManager fm;

	public Msg() {
		super("msg");

		fm = FriendSystem.getInstance().getFriendManager();
	}

	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		SettingsConfig settingsConfig = FriendSystem.getInstance().getSettingsConfig();

		if (!(sender instanceof ProxiedPlayer) || !settingsConfig.isTabComplete()) {
			return ImmutableList.of();
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;

		if (args.length == 0) {
			Collection<ProxiedPlayer> players = (settingsConfig.isTabCompleteGlobal())
					? ProxyServer.getInstance().getPlayers()
					: player.getServer().getInfo().getPlayers();

			return players.stream().map(ProxiedPlayer::getName).collect(Collectors.toList());
		}

		if (args.length == 1) {
			Collection<ProxiedPlayer> players = (settingsConfig.isTabCompleteGlobal())
					? ProxyServer.getInstance().getPlayers()
					: player.getServer().getInfo().getPlayers();

			List<String> playerNames = players
					.stream()
					.map(ProxiedPlayer::getName)
					.collect(Collectors.toList());

			return playerNames.stream()
					.filter(onlinePlayer -> onlinePlayer.toLowerCase().startsWith(args[0].toLowerCase()))
					.collect(Collectors.toList());
		}

		return ImmutableList.of();
	}

	public void execute(CommandSender cs, String[] args) {
		MessagesConfig c = FriendSystem.getInstance().getConfig();

		if (cs instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) cs;
			if (args.length < 2) {
				player.sendMessage(c.get("msg.error.syntax"));
				return;
			}

			String playerName = args[0];

			if (playerName.equalsIgnoreCase(player.getName())) {
				player.sendMessage(c.get("msg.error.interact"));
				return;
			}

			boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();

			UUIDFetcher.getUUID(playerName, onlineMode, uuid -> {
				if (uuid == null) {
					player.sendMessage(c.get("msg.error.playernotfound", "%PLAYER%", playerName));
					return;
				}

				FriendPlayer friendPlayer = FriendSystem.getInstance().getFriendManager()
						.getFriendPlayer(onlineMode ? player.getUniqueId().toString() : player.getName());
				FriendPlayer friendPlayer2 = FriendSystem.getInstance().getFriendManager().getFriendPlayer(uuid);
				if (friendPlayer2 == null) {
					player.sendMessage(c.get("friend.error.notonnetwork", "%PLAYER%", playerName));
					return;
				}
				if (!friendPlayer.isFriendsWith(uuid) && !player.hasPermission("friend.msgbypass")) {
					player.sendMessage(c.get("msg.error.notfriends", "%PLAYER%", playerName));
					return;
				}
				if (!friendPlayer2.isMsgsAllowed() && !player.hasPermission("friend.msgbypass")) {
					player.sendMessage(c.get("msg.error.msgstoggled", "%PLAYER%", playerName));
					return;
				}
				ProxiedPlayer player2 = ProxyServer.getInstance().getPlayer(playerName);
				if (player2 == null) {
					player.sendMessage(c.get("msg.error.notonline", "%PLAYER%", playerName));
					return;
				}

				StringBuilder msg = new StringBuilder();
				for (int i = 1; i < args.length; i++)
					msg.append(args[i]).append(" ");

				fm.setMsgPartner(player, player2);
				fm.setMsgPartner(player2, player);

				player.sendMessage(c.get("msg.format.sender", false,
						"%PLAYER%", player.getName(),
						"%PLAYER2%", player2.getName(),
						"%MSG%", msg.toString()));

				player2.sendMessage(c.get("msg.format.receiver", false,
						"%PLAYER%", player.getName(),
						"%PLAYER2%", player2.getName(),
						"%MSG%", msg.toString()));
			});
		}
	}

}
