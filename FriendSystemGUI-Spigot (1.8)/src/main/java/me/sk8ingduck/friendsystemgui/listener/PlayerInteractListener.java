package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.gui.GuiManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        GuiConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
        if (!config.isGuiEnabled() || !config.isRightClickPlayerToOpenMenuEnabled()) return;

        Player player = event.getPlayer();
        if (event.getAction().equals(Action.LEFT_CLICK_AIR)
                || event.getAction().equals(Action.LEFT_CLICK_BLOCK)
                || player.getInventory().getItemInHand().getType() == Material.AIR
                || player.getInventory().getItemInHand().getItemMeta() == null
                || config.get("guiItem").getItemMeta() == null
                || !player.getInventory().getItemInHand().getItemMeta().getDisplayName()
                .equalsIgnoreCase(config.get("guiItem").getItemMeta().getDisplayName())) {
            return;
        }

        GuiManager.guiMainMenu.open(player);
    }
}
