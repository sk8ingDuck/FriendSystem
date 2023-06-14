package me.sk8ingduck.friendsystem.config.languages;

import me.sk8ingduck.friendsystem.config.MessagesConfig;

public class MessagesRussianConfig extends MessagesConfig {

	public MessagesRussianConfig(String name, String path) {
		super(name, path);
	}

	@Override
	public void loadMessages() {
		messages.put("prefix", "&cДрузья &8»&9 ");
		messages.put("friend.help",
				"&9&m-----------------&r&e« Система друзей »&9&m-----------------\n" +
						"&e/friend add <player> &8- &7добавить друга\n" +
						"&e/friend remove <player> &8- &7удалить друга\n" +
						"&e/friend accept <player> &8- &7принять заявку в друзья\n" +
						"&e/friend deny <player> &8- &7отклонить заявку в друзья\n" +
						"&e/friend list &8- &7список всех друзей\n" +
						"&e/friend requests &8- &7показать открытые заявки\n" +
						"&e/msg <player> <message> &8- &7отправить сообщение другу\n" +
						"&e/r <message> &8- &7ответить на сообщение\n" +
						"&e/friend jump <player> &8- &7перейти на сервер друга\n" +
						"&e/friend toggleinvites &8- &7переключить приглашения\n" +
						"&e/friend togglemsgs &8- &7переключить личные сообщения\n" +
						"&e/friend togglejump &8- &7переключить прыжок\n" +
						"&e/friend togglenotifies &8- &7переключить сообщения о входе/выходе\n" +
						"&e/friend status <status> &8- &7установить свой статус\n" +
						"&e/friend favourite <player> &8- &7добавить/удалить из избранного\n" +
						"&9&m-----------------&r&e« Система друзей »&9&m-----------------");

		messages.put("friend.list.format.header", "&9&m-----------&r&e« Ваши друзья &7(Страница: %CURRENT_PAGE%/%TOTAL_PAGES%) »&9&m-----------");
		messages.put("friend.list.format.online.regular", "&7%PLAYERON% &8- &a%SERVER% &7(с &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.online.favourite", "&7[&c❤&7] %PLAYERON% &8- &a%SERVER% &7(с &e%ONLINE_TIME%&7) {jtextcomponents.ump}");
		messages.put("friend.list.format.offline.regular", "&7%PLAYEROFF% &8- &cОФФЛАЙН &7(с &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.offline.favourite", "&7[&c❤&7] %PLAYEROFF% &8- &cОФФЛАЙН &7(с &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.nofriends", "&cУ вас нет друзей :(");
		messages.put("friend.list.format.pagination.pagenotfound", "&4Страница не найдена.");
		messages.put("friend.list.format.pagination.textbeforepage", "\n                              ");
		messages.put("friend.list.format.pagination.previouspage", "{friendListPreviousPage}");
		messages.put("friend.list.format.pagination.currentpage", " &a%CURRENT_PAGE% ");
		messages.put("friend.list.format.pagination.nextpage", "{friendListNextPage}");
		messages.put("friend.list.format.footer", "&9&m-----------&r&e« Ваши друзья &7(Страница: %CURRENT_PAGE%/%TOTAL_PAGES%) »&9&m-----------");

		messages.put("friend.request.format.header", "&9&m--------------&r&e« Открытые заявки &7(Страница: %CURRENT_PAGE%/%TOTAL_PAGES%) &e»&9&m--------------");
		messages.put("friend.request.format.player", "&7- %PLAYER% {accept} {deny} &7(Истекает через: &e%EXPIRES_IN%&7)");
		messages.put("friend.request.format.norequests", "&cУ вас нет запросов");
		messages.put("friend.request.format.pagination.pagenotfound", "&4Страница не найдена.");
		messages.put("friend.request.format.pagination.textbeforepage", "\n                              ");
		messages.put("friend.request.format.pagination.previouspage", "{friendRequestsPreviousPage}");
		messages.put("friend.request.format.pagination.currentpage", " &a%CURRENT_PAGE% ");
		messages.put("friend.request.format.pagination.nextpage", "{friendRequestsNextPage}");
		messages.put("friend.request.format.footer", "&9&m--------------&r&e« Открытые заявки &7(Страница: %CURRENT_PAGE%/%TOTAL_PAGES%) &e»&9&m--------------");

		messages.put("friend.request.alreadyfriends", "&cВы уже друзья с &7%PLAYER%.");
		messages.put("friend.request.alreadyrequested", "&cВы уже отправили заявку в друзья &7%PLAYER%.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &cуже отправил вам заявку в друзья.\n" +
				"{accept} {deny}");
		messages.put("friend.request.toomanyfriends", "&cУ вас уже слишком много друзей. Вы можете иметь &7%MAX_FRIENDS%");
		messages.put("friend.request.invitestoggled", "&7%PLAYER% &cне принимает заявки в друзья.");
		messages.put("friend.request.sent", "&9Вы отправили заявку в друзья &7%PLAYER%!");
		messages.put("friend.request.received","&9Вы получили заявку в друзья от &7%PLAYER%!\n" +
				"{accept} {deny}");
		messages.put("friend.toggleinvites.on", "&9Приглашения в друзья теперь &aразрешены&9.");
		messages.put("friend.toggleinvites.off", "&9Приглашения в друзья теперь &cзапрещены&9.");
		messages.put("friend.togglemsgs.on", "&9Личные сообщения теперь &aразрешены&9.");
		messages.put("friend.togglemsgs.off", "&9Личные сообщения теперь &cзапрещены&9.");
		messages.put("friend.togglejump.on", "&9Прыжки к друзьям теперь &aразрешены&9.");
		messages.put("friend.togglejump.off", "&9Прыжки к друзьям теперь &cзапрещены&9.");
		messages.put("friend.togglenotifies.on", "&9Сообщения о входе/выходе теперь &aвключены&9.");
		messages.put("friend.togglenotifies.off", "&9Сообщения о входе/выходе теперь &cвыключены&9.");
		messages.put("friend.add.syntax", "&cСинтаксис: &6/friend add <player>");
		messages.put("friend.remove.syntax", "&cСинтаксис: &6/friend remove <player>");
		messages.put("friend.accept.syntax", "&cСинтаксис: &6/friend accept <player>");
		messages.put("friend.deny.syntax", "&cСинтаксис: &6/friend deny <player>");
		messages.put("friend.jump.syntax", "&cСинтаксис: &6/friend jump <player>");
		messages.put("friend.favourite.syntax", "&ccСинтаксис: &6/friend favourite <player>");
		messages.put("friend.error.interact", "&cВы не можете взаимодействовать с самим собой.");
		messages.put("friend.error.playernotfound", "&cИгрок 7%PLAYER% &cне существует.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% &cникогда не был в сети.");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &cне является вашим другом");
		messages.put("friend.remove.successful", "&7%PLAYER% &9больше не ваш друг.");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9удалил вас из друзей.");
		messages.put("friend.add.successful", "&7%PLAYER% &9теперь ваш друг.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9принял вашу заявку в друзья.");
		messages.put("friend.add.norequest", "&cУ вас нет заявки в друзья от &7%PLAYER%");
		messages.put("friend.deny.successful", "&9Вы отклонили заявку в друзья от &7%PLAYER%.");
		messages.put("friend.deny.successful2", "&7%PLAYER% &9отклонил вашу заявку.");
		messages.put("friend.deny.norequest", "&cУ вас нет заявки в друзья от &7%PLAYER%");
		messages.put("friend.jump.notfriends", "&7%PLAYER% &cне является вашим другом");
		messages.put("friend.jump.notonline", "&7%PLAYER% &cне в сети.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &cне разрешает прыжки.");
		messages.put("friend.status.toolong", "&cВаш статус слишком длинный. Максимум 64 символа.");
		messages.put("friend.status.update", "&9Ваш статус теперь &7%STATUS%");
		messages.put("friend.favourite.notfriends", "&7%PLAYER% &cне является вашим другом");
		messages.put("friend.favourite.added", "&9Вы отметили &7%PLAYER% &9как избранное");
		messages.put("friend.favourite.removed", "&7%PLAYER% &9больше не в избранном");
		messages.put("msg.error.syntax", "&cСинтаксис: &6/msg <player> <message>");
		messages.put("msg.error.interact", "&cВы не можете взаимодействовать с самим собой.");
		messages.put("msg.error.playernotfound", "&7%PLAYER% &cне существует.");
		messages.put("msg.error.notonline", "&7%PLAYER% &cне в сети.");
		messages.put("msg.error.notfriends", "&7%PLAYER% &cне является вашим другом");
		messages.put("msg.error.msgstoggled", "&7%PLAYER% &cне принимает личные сообщения.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&cСинтаксис: &6/r <message>");
		messages.put("r.error.noreceiver", "&cС кем вы общаетесь?");
		messages.put("r.error.notfriends", "&7%PLAYER% &cне является вашим другом");
		messages.put("r.error.msgstoggled", "&cВы не можете написать &7%PLAYER% &cличные сообщения.");
		messages.put("msg.r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("notifies.join", "&7%PLAYER% &9теперь &aв сети&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9теперь &cне в сети&9.");
		messages.put("join.friendcounter", "&9Сейчас в сети {onlineFriends} &9друзей");
		messages.put("join.requestcounter", "&9У вас {openRequests} &9открытых заявок в друзья");

		messages.put("friend.timeformat.years", "лет");
		messages.put("friend.timeformat.year", "год");
		messages.put("friend.timeformat.days", "дней");
		messages.put("friend.timeformat.day", "день");
		messages.put("friend.timeformat.hours", "часов");
		messages.put("friend.timeformat.hour", "час");
		messages.put("friend.timeformat.minutes", "минут");
		messages.put("friend.timeformat.minute", "минуты");
		messages.put("friend.timeformat.seconds", "секунд");
		messages.put("friend.timeformat.second", "секунда");
	}

	@Override
	public void loadTextComponents() {
		if (fileConfiguration.getSection("textcomponents").getKeys().isEmpty()) {
			fileConfiguration.set("textcomponents.accept", "&a[✔] {hovertext: &aПринять запрос на добавление в друзья от &6%PLAYER%, command: /friend accept %PLAYER%}");
			fileConfiguration.set("textcomponents.deny", "&c[✕] {hovertext: &cОтклонить запрос на добавление в друзья от &6%PLAYER%, command: /friend deny %PLAYER%}");
			fileConfiguration.set("textcomponents.onlineFriends", "&7%COUNT% {hovertext: &9Показать друзей в сети, command: /friend list}");
			fileConfiguration.set("textcomponents.openRequests", "&7%COUNT% {hovertext: &9Показать текущие запросы в друзья, command: /friend requests}");
			fileConfiguration.set("textcomponents.jump", "&a[JUMP] {hovertext: &9Телепорт к &7%PLAYERON% &9на сервер, command: /friend jump %PLAYERON%}");

			fileConfiguration.set("textcomponents.friendRequestsPreviousPage", "&6« {hovertext: &9Предыдущая страница, command: /friend requests %PREVIOUS_PAGE%}");
			fileConfiguration.set("textcomponents.friendRequestsNextPage", "&6» {hovertext: &9Следующая страница, command: /friend requests %NEXT_PAGE%}");
			fileConfiguration.set("textcomponents.friendListPreviousPage", "&6« {hovertext: &9Предыдущая страница, command: /friend list %PREVIOUS_PAGE%}");
			fileConfiguration.set("textcomponents.friendListNextPage", "&6» {hovertext: &9Следующая страница, command: /friend list %NEXT_PAGE%}");
			super.save();
		}


		boolean shouldSave = false;
		if (fileConfiguration.get("textcomponents.friendRequestsPreviousPage") == null) {
			fileConfiguration.set("textcomponents.friendRequestsPreviousPage", "&6« {hovertext: &9Предыдущая страница, command: /friend requests %PREVIOUS_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendRequestsNextPage") == null) {
			fileConfiguration.set("textcomponents.friendRequestsNextPage", "&6» {hovertext: &9Следующая страница, command: /friend requests %NEXT_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendListPreviousPage") == null) {
			fileConfiguration.set("textcomponents.friendListPreviousPage", "&6« {hovertext: &9Предыдущая страница, command: /friend list %PREVIOUS_PAGE%}");
			shouldSave = true;
		}
		if (fileConfiguration.get("textcomponents.friendListNextPage") == null) {
			fileConfiguration.set("textcomponents.friendListNextPage", "&6» {hovertext: &9Следующая страница, command: /friend list %NEXT_PAGE%}");
			shouldSave = true;
		}

		if (shouldSave)
			super.save();

		fileConfiguration.getSection("textcomponents")
				.getKeys()
				.forEach(key -> messages.put("textcomponents." + key, fileConfiguration.getString("textcomponents." + key)));
	}


}
