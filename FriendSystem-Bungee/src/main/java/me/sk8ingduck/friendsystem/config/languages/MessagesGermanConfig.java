package me.sk8ingduck.friendsystem.config.languages;

import me.sk8ingduck.friendsystem.config.MessagesConfig;

public class MessagesGermanConfig extends MessagesConfig {

	public MessagesGermanConfig(String name, String path) {
		super(name, path);
	}

	public void loadMessages() {
		messages.put("prefix", "&cFriends &8»&9 ");
		messages.put("friend.help",
				"&9&m------------------&r&e« FreundeSystem »&9&m------------------\n" +
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
				"&e/friend status <status> &8- &7Setze deinen Status\n" +
				"&e/friend favourite <Name> &8- &7Favouriten hinzufügen/entfernen\n" +
				"&9&m------------------&r&e« FreundeSystem »&9&m------------------");

		messages.put("friend.list.format.header", "&9&m--------------&r&e« Deine Freundesliste »&9&m--------------");
		messages.put("friend.list.format.online.regular", "&7%PLAYERON% &8- &a%SERVER% &7(seit &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.online.favourite", "&7[&c❤&7] %PLAYERON% &8- &a%SERVER% &7(seit &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.offline.regular", "&7%PLAYEROFF% &8- &cOFFLINE &7(seit &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.offline.favourite", "&7[&c❤&7] %PLAYEROFF% &8- &cOFFLINE &7(seit &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.footer", "&9&m--------------&r&e« Deine Freundesliste »&9&m--------------");
		messages.put("friend.request.format.header", "&9&m--------------&r&e« Offene Anfragen »&9&m--------------");
		messages.put("friend.request.format.player", "&7- %PLAYER% {accept} {deny}");
		messages.put("friend.request.format.footer", "&9&m--------------&r&e« Offene Anfragen »&9&m--------------");
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
				"{accept} {deny}");
		messages.put("friend.request.toomanyfriends", "&cDu hast bereits zu viele Freunde. Du darfst maximal &7%MAX_FRIENDS% &cFreunde haben.");
		messages.put("friend.request.invitestoggled", "&cDu kannst &7%PLAYER% &ckeine Freundschaftsanfrage schicken.");
		messages.put("friend.request.sent", "&9Du hast eine Freundschaftsanfrage an &7%PLAYER% &9geschickt!");
		messages.put("friend.request.received","&9Du hast eine Freundschaftsanfrage von &7%PLAYER% &9erhalten!\n" +
				"{accept} {deny}");
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
		messages.put("friend.status.toolong", "&cDein Status ist zu lang! Maximal 64 Zeichen");
		messages.put("friend.status.update", "&9Dein Status ist nun: &7%STATUS%");
		messages.put("friend.favourite.notfriends", "&cDu bist mit &7%PLAYER% &cnicht befreundet.");
		messages.put("friend.favourite.added", "&9Du hast &7%PLAYER% &9als Favorit markiert.");
		messages.put("friend.favourite.removed", "&7%PLAYER% &9ist kein Favourit mehr.");
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
		messages.put("join.friendcounter", "&9Momentan sind {onlineFriends} &9Freunde(n) von dir online");
		messages.put("join.requestcounter", "&9Du hast noch {openRequests} &9offene Freundschaftsanfrage(n)");

		messages.put("friend.timeformat.years", "Jahre");
		messages.put("friend.timeformat.year", "Jahr");
		messages.put("friend.timeformat.days", "Tage");
		messages.put("friend.timeformat.day", "Tag");
		messages.put("friend.timeformat.hours", "Stunden");
		messages.put("friend.timeformat.hour", "Stunde");
		messages.put("friend.timeformat.minutes", "Minuten");
		messages.put("friend.timeformat.minute", "Minute");
		messages.put("friend.timeformat.seconds", "Sekunden");
		messages.put("friend.timeformat.second", "Sekunde");

	}

	@Override
	public void loadTextComponents() {
		if (fileConfiguration.getSection("textcomponents").getKeys().isEmpty()) {
			fileConfiguration.set("textcomponents.accept", "&a[ANNEHMEN] {hovertext: &aFreundschaftsanfrage von &6%PLAYER% &aannehmen, command: /friend accept %PLAYER%}");
			fileConfiguration.set("textcomponents.deny", "&c[ABLEHNEN] {hovertext: &cFreundschaftsanfrage von &6%PLAYER% &cablehnen, command: /friend deny %PLAYER%}");
			fileConfiguration.set("textcomponents.openRequests", "&7%COUNT% {hovertext: &9Freundschaftsanfragen anzeigen, command: /friend requests}");
			fileConfiguration.set("textcomponents.onlineFriends", "&7%COUNT% {hovertext: &9Freundschaftsliste anzeigen, command: /friend list}");
			fileConfiguration.set("textcomponents.jump", "&a[JUMP] {hovertext: &9Zu &7%PLAYERON% &9Server springen, command: /friend jump %PLAYERON%}");
			super.save();
		}

		fileConfiguration.getSection("textcomponents")
				.getKeys()
				.forEach(key -> messages.put("textcomponents." + key, fileConfiguration.getString("textcomponents." + key)));
	}
}
