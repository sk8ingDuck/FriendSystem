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
		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "主菜单");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "好友请求");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "设置");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "选定的玩家 - %PLAYER%");

		items.put("guiItem", SkullCreator.createPlayerSkull("好友菜单"));
		items.put("gui.mainMenu.item.onlineFriend", SkullCreator.createPlayerSkull("%PLAYER%",
				" ", "服务器", "→ %SERVER%", " ", "在线时长", "→ %ONLINE_TIME%", " ", "状态", "→ %STATUS%"));
		items.put("gui.mainMenu.item.onlineFriendFavourite", SkullCreator.createPlayerSkull("[❤] %PLAYER%",
				" ", "服务器", "→ %SERVER%", " ", "在线时长", "→ %ONLINE_TIME%", " ", "状态", "→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriend", SkullCreator.createSkeletonSkull("%PLAYER%",
				" ", "最后出现", "→ %LAST_SEEN%", " ", "状态", "→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriendFavourite", SkullCreator.createSkeletonSkull("[❤] %PLAYER%",
				" ", "最后出现", "→ %LAST_SEEN%", " ", "状态", "→ %STATUS%"));

		items.put("gui.mainMenu.item.ownInfo", SkullCreator.createPlayerSkull("%PLAYER%",
				" ", "服务器", "→ %SERVER%", " ", "您在线时长", "→ %ONLINE_TIME%", " ", "您的状态", "→ %STATUS%"));
		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("上一页"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("下一页"));
		items.put("gui.mainMenu.item.settings", ItemCreator.createItem(Material.REDSTONE, "设置"));
		items.put("gui.mainMenu.item.requests", ItemCreator.createItem(Material.WRITTEN_BOOK, "好友请求"));

		items.put("gui.requestsMenu.item.request", SkullCreator.createSkeletonSkull("%PLAYER%"));
		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("上一页"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("下一页"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("返回"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("返回"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemCreator.createItem(Material.TORCH, "切换好友请求"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemCreator.createItem(Material.WOOD_DOOR, "切换加入/离开通知"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemCreator.createItem(Material.PAPER, "切换私人信息"));
		items.put("gui.settingsMenu.item.toggleJump", ItemCreator.createItem(Material.ENDER_PEARL, "切换跳跃"));

		items.put("gui.settingsMenu.item.invitesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "好友请求现在允许"));
		items.put("gui.settingsMenu.item.invitesOff", ItemCreator.createGlassPane(DyeColor.RED, "好友请求现在不允许"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "加入/离开通知现在启用"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemCreator.createGlassPane(DyeColor.RED, "加入/离开通知现在禁用"));
		items.put("gui.settingsMenu.item.msgsOn", ItemCreator.createGlassPane(DyeColor.GREEN, "私人信息现在允许"));
		items.put("gui.settingsMenu.item.msgsOff", ItemCreator.createGlassPane(DyeColor.RED, "私人信息现在不允许"));
		items.put("gui.settingsMenu.item.jumpOn", ItemCreator.createGlassPane(DyeColor.GREEN, "跳跃现在允许"));
		items.put("gui.settingsMenu.item.jumpOff", ItemCreator.createGlassPane(DyeColor.RED, "跳跃现在不允许"));

		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("返回"));
		items.put("gui.selectedPlayerMenu.item.addFavourite", Skull.HEART.getSkull("添加到收藏"));
		items.put("gui.selectedPlayerMenu.item.removeFavourite", Skull.CROSS.getSkull("从收藏中移除"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "发送好友请求"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemCreator.createItem(Material.BARRIER, "移除好友"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "接受好友请求"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemCreator.createItem(Material.BARRIER, "拒绝好友请求"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemCreator.createItem(Material.BARRIER, "玩家已经被请求"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemCreator.createItem(Material.ENDER_PEARL, "跳转到玩家的服务器"));

	}
}
