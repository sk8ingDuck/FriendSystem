package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.mysql.MySQL;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class R extends Command {
    FriendSystem fs = FriendSystem.getFriendSystem();

    MySQL mySQL = fs.getMySQL();

    private final MessagesConfig c = fs.getConfig();

    public R() {
        super("R");
    }

    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)cs;
            if (args.length < 1) {
                p.sendMessage(new TextComponent(c.get("r.error.syntax")));
                return;
            }
            ProxiedPlayer p1 = fs.msgs.get(p);
            if (p1 == null || !p1.isConnected()) {
                p.sendMessage(new TextComponent(c.get("r.error.noreceiver")));
                return;
            }

            mySQL.arePlayersFriends(p.getUniqueId(), p1.getUniqueId(), areFriends -> {
                if (!areFriends) {
                    p.sendMessage(new TextComponent(c.get("r.error.notfriends")
                            .replaceAll("%PLAYER%", p1.getName())));
                    return;
                }
                mySQL.getOption(p1.getUniqueId(), "msgs", msgsAllowed -> {
                    if (!msgsAllowed) {
                        p.sendMessage(new TextComponent(c.get("r.error.msgstoggled")
                                .replaceAll("%PLAYER%", p1.getName())));
                        return;
                    }

                    StringBuilder msg = new StringBuilder();

                    for (String arg : args) msg.append(arg).append(" ");

                    fs.msgs.put(p, p1);
                    fs.msgs.put(p1, p);
                    p.sendMessage(new TextComponent(c.get("msg.r.sender")
                            .replaceAll("%PLAYER%", p.getName())
                            .replaceAll("%PLAYER2%", p1.getName())
                            .replaceAll("%MSG%", msg.toString())));
                    p1.sendMessage(new TextComponent(c.get("msg.r.receiver")
                            .replaceAll("%PLAYER%", p.getName())
                            .replaceAll("%PLAYER2%", p1.getName())
                            .replaceAll("%MSG%", msg.toString())));
                });
            });
        }
    }
}
