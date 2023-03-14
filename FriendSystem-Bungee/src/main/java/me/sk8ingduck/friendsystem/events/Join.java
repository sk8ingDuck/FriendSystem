package me.sk8ingduck.friendsystem.events;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.utils.Util;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Join implements Listener {
    FriendSystem fs = FriendSystem.getFriendSystem();

    private final MySQL mySQL = FriendSystem.getFriendSystem().getMySQL();

    private final MessagesConfig c = fs.getConfig();

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();

        mySQL.doesPlayerExist(p.getUniqueId(), doesExist -> {
            if (!doesExist) {
                mySQL.addPlayerAsync(p.getUniqueId());
                return;
            }

            Util.getOnlineFriends(p.getUniqueId(), onlineFriends -> {
                for (ProxiedPlayer online : onlineFriends) {
                    mySQL.getOption(online.getUniqueId(), "notifies", notifiesAllowed -> {
                        if (notifiesAllowed) {
                            online.sendMessage(new TextComponent(c.get("notifies.join")
                                    .replaceAll("%PLAYER%", p.getName())));
                        }
                    });
                }

                p.sendMessage(new TextComponent(c.get("join.friendcounter")
                        .replaceAll("%COUNT%", String.valueOf(onlineFriends.size()))));
                mySQL.getRequestsCount(p.getUniqueId(), requests ->
                        p.sendMessage(new TextComponent(c.get("join.requestcounter")
                        .replaceAll("%COUNT%", String.valueOf(requests)))));

            });

        });
    }
}
