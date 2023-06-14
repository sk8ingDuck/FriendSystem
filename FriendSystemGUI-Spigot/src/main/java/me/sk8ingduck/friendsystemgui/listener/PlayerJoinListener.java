package me.sk8ingduck.friendsystemgui.listener;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.config.SettingsConfig;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        SettingsConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
        GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
        if (!config.isGuiEnabled()) return;

        ItemStack item = SkullCreator.itemWithUuid(guiConfig.getGuiItem(), player.getUniqueId());
        Bukkit.getScheduler().scheduleSyncDelayedTask(FriendSystemGUI.getInstance(),
                () -> player.getInventory().setItem(config.getGuiSlot(), item), 10L);
    }
}
