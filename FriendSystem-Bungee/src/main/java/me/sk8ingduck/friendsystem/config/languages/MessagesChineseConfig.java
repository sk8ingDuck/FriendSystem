package me.sk8ingduck.friendsystem.config.languages;

import me.sk8ingduck.friendsystem.config.MessagesConfig;

public class MessagesChineseConfig extends MessagesConfig {

	public MessagesChineseConfig(String name, String path) {
		super(name, path);
	}

	@Override
	public void loadMessages() {
		messages.put("prefix", "&c好友 &8»&9 ");
		messages.put("friend.help", "&9&m----------------->&r&e« 好友系统 »&9&m-----------------\n" +
				"&e/friend add <player> &8- &7添加好友\n" +
				"&e/friend remove <Name> &8- &7删除好友\n" +
				"&e/friend accept <player> &8- &7接受好友申请\n" +
				"&e/friend deny <player> &8- &7拒绝好友申请\n" +
				"&e/friend list &8- &7显示所有好友\n" +
				"&e/friend requests &8- &7显示未完成的请求\n" +
				"&e/msg <player> <message> &8- &7给好友发送一条私信\n" +
				"&e/r <message> &8- &7回复一条消息\n" +
				"&e/friend jump <player> &8- &7转跳到好友的服务器\n" +
				"&e/friend toggleinvites &8- &7切换邀请\n" +
				"&e/friend togglemsgs &8- &7切换私信\n" +
				"&e/friend togglejump &8- &7切换转跳\n" +
				"&e/friend togglenotifies &8- &7切换 上线/下线 消息\n" +
				"&e/friend status <status> &8- &7设置你的状态\n" +
				"&e/friend favourite <player> &8- &7添加/移除 一个关注\n" +
				"&9&m----------------->&r&e« 好友系统 »&9&m-----------------");
		messages.put("friend.list.format.header", "&9&m--------------&r&e« 你的好友 »&9&m--------------");
		messages.put("friend.list.format.online.regular", "&7%PLAYERON% &8- &a%SERVER% &7(上线时间 &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.online.favourite", "&7[&c❤&7] %PLAYERON% &8- &a%SERVER% &7(上线时间 &e%ONLINE_TIME%&7) {jump}");
		messages.put("friend.list.format.offline.regular", "&7%PLAYEROFF% &8- &c离线 &7(离线时间 &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.offline.favourite", "&7[&c❤&7] %PLAYEROFF% &8- &c离线 &7(离线时间 &e%OFFLINE_SINCE%&7)");
		messages.put("friend.list.format.footer", "&9&m--------------&r&e« 你的好友 »&9&m--------------");
		messages.put("friend.request.format.header", "&9&m--------------&r&e« 开放请求 »&9&m--------------");
		messages.put("friend.request.format.player", "&7- %PLAYER% {accept} {deny}");
		messages.put("friend.request.format.footer", "&9&m--------------&r&e« 开放请求 »&9&m--------------");
		messages.put("friend.request.alreadyfriends", "&c你已经和 %PLAYER% 是好友了.");
		messages.put("friend.request.alreadyrequested", "&c您已经向玩家%PLAYER%发送了一个好友请求，请求已经发出.");
		messages.put("friend.request.alreadyreceivedrequest", "&7%PLAYER% &c向您发送了好友请求.\n{accept} {deny}");
		messages.put("friend.request.toomanyfriends", "&c您已经有太多的好友了。您只能拥有&7%MAX_FRIENDS%&r个好友");
		messages.put("friend.request.invitestoggled", "&7%PLAYER% &c不接受好友请求.");
		messages.put("friend.request.sent", "&9您已向 &7%PLAYER%&9发送好友请求!");
		messages.put("friend.request.received", "&9您收到了来自&7%PLAYER%&r的好友请求!\n{accept} {deny}");
		messages.put("friend.toggleinvites.on", "&9好友请求&c已开启&9.");
		messages.put("friend.toggleinvites.off", "&9好友请求&c已关闭&9.");
		messages.put("friend.togglemsgs.on", "&9现在允许发送私信了 &a已启用&9.");
		messages.put("friend.togglemsgs.off", "&9私信 &c已禁用&9.");
		messages.put("friend.togglejump.on", "&9可以进行好友传送了 &a已启用&9.");
		messages.put("friend.togglejump.off", "&9好友传送 &c已禁用&9.");
		messages.put("friend.togglenotifies.on", "&9上线/下线消息提示 &a已启用&9.");
		messages.put("friend.togglenotifies.off", "&9上线/下线消息提示 &c已禁用&9.");
		messages.put("friend.add.syntax", "&c语法：&6/friend add <玩家>");
		messages.put("friend.add.successful", "&7%PLAYER% &9现在是你的好友了.");
		messages.put("friend.add.successful2", "&7%PLAYER% &9接受了你的好友请求.");
		messages.put("friend.add.norequest", "&c你没有来自&7%PLAYER%的好友请求");
		messages.put("friend.remove.syntax", "&c语法: &6/friend remove <玩家>");
		messages.put("friend.remove.notfriends", "&7%PLAYER% &c不是你的好友");
		messages.put("friend.remove.successful", "&7%PLAYER% &9不再是你的好友.");
		messages.put("friend.remove.successful2", "&7%PLAYER% &9将你从好友列表移除了.");
		messages.put("friend.accept.syntax", "&c语法: &6/friend accept <玩家>");
		messages.put("friend.deny.syntax", "&c语法: &6/friend deny <玩家>");
		messages.put("friend.deny.successful", "&9你拒绝了%PLAYER%的好友请求.");
		messages.put("friend.deny.successful2", "&7%PLAYER% 拒绝了你的请求.");
		messages.put("friend.deny.norequest", "&c你没有来自%PLAYER%的好友请求");
		messages.put("friend.jump.syntax", "&c语法: &6/friend jump <玩家>");
		messages.put("friend.jump.notfriends", "&7%PLAYER% &c不是你的好友");
		messages.put("friend.jump.notonline", "&7%PLAYER% &c不在线.");
		messages.put("friend.jump.jumptoggled", "&7%PLAYER% &c不允许跨服跟随.");
		messages.put("friend.error.interact", "&c你不能与自己进行互动.");
		messages.put("friend.error.playernotfound", "&c玩家 &7%PLAYER% &c不存在.");
		messages.put("friend.error.notonnetwork", "&7%PLAYER% 没有上过线.");
		messages.put("friend.status.toolong", "&c你的状态过长了，最多只能有64个字符.");
		messages.put("friend.status.update", "&9你的状态现在是 &7%STATUS%");
		messages.put("friend.favourite.notfriends", "&7%PLAYER% &c不是你的好友");
		messages.put("friend.favourite.added", "&9你把 &7%PLAYER% &9添加为关注");
		messages.put("friend.favourite.removed", "&9现在不再关注 &7%PLAYER% &9了");
		messages.put("friend.timeformat.years", "年");
		messages.put("friend.timeformat.year", "年");
		messages.put("friend.timeformat.days", "天");
		messages.put("friend.timeformat.day", "天");
		messages.put("friend.timeformat.hours", "时");
		messages.put("friend.timeformat.hour", "时");
		messages.put("friend.timeformat.minutes", "分");
		messages.put("friend.timeformat.minute", "分");
		messages.put("friend.timeformat.seconds", "秒");
		messages.put("friend.timeformat.second", "秒");
		messages.put("msg.error.syntax", "&c语法: &6/msg <玩家> <消息>");
		messages.put("msg.error.interact", "&c你不能与自己进行互动.");
		messages.put("msg.error.playernotfound", "&7%PLAYER%不存在.");
		messages.put("msg.error.notonline", "&7%PLAYER% &c不在线.");
		messages.put("msg.error.notfriends", "&7%PLAYER% &c不是你的好友");
		messages.put("msg.error.msgstoggled", "&7%PLAYER% &c不允许发送私信.");
		messages.put("msg.format.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.format.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.sender", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("msg.r.receiver", "&7%PLAYER% &b-> &7%PLAYER2%&8: &e%MSG%");
		messages.put("r.error.syntax", "&c语法: &6/r <消息>");
		messages.put("r.error.noreceiver", "&c你在和谁聊天?");
		messages.put("r.error.notfriends", "&7%PLAYER% &c不是你的好友");
		messages.put("r.error.msgstoggled", "&c你无法向玩家 &7%PLAYER% &c发送私人消息.");
		messages.put("notifies.join", "&7%PLAYER% &9现在 &aonline&9.");
		messages.put("notifies.leave", "&7%PLAYER% &9现在 &coffline&9.");
		messages.put("join.friendcounter", "&9目前有 {onlineFriends} 位好友在线");
		messages.put("join.requestcounter", "&9你有 {openRequests} 个待处理的好友请求");
	}

	@Override
	public void loadTextComponents() {
		if (fileConfiguration.getSection("textcomponents").getKeys().isEmpty()) {
			messages.put("textcomponents.accept", "&a[接受] {hovertext: &a接受来自 &6%PLAYER% 的好友申请, command: /friend accept %PLAYER%}");
			messages.put("textcomponents.deny", "&c[拒绝] {hovertext: &c拒绝来自 &6%PLAYER% 的好友申请, command: /friend deny %PLAYER%}");
			messages.put("textcomponents.onlineFriends", "&7%COUNT% {hovertext: &9展示在线好友列表, command: /friend list}");
			messages.put("textcomponents.openRequests", "&7%COUNT% {hovertext: &9展示未处理的请求, command: /friend requests}");
			messages.put("textcomponents.jump", "&a[转跳] {hovertext: &9转跳到在线好友 &7%PLAYERON% 的 &9服务器, command: /friend jump %PLAYERON%}");

			super.save();
		}

		fileConfiguration.getSection("textcomponents")
				.getKeys()
				.forEach(key -> messages.put("textcomponents." + key, fileConfiguration.getString("textcomponents." + key)));
	}

}
