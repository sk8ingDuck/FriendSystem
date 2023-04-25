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
		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Menu Amici");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "&9Richieste di amicizia");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cImpostazioni");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Giocatore selezionato &7- &6%PLAYER%");

		items.put("guiItem", SkullCreator.createPlayerSkull("&aMenu Amici"));
		items.put("gui.mainMenu.item.onlineFriend", SkullCreator.createPlayerSkull("&a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online da", "&e→ %ONLINE_TIME%", " ", "&7Stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.onlineFriendFavourite", SkullCreator.createPlayerSkull("&a&7[&c❤&7] &a%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Online da", "&e→ %ONLINE_TIME%", " ", "&7Stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriend", SkullCreator.createSkeletonSkull("&c%PLAYER%",
				" ", "&7Ultima visita", "&e→ %LAST_SEEN%", " ", "&7Stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriendFavourite", SkullCreator.createSkeletonSkull("&7[&c❤&7] &c%PLAYER%",
				" ", "&7Ultima visita", "&e→ %LAST_SEEN%", " ", "&7Stato", "&e→ %STATUS%"));

		items.put("gui.mainMenu.item.ownInfo", SkullCreator.createPlayerSkull("&6%PLAYER%",
				" ", "&7Server", "&e→ %SERVER%", " ", "&7Sei online da", "&e→ %ONLINE_TIME%", " ", "&7Il tuo stato", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("&ePagina precedente"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("&ePagina successiva"));
		items.put("gui.mainMenu.item.settings", ItemCreator.createItem(Material.REDSTONE, "&cImpostazioni"));
		items.put("gui.mainMenu.item.requests", ItemCreator.createItem(Material.WRITTEN_BOOK, "&bRichieste di amicizia"));

		items.put("gui.requestsMenu.item.request", SkullCreator.createSkeletonSkull( "&7%PLAYER%"));
		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("&ePagina precedente"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("&ePagina successiva"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("&cIndietro"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("&cIndietro"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemCreator.createItem(Material.TORCH, "&6Abilita/Disabilita richieste di amicizia"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemCreator.createItem(Material.WOOD_DOOR, "&6Abilita/Disabilita notifiche di entrata/uscita"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemCreator.createItem(Material.PAPER, "&6Abilita/Disabilita messaggi privati"));
		items.put("gui.settingsMenu.item.toggleJump", ItemCreator.createItem(Material.ENDER_PEARL, "&6Abilita/Disabilita salti"));

		items.put("gui.settingsMenu.item.invitesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Le richieste di amicizia sono ora §aconsentite"));
		items.put("gui.settingsMenu.item.invitesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Le richieste di amicizia sono ora §cnon consentite"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemCreator.createGlassPane(DyeColor.GREEN,  "§6Le notifiche di entrata/uscita sono ora §aabilitate"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Le notifiche di entrata/uscita sono ora §cdisabilitate"));
		items.put("gui.settingsMenu.item.msgsOn", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6I messaggi privati sono ora &aconsentiti"));
		items.put("gui.settingsMenu.item.msgsOff", ItemCreator.createGlassPane(DyeColor.RED, "&6I messaggi privati sono ora &cnon consentiti"));
		items.put("gui.settingsMenu.item.jumpOn", ItemCreator.createGlassPane(DyeColor.GREEN,  "&6I salti sono ora &aconsentiti"));
		items.put("gui.settingsMenu.item.jumpOff", ItemCreator.createGlassPane(DyeColor.RED, "&6I salti sono ora &cnon consentiti"));

		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("&cIndietro"));
		items.put("gui.selectedPlayerMenu.item.addFavourite", Skull.HEART.getSkull("&aAggiungi ai preferiti"));
		items.put("gui.selectedPlayerMenu.item.removeFavourite", Skull.CROSS.getSkull("&cRimuovi dai preferiti"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Invia richiesta di amicizia"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemCreator.createItem(Material.BARRIER, "&cRimuovi amico"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Accetta richiesta di amicizia"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemCreator.createItem(Material.BARRIER, "&cRifiuta richiesta di amicizia"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemCreator.createItem(Material.BARRIER, "&cGiocatore già richiesto"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemCreator.createItem(Material.ENDER_PEARL, "&cSalta al server del giocatore"));

	}

}
