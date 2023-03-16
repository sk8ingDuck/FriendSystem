package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
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
        SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
        GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
        if (!config.isGuiEnabled()) return;

        Player player = event.getPlayer();
        if (event.getAction().equals(Action.LEFT_CLICK_AIR)
                || event.getAction().equals(Action.LEFT_CLICK_BLOCK)
                || player.getInventory().getItemInHand().getType() == Material.AIR
                || player.getInventory().getItemInHand().getItemMeta() == null
                || guiConfig.get("guiItem").getItemMeta() == null
                || !player.getInventory().getItemInHand().getItemMeta().getDisplayName()
                .equalsIgnoreCase(guiConfig.get("guiItem").getItemMeta().getDisplayName())) {
            return;
        }

        GuiManager.guiMainMenu.open(player);
    }
}
