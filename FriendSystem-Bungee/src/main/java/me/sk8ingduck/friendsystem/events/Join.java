package me.sk8ingduck.friendsystem.events;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.FriendManager;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Join implements Listener {
    private final MessagesConfig c = FriendSystem.getInstance().getConfig();
    private final FriendManager friendManager = FriendSystem.getInstance().getFriendManager();
    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        friendManager.updateLastSeen(player.getUniqueId());

        FriendPlayer friendPlayer = FriendSystem.getInstance().getFriendManager().getFriendPlayer(player.getUniqueId());
        if (friendPlayer == null) {
            friendManager.addFriendPlayer(player.getUniqueId());
            return;
        }

        friendPlayer.getOnlineFriends().forEach(friend -> {
            FriendPlayer friendPlayer2 = FriendSystem.getInstance().getFriendManager().getFriendPlayer(friend.getUniqueId());
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
