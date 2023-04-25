package me.sk8ingduck.friendsystem.config.languages;

import me.sk8ingduck.friendsystem.config.MessagesConfig;

public class MessagesItalianConfig extends MessagesConfig {

	public MessagesItalianConfig(String name, String path) {
		super(name, path);
	}

	public void loadMessages() {
		messages.put("prefix", "&cAmici &8»&9 ");
		messages.put("friend.help",
				"&9&m------------------&r&e« SistemaAmici »&9&m------------------\n" +
						"&e/friend add <Nome> &8- &7Aggiungi un amico\n" +
						"&e/friend remove <Nome> &8- &7Rimuovi un amico\n" +
						"&e/friend accept <Nome> &8- &7Accetta una richiesta di amicizia\n" +
						"&e/friend deny <Nome> &8- &7Rifiuta una richiesta di amicizia\n" +
						"&e/friend list &8- &7Elenca tutti gli amici\n" +
						"&e/friend requests &8- &7Mostra le richieste di amicizia in sospeso\n" +
						"&e/msg <Nome> <Messaggio> &8- &7Invia un messaggio privato a un amico\n" +
						"&e/r <Messaggio> &8- &7Rispondi a un messaggio\n" +
						"&e/friend jump <Nome> &8- &7Connetti al server dell'amico\n" +
						"&e/friend toggleinvites &8- &7Richieste on / off\n" +
						"&e/friend togglemsgs &8- &7Messaggi privati on / off\n" +
						"&e/friend togglejump &8- &7Salto on / off\n" +
						"&e/friend togglenotifies &8- &7Notifiche di entrata / uscita on / off\n" +
						"&e/friend status <status> &8- &7Imposta il tuo stato\n" +
						"&e/friend favourite <Nome> &8- &7Aggiungi/rimuovi dai preferiti\n" +
						"&9&m------------------&r&e« SistemaAmici »&9&m------------------");

		messages.put("friend.list.format.header", "&9&m--------------&r&e« La tua lista di amici »&9&m--------------");
		messages.put("friend.list.format.online.regular", "&7%PLAYERON% &8- &a%SERVER% &7(da &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.online.favourite", "&7[&c❤&7] %PLAYERON% &8- &a%SERVER% &7(da &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.offline.regular", "&7%PLAYEROFF% &8- &cOFFLINE &7(da &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.offline.favourite", "&7[&c❤&7] %PLAYEROFF% &8- &cOFFLINE &7(da &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.footer", "&9&m--------------&r&e« La tua lista di amici »&9&m--------------");
		messages.put("friend.request.format.header", "&9&m--------------&r&e« Richieste pendenti »&9&m--------------");
		messages.put("friend.request.format.player", "&7- %PLAYER% {accept} {deny}");
		messages.put("friend.request.format.footer", "&9&m--------------&r&e« Richieste pendenti »&9&m--------------");

		messages.put("friend.request.alreadyfriends", "&cSei già amico di &7%PLAYER%.");
		messages.put("friend.request.alreadyrequested", "&cHai già inviato a &7%PLAYER% &cuna richiesta di amicizia.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &cti ha già inviato una richiesta di amicizia.\n" +
				"{accetta} {rifiuta}");
		messages.put("friend.request.toomanyfriends", "&cHai già troppi amici. Puoi avere fino a &7%MAX_FRIENDS%");
		messages.put("friend.request.invitestoggled", "&7%PLAYER% &cnon accetta richieste di amicizia.");
		messages.put("friend.request.sent", "&9Hai inviato a &7%PLAYER% &9una richiesta di amicizia!");
		messages.put("friend.request.received","&9Hai ricevuto una richiesta di amicizia da &7%PLAYER%!\n" +
				"{accetta} {rifiuta}");
		messages.put("friend.toggleinvites.on", "&9Le richieste di amicizia sono ora &apermesse&9.");
		messages.put("friend.toggleinvites.off", "&9Le richieste di amicizia sono ora &cdisabilitate&9.");
		messages.put("friend.togglemsgs.on", "&9I messaggi privati sono ora &apermessi&9.");
		messages.put("friend.togglemsgs.off", "&9I messaggi privati sono ora &cdisabilitati&9.");
		messages.put("friend.togglejump.on", "&9I salti tra amici sono ora &apermessi&9.");
		messages.put("friend.togglejump.off", "&9I salti tra amici sono ora &cdisabilitati&9.");
		messages.put("friend.togglenotifies.on", "&9I messaggi di Entrata/Uscita sono ora &aabilitati&9.");
		messages.put("friend.togglenotifies.off", "&9I messaggi di Entrata/Uscita sono ora &cdisabilitati&9.");
		messages.put("friend.add.syntax", "&cSintassi: &6/friend add <player>");
		messages.put("friend.remove.syntax", "&cSintassi: &6/friend remove <player>");
		messages.put("friend.accept.syntax", "&cSintassi: &6/friend accept <player>");
		messages.put("friend.deny.syntax", "&cSintassi: &6/friend deny <player>");
		messages.put("friend.jump.syntax", "&cSintassi: &6/friend jump <player>");
		messages.put("friend.error.interact", "&cNon puoi interagire con te stesso.");
		messages.put("friend.error.playernotfound", "&cIl giocatore 7%PLAYER% &cnon esiste.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% &cnon è mai stato sulla rete.");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &cnon è tuo amico");
		messages.put("friend.remove.successful", "&7%PLAYER% &9non è più tuo amico.");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9ti ha rimosso come amico.");
		messages.put("friend.add.successful", "&7%PLAYER% &9è oratuo amico.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9ha accettato la tua richiesta di amicizia.");
		messages.put("friend.add.norequest", "&cNon hai una richiesta di amicizia da &7%PLAYER%");
		messages.put("friend.deny.successful", "&9Hai rifiutato la richiesta di amicizia di &7%PLAYER%&9.");
		messages.put("friend.deny.successful2", "&7%PLAYER% &9ha rifiutato la tua richiesta.");
		messages.put("friend.deny.norequest", "&cNon hai una richiesta di amicizia da &7%PLAYER%");
		messages.put("friend.jump.notfriends", "&7%PLAYER% &cnon è tuo amico");
		messages.put("friend.jump.notonline", "&7%PLAYER% &cnon è in linea.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &cnon permette di saltare.");
		messages.put("friend.status.toolong", "&cIl tuo stato è troppo lungo. Il massimo è di 64 caratteri.");
		messages.put("friend.status.update", "&9Il tuo stato è ora &7%STATUS%");
		messages.put("friend.favourite.notfriends", "&7%PLAYER% &cnon è tuo amico");
		messages.put("friend.favourite.added", "&9Hai segnato &7%PLAYER% &9come preferito");
		messages.put("friend.favourite.removed", "&7%PLAYER% &cnon è più tra i preferiti");
		messages.put("msg.error.syntax", "&cSintassi: &6/msg <giocatore> <messaggio>");
		messages.put("msg.error.interact", "&cNon puoi interagire con te stesso.");
		messages.put("msg.error.playernotfound", "&7%PLAYER% &cnon esiste.");
		messages.put("msg.error.notonline", "&7%PLAYER% &cnon è in linea.");
		messages.put("msg.error.notfriends", "&7%PLAYER% &cnon è tuo amico");
		messages.put("msg.error.msgstoggled", "&7%PLAYER% &cnon permette messaggi privati.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&cSintassi: &6/r <messaggio>");
		messages.put("r.error.noreceiver", "&cCon chi stai chattando?");
		messages.put("r.error.notfriends", "&7%PLAYER% &cnon è tuo amico");
		messages.put("r.error.msgstoggled", "&cNon puoi scrivere messaggi privati a &7%PLAYER% &c.");
		messages.put("msg.r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("notifies.join", "&7%PLAYER% &9è ora &aonline&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9è ora &coffline&9.");
		messages.put("join.friendcounter", "&9Ci sono attualmente {onlineFriends} &9amici online");
		messages.put("join.requestcounter", "&9Hai {openRequests} &9richieste di amicizia in sospeso");

		messages.put("friend.timeformat.years", "anni");
		messages.put("friend.timeformat.year", "anno");
		messages.put("friend.timeformat.days", "giorni");
		messages.put("friend.timeformat.day", "giorno");
		messages.put("friend.timeformat.hours", "ore");
		messages.put("friend.timeformat.hour", "ora");
		messages.put("friend.timeformat.minutes", "minuti");
		messages.put("friend.timeformat.minute", "min");
		messages.put("friend.timeformat.seconds", "secondi");
		messages.put("friend.timeformat.second", "secondo");

	}

	@Override
	public void loadTextComponents() {
		if (fileConfiguration.getSection("textcomponents").getKeys().isEmpty()) {
			fileConfiguration.set("textcomponents.accept", "&a[ACCETTA] {hovertext: &aAccetta la richiesta di amicizia da &6%PLAYER%, command: /friend accept %PLAYER%}");
			fileConfiguration.set("textcomponents.deny", "&c[RIFIUTA] {hovertext: &cRifiuta la richiesta di amicizia da &6%PLAYER%, command: /friend deny %PLAYER%}");
			fileConfiguration.set("textcomponents.onlineFriends", "&7%COUNT% {hovertext: &9Mostra amici online, command: /friend list}");
			fileConfiguration.set("textcomponents.openRequests", "&7%COUNT% {hovertext: &9Mostra richieste aperte, command: /friend requests}");
			fileConfiguration.set("textcomponents.jump", "&a[SALTA] {hovertext: &9Salta al server di &7%PLAYERON% &9, command: /friend jump %PLAYERON%}");
			super.save();
		}

		fileConfiguration.getSection("textcomponents")
				.getKeys()
				.forEach(key -> messages.put("textcomponents." + key, fileConfiguration.getString("textcomponents." + key)));
	}

}
