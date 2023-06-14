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

		FriendSystemGUI.getInstance().getPluginMessaging().getSettings(player, (invites, notifies, msgs, jump) ->
				RyseInventory.builder()
						.title(guiConfig.getSettingsGuiTitle())
						.rows(4)
						.provider(new InventoryProvider() {
							@Override
							public void init(Player player, InventoryContents contents) {
								for (int i = 0; i < 36; i++)
									contents.set(i, ItemCreator.createGlassPane(DyeColor.BLACK, " "));

								contents.set(1, 1, IntelligentItem.of(guiConfig.getItem("settingsMenu.back"),
										clickEvent -> GuiManager.guiMainMenu.open(player)));

								contents.set(1, 3, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleInvites"),
										clickEvent -> {
											player.closeInventory();
											FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "toggleinvites");
										}));

								contents.set(1, 4, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleNotifies"),
										clickEvent -> {
											player.closeInventory();
											FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "togglenotifies");
										}));

								contents.set(1, 5, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleMsgs"),
										clickEvent -> {
											player.closeInventory();
											FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "togglemsgs");
										}));

								contents.set(1, 6, IntelligentItem.of(guiConfig.getItem("settingsMenu.toggleJump"),
										clickEvent -> {
											player.closeInventory();
											FriendSystemGUI.getInstance().getPluginMessaging().toggleOption(player, "togglejump");
										}));
								contents.set(1, 7, IntelligentItem.of(guiConfig.getItem("settingsMenu.changeStatus"),
										clickEvent -> {
											player.closeInventory();
											GuiManager.guiChangeStatus.open(player);
										}));

								contents.set(2, 3, guiConfig.getItem("settingsMenu.invites" + (invites ? "On" : "Off")));
								contents.set(2, 4, guiConfig.getItem("settingsMenu.notifies" + (notifies ? "On" : "Off")));
								contents.set(2, 5, guiConfig.getItem("settingsMenu.msgs" + (msgs ? "On" : "Off")));
								contents.set(2, 6, guiConfig.getItem("settingsMenu.jump" + (jump ? "On" : "Off")));
							}
						})
						.build(FriendSystemGUI.getInstance())
						.open(player)
		);
	}
}
