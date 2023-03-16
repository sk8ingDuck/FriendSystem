package me.sk8ingduck.friendsystem.config;

public class MessagesEnglishConfig extends MessagesConfig {

	public MessagesEnglishConfig(String name, String path) {
		super(name, path);
	}

	public void loadMessages() {
		messages.put("prefix", "&cFriends &8»&9 ");
		messages.put("friend.help", "&e---------- &9FriendSystem &e----------\n" +
				"&e/friend add <player> &8- &7add friend\n" +
				"&e/friend remove <player> &8- &7remove friend\n" +
				"&e/friend accept <player> &8- &7accept friend request\n" +
				"&e/friend deny <player> &8- &7decline friend request\n" +
				"&e/friend list &8- &7list all friends\n" +
				"&e/friend requests &8- &7show open requests\n" +
				"&e/msg <player> <message> &8- &7send a message to a friend\n" +
				"&e/r <message> &8- &7reply to a message\n" +
				"&e/friend jump <player> &8- &7jump to friend's server\n" +
				"&e/friend toggleinvites &8- &7toggle invites\n" +
				"&e/friend togglemsgs &8- &7toggle private messages\n" +
				"&e/friend togglejump &8- &7toggle jump\n" +
				"&e/friend togglenotifies &8- &7toggle join/leave messages\n" +
				"&e---------- &9FriendSystem &e----------");

		messages.put("friend.list.format.header", "&e---------- &9Your friends &e----------");
		messages.put("friend.list.format.online", "&7%PLAYERON% &8- &a%SERVER%");
		messages.put("friend.list.format.offline", "&7%PLAYEROFF% &8- &cOFFLINE &7(since &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.footer", "&e---------- &9Your friends &e----------");
		messages.put("friend.request.format.header", "&e---------- &9Open requests &e----------");
		messages.put("friend.request.format.player", "&7%PLAYER%");
		messages.put("friend.request.format.footer", "&e---------- &9Open requests &e----------");
		messages.put("friend.toggleinvites.on", "&9Friend requests are now &aallowed&9.");
		messages.put("friend.toggleinvites.off", "&Friend requests are now &cdisabled&9.");
		messages.put("friend.togglemsgs.on", "&9Private messages are now &aallowed&9.");
		messages.put("friend.togglemsgs.off", "&9Pirvate messages are now &cdisabled&9.");
		messages.put("friend.togglejump.on", "&9Friend jumps are now &aallowed&9.");
		messages.put("friend.togglejump.off", "&9Friend jumps are now &cdisabled&9.");
		messages.put("friend.togglenotifies.on", "&9Join/Leave messages are now &aenabled&9.");
		messages.put("friend.togglenotifies.off", "&9Join/Leave messages are now &cdisabled&9.");
		messages.put("friend.add.syntax", "&cSyntax: &6/friend add <player>");
		messages.put("friend.remove.syntax", "&cSyntax: &6/friend remove <player>");
		messages.put("friend.accept.syntax", "&cSyntax: &6/friend accept <player>");
		messages.put("friend.deny.syntax", "&cSyntax: &6/friend deny <player>");
		messages.put("friend.jump.syntax", "&cSyntax: &6/friend jump <player>");
		messages.put("friend.error.interact", "&cYou can't interact with yourself.");
		messages.put("friend.error.playernotfound", "&cPlayer 7%PLAYER% &cdoes not exist.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% &chas never been on the network.");
		messages.put("friend.request.alreadyfriends", "&cYou are already friend with &7%PLAYER%.");
		messages.put("friend.request.alreadyrequested", "&cYou've already sent &7%PLAYER% &ca friend request.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &chas already sent you a friend request.\n" +
				"&cType &6/friend accept &7%PLAYER% &cto accept.");
		messages.put("friend.request.invitestoggled", "&7%PLAYER% &cdoes not allow friend requests.");
		messages.put("friend.request.sent", "&9You've sent &7%PLAYER% &9a friend request!");
		messages.put("friend.request.received", "&9You received a friend request from &7%PLAYER%\n" +
				"&9Type &6/friend accept &7%PLAYER% &9to accept.");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &cis not your friend");
		messages.put("friend.remove.successful", "&7%PLAYER% &9is no longer your friend.");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9removed you as a friend.");
		messages.put("friend.add.successful", "&7%PLAYER% &9is now your friend.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9accepted your friend request.");
		messages.put("friend.add.norequest", "&cYou have no friend request from &7%PLAYER%");
		messages.put("friend.deny.successful", "&9You declined &7%PLAYER%'s &9friend request.");
		messages.put("friend.deny.successful2", "&7%PLAYER% &9declined your request.");
		messages.put("friend.deny.norequest", "&cYou have no friend request from &7%PLAYER%");
		messages.put("friend.jump.notfriends", "&7%PLAYER% &cis not your friend");
		messages.put("friend.jump.notonline", "&7%PLAYER% &cis not online.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &cdoes not allow jumping.");
		messages.put("msg.error.syntax", "&cSyntax: &6/msg <player> <message>");
		messages.put("msg.error.interact", "&cYou can't interact with yourself.");
		messages.put("msg.error.playernotfound", "&7%PLAYER% &cdoes not exist.");
		messages.put("msg.error.notonline", "&7%PLAYER% &cis not online.");
		messages.put("msg.error.notfriends", "&7%PLAYER% &cis not your friend");
		messages.put("msg.error.msgstoggled", "&7%PLAYER% &cdoes not allow private messages.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&cSyntax: &6/r <message>");
		messages.put("r.error.noreceiver", "&cWho are you chatting with?");
		messages.put("r.error.notfriends", "&7%PLAYER% &cis not your friend");
		messages.put("r.error.msgstoggled", "&cDu kannst &7%PLAYER% &ckeine privaten Nachrichten schreiben.");
		messages.put("msg.r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("notifies.join", "&7%PLAYER% &9is now &aonline&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9is now &coffline&9.");
		messages.put("join.friendcounter", "&9There are currently &7%COUNT% &9friends online");
		messages.put("join.requestcounter", "&9You have &7%COUNT% &9pending friend requests");
	}
}
