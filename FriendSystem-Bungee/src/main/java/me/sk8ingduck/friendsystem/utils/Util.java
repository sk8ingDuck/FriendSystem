package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Util {

    public static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void getOnlineFriends(UUID uuid, Consumer<ArrayList<ProxiedPlayer>> consumer) {
        FriendSystem.getFriendSystem().getMySQL().getFriendUUIDs(uuid, friendUUIDs -> {
            ArrayList<ProxiedPlayer> onlineFriends = new ArrayList<>();
            for (UUID friendUUID : friendUUIDs) {
                ProxiedPlayer onlineFriend = ProxyServer.getInstance().getPlayer(friendUUID);
                if (onlineFriend != null) onlineFriends.add(onlineFriend);
            }
            consumer.accept(onlineFriends);
        });
    }
}
