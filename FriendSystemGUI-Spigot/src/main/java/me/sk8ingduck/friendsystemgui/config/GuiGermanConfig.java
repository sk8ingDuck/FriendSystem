package me.sk8ingduck.friendsystemgui.config;

import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.Skull;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.io.File;

public class GuiGermanConfig extends GuiConfig {

	public GuiGermanConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Freunde Menü");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "&9Freundschaftsanfragen");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cEinstellungen");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Spieler ausgewählt &7- &6%PLAYER%");

		items.put("guiItem", SkullCreator.createPlayerSkull("&aFreunde Menü"));
		items.put("gui.mainMenu.item.onlineFriend", SkullCreator.createPlayerSkull("&a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online seit", "&e→ %ONLINE_TIME%", " ", "&7Status", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.onlineFriendFavourite", SkullCreator.createPlayerSkull("&a&7[&c❤&7] &a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online seit", "&e→ %ONLINE_TIME%", " ", "&7Status", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriend", SkullCreator.createSkeletonSkull("&c%PLAYER%",
				" ", "&7Offline seit", "&e→ %LAST_SEEN%", " ", "&7Status", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriendFavourite", SkullCreator.createSkeletonSkull("&7[&c❤&7] &c%PLAYER%",
				" ", "&7Offline seit", "&e→ %LAST_SEEN%", " ", "&7Status", "&e→ %STATUS%"));

		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("&eNach links"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("&eNach rechts"));
		items.put("gui.mainMenu.item.settings", ItemCreator.createItem(Material.REDSTONE, "&cEinstellungen"));
		items.put("gui.mainMenu.item.requests", ItemCreator.createItem(Material.WRITTEN_BOOK, "&bFreundschaftsanfragen"));

		items.put("gui.requestsMenu.item.request", SkullCreator.createSkeletonSkull("&7%PLAYER%"));
		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("&eNach links"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("&eNach rechts"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("&cZurück"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("&cZurück"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemCreator.createItem(Material.TORCH, "&6Freundesanfragen aktivieren / deaktiveren"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemCreator.createItem(Material.WOOD_DOOR, "&6Join/Leave Nachrichten aktivieren / deaktiveren"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemCreator.createItem(Material.PAPER, "&6Nachrichten aktivieren / deaktiveren"));
		items.put("gui.settingsMenu.item.toggleJump", ItemCreator.createItem(Material.ENDER_PEARL, "&6Nachspringen aktivieren / deaktiveren"));

		items.put("gui.settingsMenu.item.invitesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Freundesanfragen sind §aaktiviert"));
		items.put("gui.settingsMenu.item.invitesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Freundesanfragen sind §cdeaktiviert"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Join/Leave Nachrichten sind §aaktiviert"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemCreator.createGlassPane(DyeColor.RED,"§6Join/Leave Nachrichten sind §cdeaktiviert"));
		items.put("gui.settingsMenu.item.msgsOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Nachrichten sind §aaktiviert"));
		items.put("gui.settingsMenu.item.msgsOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Nachrichten sind §cdeaktiviert"));
		items.put("gui.settingsMenu.item.jumpOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Nachspringen ist §aaktiviert"));
		items.put("gui.settingsMenu.item.jumpOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Nachspringen ist §cdeaktiviert"));


		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("&cZurück"));
		items.put("gui.selectedPlayerMenu.item.addFavourite", Skull.HEART.getSkull("&aAls Favorit hinzufügen"));
		items.put("gui.selectedPlayerMenu.item.removeFavourite", Skull.CROSS.getSkull("&cVon Favoriten entfernen"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Freundschaftsanfrage senden"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemCreator.createItem(Material.BARRIER, "&cFreund entfernen"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Freundschaftsanfrage &aakzeptieren"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemCreator.createItem(Material.BARRIER, "&cFreundschaftsanfrage &cablehnen"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemCreator.createItem(Material.BARRIER, "&cSpieler bereits angefragt"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemCreator.createItem(Material.ENDER_PEARL, "&cSpieler nachspringen"));
	}
}