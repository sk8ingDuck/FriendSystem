package me.sk8ingduck.friendsystemgui.config.languages;

import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.Skull;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.io.File;

public class GuiChineseConfig extends GuiConfig {

	public GuiChineseConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		items.put("guiItem", SkullCreator.createPlayerSkull("好友菜单"));

		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9好友菜单");
		mainGuiRows = (int) getPathOrSet("gui.mainMenu.rows", 6);

		itemSlots.put("gui.mainMenu.friends.startSlot", 0);
		itemSlots.put("gui.mainMenu.friends.endSlot", 36);
		items.put("gui.mainMenu.friends.onlineFriend", SkullCreator.createPlayerSkull("%PLAYER%",
				" ", "服务器", "→ %SERVER%", " ", "在线时长", "→ %ONLINE_TIME%", " ", "状态", "→ %STATUS%"));
		items.put("gui.mainMenu.friends.onlineFriendFavourite", SkullCreator.createPlayerSkull("[❤] %PLAYER%",
				" ", "服务器", "→ %SERVER%", " ", "在线时长", "→ %ONLINE_TIME%", " ", "状态", "→ %STATUS%"));
		items.put("gui.mainMenu.friends.offlineFriend", SkullCreator.createSkeletonSkull("%PLAYER%",
				" ", "最后出现", "→ %LAST_SEEN%", " ", "状态", "→ %STATUS%"));
		items.put("gui.mainMenu.friends.offlineFriendFavourite", SkullCreator.createSkeletonSkull("[❤] %PLAYER%",
				" ", "最后出现", "→ %LAST_SEEN%", " ", "状态", "→ %STATUS%"));

		items.put("gui.mainMenu.ownInfo.item", SkullCreator.createPlayerSkull("%PLAYER%",
				" ", "服务器", "→ %SERVER%", " ", "您在线时长", "→ %ONLINE_TIME%", " ", "您的状态", "→ %STATUS%"));
		itemSlots.put("gui.mainMenu.ownInfo.slot", 49);
		items.put("gui.mainMenu.settings.item", ItemCreator.createItem(Material.REDSTONE, "设置"));
		itemSlots.put("gui.mainMenu.settings.slot", 43);
		items.put("gui.mainMenu.requests.item", ItemCreator.createItem(Material.WRITTEN_BOOK, "好友请求"));
		itemSlots.put("gui.mainMenu.requests.slot", 47);
		items.put("gui.mainMenu.previousPage.item", Skull.LEFT.getSkull("上一页"));
		itemSlots.put("gui.mainMenu.previousPage.slot", 45);
		items.put("gui.mainMenu.nextPage.item", Skull.RIGHT.getSkull("下一页"));
		itemSlots.put("gui.mainMenu.nextPage.slot", 53);

		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "&9好友请求");
		requestsGuiRows = (int) getPathOrSet("gui.requestsMenu.size", 6);
		itemSlots.put("gui.requestsMenu.requests.startSlot", 0);
		itemSlots.put("gui.requestsMenu.requests.endSlot", 36);
		items.put("gui.requestsMenu.requests.item", SkullCreator.createSkeletonSkull( "&7%PLAYER%", " ",
				"&7来自的请求", "&e→ %REQUEST_DATE%", " ", "&7剩余时间:", "&e→ %EXPIRES_IN%"));

		itemSlots.put("gui.requestsMenu.previousPage.slot", 45);
		items.put("gui.requestsMenu.previousPage.item", Skull.LEFT.getSkull("上一页"));
		items.put("gui.requestsMenu.nextPage.item", Skull.RIGHT.getSkull("下一页"));
		itemSlots.put("gui.requestsMenu.nextPage.slot", 53);
		items.put("gui.requestsMenu.back.item", Skull.BACK.getSkull("返回"));
		itemSlots.put("gui.requestsMenu.back.slot", 49);

		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&c设置");
		settingsGuiRows = (int) getPathOrSet("gui.settingsMenu.size", 4);
		items.put("gui.settingsMenu.toggleInvites.item", ItemCreator.createItem(Material.TORCH, "切换好友请求"));
		itemSlots.put("gui.settingsMenu.toggleInvites.slot", 11);
		items.put("gui.settingsMenu.toggleNotifies.item", ItemCreator.createItem(Material.WOOD_DOOR, "切换加入/离开通知"));
		itemSlots.put("gui.settingsMenu.toggleNotifies.slot", 12);
		items.put("gui.settingsMenu.toggleMsgs.item", ItemCreator.createItem(Material.PAPER, "切换私人信息"));
		itemSlots.put("gui.settingsMenu.toggleMsgs.slot", 13);
		items.put("gui.settingsMenu.toggleJump.item", ItemCreator.createItem(Material.ENDER_PEARL, "切换跳跃"));
		itemSlots.put("gui.settingsMenu.toggleJump.slot", 14);
		items.put("gui.settingsMenu.changeStatus.item", ItemCreator.createItem(Material.ANVIL, "更改状态"));
		itemSlots.put("gui.settingsMenu.changeStatus.slot", 15);

		items.put("gui.settingsMenu.invitesOn.item", ItemCreator.createGlassPane(DyeColor.GREEN, "好友请求现在允许"));
		itemSlots.put("gui.settingsMenu.invitesOn.slot", 20);
		items.put("gui.settingsMenu.invitesOff.item", ItemCreator.createGlassPane(DyeColor.RED, "好友请求现在不允许"));
		itemSlots.put("gui.settingsMenu.invitesOff.slot", 20);
		items.put("gui.settingsMenu.notifiesOn.item", ItemCreator.createGlassPane(DyeColor.GREEN, "加入/离开通知现在启用"));
		itemSlots.put("gui.settingsMenu.notifiesOn.slot", 21);
		items.put("gui.settingsMenu.notifiesOff.item", ItemCreator.createGlassPane(DyeColor.RED, "加入/离开通知现在禁用"));
		itemSlots.put("gui.settingsMenu.notifiesOff.slot", 21);
		items.put("gui.settingsMenu.msgsOn.item", ItemCreator.createGlassPane(DyeColor.GREEN, "私人信息现在允许"));
		itemSlots.put("gui.settingsMenu.msgsOn.slot", 22);
		items.put("gui.settingsMenu.msgsOff.item", ItemCreator.createGlassPane(DyeColor.RED, "私人信息现在不允许"));
		itemSlots.put("gui.settingsMenu.msgsOff.slot", 22);
		items.put("gui.settingsMenu.jumpOn.item", ItemCreator.createGlassPane(DyeColor.GREEN, "跳跃现在允许"));
		itemSlots.put("gui.settingsMenu.jumpOn.slot", 23);
		items.put("gui.settingsMenu.jumpOff.item", ItemCreator.createGlassPane(DyeColor.RED, "跳跃现在不允许"));
		itemSlots.put("gui.settingsMenu.jumpOff.slot", 23);
		items.put("gui.settingsMenu.back.item", Skull.BACK.getSkull("返回"));
		itemSlots.put("gui.settingsMenu.jumpOff.slot", 0);

		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6选中的玩家 &7- &6%PLAYER%");
		selectedPlayerGuiRows = (int) getPathOrSet("gui.selectedPlayerMenu.rows", 1);
		items.put("gui.selectedPlayerMenu.addFavourite.item", Skull.HEART.getSkull("添加到收藏"));
		itemSlots.put("gui.selectedPlayerMenu.addFavourite.slot", 5);
		items.put("gui.selectedPlayerMenu.removeFavourite.item", Skull.CROSS.getSkull("从收藏中移除"));
		itemSlots.put("gui.selectedPlayerMenu.removeFavourite.slot", 5);
		items.put("gui.selectedPlayerMenu.addFriend.item", ItemCreator.createItem(Material.YELLOW_FLOWER, "发送好友请求"));
		itemSlots.put("gui.selectedPlayerMenu.addFriend.slot", 4);
		items.put("gui.selectedPlayerMenu.removeFriend.item", ItemCreator.createItem(Material.BARRIER, "移除好友"));
		itemSlots.put("gui.selectedPlayerMenu.removeFriend.slot", 8);
		items.put("gui.selectedPlayerMenu.acceptFriend.item", ItemCreator.createItem(Material.YELLOW_FLOWER, "接受好友请求"));
		itemSlots.put("gui.selectedPlayerMenu.acceptFriend.slot", 3);
		items.put("gui.selectedPlayerMenu.denyFriend.item", ItemCreator.createItem(Material.BARRIER, "拒绝好友请求"));
		itemSlots.put("gui.selectedPlayerMenu.denyFriend.slot", 5);
		items.put("gui.selectedPlayerMenu.alreadyRequested.item", ItemCreator.createItem(Material.BARRIER, "玩家已经被请求"));
		itemSlots.put("gui.selectedPlayerMenu.alreadyRequested.slot", 4);
		items.put("gui.selectedPlayerMenu.jump.item", ItemCreator.createItem(Material.ENDER_PEARL, "跳转到玩家的服务器"));
		itemSlots.put("gui.selectedPlayerMenu.jump.slot", 3);
		items.put("gui.selectedPlayerMenu.back.item", Skull.BACK.getSkull("返回"));
		itemSlots.put("gui.selectedPlayerMenu.back.slot", 0);

	}
}
