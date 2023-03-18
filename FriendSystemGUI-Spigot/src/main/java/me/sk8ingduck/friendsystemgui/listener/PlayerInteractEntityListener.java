package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
import me.sk8ingduck.friendsystemgui.gui.GuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
        if (!config.isGuiEnabled()) return;

        if ((event.getRightClicked() instanceof Player)) {
            Player clickedPlayer = (Player) event.getRightClicked();
            GuiManager.guiSelectedPlayer.open(event.getPlayer(), clickedPlayer.getUniqueId(), clickedPlayer.getName());
        }
    }
}
