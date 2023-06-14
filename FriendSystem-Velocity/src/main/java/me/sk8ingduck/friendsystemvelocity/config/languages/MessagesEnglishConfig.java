package me.sk8ingduck.friendsystemvelocity.config.languages;

import me.sk8ingduck.friendsystemvelocity.config.MessagesConfig;

public class MessagesEnglishConfig extends MessagesConfig {

	public MessagesEnglishConfig(String path) {
		super(path);
	}

	public void loadMessages() {
		messages.put("prefix", "<red>Friends <grey>»<blue> ");

		messages.put("friend.help",
				"<gray><strikethrough>-----------------</strikethrough><yellow>« FriendSystem »<gray><strikethrough>-----------------</strikethrough>\n" +
						"<yellow>/friend add <player> <gray>- <gray>add friend\n" +
						"<yellow>/friend remove <player> <gray>- <gray>remove friend\n" +
						"<yellow>/friend accept <player> <gray>- <gray>accept friend request\n" +
						"<yellow>/friend deny <player> <gray>- <gray>decline friend request\n" +
						"<yellow>/friend list <gray>- <gray>list all friends\n" +
						"<yellow>/friend requests <gray>- <gray>show open requests\n" +
						"<yellow>/msg <player> <message> <gray>- <gray>send a message to a friend\n" +
						"<yellow>/r <message> <gray>- <gray>reply to a message\n" +
						"<yellow>/friend jump <player> <gray>- <gray>jump to friend's server\n" +
						"<yellow>/friend toggleinvites <gray>- <gray>toggle invites\n" +
						"<yellow>/friend togglemsgs <gray>- <gray>toggle private messages\n" +
						"<yellow>/friend togglejump <gray>- <gray>toggle jump\n" +
						"<yellow>/friend togglenotifies <gray>- <gray>toggle join/leave messages\n" +
						"<yellow>/friend status <status> <gray>- <gray>set your status\n" +
						"<yellow>/friend favourite <player> <gray>- <gray>add/remove a favourite\n" +
						"<gray><strikethrough>-----------------</strikethrough><yellow>« FriendSystem »<gray><strikethrough>-----------------</strikethrough>");

		messages.put("friend.list.format.header", "<gray><strikethrough>-----------</strikethrough><yellow>« Friend list <gray>(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) <yellow>»<gray><strikethrough>-----------");
		messages.put("friend.list.format.nofriends", "<red>You have no friends :(");
		messages.put("friend.list.format.online.regular", "<gray>%PLAYERON% <gray>- <green>%SERVER% <gray>(since <yellow>%ONLINE_TIME%<gray>) <click:run_command:'/friend jump %PLAYERON%'><green><hover:show_text:'<blue>Jump to <gray>%PLAYERON%<blue>\'s server'>[JUMP]</hover></click>");
		messages.put("friend.list.format.online.favourite", "<gray>[<red>❤<gray>] %PLAYERON% <gray>- <green>%SERVER% <gray>(since <yellow>%ONLINE_TIME%<gray>)  <click:run_command:'/friend jump %PLAYERON%'><green><hover:show_text:'<blue>Jump to <gray>%PLAYERON%<blue>\'s server'>[JUMP]</hover></click>");
		messages.put("friend.list.format.offline.regular", "<gray>%PLAYEROFF% <gray>- <red>OFFLINE <gray>(since <yellow>%OFFLINE_SINCE%<gray>)");
		messages.put("friend.list.format.offline.favourite", "<gray>[<red>❤<gray>] %PLAYEROFF% <gray>- <red>OFFLINE <gray>(since <yellow>%OFFLINE_SINCE%<gray>)");
		messages.put("friend.list.format.pagination.pagenotfound", "<red>Page not found.");
		messages.put("friend.list.format.pagination.textbeforepage", "\n                                  ");
		messages.put("friend.list.format.pagination.previouspage", "<gold><hover:show_text:'<white>Show page %PREVIOUS_PAGE%'><click:run_command:'/friend list %PREVIOUS_PAGE%'>[%PREVIOUS_PAGE%]</hover>");
		messages.put("friend.list.format.pagination.currentpage", "<green>[%CURRENT_PAGE%]");
		messages.put("friend.list.format.pagination.nextpage", "<gold><hover:show_text:'<white>Show page %NEXT_PAGE%'><click:run_command:'/friend list %NEXT_PAGE%'>[%NEXT_PAGE%]</hover>");
		messages.put("friend.list.format.footer", "<gray><strikethrough>-----------</strikethrough><yellow>« Friend list <gray>(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) <yellow>»<gray><strikethrough>-----------");


		messages.put("friend.request.format.header", "<gray><strikethrough>-----------</strikethrough><yellow>« Friend requests <gray>(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) <yellow>»<gray><strikethrough>-----------");
		messages.put("friend.request.format.norequests", "<red>You have no requests");
		messages.put("friend.request.format.player", "<gray>%PLAYER% <click:run_command:'/friend accept %PLAYER%'><hover:show_text:'<green>Accept %PLAYER%\\'s request'><green>[ACCEPT]</hover></click> <click:run_command:'/friend deny %PLAYER%'><hover:show_text:'<red>Deny %PLAYER%\\'s request'><red>[DENY]</hover></click>");
		messages.put("friend.request.format.pagination.pagenotfound", "<red>Page not found.");
		messages.put("friend.request.format.pagination.textbeforepage", "\n                                  ");
		messages.put("friend.request.format.pagination.previouspage", "<gold><hover:show_text:'<white>Show page %PREVIOUS_PAGE%'><click:run_command:'/friend requests %PREVIOUS_PAGE%'>[%PREVIOUS_PAGE%]</hover>");
		messages.put("friend.request.format.pagination.currentpage", "<green>[%CURRENT_PAGE%]");
		messages.put("friend.request.format.pagination.nextpage", "<gold><hover:show_text:'<white>Show page %NEXT_PAGE%'><click:run_command:'/friend requests %NEXT_PAGE%'>[%NEXT_PAGE%]</hover>");
		messages.put("friend.request.format.footer", "<gray><strikethrough>-----------</strikethrough><yellow>« Friend requests <gray>(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) <yellow>»<gray><strikethrough>-----------");


		messages.put("friend.request.alreadyfriends", "<red>You are already friend with <gray>%PLAYER%.");
		messages.put("friend.request.alreadyrequested", "<red>You've already sent <gray>%PLAYER% <red>a friend request.");
		messages.put("friend.request.alreadyreceivedrequest", "<gray>%PLAYER% <red>has already sent you a friend request.\n<green><hover:show_text:'<white>Click to accept'><click:run_command:'/friend accept %PLAYER%'>[ACCEPT]</hover> <red><hover:show_text:'<white>Click to deny'><click:run_command:'/friend deny %PLAYER%'>[DENY]</hover>");
		messages.put("friend.request.toomanyfriends", "<red>You have already got too many friends. You are allowed to have <gray>%MAX_FRIENDS%");
		messages.put("friend.request.invitestoggled", "<gray>%PLAYER% <red>does not allow friend requests.");
		messages.put("friend.request.sent", "<blue>You've sent <gray>%PLAYER% <blue>a friend request!");
		messages.put("friend.request.received", "<blue>You received a friend request from <gray>%PLAYER%!\n<green><hover:show_text:'<white>Click to accept'><click:run_command:'/friend accept %PLAYER%'>[ACCEPT]</hover> <red><hover:show_text:'<white>Click to deny'><click:run_command:'/friend deny %PLAYER%'>[DENY]</hover>");
		messages.put("friend.toggleinvites.on", "<blue>Friend requests are now <green>allowed<blue>.");
		messages.put("friend.toggleinvites.off", "<blue>Friend requests are now <red>disabled<blue>.");
		messages.put("friend.togglemsgs.on", "<blue>Private messages are now <green>allowed<blue>.");
		messages.put("friend.togglemsgs.off", "<blue>Private messages are now <red>disabled<blue>.");
		messages.put("friend.togglejump.on", "<blue>Friend jumps are now <green>allowed<blue>.");
		messages.put("friend.togglejump.off", "<blue>Friend jumps are now <red>disabled<blue>.");
		messages.put("friend.togglenotifies.on", "<blue>Join/Leave messages are now <green>enabled<blue>.");
		messages.put("friend.togglenotifies.off", "<blue>Join/Leave messages are now <red>disabled<blue>.");
		messages.put("friend.add.syntax", "<red>Syntax: <gold>/friend add <player>");
		messages.put("friend.remove.syntax", "<red>Syntax: <gold>/friend remove <player>");
		messages.put("friend.accept.syntax", "<red>Syntax: <gold>/friend accept <player>");
		messages.put("friend.deny.syntax", "<red>Syntax: <gold>/friend deny <player>");
		messages.put("friend.jump.syntax", "<red>Syntax: <gold>/friend jump <player>");
		messages.put("friend.error.interact", "<red>You can't interact with yourself.");
		messages.put("friend.error.playernotfound", "<red>Player <gray>%PLAYER% <red>does not exist.");
		messages.put("friend.error.notonnetwork", "<gray>%PLAYER% <red>has never been on the network.");
		messages.put("friend.remove.notfriends", "<gray>%PLAYER% <red>is not your friend");
		messages.put("friend.remove.successful", "<gray>%PLAYER% <blue>is no longer your friend.");
		messages.put("friend.remove.successful2", "<gray>%PLAYER% <blue>removed you as a friend.");
		messages.put("friend.add.successful", "<gray>%PLAYER% <blue>is now your friend.");
		messages.put("friend.add.successful2", "<gray>%PLAYER% <blue>accepted your friend request.");
		messages.put("friend.add.norequest", "<red>You have no friend request from <gray>%PLAYER%");
		messages.put("friend.deny.successful", "<blue>You declined <gray>%PLAYER%'s <blue>friend request.");
		messages.put("friend.deny.successful2", "<gray>%PLAYER% <blue>declined your request.");
		messages.put("friend.deny.norequest", "<red>You have no friend request from <gray>%PLAYER%");
		messages.put("friend.jump.notfriends", "<gray>%PLAYER% <red>is not your friend");
		messages.put("friend.jump.notonline", "<gray>%PLAYER% <red>is not online.");
		messages.put("friend.jump.jumptoggled", "<gray>%PLAYER% <red>does not allow jumping.");
		messages.put("friend.status.toolong", "<red>Your status is too long. Maximum is 64 characters.");
		messages.put("friend.status.update", "<blue>Your status is now <gray>%STATUS%");
		messages.put("friend.favourite.notfriends", "<gray>%PLAYER% <red>is not your friend");
		messages.put("friend.favourite.added", "<blue>You marked <gray>%PLAYER% <blue>as a favourite");
		messages.put("friend.favourite.removed", "<gray>%PLAYER% <red>is now longer a favourite");
		messages.put("msg.error.syntax", "<red>Syntax: <gold>/msg <player> <message>");
		messages.put("msg.error.interact", "<red>You can't interact with yourself.");
		messages.put("msg.error.playernotfound", "<gray>%PLAYER% <red>does not exist.");
		messages.put("msg.error.notonline", "<gray>%PLAYER% <red>is not online.");
		messages.put("msg.error.notfriends", "<gray>%PLAYER% <red>is not your friend");
		messages.put("msg.error.msgstoggled", "<gray>%PLAYER% <red>does not allow private messages.");
		messages.put("msg.format.sender", "<gray>%PLAYER% <blue>-> <gray>%PLAYER2%<dark_gray>: <yellow>%MSG%");
		messages.put("msg.format.receiver", "<gray>%PLAYER% <blue>-> <gray>%PLAYER2%<dark_gray>: <yellow>%MSG%");
		messages.put("r.error.syntax", "<red>Syntax: <gold>/r <message>");
		messages.put("r.error.noreceiver", "<red>Who are you chatting with?");
		messages.put("r.error.notfriends", "<gray>%PLAYER% <red>is not your friend");
		messages.put("r.error.msgstoggled", "<red>Du kannst <gray>%PLAYER% <red>keine privaten Nachrichten schreiben.");
		messages.put("msg.r.sender", "<gray>%PLAYER% <blue>-> <gray>%PLAYER2%<dark_gray>: <yellow>%MSG%");
		messages.put("msg.r.receiver", "<gray>%PLAYER% <blue>-> <gray>%PLAYER2%<dark_gray>: <yellow>%MSG%");
		messages.put("notifies.join", "<gray>%PLAYER% <blue>is now <green>online<blue>.");
		messages.put("notifies.leave", "<gray>%PLAYER% <blue>is now <red>offline<blue>.");
		messages.put("join.friendcounter", "<blue>There are currently <hover:show_text:'<white>Click to list your online friends'><click:run_command:'/friend list'><gray>%COUNT%</hover> <blue>friends online");
		messages.put("join.requestcounter", "<blue>You have <hover:show_text:'<white>Click to view pending friend requests'><click:run_command:'/friend requests'><gray>%COUNT%</hover> <blue>pending friend requests");

		messages.put("friend.timeformat.years", "years");
		messages.put("friend.timeformat.year", "year");
		messages.put("friend.timeformat.days", "days");
		messages.put("friend.timeformat.day", "day");
		messages.put("friend.timeformat.hours", "hours");
		messages.put("friend.timeformat.hour", "hour");
		messages.put("friend.timeformat.minutes", "minutes");
		messages.put("friend.timeformat.minute", "mins");
		messages.put("friend.timeformat.seconds", "seconds");
		messages.put("friend.timeformat.second", "second");

	}
}
