package me.sk8ingduck.friendsystemgui.gui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

import java.util.UUID;

public class GuiSelectedPlayer {

	public void open(Player player, UUID selectedPlayer, String playerName) {
		GuiConfig guiConfig = me.sk8ingduck.friendsystemgui.FriendSystemGUI.getInstance().getGuiConfig();

		ChestMenu gui = ChestMenu.builder(1).title(guiConfig.getSelectedPlayerGuiTitle()
				.replaceAll("%PLAYER%", playerName)).build();

		for (int i = 0; i < 9; i++)
			gui.getSlot(i).setItem(ItemUtil.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));

		Slot back = gui.getSlot(0);
		Slot jump = gui.getSlot(8);
		back.setItem(guiConfig.get("gui.selectedPlayerMenu.item.back"));
		jump.setItem(guiConfig.get("gui.selectedPlayerMenu.item.jump"));

		back.setClickHandler((player1, clickInformation) -> GuiManager.guiMainMenu.open(player1));
		jump.setClickHandler((player1, clickInformation) -> sendCommand(player1, "jump", playerName));

		Slot acceptRequest = gui.getSlot(3);
		Slot addFriend = gui.getSlot(4);
		Slot removeFriend = gui.getSlot(4);
		Slot alreadyRequested = gui.getSlot(4);
		Slot denyRequest = gui.getSlot(5);
		FriendSystemGUI.getInstance().getPluginMessaging().getPlayerInfo(player, selectedPlayer,
				(areFriends, hasIncomingRequest, hasOutgoingRequest) -> {
					if (areFriends) {
						removeFriend.setItem(guiConfig.get("gui.selectedPlayerMenu.item.removeFriend"));
						removeFriend.setClickHandler((player1, clickInformation) -> sendCommand(player1, "remove", playerName));
						gui.open(player);
						return;
					}
					if (hasIncomingRequest) {
						denyRequest.setItem(guiConfig.get("gui.selectedPlayerMenu.item.denyFriend"));
						denyRequest.setClickHandler((player1, clickInformation) -> sendCommand(player1, "deny", playerName));
						acceptRequest.setItem(guiConfig.get("gui.selectedPlayerMenu.item.acceptFriend"));
						acceptRequest.setClickHandler((player1, clickInformation) -> sendCommand(player1, "accept", playerName));
						gui.open(player);
						return;
					}
					if (hasOutgoingRequest) {
						alreadyRequested.setItem(guiConfig.get("gui.selectedPlayerMenu.item.alreadyRequested"));
						alreadyRequested.setClickHandler((player1, clickInformation) -> player1.closeInventory());
						gui.open(player);
						return;
					}
					addFriend.setItem(guiConfig.get("gui.selectedPlayerMenu.item.addFriend"));
					addFriend.setClickHandler((player1, clickInformation) -> sendCommand(player1, "add", playerName));
					gui.open(player);
				});
	}

	private void sendCommand(Player player, String command, String data) {
		player.closeInventory();
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(command);
		out.writeUTF(data);
		player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
	}
}
