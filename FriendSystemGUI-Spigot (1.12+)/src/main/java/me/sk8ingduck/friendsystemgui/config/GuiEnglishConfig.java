package me.sk8ingduck.friendsystemgui.config;

import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import me.sk8ingduck.friendsystemgui.util.Skull;
import org.bukkit.Material;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GuiEnglishConfig extends GuiConfig {

	public GuiEnglishConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		onlinePlayerLore = Arrays.asList(" ", "&7Server:", "&e%SERVER%", " ", "&7Online since:", "&e%ONLINE_TIME%");
		onlinePlayerLore = (List<String>) getPathOrSet("gui.mainMenu.onlinePlayerLore", onlinePlayerLore);

		offlinePlayerLore = Arrays.asList(" ", "&7Last seen:", "&e%LAST_SEEN%");
		offlinePlayerLore = (List<String>) getPathOrSet("gui.mainMenu.offlineFriendLore", offlinePlayerLore);

		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Friends Menu");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "§9Friend Requests");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cSettings");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Selected player &7- &6%PLAYER%");

		items.put("guiItem", ItemUtil.createItem(Material.PLAYER_HEAD, "§aFriends Menu"));
		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("§ePrevious Page"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("§eNext Page"));
		items.put("gui.mainMenu.item.settings", ItemUtil.createItem(Material.REDSTONE_TORCH, "§cSettings"));
		items.put("gui.mainMenu.item.requests", ItemUtil.createItem(Material.WRITTEN_BOOK, "§bFriend Requests"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("§cBack"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemUtil.createItem(Material.TORCH, "§6Toggle friend requests"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemUtil.createItem(Material.OAK_DOOR, "§6Toggle Join/Leave notifications"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemUtil.createItem(Material.PAPER, "§6Toggle private messages"));
		items.put("gui.settingsMenu.item.toggleJump", ItemUtil.createItem(Material.ENDER_PEARL, "§6Toggle jumping"));

		items.put("gui.settingsMenu.item.invitesOn", ItemUtil.createItem(Material.BLACK_STAINED_GLASS_PANE, "§6Friend requests are now §aallowed"));
		items.put("gui.settingsMenu.item.invitesOff", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "§6Friend requests are now §cdisallowed"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemUtil.createItem(Material.BLACK_STAINED_GLASS_PANE, "§6Join/Leave notifications are now §aenabled"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "§6Join/Leave notifications are now §cdisabled"));
		items.put("gui.settingsMenu.item.msgsOn", ItemUtil.createItem(Material.BLACK_STAINED_GLASS_PANE, "&6Private messages are now &aallowed"));
		items.put("gui.settingsMenu.item.msgsOff", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "&6Private messages are now &cdisallowed"));
		items.put("gui.settingsMenu.item.jumpOn", ItemUtil.createItem(Material.BLACK_STAINED_GLASS_PANE, "&6Jumping is now &aallowed"));
		items.put("gui.settingsMenu.item.jumpOff", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "&6Jumping is now &cdisallowed"));

		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("&cBack"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemUtil.createItem(Material.SUNFLOWER, "&6Send friend request"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemUtil.createItem(Material.BARRIER, "&cRemove friend"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemUtil.createItem(Material.SUNFLOWER, "&6Accept friend request"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemUtil.createItem(Material.BARRIER, "&cDeny friend request"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemUtil.createItem(Material.BARRIER, "&cPlayer already requested"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemUtil.createItem(Material.ENDER_PEARL, "&cJump to player's server"));

		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("&ePrevious page"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("&eNext page"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("&cBack"));
	}
}
