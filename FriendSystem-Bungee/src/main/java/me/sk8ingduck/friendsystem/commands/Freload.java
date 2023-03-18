package me.sk8ingduck.friendsystem.commands;

import me.sk8ingduck.friendsystem.FriendSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class Freload extends Command {

	public Freload() {
		super("freload", "friendsystem.freload", "friendreload");
	}

	@Override
	public void execute(CommandSender commandSender, String[] strings) {
		FriendSystem.getInstance().reloadConfigs();
		commandSender.sendMessage(new TextComponent("§aConfigs reloaded."));
	}
}
