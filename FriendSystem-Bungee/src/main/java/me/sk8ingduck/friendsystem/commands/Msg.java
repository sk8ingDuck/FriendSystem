package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Msg extends Command {
    private final FriendSystem fs = FriendSystem.getFriendSystem();

    private final MySQL mySQL = fs.getMySQL();

    private final MessagesConfig c = fs.getConfig();

    public Msg() {
        super("msg");
    }

    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)cs;
            if (args.length < 2) {
                p.sendMessage(new TextComponent(c.get("msg.error.syntax")));
                return;
            }

            String playerName = args[0];

            if (playerName.equalsIgnoreCase(p.getName())) {
                p.sendMessage(new TextComponent(c.get("msg.error.interact")));
                return;
            }

            UUIDFetcher.getUUID(playerName, uuid -> {
                if (uuid == null) {
                    p.sendMessage(new TextComponent(c.get("msg.error.playernotfound")
                            .replaceAll("%PLAYER%", args[0])));
                    return;
                }

                mySQL.arePlayersFriends(p.getUniqueId(), uuid, areFriends -> {
                    if (!areFriends) {
                        p.sendMessage(new TextComponent(c.get("msg.error.notfriends")
                                .replaceAll("%PLAYER%", args[0])));
                        return;
                    }

                    mySQL.getOption(uuid, "msgs", msgsAllowed -> {
                        if (!msgsAllowed) {
                            p.sendMessage(new TextComponent(c.get("msg.error.msgstoggled")
                                    .replaceAll("%PLAYER%", args[0])));
                            return;
                        }

                        ProxiedPlayer p1 = ProxyServer.getInstance().getPlayer(playerName);
                        if (p1 == null) {
                            p.sendMessage(new TextComponent(c.get("msg.error.notonline")
                                    .replaceAll("%PLAYER%",  args[0])));
                            return;
                        }

                        StringBuilder msg = new StringBuilder();
                        for (int i = 1; i < args.length; i++)
                            msg.append(args[i]).append(" ");
                        fs.msgs.put(p, p1);
                        fs.msgs.put(p1, p);

                        p.sendMessage(new TextComponent(c.get("msg.format.sender")
                                .replaceAll("%PLAYER%", p.getName())
                                .replaceAll("%PLAYER2%", p1.getName())
                                .replaceAll("%MSG%", msg.toString())));

                        p1.sendMessage(new TextComponent(c.get("msg.format.receiver")
                                .replaceAll("%PLAYER%", p.getName())
                                .replaceAll("%PLAYER2%", p1.getName())
                                .replaceAll("%MSG%", msg.toString())));

                    });
                });
            });
        }
    }

}
