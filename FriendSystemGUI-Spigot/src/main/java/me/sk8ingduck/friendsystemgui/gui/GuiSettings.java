package me.sk8ingduck.friendsystemgui.gui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

public class GuiSettings {


	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();

		ChestMenu gui = ChestMenu.builder(4).title(guiConfig.getSettingsGuiTitle()).build();

		for (int i = 0; i < 36; i++)
			gui.getSlot(i).setItem(ItemCreator.createGlassPane(DyeColor.BLACK, " "));

		Slot back = gui.getSlot(10);
		Slot toggleInvites = gui.getSlot(13);
		Slot toggleNotifies = gui.getSlot(14);
		Slot toggleMsgs = gui.getSlot(15);
		Slot toggleJump = gui.getSlot(16);

		Slot currentInvites = gui.getSlot(22);
		Slot currentNotifies = gui.getSlot(23);
		Slot currentMsgs = gui.getSlot(24);
		Slot currentJump = gui.getSlot(25);

		back.setItem(guiConfig.get("gui.settingsMenu.item.back"));
		toggleInvites.setItem(guiConfig.get("gui.settingsMenu.item.toggleInvites"));
		toggleNotifies.setItem(guiConfig.get("gui.settingsMenu.item.toggleNotifies"));
		toggleMsgs.setItem(guiConfig.get("gui.settingsMenu.item.toggleMsgs"));
		toggleJump.setItem(guiConfig.get("gui.settingsMenu.item.toggleJump"));

		FriendSystemGUI.getInstance().getPluginMessaging().getSettings(player, (invites, notifies, msgs, jump) -> {
			currentInvites.setItem(guiConfig.get("gui.settingsMenu.item.invites" + (invites ? "On" : "Off")));
			currentNotifies.setItem(guiConfig.get("gui.settingsMenu.item.notifies" + (notifies ? "On" : "Off")));
			currentMsgs.setItem(guiConfig.get("gui.settingsMenu.item.msgs" + (msgs ? "On" : "Off")));
			currentJump.setItem(guiConfig.get("gui.settingsMenu.item.jump" + (jump ? "On" : "Off")));

		});

		toggleInvites.setClickHandler((player1, clickInformation) -> {
			player1.closeInventory();

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("toggleinvites");
			player1.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		});

		toggleNotifies.setClickHandler((player1, clickInformation) -> {
			player1.closeInventory();

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("togglenotifies");
			player1.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		});

		toggleMsgs.setClickHandler((player1, clickInformation) -> {
			player1.closeInventory();

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("togglemsgs");
			player1.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		});

		toggleJump.setClickHandler((player1, clickInformation) -> {
			player1.closeInventory();

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("togglejump");
			player1.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());
		});

		back.setClickHandler((player1, clickInformation) -> GuiManager.guiMainMenu.open(player));

		gui.open(player);
	}

}
