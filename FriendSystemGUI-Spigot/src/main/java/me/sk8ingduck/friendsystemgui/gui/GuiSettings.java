package me.sk8ingduck.friendsystemgui.gui;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

public class GuiSettings {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
		int rows = guiConfig.getSettingsGuiRows();

		FriendSystemGUI.getInstance().getPluginMessaging().getSettings(player, (invites, notifies, msgs, jump) ->
				RyseInventory.builder()
						.title(guiConfig.getSettingsGuiTitle())
						.rows(rows)
						.provider(new InventoryProvider() {
							@Override
							public void init(Player player, InventoryContents contents) {

								for (int i = 0; i < rows * 9; i++) {
									contents.set(i, guiConfig.getItem("settingsMenu.backgroundItem", false));
								}

								int backSlot = guiConfig.getSlot("settingsMenu.back");
								if (backSlot != -1) {
									contents.set(backSlot, IntelligentItem.of(guiConfig.getItem("settingsMenu.back"),
											clickEvent -> GuiManager.guiMainMenu.open(player)));
								}

								int toggleInvitesSlot = guiConfig.getSlot("settingsMenu.toggleInvites");
								if (toggleInvitesSlot != -1) {
									contents.set(toggleInvitesSlot, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleInvites"),
											clickEvent -> {
												player.closeInventory();
												FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "toggleinvites");
											}));
								}

								int toggleNotifiesSlot = guiConfig.getSlot("settingsMenu.toggleNotifies");
								if (toggleNotifiesSlot != -1) {
									contents.set(toggleNotifiesSlot, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleNotifies"),
											clickEvent -> {
												player.closeInventory();
												FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "togglenotifies");
											}));
								}

								int toggleMsgsSlot = guiConfig.getSlot("settingsMenu.toggleMsgs");
								if (toggleMsgsSlot != -1) {
									contents.set(toggleMsgsSlot, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleMsgs"),
											clickEvent -> {
												player.closeInventory();
												FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "togglemsgs");
											}));
								}

								int toggleJumpSlot = guiConfig.getSlot("settingsMenu.toggleJump");
								if (toggleJumpSlot != -1) {
									contents.set(toggleJumpSlot, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleJump"),
											clickEvent -> {
												player.closeInventory();
												FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "togglejump");
											}));
								}

								int changeStatusSlot = guiConfig.getSlot("settingsMenu.changeStatus");
								if (changeStatusSlot != -1) {
									contents.set(changeStatusSlot, IntelligentItem.of(guiConfig.getItem("settingsMenu.changeStatus"),
											clickEvent -> {
												player.closeInventory();
												GuiManager.guiChangeStatus.open(player);
											}));
								}

								int invitesSlot = guiConfig.getSlot("settingsMenu.invites" + (invites ? "On" : "Off"));
								if (invitesSlot != -1) {
									contents.set(invitesSlot, guiConfig.getItem("settingsMenu.invites" + (invites ? "On" : "Off")));
								}

								int notifiesSlot = guiConfig.getSlot("settingsMenu.notifies" + (notifies ? "On" : "Off"));
								if (notifiesSlot != -1) {
									contents.set(notifiesSlot, guiConfig.getItem("settingsMenu.notifies" + (notifies ? "On" : "Off")));
								}

								int msgsSlot = guiConfig.getSlot("settingsMenu.msgs" + (msgs ? "On" : "Off"));
								if (msgsSlot != -1) {
									contents.set(msgsSlot, guiConfig.getItem("settingsMenu.msgs" + (msgs ? "On" : "Off")));
								}

								int jumpSlot = guiConfig.getSlot("settingsMenu.jump" + (jump ? "On" : "Off"));
								if (jumpSlot != -1) {
									contents.set(jumpSlot, guiConfig.getItem("settingsMenu.jump" + (jump ? "On" : "Off")));
								}
							}
						})
						.build(FriendSystemGUI.getInstance())
						.open(player)
		);
	}
}
