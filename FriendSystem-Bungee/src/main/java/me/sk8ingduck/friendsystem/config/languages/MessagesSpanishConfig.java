package me.sk8ingduck.friendsystem.config.languages;

import me.sk8ingduck.friendsystem.config.MessagesConfig;

public class MessagesSpanishConfig extends MessagesConfig {

	public MessagesSpanishConfig(String name, String path) {
		super(name, path);
	}

	public void loadMessages() {
		messages.put("prefix", "&cAmigos &8»&9 ");
		messages.put("friend.help",
				"&9&m-----------------&r&e« SistemaAmigos »&9&m-----------------\n" +
						"&e/friend add <jugador> &8- &7añadir amigo\n" +
						"&e/friend remove <jugador> &8- &7eliminar amigo\n" +
						"&e/friend accept <jugador> &8- &7aceptar solicitud de amistad\n" +
						"&e/friend deny <jugador> &8- &7rechazar solicitud de amistad\n" +
						"&e/friend list &8- &7listar todos los amigos\n" +
						"&e/friend requests &8- &7mostrar solicitudes abiertas\n" +
						"&e/msg <jugador> <mensaje> &8- &7enviar un mensaje a un amigo\n" +
						"&e/r <mensaje> &8- &7responder a un mensaje\n" +
						"&e/friend jump <jugador> &8- &7saltar al servidor del amigo\n" +
						"&e/friend toggleinvites &8- &7cambiar invitaciones\n" +
						"&e/friend togglemsgs &8- &7cambiar mensajes privados\n" +
						"&e/friend togglejump &8- &7cambiar salto\n" +
						"&e/friend togglenotifies &8- &7cambiar mensajes de entrada/salida\n" +
						"&e/friend status <estado> &8- &7establecer tu estado\n" +
						"&e/friend favourite <jugador> &8- &7añadir/eliminar un favorito\n" +
						"&9&m-----------------&r&e« SistemaAmigos »&9&m-----------------");

		messages.put("friend.list.format.header", "&9&m-----------&r&e« Tus amigos &7(Página: %CURRENT_PAGE%/%TOTAL_PAGES%) »&9&m-----------");
		messages.put("friend.list.format.online.regular", "&7%PLAYERON% &8- &a%SERVER% &7(desde &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.online.favourite", "&7[&c❤&7] %PLAYERON% &8- &a%SERVER% &7(desde &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.offline.regular", "&7%PLAYEROFF% &8- &cOFFLINE &7(desde &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.offline.favourite", "&7[&c❤&7] %PLAYEROFF% &8- &cOFFLINE &7(desde &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.nofriends", "&cNo tienes amigos :(");
		messages.put("friend.list.format.pagination.pagenotfound", "&4Página no encontrada.");
		messages.put("friend.list.format.pagination.textbeforepage", "\n                              ");
		messages.put("friend.list.format.pagination.previouspage", "{friendListPreviousPage}");
		messages.put("friend.list.format.pagination.currentpage", " &a%CURRENT_PAGE% ");
		messages.put("friend.list.format.pagination.nextpage", "{friendListNextPage}");
		messages.put("friend.list.format.footer", "&9&m-----------&r&e« Tus amigos &7(Página: %CURRENT_PAGE%/%TOTAL_PAGES%) »&9&m-----------");

		messages.put("friend.request.format.header", "&9&m--------------&r&e« Solicitudes abiertas &7(Página: %CURRENT_PAGE%/%TOTAL_PAGES%) &e»&9&m--------------");
		messages.put("friend.request.format.player", "&7- %PLAYER% {accept} {deny} &7(Expira en: &e%EXPIRES_IN%&7)");
		messages.put("friend.request.format.norequests", "&cNo tienes solicitudes");
		messages.put("friend.request.format.pagination.pagenotfound", "&4Página no encontrada.");
		messages.put("friend.request.format.pagination.textbeforepage", "\n                              ");
		messages.put("friend.request.format.pagination.previouspage", "{friendRequestsPreviousPage}");
		messages.put("friend.request.format.pagination.currentpage", " &a%CURRENT_PAGE% ");
		messages.put("friend.request.format.pagination.nextpage", "{friendRequestsNextPage}");
		messages.put("friend.request.format.footer", "&9&m--------------&r&e« Solicitudes abiertas &7(Página: %CURRENT_PAGE%/%TOTAL_PAGES%) &e»&9&m--------------");

		messages.put("friend.request.alreadyfriends", "&cYa eres amigo de &7%PLAYER%.");
		messages.put("friend.request.alreadyrequested", "&cYa has enviado una solicitud de amistad a &7%PLAYER%.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &cya te ha enviado una solicitud de amistad.\n" +
				"{accept} {deny}");
		messages.put("friend.request.toomanyfriends", "&cYa tienes demasiados amigos. Se te permite tener &7%MAX_FRIENDS%");
		messages.put("friend.request.invitestoggled", "&7%PLAYER% &cno permite solicitudes de amistad.");
		messages.put("friend.request.sent", "&9Has enviado una solicitud de amistad a &7%PLAYER%!");
		messages.put("friend.request.received","&9¡Has recibido una solicitud de amistad de &7%PLAYER%!\n" +
				"{accept} {deny}");
		messages.put("friend.toggleinvites.on", "&9Las solicitudes de amistad están ahora &apermitidas&9.");
		messages.put("friend.toggleinvites.off", "&9Las solicitudes de amistad están ahora &cdesactivadas&9.");
		messages.put("friend.togglemsgs.on", "&9Los mensajes privados están ahora &apermitidos&9.");
		messages.put("friend.togglemsgs.off", "&9Los mensajes privados están ahora &cdesactivados&9.");
		messages.put("friend.togglejump.on", "&9Los saltos de amigos están ahora &apermitidos&9.");
		messages.put("friend.togglejump.off", "&9Los saltos de amigos están ahora &cdesactivados&9.");
		messages.put("friend.togglenotifies.on", "&9Los mensajes de Entrada/Salida están ahora &aactivados&9.");
		messages.put("friend.togglenotifies.off", "&9Los mensajes de Entrada/Salida están ahora &cdesactivados&9.");
		messages.put("friend.add.syntax", "&cSintaxis: &6/friend add <jugador>");
		messages.put("friend.remove.syntax", "&cSintaxis: &6/friend remove <jugador>");
		messages.put("friend.accept.syntax", "&cSintaxis: &6/friend accept <jugador>");
		messages.put("friend.deny.syntax", "&cSintaxis: &6/friend deny <jugador>");
		messages.put("friend.jump.syntax", "&cSintaxis: &6/friend jump <jugador>");
		messages.put("friend.favourite.syntax", "&cSintaxis: &6/friend favourite <jugador>");
		messages.put("friend.error.interact", "&cNo puedes interactuar contigo mismo.");
		messages.put("friend.error.playernotfound", "&cEl jugador 7%PLAYER% &cno existe.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% &cnunca ha estado en la red.");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &cno es tu amigo");
		messages.put("friend.remove.successful", "&7%PLAYER% &9ya no es tu amigo.");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9te ha eliminado como amigo.");
		messages.put("friend.add.successful", "&7%PLAYER% &9ahora es tu amigo.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9aceptó tu solicitud de amistad.");
		messages.put("friend.add.norequest", "&cNo tienes ninguna solicitud de amistad de &7%PLAYER%");
		messages.put("friend.deny.successful", "&9Has rechazado la solicitud de amistad de &7%PLAYER%.");
		messages.put("friend.deny.successful2", "&7%PLAYER% &9ha rechazado tu solicitud.");
		messages.put("friend.deny.norequest", "&cNo tienes ninguna solicitud de amistad de &7%PLAYER%");
		messages.put("friend.jump.notfriends", "&7%PLAYER% &cno es tu amigo");
		messages.put("friend.jump.notonline", "&7%PLAYER% &cno está en línea.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &cno permite saltos.");
		messages.put("friend.status.toolong", "&cTu estado es demasiado largo. El máximo es de 64 caracteres.");
		messages.put("friend.status.update", "&9Tu estado ahora es &7%STATUS%");
		messages.put("friend.favourite.notfriends", "&7%PLAYER% &cno es tu amigo");
		messages.put("friend.favourite.added", "&9Has marcado a &7%PLAYER% &9como favorito");
		messages.put("friend.favourite.removed", "&7%PLAYER% &cya no es un favorito");
		messages.put("msg.error.syntax", "&cSintaxis: &6/msg <jugador> <mensaje>");
		messages.put("msg.error.interact", "&cNo puedes interactuar contigo mismo.");
		messages.put("msg.error.playernotfound", "&7%PLAYER% &cno existe.");
		messages.put("msg.error.notonline", "&7%PLAYER% &cno está en línea.");
		messages.put("msg.error.notfriends", "&7%PLAYER% &cno es tu amigo");
		messages.put("msg.error.msgstoggled", "&7%PLAYER% &cno permite mensajes privados.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&cSintaxis: &6/r <mensaje>");
		messages.put("r.error.noreceiver", "&c¿Con quién estás chateando?");
		messages.put("r.error.notfriends", "&7%PLAYER% &cno es tu amigo");
		messages.put("r.error.msgstoggled", "&cNo puedes enviar mensajes privados a &7%PLAYER%");
		messages.put("msg.r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("notifies.join", "&7%PLAYER% &9ahora está &aen línea&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9ahora está &cfuera de línea&9.");
		messages.put("join.friendcounter", "&9Actualmente hay {onlineFriends} &9amigo(s) en línea");
		messages.put("join.requestcounter", "&9Tienes {openRequests} &9solicitud(es) de amistad pendiente(s)");

		messages.put("friend.timeformat.years", "años");
		messages.put("friend.timeformat.year", "año");
		messages.put("friend.timeformat.days", "días");
		messages.put("friend.timeformat.day", "día");
		messages.put("friend.timeformat.hours", "horas");
		messages.put("friend.timeformat.hour", "hora");
		messages.put("friend.timeformat.minutes", "minutos");
		messages.put("friend.timeformat.minute", "minutos");
		messages.put("friend.timeformat.seconds", "segundos");
		messages.put("friend.timeformat.second", "segundo");

	}

	@Override
	public void loadTextComponents() {
		if (fileConfiguration.getSection("textcomponents").getKeys().isEmpty()) {
			fileConfiguration.set("textcomponents.accept", "&a[✔] {hovertext: &aAceptar solicitud de amistad de &6%PLAYER%, command: /friend accept %PLAYER%}");
			fileConfiguration.set("textcomponents.deny", "&c[✕] {hovertext: &cRechazar solicitud de amistad de &6%PLAYER%, command: /friend deny %PLAYER%}");
			fileConfiguration.set("textcomponents.onlineFriends", "&7%COUNT% {hovertext: &9Mostrar amigos en línea, command: /friend list}");
			fileConfiguration.set("textcomponents.openRequests", "&7%COUNT% {hovertext: &9Mostrar solicitudes abiertas, command: /friend requests}");
			fileConfiguration.set("textcomponents.jump", "&a[SALTAR] {hovertext: &9Saltar al servidor de &7%PLAYERON%, command: /friend jump %PLAYERON%}");

			fileConfiguration.set("textcomponents.friendRequestsPreviousPage", "&6« {hovertext: &9Página anterior, command: /friend requests %PREVIOUS_PAGE%}");
			fileConfiguration.set("textcomponents.friendRequestsNextPage", "&6» {hovertext: &9Próxima página, command: /friend requests %NEXT_PAGE%}");

			fileConfiguration.set("textcomponents.friendListPreviousPage", "&6« {hovertext: &9Página anterior, command: /friend list %PREVIOUS_PAGE%}");
			fileConfiguration.set("textcomponents.friendListNextPage", "&6» {hovertext: &9Próxima página, command: /friend list %NEXT_PAGE%}");
			super.save();
		}


		boolean shouldSave = false;
		if (fileConfiguration.get("textcomponents.friendRequestsPreviousPage") == null) {
			fileConfiguration.set("textcomponents.friendRequestsPreviousPage", "&6« {hovertext: &9Página anterior, command: /friend requests %PREVIOUS_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendRequestsNextPage") == null) {
			fileConfiguration.set("textcomponents.friendRequestsNextPage", "&6» {hovertext: &9Próxima página, command: /friend requests %NEXT_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendListPreviousPage") == null) {
			fileConfiguration.set("textcomponents.friendListPreviousPage", "&6« {hovertext: &9Página anterior, command: /friend list %PREVIOUS_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendListNextPage") == null) {
			fileConfiguration.set("textcomponents.friendListNextPage", "&6» {hovertext: &9Próxima página, command: /friend list %NEXT_PAGE%}");
			shouldSave = true;
		}

		if (shouldSave)
			super.save();

		fileConfiguration.getSection("textcomponents")
				.getKeys()
				.forEach(key -> messages.put("textcomponents." + key, fileConfiguration.getString("textcomponents." + key)));
	}

}
