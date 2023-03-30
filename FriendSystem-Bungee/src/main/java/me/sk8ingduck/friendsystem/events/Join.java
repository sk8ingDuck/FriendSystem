package me.sk8ingduck.friendsystem.events;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Join implements Listener {
    private final FriendManager fm;
    public Join() {
        this.fm = FriendSystem.getInstance().getFriendManager();
    }
    @EventHandler
    public void onJoin(PostLoginEvent event) {
        MessagesConfig c = FriendSystem.getInstance().getConfig();

        ProxiedPlayer player = event.getPlayer();

        boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();
        String playerUUIDorName = onlineMode ? player.getUniqueId().toString() : player.getName();

        fm.updateLastSeen(playerUUIDorName);
        FriendPlayer friendPlayer = FriendSystem.getInstance().getFriendManager().getFriendPlayer(playerUUIDorName);
        if (friendPlayer == null) {
            fm.addFriendPlayer(playerUUIDorName);
            return;
        }

        friendPlayer.getOnlineFriends().keySet().forEach(friend -> {
            FriendPlayer friendPlayer2 = FriendSystem.getInstance().getFriendManager().getFriendPlayer(playerUUIDorName);
            if (friendPlayer2.isNotifiesAllowed()) {
                friend.sendMessage(new TextComponent(c.get("notifies.join")
                        .replaceAll("%PLAYER%", player.getName())));
            }
        });

        player.sendMessage(new TextComponent(c.get("join.friendcounter")
                .replaceAll("%COUNT%", String.valueOf(friendPlayer.getOnlineFriends().size()))));
        player.sendMessage(new TextComponent(c.get("join.requestcounter")
                .replaceAll("%COUNT%", String.valueOf(friendPlayer.getRequests().size()))));
    }
}
