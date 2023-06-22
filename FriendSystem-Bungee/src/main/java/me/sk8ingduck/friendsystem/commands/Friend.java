package me.sk8ingduck.friendsystem.commands;

import com.google.common.collect.ImmutableList;
import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.config.SettingsConfig;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Friend extends Command implements TabExecutor {

	private final MySQL mySQL;
	private final FriendManager fm;

	public Friend() {
		super("friend");

		FriendSystem fs = FriendSystem.getInstance();
		mySQL = fs.getMySQL();
		fm = fs.getFriendManager();
	}

	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		SettingsConfig settingsConfig = FriendSystem.getInstance().getSettingsConfig();

		if (!(sender instanceof ProxiedPlayer) || !settingsConfig.isTabComplete()) {
			return ImmutableList.of();
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;

		if (args.length == 0) {
			return settingsConfig.getTabCompleteSuggestions();
		}

		if (args.length == 1) {
			return settingsConfig.getTabCompleteSuggestions().stream()
					.filter(action -> action.toLowerCase().startsWith(args[0].toLowerCase()))
					.collect(Collectors.toList());
		}

		if (args.length == 2 && settingsConfig.getTabCompleteSuggestPlayerAfter().contains(args[0].toLowerCase())) {
			Collection<ProxiedPlayer> players = (settingsConfig.isTabCompleteGlobal())
					? ProxyServer.getInstance().getPlayers()
					: player.getServer().getInfo().getPlayers();

			List<String> playerNames = players
					.stream()
					.map(ProxiedPlayer::getName)
					.collect(Collectors.toList());

			return playerNames.stream()
					.filter(onlinePlayer -> onlinePlayer.toLowerCase().startsWith(args[1].toLowerCase()))
					.collect(Collectors.toList());
		}

		return ImmutableList.of();
	}

	public void execute(CommandSender cs, String[] args) {
		if (!(cs instanceof ProxiedPlayer)) {
			return;
		}

		MessagesConfig c = FriendSystem.getInstance().getConfig();
		SettingsConfig settingsConfig = FriendSystem.getInstance().getSettingsConfig();
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
			cs.sendMessage(c.get("friend.help", false));
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
				player.sendMessage(c.get("friend.toggleinvites."
						+ (friendPlayer.isInvitesAllowed() ? "on" : "off")));
			} else if (action.equalsIgnoreCase("togglemsgs")) {
				friendPlayer.toggleMsgs();
				mySQL.setOptionAsync(uuid, "msgs", friendPlayer.isMsgsAllowed());
				player.sendMessage(c.get("friend.togglemsgs."
						+ (friendPlayer.isMsgsAllowed() ? "on" : "off")));
			} else if (action.equalsIgnoreCase("togglejump")) {
				friendPlayer.toggleJumping();
				mySQL.setOptionAsync(uuid, "jump", friendPlayer.isJumpingAllowed());
				player.sendMessage(c.get("friend.togglejump."
						+ (friendPlayer.isJumpingAllowed() ? "on" : "off")));
			} else if (action.equalsIgnoreCase("togglenotifies")) {
				friendPlayer.toggleNotifies();
				mySQL.setOptionAsync(uuid, "notifies", friendPlayer.isNotifiesAllowed());
				player.sendMessage(c.get("friend.togglenotifies."
						+ (friendPlayer.isNotifiesAllowed() ? "on" : "off")));
			} else if (action.equalsIgnoreCase("list")) {
				printFriendListPage(player, friendPlayer, 1, onlineMode);
			} else if (action.equalsIgnoreCase("requests")) {
				printFriendRequestPage(player, friendPlayer, 1, onlineMode);
			} else {
				player.sendMessage(c.get("friend.help", false));
				return;
			}
		}

		if (args.length >= 2 && action.equalsIgnoreCase("status")) {
			StringBuilder status = new StringBuilder();
			for (int i = 1; i < args.length; i++)
				status.append(args[i]).append(" ");

			if (status.length() > 64) {
				player.sendMessage(c.get("friend.status.toolong"));
				return;
			}

			friendPlayer.updateStatus(status.toString());
			mySQL.updateStatusAsync(uuid, status.toString());
			player.sendMessage(c.get("friend.status.update", "%STATUS%", friendPlayer.getStatus()));
			return;
		}
		if (args.length != 2) {
			if (action.equalsIgnoreCase("add")
					|| action.equalsIgnoreCase("remove")
					|| action.equalsIgnoreCase("accept")
					|| action.equalsIgnoreCase("deny")
					|| action.equalsIgnoreCase("jump")
					|| action.equalsIgnoreCase("favourite")) {
				player.sendMessage(c.get("friend." + args[0] + ".syntax"));
				return;
			}
		}

		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("list")) {
				printFriendListPage(player, friendPlayer, Integer.parseInt(args[1]), onlineMode);
				return;
			}
			if (args[0].equalsIgnoreCase("requests")) {
				printFriendRequestPage(player, friendPlayer, Integer.parseInt(args[1]), onlineMode);
				return;
			}

			if ((args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("deny"))
					&& args[1].equalsIgnoreCase("all")) {
				friendPlayer.getRequests().forEach(request ->
								UUIDFetcher.getName(UUID.fromString(request), name ->
								ProxyServer.getInstance().getPluginManager().dispatchCommand(player, "friend " + args[0] + " " + name)));
				return;
			}

			String playerName = args[1];

			if (playerName.equalsIgnoreCase(player.getName())) {
				player.sendMessage(c.get("friend.error.interact"));
				return;
			}

			UUIDFetcher.getUUID(playerName, onlineMode, uuid2 -> {
				if (uuid2 == null) {
					player.sendMessage(c.get("friend.error.playernotfound", "%PLAYER%", playerName));
					return;
				}
				FriendPlayer friendPlayer2 = fm.getFriendPlayer(uuid2);
				if (friendPlayer2 == null) {
					player.sendMessage(c.get("friend.error.notonnetwork", "%PLAYER%", playerName));
					return;
				}
				if (action.equalsIgnoreCase("add")) {
					if (friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(c.get("friend.request.alreadyfriends", "%PLAYER%", playerName));
						return;
					}
					if (friendPlayer.isRequestedBy(uuid2)) {
						player.sendMessage(c.get("friend.request.alreadyreceivedrequest",
								"%PLAYER%", playerName));
						return;
					}
					if (friendPlayer2.isRequestedBy(uuid)) {
						player.sendMessage(c.get("friend.request.alreadyrequested", "%PLAYER%", playerName));
						return;
					}
					if (settingsConfig.isPermissionsEnabled()) {
						int maxFriends = settingsConfig.getMaxFriends(player);
						if (friendPlayer.getFriends().size() >= maxFriends) {
							player.sendMessage(c.get("friend.request.toomanyfriends",
									"%MAX_FRIENDS%", String.valueOf(maxFriends)));
							return;
						}
					}
					if (!friendPlayer2.isInvitesAllowed()) {
						player.sendMessage(c.get("friend.request.invitestoggled", "%PLAYER%", playerName));
						return;
					}

					friendPlayer2.addRequest(uuid);
					mySQL.addRequestAsync(uuid2, uuid);

					player.sendMessage(c.get("friend.request.sent", "%PLAYER%", playerName));
					friendPlayer2.sendMessage(c.get("friend.request.received", false, "%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("remove")) {
					if (!friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(c.get("friend.remove.notfriends", "%PLAYER%", playerName));
						return;
					}
					friendPlayer.removeFriend(uuid2);
					friendPlayer2.removeFriend(uuid);
					mySQL.removeFriendAsync(uuid, uuid2);
					mySQL.removeFriendAsync(uuid2, uuid);
					player.sendMessage(c.get("friend.remove.successful", "%PLAYER%", playerName));
					friendPlayer2.sendMessage(c.get("friend.remove.successful2", "%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("accept")) {
					if (!friendPlayer.isRequestedBy(uuid2)) {
						player.sendMessage(c.get("friend.add.norequest", "%PLAYER%", playerName));
						return;
					}
					if (friendPlayer.isFriendsWith(uuid2)) { //should not happen
						player.sendMessage(c.get("friend.request.alreadyfriends", "%PLAYER%", playerName));
						return;
					}
					if (settingsConfig.isPermissionsEnabled()) {
						int maxFriends = settingsConfig.getMaxFriends(player);
						if (friendPlayer.getFriends().size() >= maxFriends) {
							player.sendMessage(c.get("friend.request.toomanyfriends", "%MAX_FRIENDS%", String.valueOf(maxFriends)));
							return;
						}
					}
					friendPlayer.removeRequest(uuid2);
					friendPlayer.addFriend(uuid2);
					friendPlayer2.addFriend(uuid);
					mySQL.addFriendAsync(uuid, uuid2);
					mySQL.addFriendAsync(uuid2, uuid);
					mySQL.removeRequestAsync(uuid, uuid2);
					player.sendMessage(c.get("friend.add.successful", "%PLAYER%", playerName));
					friendPlayer2.sendMessage(c.get("friend.add.successful2", "%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("deny")) {
					if (!friendPlayer.isRequestedBy(uuid2)) {
						player.sendMessage(c.get("friend.deny.norequest", "%PLAYER%", playerName));
						return;
					}
					friendPlayer.removeRequest(uuid2);
					mySQL.removeRequestAsync(uuid, uuid2);
					player.sendMessage(c.get("friend.deny.successful", "%PLAYER%", playerName));
					friendPlayer2.sendMessage(c.get("friend.deny.successful2", "%PLAYER%", player.getName()));
				} else if (action.equalsIgnoreCase("jump")) {
					if (!friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(c.get("friend.jump.notfriends", "%PLAYER%", playerName));
						return;
					}
					if (!friendPlayer2.isJumpingAllowed()) {
						player.sendMessage(c.get("friend.jump.jumptoggled", "%PLAYER%", playerName));
						return;
					}
					ProxiedPlayer player2 = ProxyServer.getInstance().getPlayer(playerName);
					if (player2 == null) {
						player.sendMessage(c.get("friend.jump.notonline", "%PLAYER%", playerName));
						return;
					}
					player.connect(player2.getServer().getInfo());
				} else if (action.equalsIgnoreCase("favourite")) {
					if (!friendPlayer.isFriendsWith(uuid2)) {
						player.sendMessage(c.get("friend.favourite.notfriends", "%PLAYER%", playerName));
						return;
					}
					boolean newState = !friendPlayer.getFriends().get(uuid2);
					friendPlayer.getFriends().put(uuid2, newState);
					mySQL.updateFavouriteAsync(uuid, uuid2, newState);
					player.sendMessage(c.get("friend.favourite." + (newState ? "added" : "removed"), "%PLAYER%", playerName));
				} else if (action.equalsIgnoreCase("removestatus") && player.hasPermission("friendsystem.removestatus")) {
					friendPlayer2.updateStatus("");
					mySQL.updateStatusAsync(uuid2, "");
					player.sendMessage(new TextComponent("§a[FriendSystem] Status of " + playerName + " removed."));
				}
			});
		}
	}


	private void printFriendRequestPage(ProxiedPlayer player, FriendPlayer friendPlayer, int page, boolean onlineMode) {
		MessagesConfig c = FriendSystem.getInstance().getConfig();

		int requestsPerPage = FriendSystem.getInstance().getSettingsConfig().getRequestsPerPage();
		ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {

			List<Map.Entry<String, LocalDateTime>> requests = new ArrayList<>(friendPlayer.getRequestsAndExpiracy().entrySet());

			if (requests.size() == 0) {
				player.sendMessage(c.get("friend.request.format.norequests"));
				return;
			}

			int totalPages = (int) Math.ceil((double) requests.size() / requestsPerPage);

			if (page > totalPages || page < 1) {
				player.sendMessage(c.get("friend.request.format.pagination.pagenotfound"));
				return;
			}

			player.sendMessage(c.get("friend.request.format.header", false, "%CURRENT_PAGE%",
					String.valueOf(page), "%TOTAL_PAGES%", String.valueOf(totalPages)));

			int startingIndex = (page - 1) * requestsPerPage;
			int endingIndex = Math.min(startingIndex + requestsPerPage, requests.size());

			requests.subList(startingIndex, Math.min(requests.size(), endingIndex)).forEach(request -> {
				player.sendMessage(c.get("friend.request.format.player", false,
						"%PLAYER%", onlineMode
								? UUIDFetcher.getName(UUID.fromString(request.getKey()))
								: request.getKey(),
						"%EXPIRES_IN%", c.formatDifferenceRequest(request.getValue())));
			});

			if (totalPages > 1) {
				//print pagination menu
				ComponentBuilder cb = new ComponentBuilder();
				cb.append(c.get("friend.request.format.pagination.textbeforepage", false));
				if (page - 1 > 0) {
					cb.append(c.get("friend.request.format.pagination.previouspage", false,
							"%PREVIOUS_PAGE%", String.valueOf((page - 1))));
				}
				cb.append(c.get("friend.request.format.pagination.currentpage", false,
						"%CURRENT_PAGE%", String.valueOf((page))));
				if (page + 1 <= totalPages) {
					cb.append(c.get("friend.request.format.pagination.nextpage", false,
							"%NEXT_PAGE%", String.valueOf((page + 1))));
				}
				player.sendMessage(cb.create());
			}

			player.sendMessage(c.get("friend.request.format.footer", false, "%CURRENT_PAGE%",
					String.valueOf(page), "%TOTAL_PAGES%", String.valueOf(totalPages)));

		});
	}

	private void printFriendListPage(ProxiedPlayer player, FriendPlayer friendPlayer, int page, boolean onlineMode) {
		MessagesConfig c = FriendSystem.getInstance().getConfig();

		int friendsPerPage = FriendSystem.getInstance().getSettingsConfig().getFriendsPerPage();
		ProxyServer.getInstance().getScheduler().runAsync(FriendSystem.getInstance(), () -> {

			List<Map.Entry<ProxiedPlayer, Boolean>> onlineFriendsList = new ArrayList<>(friendPlayer.getOnlineFriends().entrySet());
			List<Map.Entry<String, Boolean>> offlineFriendsList = new ArrayList<>(friendPlayer.getOfflineFriends().entrySet());

			onlineFriendsList.sort((friend1, friend2) -> Boolean.compare(friend2.getValue(), friend1.getValue()));
			offlineFriendsList.sort((friend1, friend2) -> Boolean.compare(friend2.getValue(), friend1.getValue()));

			int totalFriends = onlineFriendsList.size() + offlineFriendsList.size();

			if (totalFriends == 0) {
				player.sendMessage(c.get("friend.list.format.nofriends"));
				return;
			}

			int totalPages = (int) Math.ceil((double) totalFriends / friendsPerPage);

			if (page > totalPages || page < 1) {
				player.sendMessage(c.get("friend.list.format.pagination.pagenotfound"));
				return;
			}

			player.sendMessage(c.get("friend.list.format.header", false, "%CURRENT_PAGE%",
					String.valueOf(page), "%TOTAL_PAGES%", String.valueOf(totalPages)));

			int startingIndex = (page - 1) * friendsPerPage;
			int endingIndex = Math.min(startingIndex + friendsPerPage, onlineFriendsList.size() + offlineFriendsList.size());

			// Display online friends
			if (startingIndex < onlineFriendsList.size()) {
				onlineFriendsList.subList(startingIndex, Math.min(onlineFriendsList.size(), endingIndex)).forEach(onlineFriend -> {
					FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode
							? onlineFriend.getKey().getUniqueId().toString()
							: onlineFriend.getKey().getName());

					player.sendMessage(c.get("friend.list.format.online."
									+ (onlineFriend.getValue() ? "favourite" : "regular"), false,
							"%PLAYERON%", onlineFriend.getKey().getName(),
							"%SERVER%", onlineFriend.getKey().getServer().getInfo().getName(),
							"%ONLINE_TIME%", c.formatDifference(friendPlayer2.getLastSeen())));
				});
			}

			// Display offline friends, if the page includes them.
			if (endingIndex > onlineFriendsList.size()) {
				int offlineStart = Math.max(startingIndex - onlineFriendsList.size(), 0);
				int offlineEnd = endingIndex - onlineFriendsList.size();
				offlineFriendsList.subList(offlineStart, offlineEnd).forEach(offlineFriend -> {
					FriendPlayer friendPlayer2 = fm.getFriendPlayer(offlineFriend.getKey());
					player.sendMessage(c.get("friend.list.format.offline."
									+ (offlineFriend.getValue() ? "favourite" : "regular"), false,
							"%OFFLINE_SINCE%", c.formatDifference(friendPlayer2.getLastSeen()),
							"%PLAYEROFF%", onlineMode
									? UUIDFetcher.getName(UUID.fromString(offlineFriend.getKey()))
									: offlineFriend.getKey()));
				});
			}


			if (totalPages > 1) {
				//print pagination menu
				ComponentBuilder cb = new ComponentBuilder();
				cb.append(c.get("friend.list.format.pagination.textbeforepage", false));
				if (page - 1 > 0) {
					cb.append(c.get("friend.list.format.pagination.previouspage", false,
							"%PREVIOUS_PAGE%", String.valueOf((page - 1))));
				}
				cb.append(c.get("friend.list.format.pagination.currentpage", false,
						"%CURRENT_PAGE%", String.valueOf((page))));
				if (page + 1 <= totalPages) {
					cb.append(c.get("friend.list.format.pagination.nextpage", false,
							"%NEXT_PAGE%", String.valueOf((page + 1))));
				}
				player.sendMessage(cb.create());
			}

			player.sendMessage(c.get("friend.list.format.footer", false, "%CURRENT_PAGE%",
					String.valueOf(page), "%TOTAL_PAGES%", String.valueOf(totalPages)));
		});
	}
}