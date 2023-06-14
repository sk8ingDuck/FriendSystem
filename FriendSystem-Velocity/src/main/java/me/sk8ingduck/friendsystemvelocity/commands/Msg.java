package me.sk8ingduck.friendsystemvelocity.commands;

import com.google.common.collect.ImmutableList;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import me.sk8ingduck.friendsystemvelocity.config.MessagesConfig;
import me.sk8ingduck.friendsystemvelocity.config.SettingsConfig;
import me.sk8ingduck.friendsystemvelocity.util.FriendManager;
import me.sk8ingduck.friendsystemvelocity.util.FriendPlayer;
import me.sk8ingduck.friendsystemvelocity.util.UUIDFetcher;
import me.sk8ingduck.friendsystemvelocity.util.Util;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Msg implements SimpleCommand {

	private final FriendManager fm;

	public Msg() {
		fm = FriendSystem.getInstance().getFriendManager();
	}

	@Override
	public List<String> suggest(Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();

		SettingsConfig settingsConfig = FriendSystem.getInstance().getSettingsConfig();

		if (!(source instanceof Player) || !settingsConfig.isTabComplete()) {
			return ImmutableList.of();
		}

		Player player = (Player) source;

		if (args.length == 0) {
			Collection<Player> players = (settingsConfig.isTabCompleteGlobal())
					? FriendSystem.server.getAllPlayers()
					: player.getCurrentServer().map(serverConnection -> serverConnection.getServer().getPlayersConnected())
					.orElse(ImmutableList.of());

			return players.stream().map(Player::getUsername).collect(Collectors.toList());
		}

		if (args.length == 1) {
			Collection<Player> players = (settingsConfig.isTabCompleteGlobal())
					? FriendSystem.server.getAllPlayers()
					: player.getCurrentServer().map(serverConnection -> serverConnection.getServer().getPlayersConnected())
					.orElse(ImmutableList.of());

			List<String> playerNames = players
					.stream()
					.map(Player::getUsername)
					.collect(Collectors.toList());

			return playerNames.stream()
					.filter(onlinePlayer -> onlinePlayer.toLowerCase().startsWith(args[0].toLowerCase()))
					.collect(Collectors.toList());
		}

		return ImmutableList.of();
	}


	@Override
	public void execute(Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();

		MessagesConfig c = FriendSystem.getInstance().getMessagesConfig();

		if (source instanceof Player) {
			Player player = (Player) source;
			if (args.length < 2) {
				player.sendMessage(c.get("msg.error.syntax"));
				return;
			}

			String playerName = args[0];

			if (playerName.equalsIgnoreCase(player.getUsername())) {
				player.sendMessage(c.get("msg.error.interact"));
				return;
			}

			boolean onlineMode = FriendSystem.server.getConfiguration().isOnlineMode();

			UUIDFetcher.getUUID(playerName, onlineMode, uuid -> {
				if (uuid == null) {
					player.sendMessage(c.get("msg.error.playernotfound", "%PLAYER%", playerName));
					return;
				}

				FriendPlayer friendPlayer = FriendSystem.getInstance().getFriendManager()
						.getFriendPlayer(onlineMode ? player.getUniqueId().toString() : player.getUsername());
				FriendPlayer friendPlayer2 = FriendSystem.getInstance().getFriendManager().getFriendPlayer(uuid);
				if (friendPlayer2 == null) {
					player.sendMessage(c.get("friend.error.notonnetwork", "%PLAYER%", playerName));
					return;
				}


				String ownPrefix = Util.getPrefix(player.getUniqueId());
				String prefix = Util.getPrefix(UUID.fromString(uuid));
				if (!friendPlayer.isFriendsWith(uuid) && !player.hasPermission("friend.msgbypass")) {
					player.sendMessage(c.get("msg.error.notfriends", "%PLAYER%", playerName, "%PREFIX%", prefix));
					return;
				}
				if (!friendPlayer2.isMsgsAllowed() && !player.hasPermission("friend.msgbypass")) {
					player.sendMessage(c.get("msg.error.msgstoggled", "%PLAYER%", playerName, "%PREFIX%", prefix));
					return;
				}
				FriendSystem.getPlayer(playerName).ifPresent(player2 -> {
					StringBuilder msg = new StringBuilder();
					for (int i = 1; i < args.length; i++)
						msg.append(args[i]).append(" ");

					fm.setMsgPartner(player, player2);
					fm.setMsgPartner(player2, player);

					player.sendMessage(c.get("msg.format.sender", false,
							"%PLAYER%", player.getUsername(),
							"%PREFIX%", ownPrefix,
							"%PLAYER2%", player2.getUsername(),
							"%PREFIX2%", prefix,
							"%MSG%", msg.toString()));

					player2.sendMessage(c.get("msg.format.receiver", false,
							"%PLAYER%", player.getUsername(),
							"%PREFIX%", ownPrefix,
							"%PLAYER2%", player2.getUsername(),
							"%PREFIX2%", prefix,
							"%MSG%", msg.toString()));
				});

				if (!FriendSystem.getPlayer(playerName).isPresent()) {
					player.sendMessage(c.get("msg.error.notonline", "%PLAYER%", playerName));
				}

			});
		}
	}
}
