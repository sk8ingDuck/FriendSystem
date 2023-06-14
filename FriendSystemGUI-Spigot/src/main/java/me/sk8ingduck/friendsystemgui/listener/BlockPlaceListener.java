package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();

		SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();

		if (config.isCanPlaceItem()
				|| player.getGameMode() == GameMode.CREATIVE
				|| player.getInventory().getItemInHand().getType() == Material.AIR
				|| player.getInventory().getItemInHand().getItemMeta() == null
				|| guiConfig.getGuiItem().getItemMeta() == null
				|| !player.getInventory().getItemInHand().getItemMeta().getDisplayName()
				.equalsIgnoreCase(guiConfig.getGuiItem().getItemMeta().getDisplayName()))
			return;

		event.setCancelled(true);
	}
}
