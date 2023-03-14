package me.sk8ingduck.friendsystem.events;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Disconnect implements Listener {

    private final FriendSystem fs = FriendSystem.getFriendSystem();
    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();

        Util.getOnlineFriends(p.getUniqueId(), onlineFriends -> {
            for (ProxiedPlayer online : onlineFriends) {
                fs.getMySQL().getOption(online.getUniqueId(), "notifies", notifiesAllowed -> {
                    if (notifiesAllowed) {
                        online.sendMessage(new TextComponent(fs.getConfig().get("notifies.leave")
                                .replaceAll("%PLAYER%", p.getName())));
                    }
                });
            }
        });
    }

}
