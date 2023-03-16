package me.sk8ingduck.friendsystemgui.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerJoinListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("friendscount");
        player.sendPluginMessage(FriendSystemGUI.getInstance(), "me:friendsystem", out.toByteArray());

        GuiConfig config = FriendSystemGUI.getInstance().getSettingsConfig();
        if (!config.isGuiEnabled()) return;

        ItemStack item = config.get("guiItem");
        if (item.getType() == Material.SKULL) {
            SkullMeta headMeta = (SkullMeta) item.getItemMeta();
            if (headMeta != null)
                headMeta.setOwner(player.getName());
            item.setItemMeta(headMeta);
        }
        player.getInventory().setItem(config.getGuiSlot(), item);

    }
}
