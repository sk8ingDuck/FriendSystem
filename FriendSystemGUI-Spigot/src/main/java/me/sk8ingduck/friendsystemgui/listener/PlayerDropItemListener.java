package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();

		SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
		if (config.isCanDropItem()
				|| player.getGameMode() == GameMode.CREATIVE
				|| event.getItemDrop().getItemStack().getItemMeta() == null
				|| event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null
				|| !event.getItemDrop().getItemStack().getItemMeta().getDisplayName()
				.equalsIgnoreCase(guiConfig.getGuiItem().getItemMeta().getDisplayName()))
			return;

		event.setCancelled(true);
	}
}
