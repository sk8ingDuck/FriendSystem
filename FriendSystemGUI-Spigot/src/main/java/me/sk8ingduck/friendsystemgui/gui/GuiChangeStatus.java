package me.sk8ingduck.friendsystemgui.gui;

import io.github.rysefoxx.inventory.anvilgui.AnvilGUI;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.enums.InventoryOpenerType;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;

public class GuiChangeStatus {

	public void open(Player player) {

		FriendSystemGUI.getInstance().getPluginMessaging().getStatus(player,
				status ->
						RyseInventory.builder()
								.title("change status")
								.type(InventoryOpenerType.ANVIL)
								.provider(new InventoryProvider() {
									@Override
									public void anvil(Player player, AnvilGUI.Builder anvil) {
										anvil.itemLeft(ItemCreator.createItem(Material.PAPER, status));
										anvil.onClick((completion, stateSnapshot) -> {
											FriendSystemGUI.getInstance().getPluginMessaging()
													.sendCommand(player, "status", completion.toString());
											return Collections.singletonList(AnvilGUI.ResponseAction.replaceInputText("Status changed!"));
										});
									}
								})
								.build(FriendSystemGUI.getInstance())
								.open(player));

	}

}
