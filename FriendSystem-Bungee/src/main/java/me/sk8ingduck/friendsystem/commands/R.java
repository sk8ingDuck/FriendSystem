package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;
import me.sk8ingduck.friendsystem.utils.FriendPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class R extends Command {
    private final FriendSystem fs;
    private final MessagesConfig c;

    public R() {
        super("r");

        fs = FriendSystem.getInstance();
        c = fs.getConfig();
    }

	public void execute(CommandSender cs, String[] args) {
		if (cs instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) cs;
			if (args.length < 1) {
				player.sendMessage(new TextComponent(c.get("r.error.syntax")));
				return;
			}
			ProxiedPlayer player2 = fs.msgs.get(player);
			if (player2 == null || !player2.isConnected()) {
				player.sendMessage(new TextComponent(c.get("r.error.noreceiver")));
				return;
			}

            FriendPlayer friendPlayer = FriendSystem.getInstance().getFriendManager().getFriendPlayer(player.getUniqueId());
            FriendPlayer friendPlayer2 = FriendSystem.getInstance().getFriendManager().getFriendPlayer(player2.getUniqueId());

            if (!friendPlayer.isFriendsWith(player2.getUniqueId())) {
                player.sendMessage(new TextComponent(c.get("msg.error.notfriends")
                        .replaceAll("%PLAYER%", player2.getName())));
                return;
            }

            if (!friendPlayer2.isMsgsAllowed()) {
                player.sendMessage(new TextComponent(c.get("msg.error.msgstoggled")
                        .replaceAll("%PLAYER%", player2.getName())));
                return;
            }

            StringBuilder msg = new StringBuilder();

            for (String arg : args) msg.append(arg).append(" ");

            fs.msgs.put(player, player2);
            fs.msgs.put(player2, player);

            player.sendMessage(new TextComponent(c.get("msg.r.sender")
                    .replaceAll("%PLAYER%", player.getName())
                    .replaceAll("%PLAYER2%", player2.getName())
                    .replaceAll("%MSG%", msg.toString())));

            player2.sendMessage(new TextComponent(c.get("msg.r.receiver")
                    .replaceAll("%PLAYER%", player.getName())
                    .replaceAll("%PLAYER2%", player2.getName())
                    .replaceAll("%MSG%", msg.toString())));
		}
	}
}
