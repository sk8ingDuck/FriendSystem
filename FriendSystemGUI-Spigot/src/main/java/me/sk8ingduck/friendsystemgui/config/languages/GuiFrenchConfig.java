package me.sk8ingduck.friendsystemgui.config.languages;

import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.Skull;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.io.File;

public class GuiFrenchConfig extends GuiConfig {

	public GuiFrenchConfig(String name, File path) {
		super(name, path);
	}

	@Override
	public void loadValues() {
		mainGuiTitle = (String) getPathOrSet("gui.mainMenu.title", "&9Menu des amis");
		requestsGuiTitle = (String) getPathOrSet("gui.requestsMenu.title", "&9Demandes d'amis");
		settingsGuiTitle = (String) getPathOrSet("gui.settingsMenu.title", "&cParamètres");
		selectedPlayerGuiTitle = (String) getPathOrSet("gui.selectedPlayerMenu.title", "&6Joueur sélectionné &7- &6%PLAYER%");

		items.put("guiItem", SkullCreator.createPlayerSkull("&aMenu des amis"));
		items.put("gui.mainMenu.item.onlineFriend", SkullCreator.createPlayerSkull("&a%PLAYER%",
				" ", "&7Serveur", "&e→ %SERVER%", " ", "&7En ligne depuis", "&e→ %ONLINE_TIME%", " ", "&7Statut", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.onlineFriendFavourite", SkullCreator.createPlayerSkull("&a&7[&c❤&7] &a%PLAYER%",
				" ", "&7Serveur", "&e→ %SERVER%", " ", "&7En ligne depuis", "&e→ %ONLINE_TIME%", " ", "&7Statut", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriend", SkullCreator.createSkeletonSkull("&c%PLAYER%",
				" ", "&7Vu pour la dernière fois", "&e→ %LAST_SEEN%", " ", "&7Statut", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.offlineFriendFavourite", SkullCreator.createSkeletonSkull("&7[&c❤&7] &c%PLAYER%",
				" ", "&7Vu pour la dernière fois", "&e→ %LAST_SEEN%", " ", "&7Statut", "&e→ %STATUS%"));

		items.put("gui.mainMenu.item.ownInfo", SkullCreator.createPlayerSkull("&6%PLAYER%",
				" ", "&7Serveur", "&e→ %SERVER%", " ", "&7Tu est en ligne depuis", "&e→ %ONLINE_TIME%", " ", "&7Ton Statut", "&e→ %STATUS%"));
		items.put("gui.mainMenu.item.previousPage", Skull.LEFT.getSkull("&ePage précédente"));
		items.put("gui.mainMenu.item.nextPage", Skull.RIGHT.getSkull("&ePage suivante"));
		items.put("gui.mainMenu.item.settings", ItemCreator.createItem(Material.REDSTONE, "&cParamètres"));
		items.put("gui.mainMenu.item.requests", ItemCreator.createItem(Material.WRITTEN_BOOK, "&bDemandes d'amis"));

		items.put("gui.requestsMenu.item.request", SkullCreator.createSkeletonSkull( "&7%PLAYER%"));
		items.put("gui.requestsMenu.item.previousPage", Skull.LEFT.getSkull("&ePage précédente"));
		items.put("gui.requestsMenu.item.nextPage", Skull.RIGHT.getSkull("&ePage suivante"));
		items.put("gui.requestsMenu.item.back", Skull.BACK.getSkull("&cRetour"));

		items.put("gui.settingsMenu.item.back", Skull.BACK.getSkull("&cRetour"));
		items.put("gui.settingsMenu.item.toggleInvites", ItemCreator.createItem(Material.TORCH, "&6Basculer les demandes d'amis"));
		items.put("gui.settingsMenu.item.toggleNotifies", ItemCreator.createItem(Material.WOOD_DOOR, "&6Basculer les notifications d'entrée/sortie"));
		items.put("gui.settingsMenu.item.toggleMsgs", ItemCreator.createItem(Material.PAPER, "&6Basculer les messages privés"));
		items.put("gui.settingsMenu.item.toggleJump", ItemCreator.createItem(Material.ENDER_PEARL, "&6Basculer les sauts"));

		items.put("gui.settingsMenu.item.invitesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Les demandes d'amis sont maintenant §aautorisées"));
		items.put("gui.settingsMenu.item.invitesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Les demandes d'amis sont maintenant §cinterdites"));
		items.put("gui.settingsMenu.item.notifiesOn", ItemCreator.createGlassPane(DyeColor.GREEN, "§6Les notifications d'entrée/sortie sont maintenant §aactivées"));
		items.put("gui.settingsMenu.item.notifiesOff", ItemCreator.createGlassPane(DyeColor.RED, "§6Les notifications d'entrée/sortie sont maintenant §cdésactivées"));
		items.put("gui.settingsMenu.item.msgsOn", ItemCreator.createGlassPane(DyeColor.GREEN, "&6Les messages privés sont maintenant &aautorisés"));
		items.put("gui.settingsMenu.item.msgsOff", ItemCreator.createGlassPane(DyeColor.RED, "&6Les messages privés sont maintenant &cinterdits"));
		items.put("gui.settingsMenu.item.jumpOn", ItemCreator.createGlassPane(DyeColor.GREEN, "&6Les sauts sont maintenant &aautorisés"));
		items.put("gui.settingsMenu.item.jumpOff", ItemCreator.createGlassPane(DyeColor.RED, "&6Les sauts sont maintenant &cinterdits"));

		items.put("gui.selectedPlayerMenu.item.back", Skull.BACK.getSkull("&cRetour"));
		items.put("gui.selectedPlayerMenu.item.addFavourite", Skull.HEART.getSkull("&aAjouter aux favoris"));
		items.put("gui.selectedPlayerMenu.item.removeFavourite", Skull.CROSS.getSkull("&cRetirer des favoris"));
		items.put("gui.selectedPlayerMenu.item.addFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Envoyer une demande d'ami"));
		items.put("gui.selectedPlayerMenu.item.removeFriend", ItemCreator.createItem(Material.BARRIER, "&cSupprimer l'ami"));
		items.put("gui.selectedPlayerMenu.item.acceptFriend", ItemCreator.createItem(Material.YELLOW_FLOWER, "&6Accepter la demande d'ami"));
		items.put("gui.selectedPlayerMenu.item.denyFriend", ItemCreator.createItem(Material.BARRIER, "&cRefuser la demande d'ami"));
		items.put("gui.selectedPlayerMenu.item.alreadyRequested", ItemCreator.createItem(Material.BARRIER, "&cJoueur déjà demandé"));
		items.put("gui.selectedPlayerMenu.item.jump", ItemCreator.createItem(Material.ENDER_PEARL, "&cAller sur le serveur du joueur"));
	}
}
