package me.sk8ingduck.friendsystem.config;

import java.util.HashMap;

public class MessagesConfig extends Config {

	private final HashMap<String, String> messages;

	public MessagesConfig(String name, String path) {
		super(name, path);

		this.messages = new HashMap<>();
		/*
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
		messages.put("friend.list.format.offline", "&7%PLAYEROFF% &8- &cOFFLINE");
		messages.put("friend.list.format.footer", "&e---------- &9Your friends &e----------");
		messages.put("friend.request.format.header", "&e---------- &9Open requests &e----------");
		messages.put("friend.request.format.player", "&7%PLAYER%");
		messages.put("friend.request.format.footer", "&e---------- &9Open requests&e----------");
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
		*/
		messages.put("prefix", "&cFriends &8»&9 ");
		messages.put("friend.help", "&e---------- &9FreundeSystem &e----------\n" +
				"&e/friend add <Name> &8- &7Füge einen Freund hinzu\n" +
				"&e/friend remove <Name> &8- &7Entferne einen Freund\n" +
				"&e/friend accept <Name> &8- &7Nimmt eine Freundschaftsanfrage an\n" +
				"&e/friend deny <Name> &8- &7Lehnt eine Freundschaftsanfrage ab\n" +
				"&e/friend list &8- &7Liste alle Freunde\n" +
				"&e/friend requests &8- &7Zeigt offene Freundschaftsanfragen an\n" +
				"&e/msg <Name> <Nachricht> &8- &7Sendet einem Freund eine Nachricht\n" +
				"&e/r <Nachricht> &8- &7Antwortet auf eine Nachricht\n" +
				"&e/friend jump <Name> &8- &7Zum Server vom Freund verbinden\n" +
				"&e/friend toggleinvites &8- &7Anfragen an / aus\n" +
				"&e/friend togglemsgs &8- &7private Nachrichten an / aus\n" +
				"&e/friend togglejump &8- &7Nachspringen an / aus\n" +
				"&e/friend togglenotifies &8- &7Join / Leave Nachrichten an / aus\n" +
				"&e---------- &9FreundeSystem &e----------");

		messages.put("friend.list.format.header", "&e---------- &9Deine Freundesliste &e----------");
		messages.put("friend.list.format.online", "&7%PLAYERON% &8- &a%SERVER%");
		messages.put("friend.list.format.offline", "&7%PLAYEROFF% &8- &cOFFLINE");
		messages.put("friend.list.format.footer", "&e---------- &9Deine Freundesliste &e----------");
		messages.put("friend.request.format.header", "&e---------- &9Offene Anfragen &e----------");
		messages.put("friend.request.format.player", "&7%PLAYER%");
		messages.put("friend.request.format.footer", "&e---------- &9Offene Anfragen &e----------");
		messages.put("friend.toggleinvites.on", "&9Deine Freundschaftsanfragen sind nun &aeingeschaltet&9.");
		messages.put("friend.toggleinvites.off", "&9Deine Freundschaftsanfragen sind nun &causgeschaltet&9.");
		messages.put("friend.togglemsgs.on", "&9Deine privaten Nachrichten sind nun &aeingeschaltet&9.");
		messages.put("friend.togglemsgs.off", "&9Deine privaten Nachrichten sind nun &causgeschaltet&9.");
		messages.put("friend.togglejump.on", "&9Du hast das Nachrichten nun &aaktiviert&9.");
		messages.put("friend.togglejump.off", "&9Du hast das Nachspringen nun &cdeaktivert&9.");
		messages.put("friend.togglenotifies.on", "&9Du hast deine Join / Leave Nachrichten nun &aaktiviert&9.");
		messages.put("friend.togglenotifies.off", "&9Du hast deine Join / Leave Nachrichten nun &cdeaktivert&9.");
		messages.put("friend.add.syntax", "&cSyntax: &6/friend add <Name>");
		messages.put("friend.remove.syntax", "&cSyntax: &6/friend remove <Name>");
		messages.put("friend.accept.syntax", "&cSyntax: &6/friend accept <Name>");
		messages.put("friend.deny.syntax", "&cSyntax: &6/friend deny <Name>");
		messages.put("friend.jump.syntax", "&cSyntax: &6/friend jump <Name>");
		messages.put("friend.error.interact", "&cDu kannst nicht mit dir selber interagieren.");
		messages.put("friend.error.playernotfound", "&cDer Spieler &7%PLAYER% &cexistiert nicht.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% &cwar noch nie auf dem Netzwerk.");
		messages.put("friend.request.alreadyfriends", "&cDu bist mit &7%PLAYER% &cbereits befreundet.");
		messages.put("friend.request.alreadyrequested", "&cDu hast &7%PLAYER% &cbereits eine Freundschaftsanfrage geschickt.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &chat dir bereits eine Freundschaftsanfrage geschickt.\n" +
				"&cBenutze &6/friend accept &7%PLAYER% &cum diese anzunehmen.");
		messages.put("friend.request.invitestoggled", "&cDu kannst &7%PLAYER% &ckeine Freundschaftsanfrage schicken.");
		messages.put("friend.request.sent", "&9Du hast eine Freundschaftsanfrage an &7%PLAYER% &9geschickt!");
		messages.put("friend.request.received", "&9Du hast eine Freundschaftsanfrage von &7%PLAYER% &9erhalten!\n" +
				"&9Benutze &6/friend accept &7%PLAYER% &9um diese anzunehmen.");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &cist nicht mit dir befreundet.");
		messages.put("friend.remove.successful", "&9Du hast die Freundschaft zu &7%PLAYER% &9aufgelöst");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9hat die Freundschaft beendet.");
		messages.put("friend.add.successful", "&9Du bist nun mit &7%PLAYER% &9befreundet.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9hat deine Freundschaftsanfrage angenommen.");
		messages.put("friend.add.norequest", "&cDu hast keine Freundschaftsanfrage von &7%PLAYER% &cerhalten.");
		messages.put("friend.deny.successful", "&9Du hast die Freundschaftsanfrage von &7%PLAYER% &9abgelehnt.");
		messages.put("friend.deny.successful2", "&7%PLAYER% &9hat deine Freundschaftsanfrage abgelehnt.");
		messages.put("friend.deny.norequest", "&cDu hast keine Freundschaftsanfrage von &7%PLAYER% &cerhalten.");
		messages.put("friend.jump.notfriends", "&cDu bist mit &7%PLAYER% &cnicht befreundet.");
		messages.put("friend.jump.notonline", "&7%PLAYER% &cist nicht online.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &cerlaubt kein nachspringen.");
		messages.put("msg.error.syntax", "&cSyntax: &6/msg <Spieler> <Nachricht>");
		messages.put("msg.error.interact", "&cDu kannst nicht mit dir selbst interagieren.");
		messages.put("msg.error.playernotfound", "&cDer Spieler &7%PLAYER% &cexistiert nicht.");
		messages.put("msg.error.notonline", "&7%PLAYER% &cist nicht online.");
		messages.put("msg.error.notfriends", "&cDu bist mit &7%PLAYER% &cnicht befreundet.");
		messages.put("msg.error.msgstoggled", "&cDu kannst &7%PLAYER% &ckeine privaten Nachrichten senden.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&cVerwendung: &6/r <Nachricht>");
		messages.put("r.error.noreceiver", "&cDu schreibst momentan mit niemandem.");
		messages.put("r.error.notfriends", "&cDu bist mit &7%PLAYER% &cnicht befreundet.");
		messages.put("r.error.msgstoggled", "&cDu kannst &7%PLAYER% &ckeine privaten Nachrichten schreiben.");
		messages.put("msg.r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("notifies.join", "&7%PLAYER% &9ist nun &aonline&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9ist nun &coffline&9.");
		messages.put("join.friendcounter", "&9Momentan sind &7%COUNT% &9Freunde(n) von dir online");
		messages.put("join.requestcounter", "&9Du hast noch &7%COUNT% &9offene Freundschaftsanfrage(n)");

		messages.forEach((messagePath, message) -> messages.put(messagePath, (String) getPathOrSet(messagePath, message)));
	}


	public String get(String path) {
		return get(path, true);
	}

	public String get(String path, boolean prefix) {
		return ((prefix ? messages.get("prefix") : "") + messages.get(path));
	}

}
