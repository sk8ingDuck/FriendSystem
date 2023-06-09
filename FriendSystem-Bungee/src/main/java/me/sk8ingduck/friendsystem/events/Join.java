package me.sk8ingduck.friendsystem.events;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.config.SettingsConfig;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Join implements Listener {
    private final FriendManager fm;
    public Join() {
        fm = FriendSystem.getInstance().getFriendManager();
    }
    @EventHandler
    public void onJoin(PostLoginEvent event) {
        MessagesConfig c = FriendSystem.getInstance().getConfig();

        ProxiedPlayer player = event.getPlayer();

        boolean onlineMode = ProxyServer.getInstance().getConfig().isOnlineMode();
        String playerUUIDorName = onlineMode ? player.getUniqueId().toString() : player.getName();

        FriendPlayer friendPlayer = fm.getFriendPlayer(playerUUIDorName);
        if (friendPlayer == null) {
            fm.addFriendPlayer(playerUUIDorName);
            return;
        }
        fm.updateLastSeen(playerUUIDorName); //update mysql
        friendPlayer.updateLastSeen(); //update cache

        friendPlayer.getOnlineFriends().keySet().forEach(friend -> {
            FriendPlayer friendPlayer2 = fm.getFriendPlayer(onlineMode ? friend.getUniqueId().toString() : friend.getName());
            if (friendPlayer2.isNotifiesAllowed()) {
                friend.sendMessage(c.get("notifies.join", "%PLAYER%", player.getName()));
            }
        });


        SettingsConfig settings = FriendSystem.getInstance().getSettingsConfig();
        int onlineFriends = friendPlayer.getOnlineFriends().size();
        int request = friendPlayer.getRequests().size();

        if (onlineFriends >= settings.getOnlineFriendsMessageMinimum())
            player.sendMessage(c.get("join.friendcounter", "%COUNT%", String.valueOf(onlineFriends)));
        if (request >= settings.getRequestsMessageMinimum())
            player.sendMessage(c.get("join.requestcounter", "%COUNT%", String.valueOf(request)));
    }
}
