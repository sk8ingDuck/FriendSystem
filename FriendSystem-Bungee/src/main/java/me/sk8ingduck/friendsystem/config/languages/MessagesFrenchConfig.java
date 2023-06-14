package me.sk8ingduck.friendsystem.config.languages;

import me.sk8ingduck.friendsystem.config.MessagesConfig;

public class MessagesFrenchConfig extends MessagesConfig {

	public MessagesFrenchConfig(String name, String path) {
		super(name, path);
	}

	public void loadMessages() {
		messages.put("prefix", "&cAmis &8»&9 ");

		messages.put("friend.help", "&9&m-----------------&r&e« FriendSystem »&9&m-----------------\n" +
				"&e/friend add <pseudo> &8- &7Ajouter un joueur en ami\n" +
				"&e/friend remove <pseudo> &8- &7Supprimer un ami\n" +
				"&e/friend accept <pseudo> &8- &7Accepter une demande d'ami\n" +
				"&e/friend deny <pseudo> &8- &7Refuser une demande d'ami\n" +
				"&e/friend list &8- &7Afficher la liste de tout vos amis\n" +
				"&e/friend requests &8- &7Voir les demandes d'ami reçus\n" +
				"&e/msg <pseudo> <message> &8- &7Envoyer un message privé à un ami\n" +
				"&e/r <message> &8- &7répondre à un message privé\n" +
				"&e/friend jump <player> &8- &7Aller sur le même serveur que votre ami\n" +
				"&e/friend toggleinvites &8- &7Accepter/Refuser les demandes d'amis\n" +
				"&e/friend togglemsgs &8- &7Accepter/Refuser les messages privés\n" +
				"&e/friend togglejump &8- &7Accepter/Refuser le TP entre serveur\n" +
				"&e/friend togglenotifies &8- &7Activer/Désactivé les notifications de connection/déconnection \n" +
				"&e/friend status <statut> &8- &7Modifier votre statut\n" +
				"&e/friend favourite <pseudo> &8- &7Ajouter/Retirer de vos favoris\n" +
				"&9&m-----------------&r&e« FriendSystem »&9&m-----------------");

		messages.put("friend.list.format.header", "&9&m-----------&r&e« Vos Amis &7(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) »&9&m-----------");
		messages.put("friend.list.format.online.regular", "&7%PLAYERON% &8- &a%SERVER% &7(Depuis &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.online.favourite", "&7[&c❤&7] %PLAYERON% &8- &a%SERVER% &7(Depuis &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.offline.regular", "&7%PLAYEROFF% &8- &cOFFLINE &7(Depuis &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.offline.favourite", "&7[&c❤&7] %PLAYEROFF% &8- &cOFFLINE &7(Depuis &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.nofriends", "&cTu n'as pas d'amis :(");
		messages.put("friend.list.format.pagination.pagenotfound", "&4Page non trouvée.");
		messages.put("friend.list.format.pagination.textbeforepage", "\n                              ");
		messages.put("friend.list.format.pagination.previouspage", "{friendListPreviousPage}");
		messages.put("friend.list.format.pagination.currentpage", " &a%CURRENT_PAGE% ");
		messages.put("friend.list.format.pagination.nextpage", "{friendListNextPage}");
		messages.put("friend.list.format.footer", "&9&m-----------&r&e« Vos Amis &7(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) »&9&m-----------");

		messages.put("friend.request.format.header", "&9&m------------&r&e« Demandes d''ami reçus &7(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) &e»&9&m------------");
		messages.put("friend.request.format.player", "&7- %PLAYER% {accept} {deny} &7(Expire dans: &e%EXPIRES_IN%&7)");
		messages.put("friend.request.format.norequests", "&cVous n'avez aucune demande");
		messages.put("friend.request.format.pagination.pagenotfound", "&4Page non trouvée.");
		messages.put("friend.request.format.pagination.textbeforepage", "\n                              ");
		messages.put("friend.request.format.pagination.previouspage", "{friendRequestsPreviousPage}");
		messages.put("friend.request.format.pagination.currentpage", " &a%CURRENT_PAGE% ");
		messages.put("friend.request.format.pagination.nextpage", "{friendRequestsNextPage}");
		messages.put("friend.request.format.footer", "&9&m------------&r&e« Demandes d''ami reçus &7(Page: %CURRENT_PAGE%/%TOTAL_PAGES%) &e»&9&m------------");

		messages.put("friend.request.alreadyfriends", "&cVousêtes déja ami avec &7%PLAYER%.");
		messages.put("friend.request.alreadyrequested", "&cVous avez déjà envoyé une demande d''ami à &7%PLAYER% &c.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &cvous a déjà envoyé une demande d'ami.\n" +
				"&c{accept} {decline}");
		messages.put("friend.request.toomanyfriends", "&cVous avez déjà trop d'amis. Vous êtes autorisé à avoir &7%MAX_FRIENDS%");
		messages.put("friend.request.invitestoggled", "&7%PLAYER% &cn''accepte pas les demandes d''amis.");
		messages.put("friend.request.sent", "&9Vous avez envoyé une demande d''ami à &7%PLAYER% &9!");
		messages.put("friend.request.received","&9Vous avez reçu une demande d'ami de &7%PLAYER%!\n" +
				"{accept} {deny}");
		messages.put("friend.toggleinvites.on", "&9Demandes d''ami &aactivé&9.");
		messages.put("friend.toggleinvites.off", "&9Demandes d''ami &cdésactivé&9.");
		messages.put("friend.togglemsgs.on", "&9Messages privé &aactivé&9.");
		messages.put("friend.togglemsgs.off", "&9Messages privé &cdésactivé&9.");
		messages.put("friend.togglejump.on", "&9Téléportation entre serveur &aactivé&9.");
		messages.put("friend.togglejump.off", "&9Téléportation entre serveur &cdésactivé&9.");
		messages.put("friend.togglenotifies.on", "&9Messages de connection/déconnection &aactivé&9.");
		messages.put("friend.togglenotifies.off", "&9Messages de connection/déconnection &cdésactivé&9.");
		messages.put("friend.add.syntax", "&cSyntaxe: &6/friend add <pseudo>");
		messages.put("friend.add.successful", "&7%PLAYER% &9est maintenant votre ami.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9a accepté votre demande d''ami.");
		messages.put("friend.add.norequest", "&cVous avez reçu une demande d''ami de &7%PLAYER%");
		messages.put("friend.remove.syntax", "&cSyntaxe: &6/friend remove <pseudo>");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &cn''est pas votre ami.");
		messages.put("friend.remove.successful", "&7%PLAYER% &9n''est plus votre ami.");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9vous a retiré de sa liste d''amis.");
		messages.put("friend.accept.syntax", "&cSyntaxe: &6/friend accept <pseudo>");
		messages.put("friend.deny.syntax", "&cSyntaxe: &6/friend deny <pseudo>");
		messages.put("friend.deny.successful", "&9Vous avez refusé la demande d''ami de &7%PLAYER%");
		messages.put("friend.deny.successful2", "&7%PLAYER% &9a refusé votre demande d''ami.");
		messages.put("friend.deny.norequest", "&cVous avez atteint votre limite d'amis maximum. Votre limite d'amis est de &7%MAX_FRIEND%");
		messages.put("friend.jump.syntax", "&cSyntaxe: &6/friend jump <pseudo>");
		messages.put("friend.jump.notfriends", "&7%PLAYER% &cn''est pas votre ami.");
		messages.put("friend.jump.notonline", "&7%PLAYER% &cn''est pas connecté.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &cn''accepte pas les TP entre serveur.");
		messages.put("friend.error.interact", "&cVous ne pouvez pas interagir avec vous-même.");
		messages.put("friend.error.playernotfound", "&c7%PLAYER% &cn''existe pas.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% &cne s''est jamais connecté au serveur.");
		messages.put("friend.status.toolong", "&cVotre statut est trop long. Le maximum est de 64 caractères.");
		messages.put("friend.status.update", "&9Votre statut est maintenant &7%STATUS%");
		messages.put("friend.jump.favourite", "&cSyntaxe: &6/friend favourite <pseudo>");
		messages.put("friend.favourite.notfriends", "&7%PLAYER% &cn''est pas votre ami.");
		messages.put("friend.favourite.added", "&9Vous avez ajouté &7%PLAYER% &9a votre liste des favoris.");
		messages.put("friend.favourite.removed", "&7%PLAYER% &c9ne fait plus parti de votre liste de favoris.");
		messages.put("msg.error.syntax", "&cSyntaxe: &6/msg <pseudo> <message>");
		messages.put("msg.error.interact", "&cVous ne pouvez pas intéragir avec vous-même.");
		messages.put("msg.error.playernotfound", "&7%PLAYER% &cn''existe pas.");
		messages.put("msg.error.notonline", "&7%PLAYER% &cn''est pas connecté.");
		messages.put("msg.error.notfriends", "&7%PLAYER% &cn''est pas votre ami.");
		messages.put("msg.error.msgstoggled", "&7%PLAYER% &cn''accepte pas les messages privé.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&cSyntaxe: &6/r <message>");
		messages.put("r.error.noreceiver", "&cAvec qui êtes vous en train de parler ?");
		messages.put("r.error.notfriends", "&7%PLAYER% &cn''est pas votre ami.");
		messages.put("r.error.msgstoggled", "&c&7%PLAYER% &cn''accepte pas les messages privé.");
		messages.put("notifies.join", "&7%PLAYER% &9s''est &aconnecté&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9s''est &cdéconnecté&9.");
		messages.put("join.friendcounter", "&9Vous avez actuellement {onlineFriends} &9amis de connectés");
		messages.put("join.requestcounter", "&9Vous avez {openRequests} &9demandes d''amis en attente");

		messages.put("friend.timeformat.years", "ans");
		messages.put("friend.timeformat.year", "an");
		messages.put("friend.timeformat.days", "jours");
		messages.put("friend.timeformat.day", "jour");
		messages.put("friend.timeformat.hours", "heures");
		messages.put("friend.timeformat.hour", "heure");
		messages.put("friend.timeformat.minutes", "minutes");
		messages.put("friend.timeformat.minute", "minute");
		messages.put("friend.timeformat.seconds", "secondes");
		messages.put("friend.timeformat.second", "seconde");
	}

	@Override
	public void loadTextComponents() {
		if (fileConfiguration.getSection("textcomponents").getKeys().isEmpty()) {
			fileConfiguration.set("textcomponents.accept", "&a[✔] {hovertext: &aAccepter la demande d'amis de &6%PLAYER%, command: /friend accept %PLAYER%}");
			fileConfiguration.set("textcomponents.deny", "&c[✕] {hovertext: &cRefuser la demande d'amis de &6%PLAYER%, command: /friend deny %PLAYER%}");
			fileConfiguration.set("textcomponents.openRequests", "&7%COUNT% {hovertext: &9Afficher les demandes ouvertes, command: /friend requests}");
			fileConfiguration.set("textcomponents.onlineFriends", "&7%COUNT% {hovertext: &9Afficher les asmis connecté, command: /friend list}");
			fileConfiguration.set("textcomponents.jump", "&a[JUMP] {hovertext: &9TP entre serveur de &7%PLAYERON%, command: /friend jump %PLAYERON%}");

			fileConfiguration.set("textcomponents.friendRequestsPreviousPage", "&6« {hovertext: &9Page précédente, command: /friend requests %PREVIOUS_PAGE%}");
			fileConfiguration.set("textcomponents.friendRequestsNextPage", "&6» {hovertext: &9Page suivante, command: /friend requests %NEXT_PAGE%}");

			fileConfiguration.set("textcomponents.friendListPreviousPage", "&6« {hovertext: &9Page précédente, command: /friend list %PREVIOUS_PAGE%}");
			fileConfiguration.set("textcomponents.friendListNextPage", "&6» {hovertext: &9Page suivante, command: /friend list %NEXT_PAGE%}");
			super.save();
		}

		boolean shouldSave = false;
		if (fileConfiguration.get("textcomponents.friendRequestsPreviousPage") == null) {
			fileConfiguration.set("textcomponents.friendRequestsPreviousPage", "&6« {hovertext: &9Page précédente, command: /friend requests %PREVIOUS_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendRequestsNextPage") == null) {
			fileConfiguration.set("textcomponents.friendRequestsNextPage", "&6» {hovertext: &9Page suivante, command: /friend requests %NEXT_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendListPreviousPage") == null) {
			fileConfiguration.set("textcomponents.friendListPreviousPage", "&6« {hovertext: &9Page précédente, command: /friend list %PREVIOUS_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendListNextPage") == null) {
			fileConfiguration.set("textcomponents.friendListNextPage", "&6» {hovertext: &9Page suivante, command: /friend list %NEXT_PAGE%}");
			shouldSave = true;
		}

		if (shouldSave)
			super.save();

		fileConfiguration.getSection("textcomponents")
				.getKeys()
				.forEach(key -> messages.put("textcomponents." + key, fileConfiguration.getString("textcomponents." + key)));
	}

}
