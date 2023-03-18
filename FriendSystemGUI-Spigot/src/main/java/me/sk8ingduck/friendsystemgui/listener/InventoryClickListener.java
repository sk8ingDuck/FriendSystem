package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onInventoryClickListener(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
		if (config.isCanMoveItemInInventory()
				|| player.getGameMode() == GameMode.CREATIVE
				|| player.getInventory().getItemInHand().getItemMeta() == null
				|| guiConfig.get("guiItem").getItemMeta() == null
				|| player.getInventory().getItemInHand().getItemMeta().getDisplayName()
				.equalsIgnoreCase(guiConfig.get("guiItem").getItemMeta().getDisplayName()))
			return;

		event.setCancelled(true);
	}
}
