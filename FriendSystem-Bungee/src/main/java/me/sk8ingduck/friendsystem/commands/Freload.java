package me.sk8ingduck.friendsystem.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.sk8ingduck.friendsystem.FriendSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

public class Freload extends Command {

	public Freload() {
		super("freload", "friendsystem.freload", "friendreload");
	}

	@Override
	public void execute(CommandSender commandSender, String[] strings) {
		FriendSystem.getInstance().reloadConfigs();
		commandSender.sendMessage(new TextComponent("§aBungeeCord configs reloaded."));

		if (commandSender instanceof ProxiedPlayer) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("reloadConfigs");

			ServerInfo playerServer = ((ProxiedPlayer) commandSender).getServer().getInfo();
			playerServer.sendData("me:friendsystem", out.toByteArray());
		}
	}
}
