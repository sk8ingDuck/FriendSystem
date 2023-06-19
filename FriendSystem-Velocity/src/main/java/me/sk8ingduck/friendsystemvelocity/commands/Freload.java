package me.sk8ingduck.friendsystemvelocity.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import net.kyori.adventure.text.Component;

public class Freload implements SimpleCommand {

	@Override
	public void execute(Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();

		FriendSystem.getInstance().reloadConfigs();
		source.sendMessage(Component.text("[FriendSystem] Configs reloaded!"));

		if (source instanceof Player) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("reloadConfigs");
			((Player) source).getCurrentServer()
					.ifPresent(server -> {
						server.sendPluginMessage(() -> "me:friendsystem", out.toByteArray());
						source.sendMessage(Component.text("[FriendSystem] Spigot configs reloaded for server: "
								+ server.getServerInfo().getName()));
					});

		}
	}
}
