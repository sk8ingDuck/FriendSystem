package me.sk8ingduck.friendsystemgui.config.languages;

import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.Skull;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.io.File;

public class GuiItalianConfig extends GuiConfig {

	public GuiItalianConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		items.put("guiItem", SkullCreator.createPlayerSkull("&aMenu Amici"));

		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Menu Amici");
		mainGuiRows = (int) getPathOrSet("gui.mainMenu.rows", 6);

		itemSlots.put("gui.mainMenu.friends.startSlot", 0);
		itemSlots.put("gui.mainMenu.friends.endSlot", 36);
		items.put("gui.mainMenu.friends.onlineFriend", SkullCreator.createPlayerSkull("&a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online da", "&e→ %ONLINE_TIME%", " ", "&7Stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.friends.onlineFriendFavourite", SkullCreator.createPlayerSkull("&a&7[&c❤&7] &a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online da", "&e→ %ONLINE_TIME%", " ", "&7Stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.friends.offlineFriend", SkullCreator.createSkeletonSkull("&c%PLAYER%",
				" ", "&7Ultima visita", "&e→ %LAST_SEEN%", " ", "&7Stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.friends.offlineFriendFavourite", SkullCreator.createSkeletonSkull("&7[&c❤&7] &c%PLAYER%",
				" ", "&7Ultima visita", "&e→ %LAST_SEEN%", " ", "&7Stato", "&e→ %STATUS%"));

		items.put("gui.mainMenu.ownInfo.item", SkullCreator.createPlayerSkull("&6%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Sei online da", "&e→ %ONLINE_TIME%", " ", "&7Il tuo stato", "&e→ %STATUS%"));
		itemSlots.put("gui.mainMenu.ownInfo.slot", 49);
		items.put("gui.mainMenu.settings.item", ItemCreator.createItem(Material.REDSTONE, "&cImpostazioni"));
		itemSlots.put("gui.mainMenu.settings.slot", 43);
		items.put("gui.mainMenu.requests.item", ItemCreator.createItem(Material.WRITTEN_BOOK, "&bRichieste di amicizia"));
		itemSlots.put("gui.mainMenu.requests.slot", 47);
		items.put("gui.mainMenu.previousPage.item", Skull.LEFT.getSkull("&ePagina precedente"));
		itemSlots.put("gui.mainMenu.previousPage.slot", 45);
		items.put("gui.mainMenu.nextPage.item", Skull.RIGHT.getSkull("&ePagina successiva"));
		itemSlots.put("gui.mainMenu.nextPage.slot", 53);

		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "&9Richieste di amicizia");
		requestsGuiRows = (int) getPathOrSet("gui.requestsMenu.size", 6);
		itemSlots.put("gui.requestsMenu.requests.startSlot", 0);
		itemSlots.put("gui.requestsMenu.requests.endSlot", 36);
		items.put("gui.requestsMenu.requests.item", SkullCreator.createSkeletonSkull( "&7%PLAYER%", " ",
				"&7Richiesta da", "&e→ %REQUEST_DATE%", " ", "&7Scade tra:", "&e→ %EXPIRES_IN%"));

		itemSlots.put("gui.requestsMenu.previousPage.slot", 45);
		items.put("gui.requestsMenu.previousPage.item", Skull.LEFT.getSkull("&ePagina precedente"));
		items.put("gui.requestsMenu.nextPage.item", Skull.RIGHT.getSkull("&ePagina successiva"));
		itemSlots.put("gui.requestsMenu.nextPage.slot", 53);
		items.put("gui.requestsMenu.back.item", Skull.BACK.getSkull("&cIndietro"));
		itemSlots.put("gui.requestsMenu.back.slot", 49);

		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cImpostazioni");
		settingsGuiRows = (int) getPathOrSet("gui.settingsMenu.size", 4);
		items.put("gui.settingsMenu.toggleInvites.item", ItemCreator.createItem(Material.TORCH, "&6Abilita/Disabilita richieste di amicizia"));
		itemSlots.put("gui.settingsMenu.toggleInvites.slot", 11);
		items.put("gui.settingsMenu.toggleNotifies.item", ItemCreator.createItem(Material.WOOD_DOOR, "&6Abilita/Disabilita notifiche di entrata/uscita"));
		itemSlots.put("gui.settingsMenu.toggleNotifies.slot", 12);
		items.put("gui.settingsMenu.toggleMsgs.item", ItemCreator.createItem(Material.PAPER, "&6Abilita/Disabilita messaggi privati"));
		itemSlots.put("gui.settingsMenu.toggleMsgs.slot", 13);
		items.put("gui.settingsMenu.toggleJump.item", ItemCreator.createItem(Material.ENDER_PEARL, "&6Abilita/Disabilita salti"));
		itemSlots.put("gui.settingsMenu.toggleJump.slot", 14);
		items.put("gui.settingsMenu.changeStatus.item", ItemCreator.createItem(Material.ANVIL, "&6Cambiare stato"));
		itemSlots.put("gui.settingsMenu.changeStatus.slot", 15);

		items.put("gui.settingsMenu.invitesOn.item", ItemCreator.createGlassPane(DyeColor.GREEN, "&6Le richieste di amicizia sono ora &aconsentite"));
		itemSlots.put("gui.settingsMenu.invitesOn.slot", 20);
		items.put("gui.settingsMenu.invitesOff.item", ItemCreator.createGlassPane(DyeColor.RED, "&6Le richieste di amicizia sono ora &cnon consentite"));
		itemSlots.put("gui.settingsMenu.invitesOff.slot", 20);
		items.put("gui.settingsMenu.notifiesOn.item", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6Le notifiche di entrata/uscita sono ora &aabilitate"));
		itemSlots.put("gui.settingsMenu.notifiesOn.slot", 21);
		items.put("gui.settingsMenu.notifiesOff.item", ItemCreator.createGlassPane(DyeColor.RED, "&6Le notifiche di entrata/uscita sono ora &cdisabilitate"));
		itemSlots.put("gui.settingsMenu.notifiesOff.slot", 21);
		items.put("gui.settingsMenu.msgsOn.item", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6I messaggi privati sono ora &aconsentiti"));
		itemSlots.put("gui.settingsMenu.msgsOn.slot", 22);
		items.put("gui.settingsMenu.msgsOff.item", ItemCreator.createGlassPane(DyeColor.RED, "&6I messaggi privati sono ora &cnon consentiti"));
		itemSlots.put("gui.settingsMenu.msgsOff.slot", 22);
		items.put("gui.settingsMenu.jumpOn.item", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6I salti sono ora &aconsentiti"));
		itemSlots.put("gui.settingsMenu.jumpOn.slot", 23);
		items.put("gui.settingsMenu.jumpOff.item", ItemCreator.createGlassPane(DyeColor.RED, "&6I salti sono ora &cnon consentiti"));
		itemSlots.put("gui.settingsMenu.jumpOff.slot", 23);
		items.put("gui.settingsMenu.back.item", Skull.BACK.getSkull("&cIndietro"));
		itemSlots.put("gui.settingsMenu.back.slot", 0);

		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Giocatore selezionato &7- &6%PLAYER%");
		selectedPlayerGuiRows = (int) getPathOrSet("gui.selectedPlayerMenu.rows", 1);
		items.put("gui.selectedPlayerMenu.addFavourite.item", Skull.HEART.getSkull("&aAggiungi ai preferiti"));
		itemSlots.put("gui.selectedPlayerMenu.addFavourite.slot", 5);
		items.put("gui.selectedPlayerMenu.removeFavourite.item", Skull.CROSS.getSkull("&cRimuovi dai preferiti"));
		itemSlots.put("gui.selectedPlayerMenu.removeFavourite.slot", 5);
		items.put("gui.selectedPlayerMenu.addFriend.item", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Invia richiesta di amicizia"));
		itemSlots.put("gui.selectedPlayerMenu.addFriend.slot", 4);
		items.put("gui.selectedPlayerMenu.removeFriend.item", ItemCreator.createItem(Material.BARRIER, "&cRimuovi amico"));
		itemSlots.put("gui.selectedPlayerMenu.removeFriend.slot", 8);
		items.put("gui.selectedPlayerMenu.acceptFriend.item", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Accetta richiesta di amicizia"));
		itemSlots.put("gui.selectedPlayerMenu.acceptFriend.slot", 3);
		items.put("gui.selectedPlayerMenu.denyFriend.item", ItemCreator.createItem(Material.BARRIER, "&cRifiuta richiesta di amicizia"));
		itemSlots.put("gui.selectedPlayerMenu.denyFriend.slot", 5);
		items.put("gui.selectedPlayerMenu.alreadyRequested.item", ItemCreator.createItem(Material.BARRIER, "&cGiocatore già richiesto"));
		itemSlots.put("gui.selectedPlayerMenu.alreadyRequested.slot", 4);
		items.put("gui.selectedPlayerMenu.jump.item", ItemCreator.createItem(Material.ENDER_PEARL, "&cSalta al server del giocatore"));
		itemSlots.put("gui.selectedPlayerMenu.jump.slot", 3);
		items.put("gui.selectedPlayerMenu.back.item", Skull.BACK.getSkull("&cIndietro"));
		itemSlots.put("gui.selectedPlayerMenu.back.slot", 0);

	}
}
