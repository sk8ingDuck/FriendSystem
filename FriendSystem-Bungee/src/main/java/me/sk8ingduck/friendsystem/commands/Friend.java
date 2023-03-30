package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.config.SettingsConfig;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import me.sk8ingduck.friendsystem.utils.Util;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class Friend extends Command {

	private final MySQL mySQL;
	private final FriendManager fm;

	public Friend() {
		super("friend");

		FriendSystem fs = FriendSystem.getInstance();
		mySQL = fs.getMySQL();
		fm = fs.getFriendManager();
	}

	public void execute(CommandSender cs, String[] args) {
		if (!(cs instanceof ProxiedPlayer)) {
			return;
		}

		MessagesConfig c = FriendSystem.getInstance().getConfig();
		SettingsConfig settingsConfig = FriendSystem.getInstance().getSettingsConfig();
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
			cs.sendMessage(new TextComponent(c.get("friend.help", false)));
			return;
		}
		String action = args[0];

		boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();

		ProxiedPlayer player = (ProxiedPlayer) cs;
		//Use minecraft username as uuid if online mode is false
		String uuid = onlineMode ? player.getUniqueId().toString() : player.getName();
		FriendPlayer friendPlayer = fm.getFriendPlayer(uuid);
		if (args.length == 1) {
			if (action.equalsIgnoreCase("toggleinvites")) {
				friendPlayer.toggleInvites();
				mySQL.setOptionAsync(uuid, "invites", friendPlayer.isInvitesAllowed());
				player.sendMessage(new TextComponent(c.get("friend.toggleinvites." + (friendPlayer.isInvitesAllowed() ? "on" : "off"))));
			} else if (action.equalsIgnoreCase("togglemsgs")) {
				friendPlayer.toggleMsgs();
				mySQL.setOptionAsync(uuid, "msgs", friendPlayer.isMsgsAllowed());
				player.sendMessage(new TextComponent(c.get("friend.togglemsgs." + (friendPlayer.isMsgsAllowed() ? "on" : "off"))));
			} else if (action.equalsIgnoreCase("togglejump")) {
				friendPlayer.toggleJumping();
				mySQL.setOptionAsync(uuid, "jump", friendPlayer.isJumpingAllowed());
				player.sendMessage(new TextComponent(c.get("friend.togglejump." + (friendPlayer.isJumpingAllowed() ? "on" : "off"))));
			} else if (action.equalsIgnoreCase("togglenotifies")) {
				friendPlayer.toggleNotifies();
				mySQL.setOptionAsync(uuid, "notifies", friendPlayer.isNotifiesAllowed());
				player.sendMessage(new TextComponent(c.get("friend.togglenotifies." + (friendPlayer.isNotifiesAllowed() ? "on" : "off"))));
			} else if (action.equalsIgnoreCase("list")) {
				ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
					player.sendMessage(new TextComponent(c.get("friend.list.format.header", false)));
					friendPlayer.getOnlineFriends()
							.entrySet()
							.stream()
							.sorted((friend1, friend2) -> Boolean.compare(friend2.getValue(), friend1.getValue()))
							.forEach(onlineFriend -> {
								FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode
										? onlineFriend.getKey().getUniqueId().toString()
										: onlineFriend.getKey().getName());

								player.sendMessage(new TextComponent(c.get("friend.list.format.online."
												+ (onlineFriend.getValue() ? "favourite" : "regular"), false)
										.replaceAll("%PLAYERON%", onlineFriend.getKey().getName())
										.replaceAll("%SERVER%", onlineFriend.getKey().getServer().getInfo().getName())
										.replaceAll("%ONLINE_TIME%", Util.formatDifference(friendPlayer2.getLastSeen()))));
							});

					friendPlayer.getOfflineFriends()
							.entrySet()
							.stream()
							.sorted((friend1, friend2) -> Boolean.compare(friend2.getValue(), friend1.getValue()))
							.forEach(offlineFriend -> {
								FriendPlayer friendPlayer2 = fm.getFriendPlayer(offlineFriend.getKey());
								player.sendMessage(new TextComponent(c.get("friend.list.format.offline."
												+ (offlineFriend.getValue() ? "favourite" : "regular"), false)
										.replaceAll("%PLAYEROFF%", onlineMode ? UUIDFetcher.getName(UUID.fromString(offlineFriend.getKey())) : offlineFriend.getKey())
										.replaceAll("%OFFLINE_SINCE%", Util.formatDifference(friendPlayer2.getLastSeen()))));
							});

					player.sendMessage(new TextComponent(c.get("friend.list.format.footer", false)));
				});
			} else if (action.equalsIgnoreCase("requests")) {
				ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {
					player.sendMessage(new TextComponent(c.get("friend.request.format.header", false)));
					friendPlayer.getRequests().forEach(request ->
							player.sendMessage(new TextComponent(c.get("friend.request.format.player", false)
									.replaceAll("%PLAYER%", onlineMode ? UUIDFetcher.getName(UUID.fromString(request)) : request))));
					player.sendMessage(new TextComponent(c.get("friend.request.format.footer", false)));
				});
			} else {
				player.sendMessage(new TextComponent(c.get("friend.help", false)));
				return;
			}
		}

		if (args.length >= 2 && action.equalsIgnoreCase("status")) {
			StringBuilder status = new StringBuilder();
			for (int i = 1; i < args.length; i++)
				status.append(args[i]).append(" ");

			if (status.length() > 64) {
				player.sendMessage(new TextComponent(c.get("friend.status.toolong")));
				return;
			}
			String colorStatus = status.toString().replaceAll("&", "§");
			friendPlayer.updateStatus(colorStatus);
			mySQL.updateStatusAsync(uuid, colorStatus);
			player.sendMessage(new TextComponent(c.get("friend.status.update")
					.replaceAll("%STATUS%", colorStatus)));
			return;
		}
		if (args.length != 2) {
			if (action.equalsIgnoreCase("add")
					|| action.equalsIgnoreCase("remove")
					|| action.equalsIgnoreCase("accept")
					|| action.equalsIgnoreCase("deny")
					|| action.equalsIgnoreCase("jump")
					|| action.equalsIgnoreCase("favourite")) {
				player.sendMessage(new TextComponent(c.get("friend." + args[0] + ".syntax")));
				return;
			}
		}

		if (args.length == 2) {
			String playerName = args[1];

			if (playerName.equalsIgnoreCase(player.getName())) {
				player.sendMessage(new TextComponent(c.get("friend.error.interact")));
				return;
			}

			UUIDFetcher.getUUID(playerName, onlineMode, uuid2 -> {
				if (uuid2 == null) {
					player.sendMessage(new TextComponent(c.get("friend.error.playernotfound")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}
				FriendPlayer friendPlayer2 = fm.getFriendPlayer(uuid2);
				if (friendPlayer2 == null) {
					player.sendMessage(new TextComponent(c.get("friend.error.notonnetwork")
							.replaceAll("%PLAYER%", playerName)));
					return;
				}
				if (action.equalsIgnoreCase("add")) {
					if (friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.request.alreadyfriends")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					if (friendPlayer.isRequestedBy(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.request.alreadyreceivedrequest")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					if (friendPlayer2.isRequestedBy(uuid)) {
						player.sendMessage(new TextComponent(c.get("friend.request.alreadyrequested")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					if (settingsConfig.isPermissionsEnabled()) {
						int maxFriends = settingsConfig.getMaxFriends(player);
						if (friendPlayer.getFriends().size() >= maxFriends) {
							player.sendMessage(new TextComponent(c.get("friend.request.toomanyfriends")
									.replaceAll("%MAX_FRIENDS%", String.valueOf(maxFriends))));
							return;
						}
					}
					if (!friendPlayer2.isInvitesAllowed()) {
						player.sendMessage(new TextComponent(c.get("friend.request.invitestoggled")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}

					friendPlayer2.addRequest(uuid);
					mySQL.addRequestAsync(uuid2, uuid);

					player.sendMessage(new TextComponent(c.get("friend.request.sent")
							.replaceAll("%PLAYER%", playerName)));
					friendPlayer2.sendMessage(c.get("friend.request.received")
							.replaceAll("%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("remove")) {
					if (!friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.remove.notfriends")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					friendPlayer.removeFriend(uuid2);
					friendPlayer2.removeFriend(uuid);
					mySQL.removeFriendAsync(uuid, uuid2);
					mySQL.removeFriendAsync(uuid2, uuid);
					player.sendMessage(new TextComponent(c.get("friend.remove.successful")
							.replaceAll("%PLAYER%", playerName)));
					friendPlayer2.sendMessage(c.get("friend.remove.successful2")
							.replaceAll("%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("accept")) {
					if (!friendPlayer.isRequestedBy(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.add.norequest")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					if (friendPlayer.isFriendsWith(uuid2)) { //should not happen
						player.sendMessage(new TextComponent(c.get("friend.request.alreadyfriends")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					if (settingsConfig.isPermissionsEnabled()) {
						int maxFriends = settingsConfig.getMaxFriends(player);
						if (friendPlayer.getFriends().size() >= maxFriends) {
							player.sendMessage(new TextComponent(c.get("friend.request.toomanyfriends")
									.replaceAll("%MAX_FRIENDS%", String.valueOf(maxFriends))));
							return;
						}
					}					friendPlayer.removeRequest(uuid2);
					friendPlayer.addFriend(uuid2);
					friendPlayer2.addFriend(uuid);
					mySQL.addFriendAsync(uuid, uuid2);
					mySQL.addFriendAsync(uuid2, uuid);
					mySQL.removeRequestAsync(uuid, uuid2);
					player.sendMessage(new TextComponent(c.get("friend.add.successful")
							.replaceAll("%PLAYER%", playerName)));
					friendPlayer2.sendMessage(c.get("friend.add.successful2")
							.replaceAll("%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("deny")) {
					if (!friendPlayer.isRequestedBy(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.deny.norequest")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					friendPlayer.removeRequest(uuid2);
					mySQL.removeRequestAsync(uuid, uuid2);
					player.sendMessage(new TextComponent(c.get("friend.deny.successful")
							.replaceAll("%PLAYER%", playerName)));
					friendPlayer2.sendMessage(c.get("friend.deny.successful2")
							.replaceAll("%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("jump")) {
					if (!friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.jump.notfriends")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					if (!friendPlayer2.isJumpingAllowed()) {
						player.sendMessage(new TextComponent(c.get("friend.jump.jumptoggled")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					ProxiedPlayer player2 = ProxyServer.getInstance().getPlayer(playerName);
					if (player2 == null) {
						player.sendMessage(new TextComponent(c.get("friend.jump.notonline")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					player.connect(player2.getServer().getInfo());
				} else if (action.equalsIgnoreCase("favourite")) {
					if (!friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(new TextComponent(c.get("friend.favourite.notfriends")
								.replaceAll("%PLAYER%", playerName)));
						return;
					}
					boolean newState = !friendPlayer.getFriends().get(uuid2);
					friendPlayer.getFriends().put(uuid2, newState);
					mySQL.updateFavouriteAsync(uuid, uuid2, newState);
					player.sendMessage(new TextComponent(c.get("friend.favourite." + (newState ? "added" : "removed"))
							.replaceAll("%PLAYER%", playerName)));
				}
			});
		}
	}
}