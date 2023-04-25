package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onInventoryClickListener(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
		if (config.isCanMoveItemInInventory() || player.getGameMode() == GameMode.CREATIVE) {
			return;
		}

		String playerHead = FriendSystemGUI.getInstance().getGuiConfig().get("guiItem").getItemMeta().getDisplayName();

		if (event.getHotbarButton() >= 0 && event.getHotbarButton() <= player.getInventory().getSize()) {
			ItemStack item1 = player.getInventory().getItem(event.getHotbarButton());

			if (item1 != null
					&& item1.getItemMeta() != null
					&& item1.getItemMeta().getDisplayName() != null
					&& item1.getItemMeta().getDisplayName().equalsIgnoreCase(playerHead)) {
				event.setCancelled(true);
				return;
			}
		}
		ItemStack item2 = event.getCurrentItem();
		if (item2 != null
				&& item2.getItemMeta() != null
				&& item2.getItemMeta().getDisplayName() != null
				&& item2.getItemMeta().getDisplayName().equalsIgnoreCase(playerHead)) {
			event.setCancelled(true);
		}
	}
}
