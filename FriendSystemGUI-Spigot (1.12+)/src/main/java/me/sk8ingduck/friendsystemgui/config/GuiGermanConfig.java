package me.sk8ingduck.friendsystemgui.config;

import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import me.sk8ingduck.friendsystemgui.util.Skull;
import org.bukkit.Material;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GuiGermanConfig  extends GuiConfig {

	public GuiGermanConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		onlinePlayerLore = Arrays.asList(" ", "&7Online auf", "&e%SERVER%", " ", "&7Online seit", "&e%ONLINE_TIME%");
		onlinePlayerLore = (List<String>) getPathOrSet("gui.mainMenu.onlinePlayerLore", onlinePlayerLore);

		offlinePlayerLore = Arrays.asList(" ", "&7Zuletzt online vor", "&e%LAST_SEEN%");
		offlinePlayerLore = (List<String>) getPathOrSet("gui.mainMenu.offlineFriendLore", offlinePlayerLore);

		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Freunde Menü");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "§9Freundschaftsanfragen");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cEinstellungen");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Spieler ausgewählt &7- &6%PLAYER%");

		items.put("guiItem", ItemUtil.createItem(Material.PLAYER_HEAD, "§aFreunde Menü"));
		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("§eNach links"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("§eNach rechts"));
		items.put("gui.mainMenu.item.settings", ItemUtil.createItem(Material.REDSTONE_TORCH, "§cEinstellungen"));
		items.put("gui.mainMenu.item.requests", ItemUtil.createItem(Material.WRITTEN_BOOK, "§bFreundschaftsanfragen"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("§cZurück"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemUtil.createItem(Material.TORCH, "§6Freundesanfragen aktivieren / deaktiveren"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemUtil.createItem(Material.OAK_DOOR, "§6Join/Leave Nachrichten aktivieren / deaktiveren"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemUtil.createItem(Material.PAPER, "§6Nachrichten aktivieren / deaktiveren"));
		items.put("gui.settingsMenu.item.toggleJump", ItemUtil.createItem(Material.ENDER_PEARL, "§6Nachspringen aktivieren / deaktiveren"));

		items.put("gui.settingsMenu.item.invitesOn", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "§6Freundesanfragen sind §aaktiviert"));
		items.put("gui.settingsMenu.item.invitesOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Freundesanfragen sind §cdeaktiviert"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "§6Join/Leave Nachrichten sind §aaktiviert"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Join/Leave Nachrichten sind §cdeaktiviert"));
		items.put("gui.settingsMenu.item.msgsOn", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "§6Nachrichten sind §aaktiviert"));
		items.put("gui.settingsMenu.item.msgsOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Nachrichten sind §cdeaktiviert"));
		items.put("gui.settingsMenu.item.jumpOn", ItemUtil.createItem(Material.GREEN_STAINED_GLASS_PANE, "§6Nachspringen ist §aaktiviert"));
		items.put("gui.settingsMenu.item.jumpOff", ItemUtil.createItem(Material.RED_STAINED_GLASS_PANE, "§6Nachspringen ist §cdeaktiviert"));

		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("§cZurück"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemUtil.createItem(Material.SUNFLOWER, "§6Freundschaftsanfrage senden"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemUtil.createItem(Material.BARRIER, "§cFreund entfernen"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemUtil.createItem(Material.SUNFLOWER, "§6Freundschaftsanfrage §aakzeptieren"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemUtil.createItem(Material.BARRIER, "§cFreundschaftsanfrage §cablehnen"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemUtil.createItem(Material.BARRIER, "§cSpieler bereits angefragt"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemUtil.createItem(Material.ENDER_PEARL, "§cSpieler nachspringen"));

		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("§eNach links"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("§eNach rechts"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("§cZurück"));
	}
}