package me.sk8ingduck.friendsystemgui.config;

import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.Skull;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.io.File;

public class GuiEnglishConfig extends GuiConfig {

	public GuiEnglishConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Friends Menu");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "&9Friend Requests");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cSettings");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Selected player &7- &6%PLAYER%");

		items.put("guiItem", SkullCreator.createPlayerSkull("&aFriends Menu"));
		items.put("gui.mainMenu.item.onlineFriend", SkullCreator.createPlayerSkull("&a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online since", "&e→ %ONLINE_TIME%", " ", "&7Status", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.onlineFriendFavourite", SkullCreator.createPlayerSkull("&a&7[&c❤&7] &a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online since", "&e→ %ONLINE_TIME%", " ", "&7Status", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriend", SkullCreator.createSkeletonSkull("&c%PLAYER%",
				" ", "&7Last seen", "&e→ %LAST_SEEN%", " ", "&7Status", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriendFavourite", SkullCreator.createSkeletonSkull("&7[&c❤&7] &c%PLAYER%",
				" ", "&7Last seen", "&e→ %LAST_SEEN%", " ", "&7Status", "&e→ %STATUS%"));

		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("&ePrevious Page"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("&eNext Page"));
		items.put("gui.mainMenu.item.settings", ItemCreator.createItem(Material.REDSTONE, "&cSettings"));
		items.put("gui.mainMenu.item.requests", ItemCreator.createItem(Material.WRITTEN_BOOK, "&bFriend Requests"));

		items.put("gui.requestsMenu.item.request", SkullCreator.createPlayerSkull( "&7%PLAYER%"));
		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("&ePrevious page"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("&eNext page"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("&cBack"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("&cBack"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemCreator.createItem(Material.TORCH, "&6Toggle friend requests"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemCreator.createItem(Material.WOOD_DOOR, "&6Toggle Join/Leave notifications"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemCreator.createItem(Material.PAPER, "&6Toggle private messages"));
		items.put("gui.settingsMenu.item.toggleJump", ItemCreator.createItem(Material.ENDER_PEARL, "&6Toggle jumping"));

		items.put("gui.settingsMenu.item.invitesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Friend requests are now §aallowed"));
		items.put("gui.settingsMenu.item.invitesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Friend requests are now §cdisallowed"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemCreator.createGlassPane(DyeColor.GREEN,  "§6Join/Leave notifications are now §aenabled"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Join/Leave notifications are now §cdisabled"));
		items.put("gui.settingsMenu.item.msgsOn", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6Private messages are now &aallowed"));
		items.put("gui.settingsMenu.item.msgsOff", ItemCreator.createGlassPane(DyeColor.RED, "&6Private messages are now &cdisallowed"));
		items.put("gui.settingsMenu.item.jumpOn", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6Jumping is now &aallowed"));
		items.put("gui.settingsMenu.item.jumpOff", ItemCreator.createGlassPane(DyeColor.RED, "&6Jumping is now &cdisallowed"));

		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("&cBack"));
		items.put("gui.selectedPlayerMenu.item.addFavourite", Skull.HEART.getSkull("&aAdd as favourite"));
		items.put("gui.selectedPlayerMenu.item.removeFavourite", Skull.CROSS.getSkull("&cRemove from favourites"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemCreator.createItem(Material.GREEN_RECORD, "&6Send friend request"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemCreator.createItem(Material.BARRIER, "&cRemove friend"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemCreator.createItem(Material.GREEN_RECORD, "&6Accept friend request"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemCreator.createItem(Material.BARRIER, "&cDeny friend request"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemCreator.createItem(Material.BARRIER, "&cPlayer already requested"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemCreator.createItem(Material.ENDER_PEARL, "&cJump to player's server"));

	}
}
